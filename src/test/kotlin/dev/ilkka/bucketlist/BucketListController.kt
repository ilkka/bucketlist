package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import dev.ilkka.bucketlist.domain.BucketListItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BucketListController {
    @Autowired private lateinit var bucketListItemRepository: BucketListItemRepository

    // Return a list of bucket list items
    @GetMapping("/bucketlist")
    fun bucketlist(): List<BucketListItem> {
        return bucketListItemRepository.findAll().toCollection(destination = ArrayList())
    }
}
