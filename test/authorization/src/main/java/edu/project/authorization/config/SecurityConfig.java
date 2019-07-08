package edu.project.authorization.config;

import edu.project.authorization.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// 사이트가 잠겨서 비밀번호를 쳐야 접근할 수 있게 된다.
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**",
                        "/css/**",
                        "/script/**",
                        "image/**",
                        "fonts/**",
                        "lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/")
                .failureUrl("/signin")
                .and()
                .logout();
                http.cors().and();
        http.csrf().disable();
//        http.authorizeRequests()
//                    .antMatchers("/signin/**").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
////                .antMatchers("/**").permitAll()
//                    .and()
//                .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginPage("/signin")
//                .loginProcessingUrl("/signin")
//                .defaultSuccessUrl("/")
//                .failureUrl("/signin")
//                .and()
//                .logout();
//        http.cors().and();
//        http.csrf().disable();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // - (3)
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService createUserDetailService() {
//        return new CustomUserDetailService();
//    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetailsService 를 이용한 인증처리
        log.info("congifureGlobal1>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("customUserDetailService>>>>>>>>>>>>>>" + customUserDetailsService);
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bcryptPasswordEncoder());
    }

//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/signin/**")
//                        .allowedOrigins("*")
//                        .allowedMethods(HttpMethod.POST.name())
//                        .allowCredentials(false)
//                        .maxAge(3600);
//            }
//        };
//    }
}
