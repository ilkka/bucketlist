package dev.ilkka.bucketlist.domain

import org.springframework.data.repository.CrudRepository

public interface BucketListItemRepository : CrudRepository<BucketListItem, String> {
    fun findById(id: Long): BucketListItem
}
