package edu.project.authorization.config;

import edu.project.authorization.service.AuthProviderService;
import edu.project.authorization.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    AuthProviderService authProviderService;

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Autowired
    AuthFailureHandler authFailureHandler;*/

    @Autowired
    CustomUserDetailsService customUserDetailsService;

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
                    .antMatchers("/", "/signup", "/signin").permitAll()
                    .antMatchers("/**").hasRole("USER")
                    .antMatchers("/**").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").authenticated()
                    .anyRequest().authenticated()
                .and()
/*                    .formLogin()
                    .loginPage("/signin")
                    .defaultSuccessUrl("/")
                    .usernameParameter("userId")
                    .passwordParameter("userPasswd")
                    .failureHandler(authFailureHandler)
                    .successHandler(authSuccessHandler)
                    .permitAll()
                .and()*/
                    .logout();
/*                    .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                    .logoutSuccessUrl("/")
                    .permitAll();*/
//                    .invalidateHttpSession(true);
//                .and()
//                    .authenticationProvider(authProviderService);
        http.cors();
        http.csrf().disable();
        /*http.authorizeRequests()
                    .antMatchers("/signin/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .antMatchers("/**").permitAll()
                    .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/")
                .failureUrl("/signin")
                .and()
                .logout();
        http.cors().and();
        http.csrf().disable();*/
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetailsService 를 이용한 인증처리
        log.info("congifureGlobal1>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("customUserDetailService>>>>>>>>>>>>>>" + customUserDetailsService);
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bcryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Spring Security에서 사용되는 인증객체를 Bean으로 등록할 때 사용됨
        log.info("authenticationManagerBean>>>>>>>>>>>>>");
        return super.authenticationManagerBean();
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/message/**")
                        .allowedOrigins("*")
                        .allowedMethods(HttpMethod.POST.name())
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
/*    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }*/


}
