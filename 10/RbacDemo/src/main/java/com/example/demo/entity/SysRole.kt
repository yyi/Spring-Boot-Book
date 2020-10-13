package com.example.demo.entity

import lombok.Data
import java.lang.Boolean
import javax.persistence.*

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Data
@Entity /*@Table(name = "sys_role")*/
class SysRole {
    @Id
    @GeneratedValue
    private val id // 编号
            : Int? = null
    private val cnname: String? = null
    val role // 角色标识程序中判断使用,如"sys",这个是唯一的:
            : String? = null
    private val description // 角色描述,UI界面显示使用
            : String? = null
    private val available = Boolean.FALSE // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "SysRolePermission",
        joinColumns = [JoinColumn(name = "roleId")],
        inverseJoinColumns = [JoinColumn(name = "permissionId")]
    )
    val permissions: List<SysPermission>? = null

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(
        name = "SysUserRole",
        joinColumns = [JoinColumn(name = "roleId")],
        inverseJoinColumns = [JoinColumn(name = "uid")]
    )
    private val userInfos // 一个角色对应多个用户
            : List<SysUser>? = null
}
