package com.example.demo.entity

import lombok.Data
import java.io.Serializable
import java.lang.Boolean
import java.util.*
import javax.persistence.*

/**
 * @author longzhonghua
 * @data 2019/01/26 22:30
 */
@Data
@Entity
class SysPermission : Serializable {
    @Id
    @GeneratedValue
    /*
    主键
    */
    private val id: Int? = null

    //名称.
    private val name: String? = null

    @Column(columnDefinition = "enum('menu','button')") //资源类型，[menu|button]
    private val resourceType: String? = null
    val url //资源路径.
            : String? = null

    //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    val permission: String? = null
    private val parentId //父编号
            : Long? = null
    private val parentIds //父编号列表
            : String? = null
    private val available = Boolean.FALSE

    @Transient
    var permissions: List<*>? = null
        get() = Arrays.asList(*permission!!.trim { it <= ' ' }.split("|".toRegex()).toTypedArray())

    @ManyToMany
    @JoinTable(
        name = "SysRolePermission",
        joinColumns = [JoinColumn(name = "permissionId")],
        inverseJoinColumns = [JoinColumn(name = "roleId")]
    )
    private val roles: List<SysRole>? = null
}
