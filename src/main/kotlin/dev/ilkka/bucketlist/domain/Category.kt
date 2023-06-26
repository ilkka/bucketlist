package dev.ilkka.bucketlist.domain

import jakarta.persistence.*

@Entity
data class Category(
        val title: String,
        @ManyToMany(mappedBy = "categories") val item: Set<BucketListItem>,
        @ManyToOne val parent: Category? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "parent")
        val subcategories: Set<Category>,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
)
