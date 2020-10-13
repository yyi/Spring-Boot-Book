package com.example.demo.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
/**
 * 用户实体类，通过实现UserDetails接口实现认证及授权
 */
@Entity //@Table(name = "adminuser") //设置对应表名字
class SysUser : UserDetails {
    //主键及自动增长
    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(nullable = false, unique = true)
    var name: String? = null
    private var password: String? = null
    var cnname: String? = null
    var enabled = java.lang.Boolean.TRUE

    //多对多映射，用户角色
    @ManyToMany(cascade = [CascadeType.REFRESH], fetch = FetchType.EAGER)
    var roles: List<SysRole>? = null

    //获取当前用户实例的password
    override fun getPassword(): String {
        return password!!
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    constructor(name: String?, password: String?) {
        this.name = name
        this.password = password
    }

    constructor() {}

    //根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        val roles = roles
        for (role in roles!!) {
            authorities.add(SimpleGrantedAuthority(role.role))
        }
        return authorities
    }

    //获取当前用户实例的name
    override fun getUsername(): String {
        return name!!
    }

    //帐号是否不过期，false则验证不通过
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    //帐号是否不锁定，false则验证不通过
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    //凭证是否不过期，false则验证不通过
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    //该帐号是否启用，false则验证不通过
    override fun isEnabled(): Boolean {
        return true
    }
}
