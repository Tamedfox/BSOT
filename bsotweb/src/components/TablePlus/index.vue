<template>
  <div>
    <el-table :data="tableData" border class="table-plus" v-loading="listLoading">
      <!--多选框-->
      <el-table-column v-if="selection" type="selection" width="55"/>
      <!--文本数据渲染-->
      <template v-for="item in config.tableHead">
        <el-table-column :key="item.field" v-if="item.columnType === 'slot'" :prop="item.field" :label="item.label" :width="item.width" class-name="small-padding fixed-width">
          <!-- v-slot渲染 -->
          <template slot-scope="scope">
            <slot :name="item.slotName" :data="scope.row"></slot>
          </template>
        </el-table-column>
        <!--正常文本渲染-->
        <el-table-column v-else :key="item.field" :prop="item.field" :label="item.label" :width="item.width"/>
      </template>
    </el-table>
    <div class="table-footer">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.page.currPage"
          :page-sizes="this.paginationPageSizes"
          :page-size="this.page.pageSize"
          :layout="this.paginationLayout"
          :total="this.page.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TablePlus',
  props: {
    searchParams: {
      type: Object,
      default: () => {}
    },
    config: {
      type: Object,
      default: () => {}
    },
    selection: {
      type: Boolean,
      default: false
    },
    requestUrl: {
      type: Function,
      default: () => {}
    },
    paginationShow: {
      type: Boolean,
      default: true
    },
    paginationLayout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    paginationPageSizes: {
      type: Array,
      default: () => [10, 20, 30, 50]
    }
  },
  data() {
    return {
      tableData: [],
      page: {
        total: 0,
        currPage: 1,
        pageSize: 10
      },
      listLoading: true
    }
  },
  methods: {
    loadData(currPage, pageSize, params) {
      this.listLoading = true
      this.requestUrl(currPage, pageSize, params).then((response) => {
        this.tableData = response.data.rows
        this.page.total = response.data.total
        this.listLoading = false
      }).catch(err => {
        console.log(err)
        this.listLoading = false
      })
    },
    paramsLoadData() {
      this.page.currPage = 1
      this.loadData(this.page.currPage, this.page.pageSize, this.searchParams)
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.loadData(this.page.currPage, val, this.searchParams)
    },
    handleCurrentChange(val) {
      this.page.currPage = val
      this.loadData(val, this.page.pageSize, this.searchParams)
    }
  },
  mounted() {
    this.loadData(this.page.currPage, this.page.pageSize, null)
  }
}
</script>

<style lang="scss" scoped>
.table-footer {
  padding: 15px 0;
}
.table-plus {
  width: 100%;
  margin-top:20px;
}
</style>
