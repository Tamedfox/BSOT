<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.username" placeholder="用户名" clearable style="width: 150px" class="filter-item" />
      <el-input v-model="listQuery.email" placeholder="邮箱" clearable style="width: 150px" class="filter-item" />
      <el-select v-model="listQuery.status" placeholder="用户状态" clearable class="filter-item" style="width: 130px">
        <el-option label="生效" value="1" />
        <el-option label="无效" value="0" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="searchListData">
        Search
      </el-button>
    </div>

    <el-button type="primary" size="small" @click="userAdd">添加用户</el-button>

    <table-plus ref="userTable" :config="this.tableConfig" :selection="tableConfig.selection" :requestUrl="this.requestUrl" :searchParams.sync="this.listQuery">
      <template v-slot:createTime="slotData">
        {{ slotData.data.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:loginTime="slotData">
        {{ slotData.data.lastLoginTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:status="slotData">
        <el-switch v-model="slotData.data.status" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949" @change="changeUserStatus(slotData.data)"></el-switch>
      </template>
      <template v-slot:operation="slotData">
        <el-button size="small" type="danger" @click="userDel(slotData.data)">删除</el-button>
        <el-button size="small" type="success" @click="userEdit(slotData.data)">编辑</el-button>
        <el-button size="small" type="warning" @click="userRolesEdit(slotData.data)">角色调整</el-button>
      </template>
    </table-plus>

    <add-user-dialog :visible.sync="visible" :dialog-type="dialogType" :user-data="userData" @refreshUserInfo="searchListData"/>

    <edit-user-roles-dialog :visible.sync="userRolesVisible" :user-data="userData"/>
  </div>
</template>

<script>
import TablePlus from '@/components/TablePlus/index'
import { gerUserInfoList, delUserInfo, getUserRolesInfo } from '@/api/user'
import AddUserDialog from './addUserDialog'
import EditUserRolesDialog from './editUserRolesDialog'

export default {
  components: { AddUserDialog, TablePlus, EditUserRolesDialog },
  data() {
    return {
      visible: false,
      userRolesVisible: false,
      dialogType: 'Add',
      userData: {},
      listQuery: {
        username: '',
        email: '',
        status: ''
      },
      tableConfig: {
      // 表头
        tableHead: [
          { label: '用户名', field: 'username' },
          { label: '邮箱', field: 'email' },
          { label: '创建时间', field: 'createTime', columnType: 'slot', slotName: 'createTime', width: '150' },
          { label: '最近登录时间', field: 'loginTime', columnType: 'slot', slotName: 'loginTime', width: '150' },
          { label: '禁启用状态', field: 'status', columnType: 'slot', slotName: 'status' },
          { label: '操作', columnType: 'slot', slotName: 'operation', width: '230' }
        ]
      },
      requestUrl: gerUserInfoList
    }
  },
  methods: {
    searchListData() {
      this.$refs.userTable.paramsLoadData()
    },
    userAdd() {
      this.clearUserData()
      this.visible = true
      this.dialogType = 'Add'
    },
    userEdit(data) {
      this.userData = data
      this.visible = true
      this.dialogType = 'Edit'
    },
    userRolesEdit(data) {
      this.userData = data
      this.userRolesVisible = true
    },
    userDel(data) {
      this.$confirm('此操作将永久删除该选中信息, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delUserInfo(data.id).then((response) => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch((error) => {
          console.log(error)
        })
        this.searchListData()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    changeUserStatus(params) {
      console.log(params)
    },
    clearUserData() {
      this.userData = Object.assign({})
    }
  }
}
</script>

<style scoped>

</style>
