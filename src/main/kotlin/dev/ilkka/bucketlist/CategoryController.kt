package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.Category
import dev.ilkka.bucketlist.domain.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class NewCategory(val title: String, var parent_id: Long? = null)

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired private lateinit var categoryRepository: CategoryRepository

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addCategory(@RequestBody newCategory: NewCategory): Category {
        if (newCategory.parent_id != null) {
            val parent = categoryRepository.findById(newCategory.parent_id!!)
            if (parent.isPresent) {
                val cat =
                        Category(title = newCategory.title, items = setOf(), parent = parent.get())

                return categoryRepository.save(cat)
            }
            throw IllegalArgumentException("Parent category not found")
        }
        return categoryRepository.save(Category(title = newCategory.title, items = setOf()))
    }
}
