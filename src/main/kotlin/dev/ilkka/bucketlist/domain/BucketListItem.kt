package dev.ilkka.bucketlist.domain

import jakarta.persistence.*

@Entity
class BucketListItem(
        val title: String,
        val description: String,
        @ManyToMany
        @JoinTable(
                name = "bucketlistitem_category",
                joinColumns = [JoinColumn(name = "bucketlistitem_id")],
                inverseJoinColumns = [JoinColumn(name = "category_id")]
        )
        val categories: Set<Category>? = null,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
)
