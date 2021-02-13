<template>
  <div>
    <el-dialog :visible="visible" :title="dialogType==='Edit' ? '编辑任务' : '添加任务'" @close="closeDialog" width="40%">
      <el-form :model="taskData" label-width="90px" ref="taskInfoDialog">
        <el-form-item label="Bean名称" prop="beanName">
          <el-input placeholder="请输入Bean名称" v-model="taskData.beanName"></el-input>
        </el-form-item>
        <el-form-item label="任务名称" prop="jobName">
          <el-input placeholder="请输入任务名称" v-model="taskData.jobName"></el-input>
        </el-form-item>
        <el-form-item label="任务描述" prop="description">
          <el-input placeholder="请输入任务名称" v-model="taskData.description"></el-input>
        </el-form-item>
        <el-form-item label="方法名称" prop="methodName">
          <el-input placeholder="请输入方法名称" v-model="taskData.methodName"></el-input>
        </el-form-item>
        <el-form-item label="方法参数" prop="params">
          <el-input placeholder="请输入方法参数" v-model="taskData.params"></el-input>
        </el-form-item>
        <el-form-item label="cron表达式" prop="cronExpression">
          <el-input placeholder="请输入cron表达式" v-model="taskData.cronExpression"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="is_stop">
          <el-radio-group v-model="taskData.isStop">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="danger" @click="submitTask('taskInfoDialog')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { editTaskInfo, addTaskInfo } from '@/api/quartz'

export default {
  name: 'addTaskDialog',
  props: {
    visible: { type: Boolean, default: false },
    dialogType: { type: String, default: 'Edit' },
    taskData: {
      type: Object,
      default: () => {}
    }
  },
  methods: {
    closeDialog() {
      this.$emit('update:visible', false)
    },
    submitTask() {
      if (this.taskData.jobId) {
        // 修改
        editTaskInfo(this.taskData).then((repsonse) => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.$emit('refreshInfo')
        }).catch((error) => {
          console.log(error)
        })
      } else {
        addTaskInfo(this.taskData).then((response) => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$emit('refreshInfo')
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
