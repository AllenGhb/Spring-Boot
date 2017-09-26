package com.allen.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * @Discription:
 * @Author: allen
 * @Date: 2017/9/26
 */
@Entity
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String fullname;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 授予 READER权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 不过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 不加锁
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 不禁用
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

