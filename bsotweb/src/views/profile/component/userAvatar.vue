<template>
  <div>
    <div class="user-info-head" @click.prevent="editAvatar">
      <img v-bind:src="this.avatarUrl" title="点击上传头像" class="img-circle img-lg"/>
      <el-dialog :title="title" :visible.sync="visible" width="800px" append-to-body @close="close">
        <h1>Todo: 使用vue-cropper修改头像</h1>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'userAvatar',
  props: {
    avatarUrl: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      title: '修改头像',
      visible: false
    }
  },
  methods: {
    editAvatar() {
      this.visible = true
    },
    closeDialog () {
      this.visible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.user-info-head {
  position: relative;
  display: inline-block;
  height: 120px;

  .img-circle {
    border-radius: 50%;
  }

  .img-lg {
    width: 120px;
    height: 120px;
  }
}

.user-info-head:hover:after {
  content: '+';
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  color: #eee;
  background: rgba(0, 0, 0, 0.5);
  font-size: 24px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  cursor: pointer;
  line-height: 110px;
  border-radius: 50%;
}
</style>
