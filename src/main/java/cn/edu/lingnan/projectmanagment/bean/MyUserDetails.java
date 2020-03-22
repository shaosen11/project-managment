package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author shaosen
 */
@Data
public class MyUserDetails implements UserDetails, Serializable {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date birthday;
    private int gender;
    private String address;
    private String introduce;
    private Date lastLoginTime;
    private Date createTime;
    /**
     * 是否过期
     */
    private boolean accountNonExpired;
    /**
     * 是否没被锁定
     */
    private boolean accountNonLocked;
    /**
     * 是否没过期
     */
    private boolean credentialNonExpired;
    /**
     * 账户是否可用
     */
    private boolean enabled;
    /**
     * 用户权限结合
     */
    Collection<? extends GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}