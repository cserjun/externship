import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import ArticleList from '@/components/ArticleList'
import CateMana from '@/components/CateMana'
import DataCharts from '@/components/DataCharts'
import PostArticle from '@/components/PostArticle'
import UserMana from '@/components/UserMana'
import BlogDetail from '@/components/BlogDetail'
import AskQuestion from '@/components/AskQuestion'
import NotificationList from '@/components/NotificationList'
import PostNotification from '@/components/PostNotification'
import Test from '@/components/Test'
//import Home.bak from '@/components/Home.bak'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, {
      path: '/home',
      name: '',
      component: Home,
      hidden: true,
    }, 
    // {
    //   path: '/home.bak',
    //   name: '',
    //   component: Home.bak,
    //   hidden: true
    // }, 
    // {
    //   path: '/home',
    //   component: Home,
    //   name: '测试',
    //   children: [
    //     {
    //       path: '/test',
    //       name: '测试',
    //       component: CateMana
    //     }
    //   ]
    // },
    {
      path: '/home',
      component: Home,
      name: '文章管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/articleList',
          name: '文章列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发表文章',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '文章详情',
          component: BlogDetail,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑博客',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '用户管理',
      iconCls: 'fa fa-user-o',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        },
        {
          path: '/test',
          iconCls: 'fa fa-question-circle-o',
          name: '测试',
          component: Test
        },
      ]
    }, {
      path: '/home',
      component: Home,
      name: '模块管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '模块管理',
          component: CateMana
        }
      ]
    }, 
    {
      path: '/home',
      component: Home,
      iconCls: 'fa fa-question',
      name: '问题管理',
      children: [
        {
          path: '/question',
          iconCls: 'fa fa-question-circle-o',
          name: '问题管理',
          component: AskQuestion
        }
       
      ]
    }, 
    {
      path: '/home',
      component: Home,
      name: '公告管理',
      iconCls: 'fa fa-bell',
      children: [
        
          {
            path: '/NotificationList',
            name: '公告列表',
            component: NotificationList,
            meta: {
              keepAlive: true
            }
          }, {
            path: '/postNotification',
            name: '发表公告',
            component: PostNotification,
            meta: {
              keepAlive: false
            }
          }, 
        
      ]
    },
    {
      path: '/home',
      component: Home,
      //name: '数据统计',
     // iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/charts',
          //iconCls: 'fa fa-bar-chart',
          //name: '数据统计',
          component: DataCharts,
          meta: {
            keepAlive: true
          }
        }
      ]
    },
    ]
})
