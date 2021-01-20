const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

module.exports = [
  {
    url: '/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const token = tokens[username]

      if (!token) {
        return {
          code: 1,
          message: 'Account or password are wrong ' + JSON.stringify(username)
        }
      }
      return {
        code: '0',
        message: '成功',
        data: token
      }
    }
  },
  {
    url: '/user/info',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      if (!info) {
        return {
          code: 1,
          message: '登录失败，无法获取用户信息'
        }
      }
      return {
        code: 200,
        data: info
      }
    }
  },
  // logout
  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  },
  // 获取当前用户id下的菜单信息
  {
    url: '/user/list/nav',
    type: 'get',
    response: config => {
      return {
        code: 200,
        message: 'success',
        data: [
          {
            id: 1,
            parentId: 0,
            name: '首页',
            path: '/dashboard',
            component: 'Layout',
            icon: '',
            meta: {
              icon: 'dashboard',
              title: '首页'
            },
            children: []
          },
          {
            id: 2,
            parentId: 0,
            name: '权限管理',
            path: '/permission',
            component: 'Layout',
            icon: 'eye',
            meta: {
              icon: 'permission',
              title: '权限管理'
            },
            children: [
              {
                id: 3,
                parentId: 2,
                name: '用户管理',
                path: '/permission/userIndex',
                component: 'permission/user/index',
                icon: '404',
                meta: {
                  icon: 'user',
                  title: '用户管理'
                },
                children: []
              },
              {
                id: 4,
                parentId: 2,
                name: '角色管理',
                path: '/permission/roleIndex',
                component: 'permission/role/index',
                icon: '404',
                meta: {
                  icon: 'role',
                  title: '角色管理'
                },
                children: []
              },
              {
                id: 5,
                parentId: 2,
                name: '权限管理',
                path: '/permission/permissionIndex',
                component: 'permission/permission/index',
                icon: '404',
                meta: {
                  icon: 'permission-manage',
                  title: '权限管理'
                },
                children: []
              }
            ]
          }
        ]
      }
    }
  },
  // 分页获取用户信息列表
  {
    url: '/user/list',
    type: 'get',
    response: _ => {
      return {
        code: 200,
        message: '操作成功',
        data: {
          total: 2,
          rows: [
            {
              id: 1,
              username: 'admin',
              password: null,
              icon: 'icon',
              email: '191089192@qq.com',
              createTime: '2020-09-20T09:39:42.000+00:00',
              lastLoginTime: '2021-01-09T12:24:04.000+00:00',
              status: 1
            },
            {
              id: 3,
              username: 'test',
              password: null,
              icon: 'testicon',
              email: '191089192@qq.com',
              createTime: '2020-09-20T09:39:42.000+00:00',
              lastLoginTime: '2020-09-25T14:47:18.000+00:00',
              status: 1
            }
          ]
        }
      }
    }
  },
  // 删除用户
  {
    url: '/user/\d*',
    type: 'delete',
    response: config => {
      return {
        code: 200,
        message: 'delete success',
        data: config.query
      }
    }
  }
]
