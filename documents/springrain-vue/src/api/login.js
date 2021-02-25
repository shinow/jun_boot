/*
 * @Author: your name
 * @Date: 2020-10-27 22:04:52
 * @LastEditTime: 2020-11-17 22:25:09
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /springrain-vue/src/api/login.js
 */
import request from '@/utils/request'

// 登录方法
export function login(account, password, captcha, captchaKey) {
  const data = {
    account,
    password,
    captcha,
    captchaKey
  }
  return request({
    url: '/api/system/login',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/api/system/user/info',
    method: 'post'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/api/system/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/api/getCaptcha',
    method: 'post'
  })
}