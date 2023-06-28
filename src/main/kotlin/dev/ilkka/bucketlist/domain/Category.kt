package dev.ilkka.bucketlist.domain

import jakarta.persistence.*

@Entity
class Category(
        val title: String,
        @ManyToMany(mappedBy = "categories") val items: Set<BucketListItem>? = null,
        @ManyToOne val parent: Category? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "parent")
        val subcategories: Set<Category>? = null,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
)
