package com.example.demo.repository

import com.example.demo.entity.SysRole
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
interface SysRoleRepository : JpaRepository<SysRole?, Long?> {
    fun findByRole(name: String?): SysRole?
}
