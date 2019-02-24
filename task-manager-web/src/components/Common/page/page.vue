<template>
  <panel name="分页">
    <div>
      pageData初始化数据
      <br />
      参数：pageSizes 默认为 [5, 10, 20, 50, 100] ，按需求判断是否添加
    </div>
    <page-build :page-list="pageData" @pagesearch="init"></page-build>
  </panel>
</template>

<script>
    import panel from './panel.vue'
    import pageBuild from './pageBuild.vue'
  export default {
      components:{
          panel,
          pageBuild,
      },
    data(){
        return{
          pageData: {
            list: [],
              pageNum: 1,
            pageSize: 10,
              total: 0,
            totalPage: 0,
            pageSizes: [100,200,300,400]
          },
        }
    },
    methods:{
      init(number,size){
        console.log(number,size);
      }
    }
  }
</script>
