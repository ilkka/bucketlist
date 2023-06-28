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
    public fun testSaveBucketListItem() {
        // Given a bucket list item
        val item = BucketListItem("Test item", "Test description", setOf())
        // When the item is persisted
        entityManager.persist(item)
        entityManager.flush()

        // When I load the item with id 1
        val found = repository.findById(1L)

        // Then the titles should be the same
        assertThat(found.title).isEqualTo(item.title)
    }
}
