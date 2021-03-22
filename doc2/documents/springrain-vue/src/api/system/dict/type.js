/*
 * @Author: your name
 * @Date: 2020-10-27 22:04:52
 * @LastEditTime: 2021-01-31 15:00:30
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /springrain-vue/src/api/api/system/dict/type.js
 */
import request from '@/utils/request'

// 查询字典类型列表
export function listType(query) {
  return request({
    url: '/api/system/dicdata/type/list',
    method: 'get',
    params: query
  })
}

// 查询字典类型详细
export function getType(dictId) {
  return request({
    url: '/api/system/dicdata/type/' + dictId,
    method: 'get'
  })
}

// 新增字典类型
export function addType(data) {
  return request({
    url: '/api/system/dicdata/type/update',
    method: 'post',
    data: data
  })
}

// 修改字典类型
export function updateType(data) {
  return request({
    url: '/api/system/dicdata/type/update',
    method: 'post',
    data: data
  })
}

// 删除字典类型
export function delType(dictId) {
  return request({
    url: '/api/system/dicdata/type/delete/' + dictId,
    method: 'post'
  })
}

// 清理参数缓存
export function clearCache() {
  return request({
    url: '/api/system/dict/type/clearCache',
    method: 'delete'
  })
}

// 导出字典类型
export function exportType(query) {
  return request({
    url: '/api/system/dict/type/export',
    method: 'get',
    params: query
  })
}

// 获取字典选择框列表
export function optionselect() {
  return request({
    url: '/api/system/dict/type/optionselect',
    method: 'get'
  })
}