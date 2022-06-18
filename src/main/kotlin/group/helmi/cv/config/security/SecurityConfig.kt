package group.helmi.cv.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .requiresChannel { channel -> channel.anyRequest().requiresSecure() }
            .authorizeRequests { authorize -> authorize.anyRequest().permitAll() }
            .build()
    }
}