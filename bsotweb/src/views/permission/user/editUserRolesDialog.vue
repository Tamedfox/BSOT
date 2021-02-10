<template>
  <div width="40%">
    <el-dialog :visible="visible" title="用户角色调整" @close="closeDialog" width="40%" @open="open">
      <el-form :model="userData" label-width="80px" ref="userRolesDialog">
        <el-form-item label="用户名" prop="username">
          <span>{{ userData.username }}</span>
        </el-form-item>
        <el-form-item label="角色">
          <el-checkbox-group v-model="roleIds">
            <el-checkbox v-for="role in rolesList" :label="role.id" :key="role.id">{{ role.name }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="danger" @click="updateUserRolesRef">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUserRolesInfo, updateUserRoles } from '@/api/user'
import { getRoleList } from '@/api/role'

export default {
  name: 'editUserRolesDialog',
  props: {
    visible: { type: Boolean, default: false },
    userData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      rolesList: [],
      roleIds: []
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:visible', false)
    },
    getRolesInfo() {
      getRoleList().then((response) => {
        this.rolesList = response.data
      })
    },
    getCurUserRolesInfo() {
      getUserRolesInfo(this.userData.id).then(response => {
        const rolesInfo = response.data
        rolesInfo.forEach((role) => {
          this.roleIds.push(role.id)
        })
      })
    },
    updateUserRolesRef() {
      updateUserRoles(this.userData.id, this.roleIds).then(response => {
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
      })
      this.closeDialog()
    },
    open() {
      this.roleIds = []
      this.getRolesInfo()
      this.getCurUserRolesInfo()
    }
  }
}
</script>

<style scoped>

</style>
