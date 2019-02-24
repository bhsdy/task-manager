<template>
	<div class="table-item">
		<el-row style="margin:10px;">
			<el-col style="background: #d3dce6;border-radius: 4px;height: 50px;">
				<span>任务列表</span>
				<el-button style="float: right;align-items:center;" type="primary" @click="addTaskMethod">新建任务</el-button>
			</el-col>
			<el-col>

				<el-table
							:data="pageData.list"
							stripe
							style="width: 100%;height: 480px">
						<el-table-column
								prop="taskName"
								width="180"
								label="任务名称">
						</el-table-column>
						<el-table-column
								prop="taskDetail"
								width="180"
								label="详细任务内容">
						</el-table-column>
						<el-table-column
								prop="sysDict.name"
								width="180"
								label="优先级">
						</el-table-column>
						<el-table-column
								prop="accomplish"
								width="180"
								label="负责人">
						</el-table-column>
						<el-table-column
								prop="output"
								width="180"
								label="输出">
						</el-table-column>
						<el-table-column
								width="180"
								label="时间节点">
							<template slot-scope="scope">
								<div v-for="item in scope.row.frontTaskNodeList">
									<span>{{item.time | formatDate('YYYY-MM-DD')}}  {{item.log}}</span>
								</div>
							</template>
						</el-table-column>
						<el-table-column
								prop="progress"
								width="180"
								label="完成情况">
						</el-table-column>
						<el-table-column
								prop="remark"
								label="备注">
						</el-table-column>
						<el-table-column
								fixed="right"
								width="100"
								label="操作">
							<template scope="scope">
								<el-button @click="lookRow(scope.row)" type="text" size="small">查看</el-button>
								<el-button @click="editRow(scope.row)" type="text" size="small">编辑</el-button>
							</template>
						</el-table-column>
					</el-table>
				<div class="page-wrap">
					<page :page-list="pageData" @pagesearch="init"></page>
				</div>

			</el-col>
		</el-row>

		<add-task-box
			:visible="addTaskBoxShow"
			:data="addTaskBoxData"
			@close-box="addTaskBoxClose"
		></add-task-box>



	</div>
</template>

<script>
    import btnGroup from '../Common/btn-group.vue'
    import page from '../Common/page/pageBuild.vue'
    import http from '../../assets/js/http'
	import addTaskBox from './addTaskBox'

    export default {
        components:{
            btnGroup,
            addTaskBox,
            page
        },
        data() {
            return {
                pageData:{
                    list: [],
                    pageNum: 1,
                    pageSize: 10,
                    total: 1,
                    totalPage: 1
				},

                addTaskBoxData: null,
                addTaskBoxShow: false,

            }
        },
        methods: {
            init() {
                this.queryTaskList();
			},
            lookRow(row) {
                console.log(row)
                this.addTaskBoxShow = true
                this.addTaskBoxData = row
                this.addTaskBoxData.buttonShow = false
			},
			editRow(row) {
                this.addTaskBoxShow = true
                this.addTaskBoxData = row
                this.addTaskBoxData.buttonShow = true
			},
            addTaskBoxClose() {
                this.addTaskBoxData = null
                this.addTaskBoxShow = false
			},
            addTaskMethod() {
                this.addTaskBoxShow = true
            },
            queryTaskList() {
                let param = {
                    pageNum:this.pageData.pageNum,
                    pageSize:this.pageData.pageSize,
                }
                this.apiPost('api/frontTask/queryTaskList',param).then(res => {
                    if(res.data != null){
                        console.log("222222",res.data)
                        this.pageData.list = res.data.records
                        this.pageData.pageNum = res.data.current
                        this.pageData.pageSize= res.data.size
                        this.pageData.total= res.data.total
                        this.pageData.totalPage= res.data.pages
                    }
                    _g.closeGlobalLoading()
                })
            }
        },
        created() {
            this.init();
        },
        computed: {
            addShow() {
                return _g.getHasRule('rules-save')
            },
            editShow() {
                return _g.getHasRule('rules-update')
            },
            deleteShow() {
                return _g.getHasRule('rules-delete')
            }
        },
        mixins: [http]
    }
</script>
<style>
	/*分页*/
	.page-wrap {
		margin-top: 15px;
		padding-right: 15px;
		text-align: right;
	}
	.table-item {
		width: 100%;
		height: 100%;
	}


	el-row {
		background: #d3dce6;
	}
	.bg-purple-light {
		width: 29%;
		float:right;
	}
	.bg-purple {
		float:left;
		width: 70%;
		background: #d3dce6;
	}
	.bg-purple-dark {
	.parentCss
	height: 300px;
		border-radius: 4px;
	}
	.grid-content {
		height: 250px;
		border-radius: 4px;
	}
	.off-line {
		height: 200px;
	}
	.person-detail {

	}
	.parentCss {
		background: #d3dce6;
	}
</style>
