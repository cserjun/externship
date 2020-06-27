<template>
  <el-table
    :data="notifications"
    >
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="center" inline class="demo-table-expand">
          <el-form-item label="公告ID">
            <span>{{ props.row.id}}</span>
          </el-form-item>
          <el-form-item label="公告内容">
            <span>{{ props.row.notification}}</span>
          </el-form-item>
          <el-form-item label="实习单位">
            <span>{{props.row.upracticecompany}}</span>
          </el-form-item> 
          <el-form-item label="发布时间">
            <span>{{ props.row.date | formatDateTime}}</span>
          </el-form-item>
        </el-form>
       </template> 
    </el-table-column>

  <el-table-column label="操作" align="left" v-if="isAdmin">
          <template slot-scope="scope">
            <!-- <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑
            </el-button> -->
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              
              >删除
            </el-button>
          </template>
        </el-table-column>
   
    
    <el-table-column
      label="公告 ID"
      prop="id">
    </el-table-column>
    <el-table-column
      label="实习单位"
      prop="upracticecompany">
    </el-table-column>
    
    <el-table-column
      label="发布时间"> 
      <template slot-scope="prop">
    <span>{{prop.row.date| formatDateTime}}</span>
     </template>
    </el-table-column>
   
  </el-table>

</template>

<script>
 import {getRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  export default {

    mounted:function(){
      var _this = this;
        getRequest("/isAdmin").then(resp=> {
        if (resp.status == 200) {
          _this.isAdmin = resp.data;
        }
      })
     _this.refresh();
    },
    methods:{

    refresh(){
    let _this=this;
        getRequest("/notificationsList").then(resp=>{
        if(resp.status==200){
          _this.notifications=resp.data;
          }
        },resp=> {
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
          _this.loading = false;
        }
        );
      },
  //  handleEdit(index, row){
  //       var _this = this;
  //       this.$prompt('请输入新公告内容', '编辑', {
  //         confirmButtonText: '更新',
  //         inputValue: row.question,
  //         cancelButtonText: '取消'
  //       }).then(({value}) => {
  //         //value就是输入值
  //         if (value == null || value.length == 0) {
  //           _this.$message({
  //             type: 'info',
  //             message: '数据不能为空!'
  //           });
  //         } else {
  //           _this.loading = true;
  //           putRequest("/question/", {id: row.id, question: value}).then(resp=> {
  //             var json = resp.data;
  //             _this.$message({
  //               type: json.status,
  //               message: json.msg
  //             });
  //             _this.refresh();
  //           }, resp=> {
  //             if (resp.response.status == 403) {
  //               _this.$message({
  //                 type: 'error',
  //                 message: resp.response.data
  //               });
  //             }
  //             _this.loading = false;
  //           });
  //         }
  //       });
  //     },
      handleDelete(index, row){
        let _this = this;
        this.$confirm('确认删除 ID= '  +row.id+  '的公告吗', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.deleteCate(row.id);
        }).catch(() => {
          //取消
          _this.loading = false;
        });
      },
      deleteCate(ids){
        var _this = this; 
        this.loading = true;
        //删除
        deleteRequest("/notification/" + ids).then(resp=> {
          var json = resp.data;
          _this.$message({
            type: json.status,
            message: json.msg
          });
          _this.refresh();
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          } else if (resp.response.status == 500) {
            _this.$message({
              type: 'error',
              message: '服务器错误，删除失败!'
            });
          }
        })
      },
    },
    data() {
      return {
        // tableData: [{
        //   id: '12987122',
        //   name: '好滋好味鸡蛋仔',
        //   category: '江浙小吃、小吃零食',
        //   desc: '荷兰优质淡奶，奶香浓而不腻',
        //   address: '上海市普陀区真北路',
        //   shop: '王小虎夫妻店',
        //   shopId: '10333'
        // }, 
        // ],
        isAdmin:'',
        notifications:[{
            id:'',
            notification:'',
            date:'',
            upracticecompany:''
        }
            
        ],
      }
    }
  }
</script>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
