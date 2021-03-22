/*
 * @Author: your name
 * @Date: 2020-10-27 22:04:52
 * @LastEditTime: 2020-11-17 22:56:17
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /springrain-vue/src/store/modules/user.js
 */
import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
		console.log(userInfo)
      const account = userInfo.account.trim()
      const password = userInfo.password
      const captcha = userInfo.captcha
      const captchaKey = userInfo.captchaKey
      return new Promise((resolve, reject) => {
        login(account, password, captcha, captchaKey).then(res => {
          setToken(res.result.springraintoken)
          commit('SET_TOKEN', res.result.springraintoken)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.result
          // const avatar = user.avatar == "" ? require("@/assets/image/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;
          

          if (res.result && res.result.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.result)
            commit('SET_PERMISSIONS', res.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.userName)
          // commit('SET_AVATAR', avatar)
          // 超管用户特殊权限放开  之在开发环境中使用  TODO
          if(res.result.id === 'u_10001'){
            commit('SET_ROLES', ['admin'])
            commit('SET_PERMISSIONS', ['*:*:*'])
          }
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    
    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
