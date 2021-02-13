import request from '../utils/request'

export function getTaskInfoList (params) {
  return request({
    url: '/quartz/list/info',
    method: 'get',
    params
  })
}

export function delTaskInfo (taskId) {
  return request({
    url: '/quartz/del',
    method: 'get',
    params: { id: taskId }
  })
}

export function editTaskInfo (data) {
  return request({
    url: '/quartz/update',
    method: 'post',
    data
  })
}

export function addTaskInfo (data) {
  return request({
    url: '/quartz/add',
    method: 'post',
    data
  })
}

export function pauseRunningTask (taskId) {
  return request({
    url: '/quartz/pause',
    method: 'get',
    params: { id: taskId }
  })
}

export function runningTask (taskId) {
  return request({
    url: '/quartz/run',
    method: 'get',
    params: { id: taskId }
  })
}
