package group.helmi.cv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CvApplication

fun main(args: Array<String>) {
	runApplication<CvApplication>(*args)
}
