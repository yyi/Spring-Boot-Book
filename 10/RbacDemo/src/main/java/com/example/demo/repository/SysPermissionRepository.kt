package com.example.demo.repository

import com.example.demo.entity.SysPermission
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author longzhonghua
 * @data 2019/01/27 08:26
 */
interface SysPermissionRepository : JpaRepository<SysPermission?, Long?> {
    fun findById(id: Long): SysPermission?
}
