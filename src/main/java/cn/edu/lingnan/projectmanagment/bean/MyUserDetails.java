package cn.edu.lingnan.projectmanagment.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author shaosen
 */
@Data
public class MyUserDetails implements UserDetails, Serializable {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date birthday;
    private Integer gender;
    private String address;
    private String tag;
    private String introduce;
    private String photo;
    private Date lastLoginTime;
    private Date createTime;
    private String validataCode;
    private Timestamp outDate;
    private List<Myprojects> myprojectsList;
    private List<UserCodeUpdateRecord> userCodeUpdateRecordList;
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
