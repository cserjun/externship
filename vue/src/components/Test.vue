<template>
  <div class="people">
    <div class="topButton">
      <el-button size="mini" @click="showAddUser()" type="primary">添加人员</el-button>

      <el-button size="mini" type="warning" @click="showChange()">修改</el-button>

      <el-button size="mini" type="info" @click="mShow()">查看密码</el-button>

      <el-button size="mini" type="danger" @click="showDelete()">删除</el-button>
      <el-input
        placeholder="默认展示部分用户，可以通过用户名搜索用户..."
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="small">
      </el-input>
       <el-button type="primary" icon="el-icon-search" size="small" style="margin-left: 3px" @click="searchClick">搜索
      </el-button>
      <el-button size="mini" type="info" @click="handleDownload" >打印</el-button>
        <div >
      
     
    </div>
    <!-- <template slot="header">
          <el-input v-model="keywords" placeholder="输入关键字搜索" />
        </template> -->
    </div>
    <!-- 表格 -->
    <div class="list">
      <!-- <div class="search">
        <el-input v-model="search" placeholder="请输入内容"></el-input>
      </div>-->

      <el-table
        :data="users.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
        highlight-current-row
        border
        @current-change="handleCurrentsChange"
      >
        <el-table-column width="100" label="ID" prop="id"></el-table-column>

        <el-table-column width="140" align="left" label="学号" prop="usernumber"></el-table-column>

        <el-table-column width="140" align="left" label="密码" prop="password" v-if="flag4"></el-table-column>

        <el-table-column width="130" align="left" label="姓名" prop="nickname"></el-table-column>
        
        <el-table-column  width="120" align="left" label="专业" prop="major"></el-table-column>

        <el-table-column width="200" align="left" label="邮箱" prop="email"></el-table-column>

        <el-table-column width="180" align="left" label="注册时间" prop="regTime"  :formatter="formatDateTime">
        </el-table-column>
        <!-- :formatter="formatDateTime" -->
        <!-- <el-table-column label="部门" prop="deptName"></el-table-column> -->

        <el-table-column width="200" align="left" label="实习单位" prop="practicecompany"></el-table-column>

        <!-- <template slot="header">
          <el-input v-model="keywords" placeholder="输入关键字搜索" />
        </template> -->
      </el-table>

      <!-- 分页 -->
      <div class="page">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="users.length"
        ></el-pagination>
      </div>
    </div>

    <!-- 添加人员弹框 -->

    <div v-if="flag" class="addUser">
      <div class="title">
        <h2>添加人员</h2>
        <i class="el-icon-close" @click="clossAdd()"></i>
      </div>
      <div class="add">
        <!-- :rules="rules" -->
        <el-form ref="form" :model="addUser" label-width="80px" :rules="rules">
          <el-form-item label="学号" prop="usernumber">
            <el-input v-model="addUser.usernumber"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="nickname">
            <el-input v-model="addUser.nickname" ></el-input>
          </el-form-item>
          <el-form-item label="拼音" prop="username">
            <el-input v-model="addUser.username" placeholder="请输入名字拼音"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addUser.password"></el-input>
          </el-form-item>
         <el-form-item label="邮箱" prop="email">
            <el-input v-model="addUser.email"></el-input>
          </el-form-item>
           <el-form-item label="专业" prop="major">
            <el-input v-model="addUser.major"></el-input>
          </el-form-item>
          <el-form-item label="实习单位" prop="practicecompany">
            <el-input v-model="addUser.practicecompany"></el-input>
          </el-form-item>
          <div class="addButton">
            <el-form-item>
              <el-button type="primary" @click="addPeople()">提交</el-button>
              <el-button @click="addRest()">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
      </div>
    </div>
    <!-- 修改弹框 -->

    <div v-if="flag2" class="changeUser">
      <div class="title">
        <h2>修改人员</h2>
        <i class="el-icon-close" @click="clossChange()"></i>
      </div>

      <div class="change">
        <el-form ref="form" :model="changeList" label-width="80px">
           <el-form-item label="学号">
            <el-input v-model="changeList.usernumber"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="changeList.nickname"></el-input>
          </el-form-item>
            <!-- <el-form-item label="密码">
              <el-input v-model="changeList.password"></el-input>
            </el-form-item> -->
          <el-form-item label="专业">
            <el-input v-model="changeList.major"></el-input>
          </el-form-item>
          <el-form-item label="实习公司">
            <el-input v-model="changeList.practicecompany"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="changeList.email"></el-input>
          </el-form-item>
          <div class="addButton">
            <el-form-item>
              <el-button type="primary" @click="changeUser()">提交</el-button>
              <el-button @click="changeRest()">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
      </div>
    </div>

    <!-- 删除弹框 -->
    <div class="delete" v-if="flag3">
      <div class="title">
        <span>提示</span>
        <i class="el-icon-close" @click="closeDelete()"></i>
      </div>
      <div class="main">
        <i class="el-icon-warning"></i>
        <span>此操作将永久删除该数据，是否继续</span>
      </div>
      <div class="deleteButton">
        <el-button size="mini" @click="closeDelete()">取消</el-button>
        <el-button size="mini" type="danger" @click="deleteInfo()">确定</el-button>
      </div>
    </div>

    <div class="popContainer" v-if="flag"></div>
    <div class="popContainer" v-if="flag2"></div>
    <div class="popContainer" v-if="flag3"></div>
  </div>
