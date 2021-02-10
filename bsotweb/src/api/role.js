import request from '../utils/request'

export function getRoleInfoList (query) {
  return request({
    url: '/role/list/info',
    method: 'get',
    params: query
  })
}

export function getRoleList () {
  return request({
    url: '/role/list',
    method: 'get'
  })
}

export function delRoleInfo (roleId) {
  return request({
    url: '/role/del',
    method: 'get',
    params: { id: roleId }
  })
}

export function editRoleInfo (data) {
  return request({
    url: '/role/update',
    method: 'post',
    data
  })
}

export function addRoleInfo (data) {
  return request({
    url: '/role/add',
    method: 'post',
    data
  })
}

export function getRoleMenusInfo (userId) {
  return request({
    url: '/role/menus',
    method: 'get',
    params: { id: userId }
  })
}
