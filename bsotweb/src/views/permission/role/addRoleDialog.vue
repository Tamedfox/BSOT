<template>
  <div class="app-container">
    <el-dialog :visible="visible" :title="dialogType==='Edit' ? '编辑角色' : '添加角色'" @close="closeDialog" width="40%" @open="open">
      <el-form :model="roleData" label-width="80px" ref="roleInfoDialog">
        <el-form-item label="角色名称" prop="name">
          <el-input placeholder="请输入角色名称" v-model="roleData.name"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="description">
          <el-input placeholder="请输入角色描述" v-model="roleData.description">
          </el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="roleData.status">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单">
          <el-tree ref="tree" :data="menuTreeData" :check-strictly="checkStrictly" :props="defaultProps" :default-checked-keys="menuIds" show-checkbox node-key="id" class="permission-tree">
           <span class="custom-tree-node" slot-scope="{ node, data }">
             <span style="width: 100px">{{ node.label }}</span>
             <span style="width: 150px">{{ data.path }}</span>
             <el-tag :type="data.type | typeStatusFilter" size="mini">{{ data.type | typeStringFilter }}</el-tag>
            </span>
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="danger" @click="submitRole('roleInfoDialog')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { editRoleInfo, addRoleInfo, getRoleMenusInfo } from '@/api/role'
import { getMenuTreeListInfo } from '@/api/permission'
import { mapState } from 'vuex'

export default {
  name: 'addRoleDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    dialogType: {
      type: String,
      default: 'Edit'
    },
    roleData: {
      type: Object,
      default: () => {
      }
    }
  },
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      checkStrictly: false,
      menuTreeData: [],
      menuIds: []
    }
  },
  computed: {
    ...mapState({
      userId: state => state.user.userId
    })
  },
  methods: {
    open() {
      this.getMenuTreeData()
      this.getRoleMenusIds()
    },
    generateArr(menus) {
      let data = []
      menus.forEach(menu => {
        data.push(menu)
        if (menu.children) {
          const temp = this.generateArr(menu.children)
          if (temp.length > 0) {
            data = [...data, ...temp]
          }
        }
      })
      return data
    },
    getMenuTreeData() {
      getMenuTreeListInfo().then((response) => {
        this.menuTreeData = response.data
      })
    },
    getRoleMenusIds() {
      getRoleMenusInfo(this.userId).then((response) => {
        const menusInfo = response.data
        this.checkStrictly = true
        this.$nextTick(() => {
          this.$refs.tree.setCheckedNodes(this.generateArr(menusInfo))
          this.checkStrictly = false
        })
      })
    },
    closeDialog () {
      this.$emit('update:visible', false)
    },
    submitRole () {
      if (this.roleData.id) {
        this.roleData.menuIds = this.$refs.tree.getCheckedKeys()
        console.log(JSON.stringify(this.roleData))
        editRoleInfo(this.roleData).then((response) => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.$emit('refreshRoleInfo')
        })
      } else {
        addRoleInfo(this.roleData).then((response) => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$emit('refreshRoleInfo')
        }).catch((error) => {
          console.log(error)
        })
      }
      this.closeDialog()
    }
  },
  filters: {
    typeStatusFilter(status) {
      const typeStatusMap = {
        0: 'success',
        1: 'warning',
        2: 'danger'
      }
      return typeStatusMap[status]
    },
    typeStringFilter(status) {
      const typeStringMap = {
        0: '菜单',
        1: '菜单项',
        2: '请求'
      }
      return typeStringMap[status]
    }
  }
}
</script>

<style lang="scss" scoped>
.app-container {
  .permission-tree {
    margin-bottom: 30px;
  }

  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
}
</style>
