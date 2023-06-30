package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import dev.ilkka.bucketlist.domain.BucketListItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BucketListController {
    @Autowired private lateinit var bucketListItemRepository: BucketListItemRepository

    // Return a list of bucket list items
    @GetMapping("/bucketlist")
    fun getBucketList(): List<BucketListItem> {
        return bucketListItemRepository.findAll().toCollection(destination = ArrayList())
    }

    // Add a new bucket list item
    @PostMapping("/bucketlist")
    fun addBucketListItem(@RequestBody item: BucketListItem): BucketListItem {
        return bucketListItemRepository.save(item)
    }
}
