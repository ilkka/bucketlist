package dev.ilkka.bucketlist.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

public interface BucketListItemRepository : JpaRepository<BucketListItem, Long> {
    @Query("SELECT b FROM BucketListItem b JOIN b.categories c WHERE c.title = ?1")
    fun findByCategoryName(category: String): List<BucketListItem>
}
