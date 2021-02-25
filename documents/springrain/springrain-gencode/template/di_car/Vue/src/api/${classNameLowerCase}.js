<#assign className = table.className>
<#assign tableName = table.tableAlias>
<#assign classNameLowerCase = className?lower_case>
import {
    axios
} from '@/utils/axios'

/**
 *
 * 获取 ${tableName} 列表
 * @param
 */
export function get${className}s(parameter) {
    return axios({
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
    return axios({
        url: '/system/${classNameLowerCase}/list',
        method: 'get',
        data: parameter
    })
}

/**
 *
 * 添加/更新  ${tableName}
 * @param
 */
export function addorupdate${className}(parameter) {
    const url = parameter.id === null ? '/system/${classNameLowerCase}/save' : '/system/${classNameLowerCase}/update'
    return axios({
        url: url,
        method: 'post',
        data: parameter
    })
}




/**
 *
 * 删除  ${tableName}
 * @param  {id:23}
 */
export function del${className}(parameter) {
    return axios({
        url: '/${classNameLowerCase}/del/' + parameter.id,
        method: 'get'
    })
}