</template>

<script>
import {getRequest} from '../utils/api'
import {putRequest} from '../utils/api'
import {deleteRequest} from '../utils/api'
import {postRequest} from '../utils/api'
 import {isNotNullORBlank} from '../utils/utils'
export default {
  data() {
     
  var checknumber = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('不能为空'));
        }
  };     
    return {
       
      users: [],
      flag: false,
      flag2: false,
      flag3: false,
      flag4:false,
      search: "",
      keywords:'',
      currentPage: 1, //初始页
      pagesize: 10,
      addUser: [
        {
          usernumber:'',
          nickname: "",
          username:"",
          password: "",
          email:"",
          major: "",
          practicecompany: "",
          //role:""
        }
      ],
      changeList: [
        {
          usernumber:'',
          nickname: "",
          username:"",
          //password: "",
          email:"",
          major: "",
          practicecompany: "",
        }
      ],
      // deleteList: [
      //   {
      //     userName: "",
      //     passWord: "",
      //     userTel: "",
      //     userSex: "",
      //     deptName: "",
      //     jobNum: ""
      //   }
      // ],
     
      // rules: {
      //  usernumber: [
      //     { required: true, message: "请输入账号", trigger: "blur"},
      //     { max: 10, message: "不能大于10个字符", trigger: "blur" },
      //      {validator:checknumber , trigger: "blur" }
      //   ],
      //   password: [
      //     { required: true, message: "请输入密码", trigger: "blur" },
      //     { max: 10, message: "不能大于10个字符", trigger: "blur" },
      //     {validator:checknumber , trigger: "blur" }
      //   ],
      //   practicecompany: [
      //     { required: true, message: "请输入实习单位", trigger: "blur",},
      //     { max: 10, message: "不能大于10个字符", trigger: "blur" },
      //     {validator:checknumber , trigger: "blur" }
      //   ],
      //   nickname: [
      //     { required: false, message: "请输入姓名", trigger: "blur" },
      //     { max: 10, message: "不能大于10个字符", trigger: "blur" },
      //     {validator:checknumber , trigger: "blur" }
      //   ],
      // }
    };
  },
  mounted:function ()  {
  
    this.loadUserList();
  },
  // inject: ["reload"],
  methods: {
       handleDownload() {
      // this.loading = true;
      console.log(this.users);
      require.ensure([], () => {
        const { export_json_to_excel } = require("@/vendor/Export2Excel");
        const tHeader = [
          "学号",
          "姓名",
          "姓名拼音",         
          "密码",
          "邮箱",
          "专业",
          "实习单位",
          "注册时间"
          
        ];
        const filterVal = [
          "usernumber",
          "nickname",
          "username",
          "password",
          "email",
          "major",
          "practicecompany",
           "regTime"
        ];
        const list = this.users;
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, "学生excel");
        // this.loading= false;
      });
    },
      formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
    },
    loadUserList(){
        var _this = this;
        getRequest("/admin/user?nickname="+this.keywords).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            _this.users = resp.data;
          } else {
            _this.$message({type: 'error', message: '数据加载失败!'});
          }
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
        });
      },
       searchClick(){
        this.loading = true;
        this.loadUserList();
      },
    showAddUser() {
      this.flag = !this.flag;
    },
    addPeople() {
        if (!(isNotNullORBlank(this.addUser.usernumber,this.addUser.nickname,this.addUser.practicecompany
        ,this.addUser.email
        ))) {
         //$message用于提示
         this.$message({type: 'error', message: '数据不能为空!'});
          return;
        }
      if(this.addUser.usernumber){
       var _this = this;
        _this.loading = true;
        //调用封装的axios
        postRequest("/reg", {
          usernumber:_this.addUser.usernumber,
          nickname: _this.addUser.nickname,
          username:_this.addUser.usernumber,
          password: _this.addUser.password,
          email:_this.addUser.email,
          major:_this.addUser.major,
          practicecompany: _this.addUser.practicecompany,
          //role:addUser.role
        }).then(resp=> {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.users.id = resp.data.msg;
            _this.$message("添加成功");
             this.flag = !this.flag;
            _this.loadUserList();
          }else{
             _this.$message({type: 'error', message: resp.data.msg});
          }
        },)
      }
    },
    changeUser() {
      let _this = this;
          putRequest("/admin/user", {
          usernumber:_this.changeList.usernumber,
          nickname: _this.changeList.nickname,
          username:_this.changeList.usernumber,
          //password: _this.addUser.password,
          email:_this.changeList.email,
          major:_this.changeList.major,
          practicecompany:_this.changeList.practicecompany,
        }).then(resp=> {
          //_this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
            //_this.article.id = resp.data.msg;
            this.$message("修改成功");
            this.flag2 = !this.flag2;
            //_this.loadUserList();
          }else{
            this.$message({type:'error',message:resp.data.msg});
          }
        }
        )
      // this.$axios
      //   .put("/user", {
      //     usernumber:_this.changeList.usernumber,
      //     nickname: _this.changeList.nickname,
      //     username:_this.changeList.usernumber,
      //     //password: _this.addUser.password,
      //     email:_this.changeList.email,
      //     major:_this.changeList.major,
      //     practicecompany:_this.changeList.practicecompany,
          
      //   })
      //   .then(res => {
      //     if (res.data.code === 200) {
      //       this.$message("修改成功");
      //       this.flag2 = !this.flag2;
      //       // this.flag2=!this.flag2
      //       // this.$router.go(0)
      //       this.reload();
      //     }else{
      //       this.$message("修改失败");
      //     }
      //   })
    },
    deleteInfo() {
        var _this = this;
          deleteRequest("/admin/user/" + _this.deleteList.id).then(resp=> {
            if (resp.status == 200 && resp.data.status == 'success') {
              _this.$message({type: 'success', message: '删除成功!'})
              this.flag3 = !this.flag3;
              _this.loadUserList();
              return;
            }
            _this.loading = false;
            _this.$message({type: 'error', message: '删除失败!'})
          }, resp=> {
            _this.loading = false;
            _this.$message({type: 'error', message: '删除失败!'})
          });
    },
    showDelete() {
      if (this.deleteList.nickname) {
        this.flag3 = !this.flag3;
      } else {
        this.$message("请选择一条数据");
      }
    },
    clossAdd() {
      this.flag = !this.flag;
      this.$message("操作已取消");
    },
    addRest() {
      this.addUser = [];
    },
    // 点击选中
    setCurrent(row) {
      this.$refs.singleTable.setCurrentRow(row);
    },
    handleCurrentsChange(val) {
      this.currentRow = val;
      this.changeList = val;
      this.deleteList = val;
      // this.changeList = []
    },
    handleSizeChange: function(size) {
      this.pagesize = size;
      console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      console.log(this.currentPage); //点击第几页
    },
    clossChange() {
      this.flag2 = !this.flag2;
      this.$message("操作已取消");
    },
    showChange() {
      if (this.changeList.nickname) {
        this.flag2 = !this.flag2;
      } else {
        this.$message("请选择一条数据");
      }
    },
    closeDelete() {
      this.flag3 = !this.flag3;
      this.$message("操作已取消");
    },
    changeRest() {
      this.changeList = [];
    },
    mShow(){
       this.flag4 = !this.flag4;
    }
  }
};
</script>

