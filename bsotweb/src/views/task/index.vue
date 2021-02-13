<template>
  <div class="app-container">

    <el-button type="primary" size="small" @click="taskAdd">添加任务</el-button>

    <table-plus ref="taskTable" :config="this.tableConfig" :requestUrl="this.requestUrl" :searchParams.sync="this.listQuery">>
      <template v-slot:params="slotData">
        {{ slotData.data.params || '--' }}
      </template>
      <template v-slot:createTime="slotData">
        {{ slotData.data.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:updateTime="slotData">
        {{ slotData.data.updateTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
      </template>
      <template v-slot:status="slotData">
        <el-switch v-model="slotData.data.isStop" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949" @change="changeUserStatus(slotData.data)"></el-switch>
      </template>
      <template v-slot:operation="slotData">
        <el-button size="small" type="danger" @click="taskDel(slotData.data)">删除</el-button>
        <el-button size="small" type="success" @click="taskEdit(slotData.data)">编辑</el-button>
        <el-button size="small" type="warning" @click="taskPause(slotData.data)">暂停任务</el-button>
        <el-button size="small" type="primary" @click="taskRun(slotData.data)">执行任务</el-button>
      </template>
    </table-plus>

    <add-task-dialog :visible.sync="visible" :dialog-type="dialogType" :task-data="taskData" @refreshInfo="searchListData"/>
  </div>
</template>

<script>
import { getTaskInfoList, delTaskInfo, pauseRunningTask, runningTask } from '@/api/quartz'
import TablePlus from '@/components/TablePlus/index'
import AddTaskDialog from './addTaskDialog'

export default {
  components: { TablePlus, AddTaskDialog },
  data() {
    return {
      visible: false,
      dialogType: 'Add',
      taskData: {},
      listQuery: {
        jobName: ''
      },
      tableConfig: {
        tableHead: [
          { label: 'Bean名称', field: 'beanName' },
          { label: '任务名称', field: 'jobName' },
          { label: '任务描述', field: 'description' },
          { label: '方法名称', field: 'methodName' },
          { label: '方法参数', field: 'params', columnType: 'slot', slotName: 'params' },
          { label: 'cron表达式', field: 'cronExpression' },
          { label: '创建时间', field: 'createTime', columnType: 'slot', slotName: 'createTime' },
          { label: '修改时间', field: 'updateTime', columnType: 'slot', slotName: 'updateTime' },
          { label: '启用状态', field: 'isStop', columnType: 'slot', slotName: 'status' },
          { label: '操作', columnType: 'slot', slotName: 'operation', width: '315' }
        ]
      },
      requestUrl: getTaskInfoList
    }
  },
  methods: {
    searchListData() {
      this.$refs.taskTable.paramsLoadData()
    },
    taskDel(data) {
      this.$confirm('此操作将永久删除该选中信息, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delTaskInfo(data.id).then((response) => {
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
    taskPause(data) {
      pauseRunningTask(data.jobId).then(() => {
        this.$message({
          type: 'success',
          message: '任务暂停成功!'
        })
      })
      this.searchListData()
    },
    taskRun(data) {
      runningTask(data.jobId).then(() => {
        this.$message({
          type: 'success',
          message: '任务启动成功!'
        })
        this.searchListData()
      })
    },
    taskEdit(data) {
      this.taskData = data
      this.visible = true
      this.dialogType = 'Edit'
    },
    taskAdd() {
      this.clearTaskData()
      this.visible = true
      this.dialogType = 'Add'
    },
    clearTaskData() {
      this.taskData = Object.assign({})
    }
  }
}
</script>

<style scoped>

</style>
