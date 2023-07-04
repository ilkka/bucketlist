package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.Category
import dev.ilkka.bucketlist.domain.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired private lateinit var categoryRepository: CategoryRepository

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addCategory(@RequestBody category: Category): Category {
        return categoryRepository.save(category)
    }
}
