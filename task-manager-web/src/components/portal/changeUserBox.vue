<template>
	<el-dialog :visible.sync="visible"  custom-class="w-400 h-300">
		<div>
			<span>{{userInfo.userName}}</span>
			<span>{{userInfo.sysStructure.name}}</span>
			<span>{{userInfo.sysPost.name}}</span>
		</div>
		<div class="ovf-auto">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="邮箱">
					<el-input v-model="userInfo.sysUserDetail.email"></el-input>
				</el-form-item>
				<el-form-item label="电话">
					<el-input v-model="userInfo.sysUserDetail.phone"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<div class="p-t-20">
			<el-button type="primary" class="fl m-l-20" :disabled="disable" @click="submit()">提交</el-button>
		</div>
	</el-dialog>
</template>
<style>

</style>
<script>
	import http from '../../assets/js/http'

	export default {
		props: {
			userInfo: {
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
				disable: false,

			}
		},
		methods: {
			submit() {
			    const _this = this;
                _this.disable = !_this.disable
                _this.apiPost('admin/user/updateUserDetail', _this.userInfo.sysUserDetail).then((res) => {
                    if(res.code === "200"){
                        _g.toastMsg('success', '修改成功')
                        _this.visible = false
					}else {
                        _this.disable = !_this.disable
                        _g.toastMsg('warning', '修改失败')
					}
				})
			}
		},
		created() {

		},
		mixins: [http]
	}
</script>
