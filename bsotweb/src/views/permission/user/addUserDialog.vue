<template>
  <div>
    <el-dialog :visible="visible" :title="dialogType==='Edit' ? '编辑用户' : '添加用户'" @close="closeDialog" width="40%">
      <el-form :model="userData" label-width="80px" ref="userInfoDialog">
        <el-form-item label="用户名" prop="username">
          <el-input placeholder="请输入用户名" v-model="userData.username"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input placeholder="请输入邮箱" v-model="userData.email">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" v-if="dialogType === 'Add'">
          <el-input placeholder="请输入密码" type="password" v-model="userData.password"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status" v-if="dialogType === 'Edit'">
          <el-radio-group v-model="userData.status">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="danger" @click="submitUser('userInfoDialog')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addUserInfo, editUserInfo } from '@/api/user'

export default {
  name: 'addUserDialog',
  props: {
    visible: { type: Boolean, default: false },
    dialogType: { type: String, default: 'Edit' },
    userData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      // roleData: {
      //   status: "1"
      // }
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:visible', false)
    },
    submitUser() {
      if (this.userData.id) {
        // 修改
        editUserInfo(this.userData).then((repsonse) => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.$emit('refreshUserInfo')
        }).catch((error) => {
          console.log(error)
        })
      } else {
        addUserInfo(this.userData).then((response) => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$emit('refreshUserInfo')
        }).catch((error) => {
          console.log(error)
        })
      }
      this.closeDialog()
    }
  }
}
</script>

<style scoped>

</style>
