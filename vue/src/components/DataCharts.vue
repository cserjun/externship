


<template>
<div>
   	<section>
		<el-row :gutter="20" style="padding:10px;">
	    <el-col :span="6">
	    <el-card  style="height:130px;">
  	    	<div>
   	    	 <i class="el-icon-user"></i>  <span>管理人数</span>
 	    	 </div>
  	    	<div id="sitecount" style="height:200px;">
            <br/>
  	     <div>
           <i class="el-icon-user"></i> 
            <span style="font-family:Arial;font-size:30px;">{{userCounts}}</span><br/>
            <!-- <span style="font-family:PingFang SC;font-size:14px;">同比上涨100%</span><br/> -->
            
         </div>
          </div>
       </el-card>
	    </el-col>
	    <el-col :span="6">
	    <el-card  style="height:130px;">
  	    	<div>
   	    	 <span>技术周报总数</span>
 	    	 </div>
  	    	<div id="sitecount" style="height:200px;">
            <br/>
  	     <div>
            <span style="font-family:Arial;font-size:30px;">{{techCounts}}</span><br/>
            <!-- <span style="font-family:PingFang SC;font-size:14px;">同比上涨100%</span><br/> -->
            
         </div>
          </div>
       </el-card>
	    </el-col>
	    <el-col :span="6">
	    <el-card  style="height:130px;">
  	    	<div>
   	    	 <span>身体状况总数</span>
 	    	 </div>
  	    	<div id="sitecount" style="height:200px;">
            <br/>
  	     <div>
            <span style="font-family:Arial;font-size:30px;">{{phyCounts}}</span><br/>
            <!-- <span style="font-family:PingFang SC;font-size:14px;">同比上涨100%</span><br/> -->
           
         </div>
          </div>
         
  	     
       </el-card>
	    </el-col>
	    <el-col :span="6">
	    <el-card  style="height:130px;">
  	    	<div>
   	    	 <span>自我评价总数</span>
 	    	 </div>
  	    	<div id="customer" style="height:200px;">
            
   	    	 <br/>
  	     <div>
            <span style="font-family:Arial;font-size:30px;">{{evalCounts}}</span><br/>
            <!-- <span style="font-family:PingFang SC;font-size:14px;">同比上涨100%</span><br/> -->
           
         </div>
 	    	 </div>
       </el-card>
	    </el-col>
	</el-row>

	<el-row :gutter="20" style="padding:10px;">
	    <el-col :span="24">
	    <el-card class="box-card" style="height:150px;" align="left">
  	    	<el-tabs v-model="activeName">
            <el-tab-pane label="提示信息" name="first">
            </el-tab-pane>
          </el-tabs>
          <i class="el-icon-arrow-right"></i>
          <span >您还有<span> {{quesCounts}}</span>个问题没有解决,如已解决请忽略此消息</span>
       </el-card>
	    </el-col>
	    </el-row>

	    <el-row :gutter="20" style="padding:10px;">
	    <el-col :span="12">
	    <el-card class="box-card" style="height:480px;">
  	    	<div slot="header" class="clearfix">
   	    	 <span>文章查看情况</span>
   	    	 <!-- <el-button style="float: right; padding: 3px 0" type="text">查看</el-button> -->
 	    	 </div>
  	    	<div  class="text item">
   	    	 <chart ref="dschart" :options="polar" style="margin-top: 15px"></chart>
 	    	 </div>
       </el-card>
	    </el-col>
	    <el-col :span="12">
	    <el-card class="box-card" style="height:480px;">
  	    	<div slot="header" class="clearfix">
   	    	 <span>任务情况</span>
   	    	 <!-- <el-button style="float: right; padding: 3px 0" type="text">查看</el-button> -->
 	    	 </div>
  	    	<div  class="text item">
   	    	 <chart ref="achart" :options="weeksnum" style="margin-top: 15px"></chart>
 	    	 </div>
       </el-card>
	    </el-col>
	    </el-row>
	</section>
          
   
  <!-- <div
    style="display: flex;height: 500px;width: 100%;align-items: center;justify-content:;">
    <chart ref="dschart" :options="polar" style="margin-top: 20px"></chart>
    <chart ref="achart" :options="option" style="margin-top: 20px"></chart>
  </div> -->
  </div>
