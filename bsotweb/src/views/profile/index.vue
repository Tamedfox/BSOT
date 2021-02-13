<template>
  <div class="app-container">
    <div>
      <el-row :gutter="20">

        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs>
              <el-tab-pane label="基本信息">
                基本信息
              </el-tab-pane>
              <el-tab-pane label="其他信息">
                其他信息
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>

    </div>
  </div>
</template>

<script>
import userCard from './component/userCard'
import { mapGetters } from 'vuex'

export default {
  name: 'Profile',
  components: { userCard },
  data() {
    return {
      user: {}
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.user = {
        name: this.name,
        role: this.roles,
        email: 'admin@test.com',
        avatar: this.avatar
      }
    }
  }
}
</script>

<style scoped>

</style>
