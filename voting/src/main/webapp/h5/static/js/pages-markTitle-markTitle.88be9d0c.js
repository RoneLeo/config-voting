(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-markTitle-markTitle"],{"0356":function(t,i,a){var e=a("0bf4");"string"===typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);var s=a("4f06").default;s("37614f2c",e,!0,{sourceMap:!1,shadowMode:!1})},"0712":function(t,i,a){"use strict";var e=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("v-uni-view",{staticClass:"page"},[a("login"),a("cu-custom",{directives:[{name:"show",rawName:"v-show",value:t.isBack,expression:"isBack"}],attrs:{bgColor:"bg-blue",isBack:t.isBack}},[a("template",{staticStyle:{width:"calc(100% - 100px)"},attrs:{slot:"content"},slot:"content"},[t._v(t._s(t.activity&&t.activity.bt))])],2),a("v-uni-view",{staticClass:"vote-wrapper padding"},[a("v-uni-view",{staticClass:"vote-tt title",staticStyle:{"font-size":"18px"}},[t._v(t._s(t.activity&&t.activity.bt))]),a("v-uni-view",{staticClass:"vote-tt",staticStyle:{"margin-top":"40upx"}},[t._v("考评对象："),a("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s(t.obj.dx))])],1),a("v-uni-view",{staticClass:"vote-tt"},[t._v("活动状态："),a("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s("0"==t.activity.sffb?"尚未发布":"1"==t.activity.sffb?"已发布":"活动已结束"))])],1),a("v-uni-view",{staticClass:"vote-tt"},[t._v("活动期限："),a("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s(t.activity.kssj&&t.activity.kssj.substring(0,11))+" 至 "+t._s(t.activity.kssj&&t.activity.kssj.substring(0,11)))])],1),a("v-uni-view",{staticClass:"vote-tt"},[t._v("活动说明："),a("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s(t.activity.nr))])],1),a("v-uni-view",{staticClass:"padding",staticStyle:{"margin-top":"40upx"}},[a("v-uni-view",{staticClass:"mark-item"},[a("v-uni-text",{staticClass:"mark-name"},[t._v("考评内容")]),a("v-uni-text",{staticClass:"mark-status"},[t._v("打分区间")]),a("v-uni-text",{staticClass:"mark-input"},[t._v("打分")])],1),t._l(t.activity.scoreoblist,function(i,e){return a("v-uni-view",{key:e,staticClass:"mark-item"},[a("v-uni-text",{staticClass:"mark-name"},[t._v(t._s(i.mc))]),a("v-uni-view",{staticClass:"mark-status"},[a("v-uni-text",{staticClass:"mark-num"},[t._v(t._s(i.zdf))]),a("v-uni-view",{staticClass:"mark-range"},[a("Range",{attrs:{min:t.rangeValues[e][0],width:180,max:t.rangeValues[e][1],"data-index":e},on:{rangechange:function(i){i=t.$handleEvent(i),t.onRangeChange(i)}},model:{value:i.val,callback:function(a){t.$set(i,"val",a)},expression:"item.val"}})],1),a("v-uni-text",{staticClass:"mark-num"},[t._v(t._s(i.zgf))])],1),a("v-uni-text",{staticClass:"mark-input"},[t._v(t._s(t.scores[e]))])],1)})],2),a("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"1"==t.activity.sffb&&t.curTime>t.strTime&&t.curTime<t.endTime,expression:"activity.sffb=='1' && curTime > strTime && curTime < endTime"}],staticStyle:{"margin-top":"50upx","text-align":"center"}},[a("v-uni-button",{staticClass:"cu-btn bg-blue shadow-blur",on:{click:function(i){i=t.$handleEvent(i),t.saveRes(i)}}},[t._v("保存打分结果")])],1)],1)],1)},s=[];a.d(i,"a",function(){return e}),a.d(i,"b",function(){return s})},"0bf4":function(t,i,a){i=t.exports=a("2350")(!1),i.push([t.i,'@charset "UTF-8";\n/**\r\n * 这里是uni-app内置的常用样式变量\r\n *\r\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\r\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\r\n *\r\n */\n/**\r\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\r\n *\r\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\r\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.vote-wrapper[data-v-57bb3d89]{padding:20px %?30?%}.vote-wrapper .vote-tt[data-v-57bb3d89]{font-size:14px;color:#333;line-height:2}.vote-wrapper .vote-tt .vote-subtt[data-v-57bb3d89]{color:#666}.mark-item[data-v-57bb3d89]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-flex-wrap:nowrap;-ms-flex-wrap:nowrap;flex-wrap:nowrap;-webkit-justify-content:space-around;-ms-flex-pack:distribute;justify-content:space-around;margin-bottom:%?20?%;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}.mark-item .mark-input[data-v-57bb3d89],.mark-item .mark-name[data-v-57bb3d89],.mark-item .mark-status[data-v-57bb3d89]{text-align:center}.mark-item .mark-name[data-v-57bb3d89]{width:%?230?%}.mark-item .mark-status[data-v-57bb3d89]{width:%?320?%;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-justify-content:space-around;-ms-flex-pack:distribute;justify-content:space-around;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}.mark-item .mark-status .mark-num[data-v-57bb3d89]{width:%?50?%}.mark-item .mark-status .mark-range[data-v-57bb3d89]{width:%?180?%}.mark-item .mark-input[data-v-57bb3d89]{width:%?130?%}',""])},"2fcf":function(t,i,a){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var e={data:function(){return{hdid:null,obid:null,isBack:!1,rangeValues:[],slideMin:30,slideMax:60,scores:[],activity:{},obj:{},curTime:"",strTime:"",endTime:"",data:[{name:"专业能力专业能力专业能力",min:0,max:100},{name:"协调能力",min:10,max:100},{name:"管理能力",min:40,max:100},{name:"应变能力",min:60,max:100}]}},mounted:function(){},onLoad:function(t){this.hdid=t.hdid,this.obid=t.obid,this.user=this.getGlobalUser()||{},this.hdid&&this.obid&&this.getData(),getCurrentPages().length>1&&(this.isBack=!0)},onShow:function(){this.user=this.getGlobalUser()||{},this.hdid&&this.obid&&this.getData()},methods:{getData:function(){var t=this;this.$api.post("/theme/findAllInfoById",{id:this.hdid}).then(function(i){200==i.resCode&&(t.activity=i.data,t.curTime=(new Date).getTime(),t.strTime=new Date(Date.parse(t.activity.kssj.replace(/-/g,"/"))).getTime(),t.endTime=new Date(Date.parse(t.activity.jssj.replace(/-/g,"/"))).getTime(),t.activity.scorelist.forEach(function(i){i.id!=t.obid||(t.obj=i)}),t.data=t.activity.scoreoblist,t.rangeValues=[],t.scores=[],t.data.forEach(function(i){t.rangeValues.push([i.zdf,i.zgf]),i.val=i.zdf,t.scores.push(i.zgf)}))})},saveRes:function(){var t=this;console.log(this.data);var i=this.data.map(function(i){return{df:i.val,oid:i.id,qid:t.obid,pid:t.user.id}});this.$api.post("/score/score",JSON.stringify(i)).then(function(t){200==t.resCode?(uni.showToast({title:"保存成功！",icon:"none"}),uni.navigateBack()):uni.showToast({title:"保存失败！",icon:"none"})}),console.log(i)},onRangeChange:function(t){this.scores=this.data.map(function(t){return t.val})},gotoMarkTitle:function(){uni.navigateTo({url:"../../pages/markTitle/markTitle"})}}};i.default=e},"709b":function(t,i,a){"use strict";a.r(i);var e=a("0712"),s=a("926f");for(var n in s)"default"!==n&&function(t){a.d(i,t,function(){return s[t]})}(n);a("d68d");var r=a("2877"),o=Object(r["a"])(s["default"],e["a"],e["b"],!1,null,"57bb3d89",null);i["default"]=o.exports},"926f":function(t,i,a){"use strict";a.r(i);var e=a("2fcf"),s=a.n(e);for(var n in e)"default"!==n&&function(t){a.d(i,t,function(){return e[t]})}(n);i["default"]=s.a},d68d:function(t,i,a){"use strict";var e=a("0356"),s=a.n(e);s.a}}]);