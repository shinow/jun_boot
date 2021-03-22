/*
 * @Author: your name
 * @Date: 2021-01-10 13:08:54
 * @LastEditTime: 2021-01-13 21:22:25
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \springrain-vue\src\api\system\dict\data.js
 */
import request from '@/utils/request'

// 查询字典数据列表
export function listData(query) {
  return request({
    url: '/api/system/dicdata/data/list',
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: '/api/system/dict/data/' + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: '/api/system/dicdata/data/type/' + dictType,
    method: 'get'
  })
}

// 新增字典数据
export function addData(data) {
  return request({
    url: '/api/system/dict/data',
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: '/api/system/dict/data',
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: '/api/system/dict/data/' + dictCode,
    method: 'delete'
  })
}

// 导出字典数据
export function exportData(query) {
  return request({
    url: '/api/system/dict/data/export',
    method: 'get',
    params: query
  })
}