(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-mark-mark"],{"2b2e":function(t,i,e){"use strict";var s=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("v-uni-view",{staticClass:"page"},[e("login"),e("cu-custom",{directives:[{name:"show",rawName:"v-show",value:t.isBack,expression:"isBack"}],attrs:{bgColor:"bg-blue",isBack:t.isBack}},[e("template",{staticStyle:{width:"calc(100% - 100px)"},attrs:{slot:"content"},slot:"content"},[t._v(t._s(t.activity&&t.activity.bt))])],2),e("v-uni-view",{staticClass:"vote-wrapper padding"},[e("v-uni-view",{staticClass:"vote-tt title",staticStyle:{"font-size":"18px"}},[t._v(t._s(t.activity&&t.activity.bt)),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"1"==t.activity.tpzt,expression:"activity.tpzt == '1'"}],staticClass:"cu-tag line-orange radius",staticStyle:{"margin-left":"20upx"}},[t._v("已完成投票")])],1),e("v-uni-view",{staticClass:"vote-tt",staticStyle:{"margin-top":"40upx"}},[t._v("活动状态："),e("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s("0"==t.activity.sffb?"尚未发布":"1"==t.activity.sffb?"已发布":"活动已结束"))])],1),e("v-uni-view",{staticClass:"vote-tt"},[t._v("活动期限："),e("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s(t.activity.kssj&&t.activity.kssj.substring(0,11))+" 至 "+t._s(t.activity.kssj&&t.activity.kssj.substring(0,11)))])],1),e("v-uni-view",{staticClass:"vote-tt"},[t._v("活动说明："),e("v-uni-text",{staticClass:"vote-subtt"},[t._v(t._s(t.activity.nr))])],1),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"2"!=t.activity.sffb,expression:"activity.sffb != '2'"}],staticClass:"padding",staticStyle:{"margin-top":"40upx"}},[e("v-uni-view",{staticClass:"mark-item"},[e("v-uni-text",{staticClass:"mark-name"},[t._v("参评对象")]),e("v-uni-text",{staticClass:"mark-status"},[t._v("打分状态")]),e("v-uni-text",{staticClass:"mark-opt"},[t._v("操作")])],1),t._l(t.activity.scorelist,function(i,s){return e("v-uni-view",{staticClass:"mark-item"},[e("v-uni-text",{staticClass:"mark-name"},[t._v(t._s(i.dx))]),e("v-uni-text",{staticClass:"mark-status"},[t._v(t._s(i.zt?"已完成":"未完成"))]),e("v-uni-text",{staticClass:"mark-opt"},[e("v-uni-text",{directives:[{name:"show",rawName:"v-show",value:!i.zt,expression:"!obj.zt"}],staticClass:"cu-tag radius light bg-blue",on:{click:function(i){i=t.$handleEvent(i),t.gotoMarkTitle(s)}}},[t._v("打分")])],1)],1)}),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"1"==t.user.js&&"0"==t.activity.sffb,expression:"user.js == '1' && activity.sffb == '0'"}],staticClass:"padding-sm",staticStyle:{"text-align":"center"}},[e("v-uni-button",{staticClass:"cu-btn bg-blue",staticStyle:{"margin-top":"60upx"},on:{click:function(i){i=t.$handleEvent(i),t.createCode(i)}}},[t._v("发布本次活动并生成二维码")])],1),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"1"==t.user.js&&"1"==t.activity.sffb&&t.curTime>t.strTime&&t.curTime<t.endTime,expression:"user.js == '1' && activity.sffb == '1' && curTime > strTime && curTime < endTime"}],staticClass:"padding-sm",staticStyle:{"text-align":"center"}},[e("v-uni-button",{staticClass:"cu-btn bg-blue",on:{click:function(i){i=t.$handleEvent(i),t.updateFbzt(2)}}},[t._v("提前结束活动")])],1),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"1"==t.user.js&&"1"==t.activity.sffb,expression:"user.js == '1' && activity.sffb == '1'"}],staticClass:"padding-sm",staticStyle:{"text-align":"center"}},[e("v-uni-button",{staticClass:"cu-btn bg-blue",on:{click:function(i){i=t.$handleEvent(i),t.gotoCodePage(i)}}},[t._v("查看活动二维码")])],1)],2),e("v-uni-view",{directives:[{name:"show",rawName:"v-show",value:"2"==t.activity.sffb,expression:"activity.sffb == '2'"}],staticClass:"padding"},[t._v("活动已结束"),e("v-uni-image",{staticStyle:{width:"150upx",height:"150upx"},attrs:{src:"../../static/end.png",mode:""}}),e("v-uni-view",{staticClass:"padding-sm",staticStyle:{"text-align":"center"}},[e("v-uni-button",{staticClass:"cu-btn bg-blue",on:{click:function(i){i=t.$handleEvent(i),t.createCode(i)}}},[t._v("查看活动统计结果")])],1)],1)],1)],1)},a=[];e.d(i,"a",function(){return s}),e.d(i,"b",function(){return a})},"2cb2":function(t,i,e){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var s={data:function(){return{isBack:!1,hdid:null,user:{},activity:{},curTime:"",endTime:"",strTime:""}},mounted:function(){},computed:{logined:function(){return this.$store.state.logined}},watch:{logined:{handler:function(t,i){this.user=this.getGlobalUser()||{},t&&this.getData()},immediate:!0}},onShow:function(){(this.logined||this.hdid)&&this.getData()},onLoad:function(t){this.hdid=t.hdid,this.user=this.getGlobalUser()||{},getCurrentPages().length>1&&(this.isBack=!0),this.logined&&this.getData()},methods:{getData:function(){var t=this;this.$api.post("/theme/findAllInfoById",{id:this.hdid}).then(function(i){200==i.resCode&&(t.activity=i.data,t.curTime=(new Date).getTime(),t.strTime=new Date(Date.parse(t.activity.kssj.replace(/-/g,"/"))).getTime(),t.endTime=new Date(Date.parse(t.activity.jssj.replace(/-/g,"/"))).getTime())})},createCode:function(){var t=this;this.$api.post("/theme/updateFbzt",{id:this.hdid,fbzt:1}).then(function(i){200==i.resCode?(uni.showToast({title:"发布成功！",icon:"none"}),t.gotoCodePage()):uni.showToast({title:"操作失败！",icon:"none"})})},updateFbzt:function(t){this.$api.post("/theme/updateFbzt",{id:this.hdid,fbzt:t}).then(function(t){200==t.resCode?uni.showToast({title:"操作成功！",icon:"none"}):uni.showToast({title:"操作失败！",icon:"none"})})},gotoCodePage:function(){uni.navigateTo({url:"../../pages/code/code?lx=mark&id="+this.hdid+"&title="+this.activity.bt+"&kssj="+this.activity.kssj.substring(0,11)+"&jssj="+this.activity.jssj.substring(0,11)})},gotoMarkTitle:function(t){uni.navigateTo({url:"../../pages/markTitle/markTitle?hdid="+this.hdid+"&obid="+this.activity.scorelist[t].id})}}};i.default=s},"4dcb":function(t,i,e){i=t.exports=e("2350")(!1),i.push([t.i,'@charset "UTF-8";\n/**\r\n * 这里是uni-app内置的常用样式变量\r\n *\r\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\r\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\r\n *\r\n */\n/**\r\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\r\n *\r\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\r\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.vote-wrapper[data-v-435846a8]{padding:20px %?30?%}.vote-wrapper .vote-tt[data-v-435846a8]{font-size:14px;color:#333;line-height:2}.vote-wrapper .vote-tt .vote-subtt[data-v-435846a8]{color:#666}.mark-item[data-v-435846a8]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-flex-wrap:nowrap;-ms-flex-wrap:nowrap;flex-wrap:nowrap;-webkit-justify-content:space-around;-ms-flex-pack:distribute;justify-content:space-around;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;margin-bottom:%?20?%}.mark-item .mark-name[data-v-435846a8],.mark-item .mark-opt[data-v-435846a8],.mark-item .mark-status[data-v-435846a8]{width:%?200?%;text-align:center;-webkit-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1}',""])},a4cf:function(t,i,e){"use strict";var s=e("bb91"),a=e.n(s);a.a},afc4:function(t,i,e){"use strict";e.r(i);var s=e("2b2e"),a=e("c06e");for(var n in a)"default"!==n&&function(t){e.d(i,t,function(){return a[t]})}(n);e("a4cf");var c=e("2877"),o=Object(c["a"])(a["default"],s["a"],s["b"],!1,null,"435846a8",null);i["default"]=o.exports},bb91:function(t,i,e){var s=e("4dcb");"string"===typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);var a=e("4f06").default;a("9b071f52",s,!0,{sourceMap:!1,shadowMode:!1})},c06e:function(t,i,e){"use strict";e.r(i);var s=e("2cb2"),a=e.n(s);for(var n in s)"default"!==n&&function(t){e.d(i,t,function(){return s[t]})}(n);i["default"]=a.a}}]);