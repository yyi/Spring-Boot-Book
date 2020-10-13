package com.example.demo.repository

import com.example.demo.entity.SysUser
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
interface SysUserRepository : JpaRepository<SysUser?, Long?> {
    fun findByName(name: String?): SysUser?
    fun findById(id: Long): SysUser?
}