</template>

<style>
</style>
<script>
  import ECharts from 'vue-echarts/components/ECharts.vue'
  import 'echarts/lib/chart/line'
  import 'echarts/lib/component/tooltip'

  import 'echarts/lib/component/polar'

  import 'echarts/lib/component/legend'
  import 'echarts/lib/component/title'
  import 'echarts/theme/dark'
  import 'echarts/lib/chart/bar'

  import {getRequest} from '../utils/api'
  
  export default{
    components: {
      'chart': ECharts
    },
    methods:{
        getUserCounts(){
          var _this = this;
           getRequest("/userCount").then(resp=> {
        if (resp.status == 200) {
          _this.userCounts = resp.data;
      ;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
           },
        getTechCounts(){
          var _this = this;
           getRequest("/article/getTechCounts").then(resp=> {
        if (resp.status == 200) {
          _this.techCounts = resp.data;
      ;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
           },
            getPhyCounts(){
          var _this = this;
           getRequest("/article/getPhyCounts").then(resp=> {
        if (resp.status == 200) {
          _this.phyCounts = resp.data;
      ;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
           },
       getEvalCounts(){
          var _this = this;
           getRequest("/article/getEvalCounts").then(resp=> {
        if (resp.status == 200) {
          _this.evalCounts = resp.data;
      ;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
           },
             getQuesCounts(){
          var _this = this;
           getRequest("/question/getQuestionsCounts").then(resp=> {
        if (resp.status == 200) {
          _this.quesCounts = resp.data;
      ;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
           },
    },
    mounted: function () {
      var _this = this;
      getRequest("/article/dataStatistics").then(resp=> {
        if (resp.status == 200) {
          _this.$refs.dschart.options.xAxis.data = resp.data.categories;
          _this.$refs.dschart.options.series[0].data = resp.data.ds;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
        getRequest("/article/getDataArticles").then(resp=> {
        if (resp.status == 200) {
          // _this.$refs.weeksnum.options.xAxis.data = resp.data.categories;
          _this.$refs.achart.options.series[0].data = resp.data.gt;
          _this.$refs.achart.options.series[1].data = resp.data.gp;
          _this.$refs.achart.options.series[2].data = resp.data.ge;
        } else {
          _this.$message({type: 'error', message: '数据加载失败!'});
        }
      }, resp=> {
        _this.$message({type: 'error', message: '数据加载失败!'});
      });
      _this.getUserCounts();
      _this.getTechCounts();
      _this.getPhyCounts();
      _this.getEvalCounts();
      _this.getQuesCounts();
    },
    // methods: {},
    data: function () {
      return {
        userCounts:'',
        techCounts:'',
        phyCounts:'',
        evalCounts:'',
        quesCounts:'',
        weeksnum : {
    tooltip: {
       trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data: ['技术周报', '身体状况', '自我评价']
    },
    xAxis: [
        {
            type: 'category',
            data: ['第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: {},
    series: [
        {
            name: '技术周报',
            type: 'line',
              data: []
        },
        {
            name: '身体状况',
            type: 'line',
               data: []
        },
        {
            name: '自我评价',
            type: 'line',
           // yAxisIndex: 1,
            data: []
        }
    ]
},
        polar: {
          title: {
            text: ''
          },
          toolbox: {
            show: true,
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              dataView: {
                readOnly: false
              },
              magicType: {
                type: ['line', 'bar']
              },
              restore: {},
              saveAsImage: {}
            }
          },
          tooltip: {},
          legend: {
            data: ['pv']
          },
          xAxis: {
            data: []
          },
          yAxis: {},
          series: [{
            name: 'pv',
            type: 'line',
            data: []
          }],
          animationDuration: 3000
        }
      }
    }
  }
</script>
