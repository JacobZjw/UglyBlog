package com.ugly.blog.config;

import com.ugly.blog.filter.JWTAuthenticationEntryPoint;
import com.ugly.blog.filter.JWTAuthenticationFilter;
import com.ugly.blog.filter.JWTAuthorizationFilter;
import com.ugly.blog.handler.JWTAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author JwZheng
 * @date 2021/4/17 13:43
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/css/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                // 指定路径下的资源需要验证了的用户才能访问
                .antMatchers("/api/system/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("admin")
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                //添加自定义Filter
                .addFilterAt(new JWTAuthenticationFilter(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler())
                .and()
                .formLogin().loginPage("/login")
                // 登陆处理路径
                .loginProcessingUrl("/loginVerify").permitAll()
                .and()
                // 关闭csrf
                .csrf().disable();


//        http.cors()
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                // 指定路径下的资源需要验证了的用户才能访问
//                .antMatchers("/api/system/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("admin")
//                // 其他都放行了
//                .anyRequest().permitAll()
//                .and()
//                //添加自定义Filter
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter())
//                // 不需要session（不创建会话）
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // 授权异常处理
//                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
//                .accessDeniedHandler(new JWTAccessDeniedHandler());
    }
}
