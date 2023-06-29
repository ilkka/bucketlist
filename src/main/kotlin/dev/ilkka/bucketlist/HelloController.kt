package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun index(): String {
        return "Moro!"
    }

    // Return a list of bucket list items
    @GetMapping("/bucketlist")
    fun bucketlist(): List<BucketListItem> {
        return listOf(
                BucketListItem("Go to space", "Yes really go to space", setOf()),
                BucketListItem("Learn to fly an airplane", "Wouldn't it be cool", setOf()),
                BucketListItem("Learn to play an instrument", "Guitar or piano?", setOf())
        )
    }
}
