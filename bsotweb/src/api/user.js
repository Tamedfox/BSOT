import request from '../utils/request'
import qs from 'qs'

export function login (data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo (token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout () {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function getRoutes() {
  return request({
    url: '/user/list/nav',
    method: 'get'
  })
}

export function gerUserInfoList (query) {
  return request({
    url: '/user/list/info',
    method: 'get',
    params: query
  })
}

export function addUserInfo (userInfo) {
  return request({
    url: '/user/add',
    method: 'post',
    data: userInfo
  })
}

export function delUserInfo (userId) {
  return request({
    url: '/user/del',
    method: 'get',
    params: { id: userId }
  })
}

export function editUserInfo (data) {
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}

export function getUserRolesInfo (userId) {
  return request({
    url: '/user/roles',
    method: 'get',
    params: { id: userId }
  })
}

export function updateUserRoles (userId, roleIds) {
  return request({
    url: '/user/role/update',
    method: 'get',
    params: {
      userId: userId,
      roleIds: roleIds
    },
    paramsSerializer: params => {
      return qs.stringify(params, { indices: false })
    }
  })
}
