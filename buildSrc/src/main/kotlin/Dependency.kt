object Dependency {
    object Kotlin {
        val reflect = "org.jetbrains.kotlin:kotlin-reflect"
        val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }

    object Spring {
        val web = "org.springframework.boot:spring-boot-starter-web"
        val security = "org.springframework.boot:spring-boot-starter-security"
        val thymeleaf = "org.springframework.boot:spring-boot-starter-thymeleaf"
    }

    val lombok = "org.projectlombok:lombok"
    val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin"

    object Test {
        object Spring {
            val web = "org.springframework.boot:spring-boot-starter-test"
            val security = "org.springframework.security:spring-security-test"
        }
    }
}