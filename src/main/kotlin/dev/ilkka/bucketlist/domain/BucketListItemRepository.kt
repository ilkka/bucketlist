package dev.ilkka.bucketlist.domain

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

public interface BucketListItemRepository : CrudRepository<BucketListItem, String> {
    fun findById(id: Long): BucketListItem
    fun findByTitle(title: String): BucketListItem

    @Query("SELECT b FROM BucketListItem b JOIN b.categories c WHERE c.title = ?1")
    fun findByCategoryName(category: String): List<BucketListItem>
}
