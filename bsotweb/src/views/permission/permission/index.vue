<template>
  <div class="app-container">

    <el-button type="primary" size="small" @click="menuAdd">添加菜单</el-button>

    <el-table :data="menuTreeData" row-key="id" border :tree-props="{children: 'children', hasChildren: 'hasChildren'}" class="table-tree">
      <el-table-column prop="id" label="id" sortable width="110px"/>
      <el-table-column prop="parentId" label="父项id" width="50px">
        <template slot-scope="{ row }">
          {{ row.parentId || '0'}}
        </template>
      </el-table-column>
      <el-table-column label="图标" width="70px" align="center">
        <template slot-scope="{ row }">
          <span class="svg-container" v-if="row.icon">
            <svg-icon :icon-class="row.icon" />
          </span>
          <span v-else>
            --
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称"/>
      <el-table-column label="标题">
        <template slot-scope="{ row }">
          {{ row.title || '--'}}
        </template>
      </el-table-column>
      <el-table-column prop="path" label="请求url"/>
      <el-table-column label="重定向url">
        <template slot-scope="{ row }">
          {{ row.redirect || '--'}}
        </template>
      </el-table-column>
      <el-table-column label="类型" width="80px" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.type | typeStatusFilter">{{ scope.row.type | typeStringFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="组件">
        <template slot-scope="{ row }">
          {{ row.component || '--'}}
        </template>
      </el-table-column>
      <el-table-column label="状态" width="70px" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949" @change="changeMenuStatus(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="orderSeq" label="排序" width="50px"/>
      <el-table-column width="150px" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="danger" @click="menuDel(scope.row)">删除</el-button>
          <el-button size="small" type="success" @click="menuEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <add-menu-dialog :visible.sync="visible" :dialog-type="dialogType" :menu-data="menuData" @refreshMenuInfo="loadMenuListInfo"/>
  </div>
</template>

<script>
import { getMenuTreeListInfo, delMenuInfo } from '@/api/permission'
import addMenuDialog from './addMenuDialog'

export default {
  components: { addMenuDialog },
  data() {
    return {
      visible: false,
      dialogType: 'Add',
      menuTreeData: [],
      menuData: {
        icon: ''
      },
      typeStatusMap: {
        0: 'success',
        1: 'warning',
        2: 'info'
      }
    }
  },
  methods: {
    loadMenuListInfo() {
      getMenuTreeListInfo().then((response) => {
        this.menuTreeData = response.data
      })
    },
    menuDel(data) {
      this.$confirm('此操作将永久删除该选中信息, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delMenuInfo(data.id).then((response) => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch((error) => {
          console.log(error)
        })
        this.loadMenuListInfo()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    menuAdd() {
      this.clearMenuData()
      this.visible = true
      this.dialogType = 'Add'
    },
    menuEdit(data) {
      this.menuData = data
      this.visible = true
      this.dialogType = 'Edit'
    },
    changeMenuStatus(data) {
      alert(data.id)
    },
    clearMenuData() {
      this.menuData = Object.assign({})
    }
  },
  created () {
    this.loadMenuListInfo()
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

<style scoped>
.table-tree {
  width: 100%;
  margin-top:20px;
}
</style>
