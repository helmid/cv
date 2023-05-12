object Dependency {
    object Kotlin {
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
        const val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }

    object Spring {
        const val web = "org.springframework.boot:spring-boot-starter-web"
        const val security = "org.springframework.boot:spring-boot-starter-security"
        const val thymeleaf = "org.springframework.boot:spring-boot-starter-thymeleaf"
        const val mail = "org.springframework.boot:spring-boot-starter-mail"
    }

    const val lombok = "org.projectlombok:lombok"
    const val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin"

    object Test {
        object Spring {
            const val web = "org.springframework.boot:spring-boot-starter-test"
            const val security = "org.springframework.security:spring-security-test"
        }
    }
}