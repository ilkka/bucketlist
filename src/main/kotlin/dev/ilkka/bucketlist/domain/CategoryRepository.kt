package dev.ilkka.bucketlist.domain

import org.springframework.data.jpa.repository.JpaRepository

public interface CategoryRepository : JpaRepository<Category, Long> {}
