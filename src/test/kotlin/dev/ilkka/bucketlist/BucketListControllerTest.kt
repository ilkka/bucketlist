package dev.ilkka.bucketlist

import dev.ilkka.bucketlist.domain.BucketListItem
import dev.ilkka.bucketlist.domain.Category
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
public class BucketListControllerTest {
    @Autowired private lateinit var mvc: MockMvc

    @Test
    fun testAddToBucketList() {
        // Given a bucket list item
        val item = BucketListItem("Test item", "Test description")

        // When the item is saved through the API
        mvc.perform(
                        MockMvcRequestBuilders.post("/bucketlist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"title\": \"${item.title}\", \"description\": \"${item.description}\"}"
                                )
                )
                .andExpect(status().isOk())

        // Then the item should be returned by the API when listing all items
        mvc.perform(MockMvcRequestBuilders.get("/bucketlist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .json(
                                        "[{\"id\":1,\"title\":\"Test item\",\"description\":\"Test description\",\"categories\":[]}]"
                                )
                )

        // And the item should be accessible by ID
        mvc.perform(
                        MockMvcRequestBuilders.get("/bucketlist/items/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .json(
                                        "{\"id\":1,\"title\":\"Test item\",\"description\":\"Test description\",\"categories\":[]}"
                                )
                )
    }

    @Test
    fun testAddWithCategory() {
        // Given a category
        val category = Category("Test category")
        // and a bucket list item in that category
        val item = BucketListItem("Test item", "Test description", setOf(category))

        // When the category is saved through the API and the result is saved to a variable
        val result =
                mvc.perform(
                                MockMvcRequestBuilders.post("/category")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content("{\"title\": \"${category.title}\"}")
                        )
                        .andExpect(status().isOk())
                        .andReturn()
        val json = result.response.contentAsString
        val categoryId =
                json.substringAfter("id\":")
                        .takeWhile { c: Char -> c == '-' || c.digitToIntOrNull() != null }
                        .toLong()

        // When the item is saved through the API
        mvc.perform(
                        MockMvcRequestBuilders.post("/bucketlist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"title\": \"${item.title}\", \"description\": \"${item.description}\", \"categories\": [$categoryId]}"
                                )
                )
                .andExpect(status().isOk())
    }
}
