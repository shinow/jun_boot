/*
 * @Author: your name
 * @Date: 2020-10-27 22:04:52
 * @LastEditTime: 2020-11-17 22:50:01
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: /springrain-vue/src/directive/permission/hasPermi.js
 */
 /**
 * 操作权限处理
 * Copyright (c) 2019 ruoyi
 */
 
import store from '@/store'

export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const all_permission = "*:*:*";
    const permissions = store.getters && store.getters.permissions

    if (value && value instanceof Array && value.length > 0) {
      const permissionFlag = value

      const hasPermissions = permissions.some(permission => {
        return all_permission === permission || permissionFlag.includes(permission)
      })

      if (!hasPermissions) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`请设置操作权限标签值`)
    }
  }
}
