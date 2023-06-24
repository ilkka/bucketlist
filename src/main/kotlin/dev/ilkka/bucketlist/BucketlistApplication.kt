package dev.ilkka.bucketlist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BucketlistApplication

fun main(args: Array<String>) {
	runApplication<BucketlistApplication>(*args)
}
