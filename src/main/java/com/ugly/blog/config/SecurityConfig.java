package com.ugly.blog.config;

import com.ugly.blog.constant.Constants;
import com.ugly.blog.filter.TokenFilter;
import com.ugly.blog.filter.VerifyCodeFilter;
import com.ugly.blog.handler.MyAuthenticationFailureHandler;
import com.ugly.blog.handler.MyAuthenticationSuccessHandler;
import com.ugly.blog.handler.SimpleAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author JwZheng
 * @date 2021/4/17 13:43
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenFilter tokenFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, TokenFilter tokenFilter) {
        this.userDetailsService = userDetailsService;
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 角色继承 admin继承自user
     *
     * @return RoleHierarchy
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
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
        http.
                addFilterBefore(new VerifyCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/system/**").authenticated()
                .antMatchers("/sys/**").authenticated()
                .anyRequest().permitAll();

        http
                .formLogin().loginPage("/login").loginProcessingUrl(Constants.LOGIN_PROCESSING_URL).permitAll()
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll()
                .and()
                .rememberMe();
    }
}
