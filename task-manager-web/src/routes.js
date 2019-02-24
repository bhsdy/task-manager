import Login from './components/Account/Login.vue'
import refresh from './components/refresh.vue'
import Home from './components/Home.vue'
import usersList from './components/Administrative/personnel/users/list.vue'
import usersAdd from './components/Administrative/personnel/users/add.vue'
import usersEdit from './components/Administrative/personnel/users/edit.vue'

import taskList from './components/task/list.vue'
import addTaskBox from './components/task/addTaskBox.vue'
import homeNode from './components/portal/homeNode.vue'

/**
 * meta参数解析
 * hideLeft: 是否隐藏左侧菜单，单页菜单为true
 * module: 菜单所属模块
 * menu: 所属菜单，用于判断三级菜单是否显示高亮，如菜单列表、添加菜单、编辑菜单都是'menu'，用户列表、添加用户、编辑用户都是'user'，如此类推
 */

const routes = [
    { path: '/', component: Login, name: 'Login' },
    {
        path: '/home',
        component: Home,
        children: [
            { path: '/refresh', component: refresh, name: 'refresh' }
        ]
    },
    {
        path: '/home',
        component: Home,
        children: [
            { path: 'task/list', component: taskList, name: 'menuList', meta: { hideLeft: false, module: 'Administrative', menu: 'task' }},
            { path: 'task/addTaskBox', component: addTaskBox, name: 'addTaskBox', meta: { hideLeft: false, module: 'Administrative', menu: 'task' }},
        ]
    },
    {
        path: '/home',
        component: Home,
        children: [
            { path: 'portal/homeNode', component: homeNode, name: 'homeNode', meta: { hideLeft: false, module: 'Administrative', menu: 'portal' }},
        ]
    },
    {
        path: '/home',
        component: Home,
        children: [
            { path: 'users/list', component: usersList, name: 'usersList', meta: { hideLeft: false, module: 'Administrative', menu: 'users' }},
            { path: 'users/add', component: usersAdd, name: 'usersAdd', meta: { hideLeft: false, module: 'Administrative', menu: 'users' }},
            { path: 'users/edit/:id', component: usersEdit, name: 'usersEdit', meta: { hideLeft: false, module: 'Administrative', menu: 'users' }}
        ]
    }
]
export default routes
