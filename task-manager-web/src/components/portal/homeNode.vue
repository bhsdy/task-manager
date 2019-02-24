<template>
	<div class="fist-div">
		<el-row style="margin:10px;">
			<el-col>
				<el-col>
					<div class="grid-content bg-purple">
						<el-col :spon="12" style="margin:10px;">
								<span>高危任务</span>
							<template>
								<el-table
									:data="highTaskList"
									stripe
									style="width: 100%">
									<el-table-column
										prop="date"
										width="180">
									</el-table-column>
									<el-table-column
										prop="name"
										width="180">
									</el-table-column>
									<el-table-column
										prop="address">
									</el-table-column>
								</el-table>
							</template>

						</el-col>
					</div>
					<div class="grid-content bg-purple-light">
						<el-row>

						</el-row>
						<el-row>
							<el-upload
									class="avatar-uploader"
									:action="uploadUrl"
									:show-file-list="false"
									:on-success="handleAvatarSuccess"
									:before-upload="beforeAvatarUpload">
								<img v-if="imageUrl" :src="imageUrl" class="avatar">
								<i v-else class="el-icon-plus avatar-uploader-icon"></i>
							</el-upload>
							<span>{{userInfo.userName}}</span>
							<span>{{userInfo.sysUserDetail.phone}}</span>
						</el-row>
						<el-row>
							<template>
								<el-radio class="radio" v-model="radio" label="1">团队</el-radio>
								<span>{{userInfo.sysStructure.name}}</span>
							</template>
							<template>
								<el-radio class="radio" v-model="radio2" label="2">职务</el-radio>
									<span>{{userInfo.sysPost.name}}</span>
							</template>
						</el-row>
						<el-row>
							<template>
								<el-radio class="radio" v-model="radio3" label="3">Email</el-radio>
								<span>{{userInfo.sysUserDetail.email}}</span>
							</template>
						</el-row>
						<el-row>
							<el-button @click="changeButton" type="primary">修改</el-button>
						</el-row>
					</div>
				</el-col>
			</el-col>
		</el-row>

		<el-row style="margin:10px;">
			<el-col>
				<div class="bg-purple-dark">
					<el-col :spon="12" style="margin:10px;">
					<span>我的任务</span>
					</el-col>
				</div>
			</el-col>
		</el-row>
		<change-user-box
			:visible="changeUserBoxShow"
			:userInfo="userInfo"
		></change-user-box>
	</div>
</template>

<script>
  import btnGroup from '../Common/btn-group.vue'
  import http from '../../assets/js/http'
  import changeUserBox from './changeUserBox'

  export default {
		components:{
			changeUserBox,
			btnGroup
		},
    data() {
      return {
        imageUrl: '',
          uploadUrl:'http://localhost:8081/upload/image',


	  	changeUserBoxShow: false,
		highTaskList: [],

		radio3: '3',
		radio2: '2',
		radio: '1',
		userInfo:null,
        tableData: [],
        multipleSelection: []
      }
    },
    methods: {
        handleAvatarSuccess(res, file) {
            console.log(res)
            console.log(file.raw)
            this.imageUrl = URL.createObjectURL(file.raw);
            this.uploadUrl = 'http://localhost:8081/upload/image?imageUrl=' + this.imageUrl;
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },









			changeButton(){
				console.log(this.changeUserBoxShow)
				this.changeUserBoxShow = true
			},

    },
    created() {
      console.log(Lockr.get('userInfo'))
			this.userInfo = Lockr.get('userInfo');
        _g.closeGlobalLoading();
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
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}
	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}
	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 120px;
		height: 120px;
		line-height: 120px;
		text-align: center;
	}
	.avatar {
		width: 120px;
		height: 120px;
		display: block;
	}





	.bg-purple-light {
		width: 29%;
		float:right;
		background: #d3dce6;
	}
	.bg-purple {
		float:left;
		width: 70%;
		background: #d3dce6;
	}
	.bg-purple-dark {
		background: #d3dce6;
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
.task-list {

}
</style>
