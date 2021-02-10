<template>
  <div>
    <el-dialog :visible="visible" :title="dialogType==='Edit' ? '编辑菜单' : '添加菜单'" @close="closeDialog" width="40%">
      <el-form :model="menuData" label-width="120px" ref="menuInfoDialog">
        <el-form-item label="父项菜单id" prop="parentId">
          <el-input placeholder="请输入父项菜单id，父项为顶层请输入0" v-model="menuData.parentId"></el-input>
        </el-form-item>
        <el-form-item label="菜单名称" prop="name">
          <el-input placeholder="请输入菜单名称" v-model="menuData.name"></el-input>
        </el-form-item>
        <el-form-item label="菜单描述" prop="description">
          <el-input placeholder="请输入菜单描述" v-model="menuData.description"></el-input>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-radio-group v-model="menuData.type" size="small">
            <el-radio-button label="0">菜单</el-radio-button>
            <el-radio-button label="1">菜单项</el-radio-button>
            <el-radio-button label="2">请求</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单标题" prop="title">
          <el-input placeholder="请输入菜单标题" v-model="menuData.title"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon" v-if=menuType>
          <el-input v-model="menuData.icon" placeholder="请点击右侧选择图标" style="width: 250px" readonly>
            <template slot="prepend" v-if="menuData.icon">
              <svg-icon :icon-class="menuData.icon"/>
            </template>
            <el-popover placement="right" width="370" trigger="click" slot="append" ref="iconsPopover">
              <el-row>
                <el-col v-for="item of svgIcons" :key="item" :span="6">
                  <div @click="chooseIcon(item)">
                    <el-tooltip placement="top">
                      <div slot="content">
                        {{ generateIconCode(item) }}
                      </div>
                      <div class="icon-item">
                        <svg-icon :icon-class="item" class-name="disabled"/>
                      </div>
                    </el-tooltip>
                  </div>
                </el-col>
              </el-row>
              <el-button slot="reference">选择</el-button>
            </el-popover>
          </el-input>
        </el-form-item>
        <el-form-item label="请求路径" prop="path">
          <el-input placeholder="请输入菜单请求路径" v-model="menuData.path" ref="path"></el-input>
        </el-form-item>
        <el-form-item label="重定向路径" prop="redirect" v-if="menuType">
          <el-input placeholder="请输入重定向路径" v-model="menuData.redirect"></el-input>
        </el-form-item>
        <el-form-item label="菜单组件" prop="component" v-if="menuType">
          <el-input placeholder="请输入菜单组件" v-model="menuData.component"></el-input>
        </el-form-item>
        <el-form-item label="菜单固定" prop="affix" v-if="menuType">
          <el-switch v-model="menuData.affix" :active-value="1" :inactive-value="0" active-color="#13ce66"
                     inactive-color="#ff4949"></el-switch>
        </el-form-item>
        <el-form-item label="菜单序号" prop="orderSeq">
          <el-input placeholder="请输入菜单序号" v-model="menuData.orderSeq"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="menuData.status">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">启用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="danger" @click="submitRole('menuInfoDialog')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addMenuInfo, editMenuInfo } from '@/api/permission'
import svgIcons from '@/views/icons/svg-icons'

export default {
  name: 'adMenuDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    dialogType: {
      type: String,
      default: 'Edit'
    },
    menuData: {
      type: Object,
      default: () => {
      }
    }
  },
  data () {
    return {
      svgIcons,
      menuType: true
    }
  },
  watch: {
    'menuData.type': function (val) {
      if (val === '2') {
        this.menuType = false
      } else {
        this.menuType = true
      }
    }
  },
  methods: {
    generateIconCode (symbol) {
      return `<svg-icon icon-class="${symbol}" />`
    },
    closeDialog () {
      this.$emit('update:visible', false)
    },
    chooseIcon (icon) {
      this.menuData.icon = icon
      this.$forceUpdate()
      this.$refs.iconsPopover.showPopper = false
    },
    submitRole () {
      if (this.menuData.id) {
        editMenuInfo(this.menuData).then((response) => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.$emit('loadMenuListInfo')
        })
      } else {
        addMenuInfo(this.menuData).then((response) => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$emit('refreshRoleInfo')
        }).catch((error) => {
          console.log(error)
        })
      }
      this.closeDialog()
    }
  }
}
</script>

<style lang="scss" scoped>
.icon-item {
  margin: 20px;
  height: 50px;
  text-align: center;
  width: 50px;
  float: left;
  font-size: 30px;
  color: #24292e;
  cursor: pointer;
}
</style>
