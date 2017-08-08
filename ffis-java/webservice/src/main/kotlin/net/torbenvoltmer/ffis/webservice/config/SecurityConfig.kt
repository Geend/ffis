package net.torbenvoltmer.ffis.webservice.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by torben on 28.03.16.
 */

@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {


    @Autowired
    lateinit var userConfig: UserConfig;

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/flying/set")
                .authenticated()
                .and()
                .httpBasic()
    }

    override fun configure(web: WebSecurity) {
        web
            .ignoring()
            .antMatchers("/opind/get");
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser(userConfig.user).password(userConfig.password).roles("USER")



    }


}