<#assign className = table.className>
<#assign tableName = table.tableAlias>
<#assign classNameLowerCase = className?lower_case>
import request from '@/utils/request'

/**
 *
 * 获取 ${tableName} 列表
 * @param
 */
export function get${className}s(parameter) {
    return request({
        url: '/system/${classNameLowerCase}/list',
        method: 'post',
        data: parameter
    })
}

/**
 *
 * 获取  ${tableName}  列表
 * @param {id:23}
 */
export function get${className}(parameter) {
    return request({
        url: '/system/${classNameLowerCase}/list',
        method: 'post',
        data: parameter
    })
}

/**
 *
 * 添加  ${tableName}
 * @param
 */

export function create${className}(data) {
    return request({
        url: '/system/${classNameLowerCase}/save',
        method: 'post',
        data
    })
}


/**
 *
 * 更新  ${tableName}
 * @param
 */
export function update${className}(data) {
    return request({
        url: '/system/${classNameLowerCase}/update',
        method: 'post',
        data
    })
}

/**
 *
 * 删除  ${tableName}
 * @param  {id:23}
 */
export function del${className}(id) {
    return request({
        url: '/system/${classNameLowerCase}/delete',
        method: 'post',
        data: {id:id}
    })
}