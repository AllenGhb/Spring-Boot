package com.allen.config;

import com.allen.dao.ReaderRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Discription:
 * @Author: allen
 * @Date: 2017/9/26
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private ReaderRepository readerRepository;

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 要求登录者要有READER角色,设置登录表单的路径
        http.authorizeRequests().antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll().and().formLogin().loginPage("/login").failureUrl("login?error=true");
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails userDetails = readerRepository.findOne(username);
                if(userDetails != null){
                    return userDetails;
                }
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
        });
    }

}
