<template>
	<el-dialog :visible.sync="visible" custom-class="w-800" style="height: 100%" :before-close="closeBox">
		<div>
			<el-form :rules="rules" ref="form" :model="form" label-width="80px">
				<el-form-item label="任务名称" prop="taskName">
					<el-input v-model="form.taskName"></el-input>
				</el-form-item>
				<el-form-item label="优先级" prop="level">
						<el-select v-model="form.dictCode" clearable  placeholder="请选择">
							<el-option
									v-for="item in sysDictList"
									:label="item.name"
									:value="item.dictCode"
									>
							</el-option>
						</el-select>
				</el-form-item>
				<el-form-item label="任务详情">
					<el-input type="textarea" v-model="form.taskDetail"></el-input>
				</el-form-item>
				<el-form-item label="任务来源">
					<el-input v-model="form.source"></el-input>
				</el-form-item>
				<el-form-item label="负责人">
					<el-input v-model="form.accomplish"></el-input>
				</el-form-item>
				<el-form-item label="任务输出">
					<el-input type="textarea" v-model="form.output"></el-input>
				</el-form-item>

				<el-form-item label="时间节点">
					<el-table
							:data="form.frontTaskNodeList"
							stripe
							style="width: 100%;">
						<el-table-column
								prop="time"
								width="180"
								label="时间">
							<template slot-scope="scope">
								<el-date-picker
										v-model="scope.row.time"
										type="date"
										placeholder="选择日期"
										:picker-options="pickerOptions"
										style="width: 100%;"
										>
								</el-date-picker>
							</template>
						</el-table-column>
						<el-table-column
								prop="log"
								width="180"
								label="标识">
							<template slot-scope="scope">
								<el-input v-model="scope.row.log" style="width: 100%;"></el-input>
							</template>
						</el-table-column>
						<el-table-column
								prop="output"
								label="输出">
							<template slot-scope="scope">
								<el-input v-model="scope.row.output" style="width: 100%;"></el-input>
							</template>
						</el-table-column>
						<el-table-column
								label="操作">
							<template slot-scope="scope">
								<el-button type="danger" size="small" :disabled="disable" @click="deleteRow(scope)">删除</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" :disabled="disable" @click="addTaskNodeRow">添加时间</el-button>
				</el-form-item>
				<el-form-item label="完成情况">
					<el-input type="textarea" v-model="form.progress"></el-input>
				</el-form-item>
				<el-form-item label="备注">
					<el-input type="textarea" v-model="form.remark"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" :disabled="disable" @click="submit">提交任务</el-button>
				</el-form-item>
			</el-form>
		</div>
	</el-dialog>
</template>
<style>

</style>
<script>
	import http from '../../assets/js/http'

	export default {
		props: {
			data: {
				type: Object,
				default: {}
			},
            visible: {
                type: Boolean,
                default: false
            },
		},
		data() {
			return {
                sysDictList: [],


				disable: false,
                form: {
                    dictCode: '',
                    taskName: '',
                    source: '',
                    accomplish: '',
                    taskDetail: '',
                    input: '',
                    output: '',
                    progress: '',
                    remark: '',
                    frontTaskNodeList: [
						{
						    time: null,
							log: '',
							output: '',
						}
					],
                },
                pickerOptions: {
				},

                rules: {
                    taskName: [
                        { required: true, message: '请输入活动名称', trigger: 'blur' },
                    ],
                    level: [
                        { required: true, message: '请选择优先级', trigger: 'blur' },
                    ],
				}
			}
		},
		methods: {
            closeBox() {
                this.$emit('close-box', false);
			},
		    queryDictList(){
		        const _this = this;
		        let param = {
		            dictGroupCode: "L",
					status: "1",
				}
                _this.apiPost('admin/sysDict/queryList',param).then(response =>{
					if(response.code === "200"){
                        _this.sysDictList = response.data
                        _this.form.dictCode = "L10"
					}else {
                        _this.form.dictCode= ""
					}
				})
			},
            deleteRow(scope) {
                this.form.frontTaskNodeList.splice(scope.$index,1)
			},
            addTaskNodeRow() {
                this.form.frontTaskNodeList.push({
					time: null,
					log:'',
					output:''
				})
			},
			submit() {
		        const _this = this;
                _this.disable = !_this.disable
                _this.apiPost('api/frontTask/addTask', _this.form).then((res) => {
					if(res.code === "200"){
                        _g.toastMsg('success', '保存成功')
                        _this.disable = false
                        _this.$emit('close-box', false)
					}else {
                        _g.toastMsg('warning', '保存失败')
                        _this.disable = !_this.disable
					}
				})
			}
		},
		created() {

		},
		watch: {
            visible(vis){
                if(vis){
                    console.log(this.data)
                    if(this.data == null){
                        this.form = {}
                        this.form.frontTaskNodeList = []
                        this.form.frontTaskNodeList.push({
                            time: null,
                            log: '',
                            output: '',
                        })

                        this.disable = false  //显示按钮
					}else {
                        this.form = this.data
                        if(this.data.buttonShow){
                            this.disable = false	//隐藏按钮
                        }else {
                            this.disable = true	//显示按钮
                        }
                    }
                	this.queryDictList()
				}
			}
		},
		mixins: [http]
	}
</script>
