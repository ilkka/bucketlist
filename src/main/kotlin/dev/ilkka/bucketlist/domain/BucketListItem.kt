package dev.ilkka.bucketlist.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "bucketlistitem")
class BucketListItem(
        val title: String,
        val description: String,
        @ManyToMany
        @JoinTable(
                name = "bucketlistitem_categories",
                joinColumns = [JoinColumn(name = "bucketlistitem_id")],
                inverseJoinColumns = [JoinColumn(name = "category_id")]
        )
        @JsonManagedReference
        val categories: Set<Category>? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucketlistitem_generator")
        @SequenceGenerator(
                name = "bucketlistitem_generator",
                sequenceName = "bucketlistitem_id_seq"
        )
        val id: Long? = null,
)
