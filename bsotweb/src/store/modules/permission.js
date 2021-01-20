import { asyncRoutes, constantRoutes } from '@/router'
import { getRoutes } from '@/api/user'
import Layout from '@/views/layout'

const state = {
  routes: [],
  addRoutes: []
}

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes) {
  routes.filter(route => {
    // Layout组件处理
    if (route.component) {
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadView(route.component)
      }
    }
    // 子路由则递归加载
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRoutes(route.children)
    }
  })
  return routes
  // const res = []
  //
  // routes.forEach(route => {
  //   console.log(route)
  //   const tmp = { ...route }
  //   if (hasPermission(roles, tmp)) {
  //     if (tmp.children) {
  //       tmp.children = filterAsyncRoutes(tmp.children, roles)
  //     }
  //     res.push(tmp)
  //   }
  // })
  //
  // return res
}

export const loadView = (view) => { // 路由懒加载
  return (resolve) => require([`@/views/${view}`], resolve)
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, userId) {
    return new Promise(resolve => {
      // 向后端请求路由数据
      getRoutes().then(response => {
        const accessedRoutes = filterAsyncRoutes(response.data) || []
        commit('SET_ROUTES', accessedRoutes)
        resolve(accessedRoutes)
      })
      // let accessedRoutes
      // if (roles.includes('admin')) {
      //   accessedRoutes = asyncRoutes || []
      // } else {
      //   accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      // }
      // commit('SET_ROUTES', accessedRoutes)
      // resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