<style>
.topButton {
  width: 100%;
  height: 50px;
  line-height: 50px;
  padding-bottom: 20px;
  text-align:left
}
.el-table-column {
  text-align: center;
}
.el-submenu .el-menu-item:hover {
  background-color: rgb(232, 243, 255);
}
.list {
  position: relative;
}
.addUser {
  position: absolute;
  z-index: 1001;
  width: 400px;
  height: 600px;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.5);
}
.addUser .title {
  height: 50px;
  width: 100%;
  text-align: center;
  line-height: 30px;
  /* border-bottom: 1px solid #dcdfe6; */
  color: #409eff;
  text-shadow: 2px 2px 2px #ccc;
}
.addUser .title i {
  position: relative;
  top: -55px;
  right: -170px;
  color: black;
  font-size: 20px;
}
.addUser .add {
  margin-right: 40px;
  margin-top: 30px;
}
.addUser .addButton {
  margin-left: 10px;
  margin-top: 30px;
}
.addUser .addButton .el-button {
  width: 110px;
}
.addUser .el-select {
  width: 280px;
}
.changeUser {
  position: absolute;
  z-index: 1001;
  width: 400px;
  height: 500px;
  /* border:1px solid #dcdfe6; */
  left: 0;
  top: -50px;
  bottom: 0;
  right: 0;
  margin: auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.5);
}
.changeUser .title {
  height: 50px;
  width: 100%;
  text-align: center;
  line-height: 30px;
  /* border-bottom: 1px solid #dcdfe6; */
  color: #409eff;
  text-shadow: 2px 2px 2px #ccc;
}
.changeUser .title i {
  position: relative;
  top: -55px;
  right: -170px;
  color: black;
  font-size: 20px;
}
.changeUser .change {
  margin-right: 40px;
  margin-top: 30px;
}
.changeUser .addButton .el-button {
  width: 115px;
}
.changeUser .el-select {
  width: 280px;
}
.delete {
  position: absolute;
  z-index: 1001;
  width: 430px;
  height: 140px;
  border: 1px solid #dcdfe6;
  left: 0;
  top: -50px;
  bottom: 0;
  right: 0;
  margin: auto;
  background-color: #fff;
  border-radius: 10px;
}
.title {
  width: 100%;
  height: 45px;
  line-height: 45px;
}
.title span {
  float: left;
  margin-left: 20px;
}
.delete .title i {
  float: right;
  margin-top: 10px;
  margin-right: 10px;
}
.delete .main {
  height: 50px;
  width: 100%;
  line-height: 50px;
  margin-left: 50px;
}
.delete .main i {
  color: #e6a23c;
  font-size: 20px;
  margin-right: 10px;
}
.delete .main span {
  margin-bottom: 5px;
}
.delete .deleteButton {
  float: right;
  margin-right: 20px;
  margin-top: 7px;
}
.popContainer {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 500;
}
.page {
  position: absolute;
  right: 0;
  bottom: -50px;
}
.search {
  position: relative;
  top: -12px;
}
</style>