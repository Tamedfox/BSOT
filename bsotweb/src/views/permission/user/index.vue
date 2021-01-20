<template>
  <div class="app-container">
    <el-button type="primary">增加用户</el-button>

    <table-plus ref="userTable" :config="this.tableConfig" :selection="tableConfig.selection" :requestUrl="this.requestUrl" :searchParams.sync="this.listQuery">
      <template v-slot:createTime="slotData">
        {{ slotData.data.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:loginTime="slotData">
        {{ slotData.data.lastLoginTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:status="slotData">
        <el-switch v-model="slotData.data.status" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
      </template>
      <template v-slot:operation="slotData">
        <el-button size="small" type="danger" @click="userDel(slotData.data)">删除</el-button>
        <el-button size="small" type="success" @click="handlerEdit(slotData.data)">编辑</el-button>
      </template>
    </table-plus>
  </div>
</template>

<script>
import TablePlus from '@/components/TablePlus/index'
import { gerUserInfoList, delUserInfo } from '@/api/user'

export default {
  components: { TablePlus },
  data() {
    return {
      listQuery: {
        username: '',
        nickName: '',
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
          { label: '操作', columnType: 'slot', slotName: 'operation', width: '150' }
        ]
      },
      requestUrl: gerUserInfoList
    }
  },
  methods: {
    searchListData() {
      this.$refs.userTable.paramsLoadData()
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
    }
  }
}
</script>

<style scoped>

</style>
