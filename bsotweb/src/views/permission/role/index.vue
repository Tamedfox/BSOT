<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="角色名称" clearable style="width: 150px" class="filter-item" />
      <el-select v-model="listQuery.status" placeholder="角色状态" clearable class="filter-item" style="width: 130px">
        <el-option label="生效" value="1" />
        <el-option label="无效" value="0" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="searchListData">
        Search
      </el-button>
    </div>

    <el-button type="primary" size="small" @click="roleAdd">添加角色</el-button>

    <table-plus ref="roleTable" :config="this.tableConfig" :selection="tableConfig.selection" :requestUrl="this.requestUrl" :searchParams.sync="this.listQuery">
      <template v-slot:createTime="slotData">
        {{ slotData.data.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:updateTime="slotData">
        {{ slotData.data.updateTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:status="slotData">
        <el-switch v-model="slotData.data.status" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949" @change="changeRoleStatus(slotData.data)"></el-switch>
      </template>
      <template v-slot:operation="slotData">
        <el-button size="small" type="danger" @click="roleDel(slotData.data)">删除</el-button>
        <el-button size="small" type="success" @click="roleEdit(slotData.data)">编辑</el-button>
      </template>
    </table-plus>

    <add-role-dialog :visible.sync="visible" :dialog-type="dialogType" :role-data="roleData" @refreshRoleInfo="searchListData"/>
  </div>
</template>

<script>
import TablePlus from '@/components/TablePlus/index'
import { getRoleInfoList, delRoleInfo } from '@/api/role'
import AddRoleDialog from './addRoleDialog'

export default {
  components: { TablePlus, AddRoleDialog },
  data () {
    return {
      visible: false,
      dialogType: 'Add',
      roleData: {},
      listQuery: {
        name: '',
        status: ''
      },
      tableConfig: {
        tableHead: [
          { label: '角色名称', field: 'name' },
          { label: '角色描述', field: 'description' },
          { label: '创建时间', field: 'createTime', columnType: 'slot', slotName: 'createTime', width: '150' },
          { label: '更新时间', field: 'updateTime', columnType: 'slot', slotName: 'updateTime', width: '150' },
          { label: '禁启用状态', field: 'status', columnType: 'slot', slotName: 'status' },
          { label: '排序', field: 'orderSeq', width: '50' },
          { label: '操作', columnType: 'slot', slotName: 'operation', width: '140' }
        ]
      },
      requestUrl: getRoleInfoList
    }
  },
  methods: {
    searchListData () {
      this.$refs.roleTable.paramsLoadData()
    },
    roleAdd() {
      this.clearRoleData()
      this.visible = true
      this.dialogType = 'Add'
    },
    roleDel(data) {
      this.$confirm('此操作将永久删除该选中信息, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRoleInfo(data.id).then((response) => {
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
    roleEdit(data) {
      this.roleData = data
      this.visible = true
      this.dialogType = 'Edit'
    },
    changeRoleStatus(params) {
      alert(params)
    },
    clearRoleData() {
      this.roleData = Object.assign({})
    }
  }
}
</script>

<style scoped>

</style>
