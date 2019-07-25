package edu.project.authorization.config;

import edu.project.authorization.service.UserAuthService;
import edu.project.authorization.service.UserAuthServiceImpl;
import edu.project.authorization.util.JwtAuthenticationFilter;
import edu.project.authorization.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthService userAuthService;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserAuthService userAuthService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAuthService = userAuthService;
    }

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
                    .antMatchers("/", "/signup", "/signin").permitAll() // 모든 권한에 접근 허용
                    .antMatchers("/**").hasRole("USER")     // USER 권한에 접근 허용
                    .antMatchers("/**").hasRole("ADMIN")    // ADMIN 권한에 접근 허용
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").authenticated()
                    .anyRequest().authenticated()   // 인증된 사용자인지 확인
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//                .and().logout();
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // Jwt 를 이용한 인증이므로 세션은 생성 안함
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* UserDetailsService 를 이용한 인증처리 */
        auth.userDetailsService(userAuthService).passwordEncoder(bcryptPasswordEncoder());
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
}
