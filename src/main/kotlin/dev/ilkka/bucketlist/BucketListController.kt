package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import dev.ilkka.bucketlist.domain.BucketListItemRepository
import dev.ilkka.bucketlist.domain.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class NewBucketListItem(
        val title: String,
        val description: String,
        val categories: Set<Long>? = null
)

@RestController
@RequestMapping("/bucketlist")
class BucketListController {
    @Autowired private lateinit var bucketListItemRepository: BucketListItemRepository
    @Autowired private lateinit var categoryRepository: CategoryRepository

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
    fun addBucketListItem(@RequestBody item: NewBucketListItem): BucketListItem {
        // Get all the categories in the item
        val categories = item.categories?.map { id -> categoryRepository.findById(id).get() }
        // create a new bucket list item with the categories and the values from the request body
        val newItem =
                BucketListItem(
                        title = item.title,
                        description = item.description,
                        categories = categories?.toSet()
                )
        return bucketListItemRepository.save(newItem)
    }

    // Return a single bucket list item
    @GetMapping("items/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBucketListItem(@PathVariable id: Long): BucketListItem {
        return bucketListItemRepository.findById(id).get()
    }
}
