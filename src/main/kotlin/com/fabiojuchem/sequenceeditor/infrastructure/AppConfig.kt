package com.fabiojuchem.authapi.infrastructure


//@EnableWebSecurity
//@Configuration
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
//class AppConfig(
//        val authenticationWebFilter: AuthenticationWebFilter
//) : WebSecurityConfigurerAdapter() {
//
//    @Override
//    override fun configure(http: HttpSecurity) {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
//                .antMatchers("/*")
//                .permitAll()
//    }
//}