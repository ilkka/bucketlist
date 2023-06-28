package dev.ilkka.bucketlist.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DummyBucketListTest {
    @Autowired private lateinit var entityManager: TestEntityManager
    @Autowired private lateinit var repository: BucketListItemRepository

    @Test
    public fun testGetBucketListItemsForCategory() {
        // Given two categories
        val category = Category("Test category")
        val category2 = Category("Test category 2")

        // Given three bucket list items that belong to the categories
        val item = BucketListItem("Test item", "Test description", setOf(category))
        val item2 = BucketListItem("Test item 2", "Test description 2", setOf(category, category2))
        val item3 = BucketListItem("Test item 3", "Test description 3", setOf(category2))

        // When the items are persisted
        entityManager.persist(item)
        entityManager.persist(item2)
        entityManager.persist(item3)
        entityManager.persist(category)
        entityManager.persist(category2)
        entityManager.flush()

        // When I load the items for the category by name
        val found = repository.findByCategoryName("Test category")

        // Then the list should contain two items
        assertThat(found).hasSize(2)
        // and the list should contain the items that belong to the category
        assertThat(found).contains(item, item2)
        // and the list should not contain the item that does not belong to the category
        assertThat(found).doesNotContain(item3)

        // When I load the items for the second category by name
        val found2 = repository.findByCategoryName("Test category 2")

        // Then the list should contain two items
        assertThat(found2).hasSize(2)
        // and the list should contain the items that belong to the category
        assertThat(found2).contains(item2, item3)
        // and the list should not contain the item that does not belong to the category
        assertThat(found2).doesNotContain(item)
    }
}
