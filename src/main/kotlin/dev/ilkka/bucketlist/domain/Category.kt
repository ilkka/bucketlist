package dev.ilkka.bucketlist.domain

import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
        val title: String,
        @ManyToMany(mappedBy = "categories") val items: Set<BucketListItem>? = null,
        @ManyToOne @JoinColumn(name = "parent_id") val parent: Category? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "parent")
        val subcategories: Set<Category>? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
        @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq")
        val id: Long? = null,
)
