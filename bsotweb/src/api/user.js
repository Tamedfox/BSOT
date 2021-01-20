import request from '../utils/request'

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

export function gerUserInfoList (pageNum, pageSize, query) {
  return request({
    url: `/user/list/${pageNum}/${pageSize}`,
    method: 'get',
    params: query
  })
}

export function delUserInfo (userId) {
  return request({
    url: `/user/${userId}`,
    method: 'delete'
  })
}
