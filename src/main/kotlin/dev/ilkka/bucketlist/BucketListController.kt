package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import dev.ilkka.bucketlist.domain.BucketListItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bucketlist")
class BucketListController {
    @Autowired private lateinit var bucketListItemRepository: BucketListItemRepository

    // Return a list of bucket list items
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBucketList(): List<BucketListItem> {
        return bucketListItemRepository.findAll().toCollection(destination = ArrayList())
    }

    // Add a new bucket list item
    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addBucketListItem(@RequestBody item: BucketListItem): BucketListItem {
        return bucketListItemRepository.save(item)
    }

    // Return a single bucket list item
    @GetMapping("items/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBucketListItem(@PathVariable id: Long): BucketListItem {
        return bucketListItemRepository.findById(id).get()
    }
}
