<template>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="pageList.pageNum"
    :page-sizes="pageSizes"
    :page-size="pageList.pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pageList.total">
  </el-pagination>
</template>

<script>
    export default {
      name: 'pageBuild',
      props: ['pageList'],
      data() {
          return{
            pageSizes: [5, 10, 20, 50, 100]
          }
      },
      created() {
        if(this.pageList.pageSizes){
          this.pageSizes = this.pageList.pageSizes;
        }
      },
      methods: {
        /**
         * 获取 每页数量
         */
        handleSizeChange(val) {
            this.pageList.pageSize = val;
            this.$emit('pagesearch', this.pageList.pageNum, val);
        },

        /**
         * 获取 当前页
         */
        handleCurrentChange(val) {
            this.pageList.pageNum = val;
            this.$emit('pagesearch', val, this.pageList.pageSize);
        }
      }
    }
</script>
