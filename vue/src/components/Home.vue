<template>
  <el-container class="home_container">
     <el-header class="my-header">
       <el-row type="flex" justify="space-between">
        <!-- <div class="left"> -->
          <!-- <img src="../assets/logo2.png" alt /> -->
          <span class="left">校外实习管理平台</span>
            <el-col :span="13" align="left">
        <el-button class="btn-tool" title="刷新" @click="refresh">
            <i class="el-icon-refresh"></i>
        </el-button>       
          <button class="btn-tool" title="全屏" @click="Utils.fullScreen ">
            <i class="el-icon-rank"></i>
        </button>       
         <button class="btn-tool" title="便签" @click="dialogVisible = true" >
          <i class="el-icon-edit"></i>
        </button>
      </el-col>
        <!-- 用户 -->
        <!-- <div class="right"> -->
           <el-col :span="6" align="right">
             <span>{{Utils.todayDate() }}</span>
          <el-dropdown size="medium" @command="handleCommand">
            <span class="el-dropdown-link" style="font-size:20px;">
              <i class="el-icon-user-solid" style="font-size:20px; margin-right:10px"></i>
              {{currentUserName}}
            </span>
            <!-- <span class="el-dropdown-link">
            {{currentUserName}}<i class="el-icon-caret-bottom el-icon-right"></i>
          </span> -->
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-user" split-button="true"
              command="logout" divided>
                退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
           </el-col>
        <!-- </div> -->
      <el-dialog title="便签" width="400px"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible"
      @close="saveNotes">
      <el-input
        type="textarea"
        :rows="7"
        placeholder="请输入内容"
        v-model="notes">
      </el-input>
    </el-dialog>
       </el-row>
 
  </el-header>
    <!-- <el-header>
      <div class="home_title">校外实习管理平台</div>
      <div class="home_userinfoContainer">
        <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo">
    {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
            <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
            <el-dropdown-item command="MyHome">个人主页</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header> -->
    <el-container>
      <el-aside width="200px">
          <el-aside width="200px">
<!-- style="min-height: 100%;" -->
         
          <el-menu :default-active="$route.path"  style="background-color: #ECECEC"  router>
            <el-menu-item index="/charts" v-if="isAdmin">
              <i class="fa fa-bar-chart"></i>
              <span slot="title">系统分析</span>
            </el-menu-item>
            
            <el-submenu index="2">
              <template slot="title">
                <i class="fa fa-file-text-o"></i>
                <span>文章管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/articleList">文章列表</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group v-if="!isAdmin">
                <el-menu-item index="/postArticle">发表文章</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-menu-item index="/cateMana">
                <i class="fa fa-reorder"></i>
                <span slot="title">模块管理</span>
            </el-menu-item>

            <el-submenu index="4" v-if="isAdmin">
              <template slot="title">
                <i class="fa fa-user-o"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/user">卡片管理</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/test">人员管理</el-menu-item>
              </el-menu-item-group>

            </el-submenu>

              <el-menu-item index="/question">
              <i class="fa fa-question-circle-o"></i>
              <span slot="title">问题管理</span>
            </el-menu-item>

            <el-submenu index="5">
              <template slot="title">
                <i class="fa fa-bell"></i>
                <span>公告管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/NotificationList">公告列表</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group v-if="isAdmin"> 
                <el-menu-item index="/postNotification">发表公告</el-menu-item>
              </el-menu-item-group>
            </el-submenu>


          
          </el-menu>
        </el-aside>
        <!-- <el-menu
          default-active="0"
          class="el-menu-vertical-demo" style="background-color: #ECECEC" router>
          <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
            <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
              <template slot="title">
                <i :class="item.iconCls"></i>
                <span>{{item.name}}</span>
              </template>
              <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">
                {{child.name}}
              </el-menu-item>
            </el-submenu>
            <template  v-else>
              <el-menu-item :index="item.children[0].path" :key="item.children[0].path">
                <i :class="item.children[0].iconCls"></i>
                <span slot="title">{{item.children[0].name}}</span>
              </el-menu-item>
            </template>
               
          </template>
        </el-menu> -->
      </el-aside>
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/charts' }" v-if="isAdmin">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive" ></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>
  import {getRequest} from '../utils/api'
  export default{
   inject: ['reload'],
    methods: {
    refresh () {
      this.reload()
    },
    saveNotes () {
      localStorage.setItem('notes', this.notes)
    },
      handleCommand(command){
        var _this = this;
        if (command == 'logout') {
          this.$confirm('注销登录吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            getRequest("/logout")
            _this.currentUserName = '游客';
            _this.$router.replace({path: '/'});
          }, function () {
            //取消
          })
        }
      }
    },
    mounted: function () {
      // this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
      //   confirmButtonText: '确定',
      //   callback: action => {
      //   }
      // });
      var _this = this;
      getRequest("/currentUserName").then(function (msg) {
        _this.currentUserName = msg.data;
      }, function (msg) {
        _this.currentUserName = '游客';
      });
        
      getRequest("/isAdmin").then(resp=> {
        if (resp.status == 200) {
          _this.isAdmin = resp.data;
        }
      })
    },
     
    data(){
      return {
         currentUserName: '',
         dialogVisible: false,
        notes: localStorage.getItem('notes') || '便签中的内容会存储在本地，这样即便你关掉了浏览器，在下次打开时，依然会读取到上一次的记录。是个非常小巧实用的本地备忘录',
        //token:this.GLOBAL.currentUserName
        isAdmin:false,
      }
    }

  }
</script>
<style>
  .btn-tool {
    border: none;
    background: transparent;
    font-size: 18px;
    padding: 5px 10px;
    cursor: pointer;
    outline: none;
    text-align: left;
  }
.my-header {
  position: relative;
}
.el-header {
  background-color: rgb(245, 246, 246);
  line-height: 60px;
  box-shadow: 0 2px 12px 0 rgba(102, 100, 100, 0.1);
  border-bottom: 1px solid #dcdfe6;
}
.left {
  margin-left: 15px;
    float: left;
  width: 150px;
  height: 60px;
}
.right {
  /* float: right; */
  position: absolute;
  top: 0;
  right: 20px;
}

  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  /* .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  } */

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }
  .btn-fullscreen {
  position: absolute;
  right: 180px;
  top: 0;
  /* display: block; */
  /* float: right; */
}
</style>
