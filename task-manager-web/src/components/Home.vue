<template>
	<el-row class="panel m-w-1280">
		<el-col :span="24" class="panel-top">
			<el-col :span="4">
				<template v-if="logo_type == '1'">
					<img :src="img" class="logo">
				</template>
				<template v-else>
					<span class="p-l-20">{{title}}22</span>
				</template>
			</el-col>
			<el-col :span="16" class="ofv-hd">
				<!--<div class="fl p-l-20 p-r-20 top-menu" :class="{'top-active': menu.selected}" v-for="menu in topMenu" @click="switchTopMenu(menu)">{{menu.title}} 11</div>-->

				<el-menu theme="dark" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
					<el-menu-item index="1" @click="toPortal">主页</el-menu-item>
					<el-menu-item index="3" @click="toTask">
						任务
					</el-menu-item>
				</el-menu>
			</el-col>

			<el-col :span="4" class="pos-rel">
				<el-dropdown @command="handleMenu" class="user-menu">
                    <span class="el-dropdown-link c-gra" style="cursor: default">
                        {{userName}}&nbsp;&nbsp;<i class="fa fa-user" aria-hidden="true"></i>
                    </span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item command="changePwd">修改密码</el-dropdown-item>
						<el-dropdown-item command="logout">退出</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>


		<el-col :span="24" class="panel-center">
			<!--<el-col :span="4">-->
			<!--<aside class="w-180 ovf-hd" v-show="!showLeftMenu">
				<leftMenu :menuData="menuData" :menu="menu" ref="leftMenu"></leftMenu>
			</aside>-->
			<!--<section class="panel-c-c" :class="{'hide-leftMenu': hasChildMenu}">-->
			<!--<div class="grid-content bg-purple-light">-->
			<!--<el-col :span="24">-->
			<transition name="fade" mode="out-in" appear>
				<router-view v-loading="showLoading"></router-view>
			</transition>
			<!--</el-col>-->
			<!--</div>-->
			<!--</section>-->
		</el-col>

		<changePwd ref="changePwd"></changePwd>

	</el-row>
</template>

<script>
    import leftMenu from './Common/leftMenu.vue'
    import changePwd from './Account/changePwd.vue'
    import http from '../assets/js/http'

    export default {
        components: {
            leftMenu,
            changePwd
        },
        data() {
            return {
                // showLoading: true,
                activeIndex: '1',
                userName: '',
                topMenu: [],
                childMenu: [],
                menuData: [],
                hasChildMenu: false,
                menu: null,
                module: null,
                img: '',
                title: '',
                logo_type: null
            }
        },
        methods: {
            logout() {
                this.$confirm('确认退出吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then(() => {
                    _g.openGlobalLoading()
                    let data = {
                        authkey: Lockr.get('authKey'),
                        sessionId: Lockr.get('sessionId')
                    }
                    this.apiPost('admin/logout', data).then((res) => {
                        _g.closeGlobalLoading()
                        this.handelResponse(res, (data) => {
                            Lockr.rm('menus')
                            Lockr.rm('authKey')
                            Lockr.rm('rememberKey')
                            Lockr.rm('authList')
                            Lockr.rm('userInfo')
                            Lockr.rm('sessionId')
                            Cookies.remove('rememberPwd')
                            _g.toastMsg('success', '退出成功')
                            setTimeout(() => {
                                router.replace('/')
                            }, 1500)
                        })
                    })
                }).catch(() => {

                })
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath)
            },
            toPortal() {
                router.push('/home/portal/homeNode')
            },
            toTask() {
                router.push('/home/task/list')
            },
            handleMenu(val) {
                switch (val) {
                    case 'logout':
                        this.logout()
                        break
                    case 'changePwd':
                        this.changePwd()
                        break
                }
            },
            changePwd() {
                this.$refs.changePwd.open()
            },
            getTitleAndLogo() {
                /*this.apiPost('admin/configs').then((res) => {
                    console.log(22222)
                    console.log(res)
                  this.handelResponse(res, (data) => {
                    document.title = data.SYSTEM_NAME
                    this.logo_type = data.LOGO_TYPE
                    this.title = data.SYSTEM_NAME
                    this.img = window.HOST + data.SYSTEM_LOGO
                  })
                })*/
            },
            getUsername() {
                this.userName = Lockr.get('userInfo').userName
            }
        },
        created() {
            this.getTitleAndLogo()
            let authKey = Lockr.get('authKey')
            let sessionId = Lockr.get('sessionId')
            if (!authKey || !sessionId) {
                _g.toastMsg('warning', '您尚未登录')
                setTimeout(() => {
                    router.replace('/')
                }, 1500)
                return
            }
            this.getUsername()
            let menus = Lockr.get('menus')
            this.menu = this.$route.meta.menu
            this.module = this.$route.meta.module
            this.topMenu = menus
            _(menus).forEach((res) => {
                if (res.module == this.module) {
                    this.menuData = res.child
                    res.selected = true
                } else {
                    res.selected = false
                }
            })
        },
        computed: {
            routerShow() {
                return store.state.routerShow
            },
            showLeftMenu() {
                this.hasChildMenu = store.state.showLeftMenu
                return store.state.showLeftMenu
            }
        },
        watch: {
            '$route' (to, from) {
                _(this.topMenu).forEach((res) => {
                    if (res.module == to.meta.module) {
                        res.selected = true
                        if (!to.meta.hideLeft) {
                            this.menu = to.meta.menu
                            this.menuData = res.child
                        }
                    } else {
                        res.selected = false
                    }
                })
            }
        },
        mixins: [http]
    }
</script>
<style>
	.fade-enter-active,
	.fade-leave-active {
		transition: opacity .5s
	}

	.fade-enter,
	.fade-leave-active {
		opacity: 0
	}

	.panel {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
	}

	.panel-top {
		height: 60px;
		line-height: 60px;
		background: #1F2D3D;
		color: #c0ccda;
	}

	.panel-center {
		background: #324057;
		position: absolute;
		top: 60px;
		bottom: 0px;
		overflow: hidden;
	}

	.panel-c-c {
		background: #f1f2f7;
		position: absolute;
		right: 0px;
		top: 0px;
		bottom: 0px;
		left: 180px;
		overflow-y: scroll;
		padding: 20px;
	}

	.logout {
		background: url(../assets/images/logout_36.png);
		background-size: contain;
		width: 20px;
		height: 20px;
		float: left;
	}

	.logo {
		width: 150px;
		float: left;
		margin: 10px 10px 10px 18px;
	}

	.tip-logout {
		float: right;
		margin-right: 20px;
		padding-top: 5px;
		cursor: pointer;
	}

	.admin {
		color: #c0ccda;
		text-align: center;
	}
	.hide-leftMenu {
		left: 0px;
	}
</style>
