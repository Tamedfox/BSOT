import request from '../utils/request'

export function getMenuTreeListInfo () {
  return request({
    url: '/menu/tree',
    method: 'get'
  })
}

export function delMenuInfo (menuId) {
  return request({
    url: '/menu/del',
    method: 'get',
    params: { id: menuId }
  })
}

export function addMenuInfo (data) {
  return request({
    url: '/menu/add',
    method: 'post',
    data
  })
}

export function editMenuInfo (data) {
  return request({
    url: '/menu/update',
    method: 'post',
    data
  })
}
