"use strict";(self["webpackChunkvue_edusystem"]=self["webpackChunkvue_edusystem"]||[]).push([[614],{6614:function(t,e,s){s.r(e),s.d(e,{default:function(){return l}});var a=function(){var t=this,e=t._self._c;return e("el-container",{staticStyle:{height:"500px",border:"1px solid #eee"}},[e("el-header",{staticClass:"el-icon-s-home",staticStyle:{"font-size":"40px","background-color":"#409EFF",color:"azure"}},[t._v(" 教考系统")]),e("el-footer",[e("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":t.activeIndex,mode:"horizontal"},on:{select:t.handleSelect}},[e("el-menu-item",{attrs:{index:"1"}},[e("router-link",{attrs:{to:"/student",tag:"span"}},[t._v("账号信息")])],1),e("el-menu-item",{attrs:{index:"2"}},[e("router-link",{attrs:{to:"/course",tag:"span"}},[t._v("课程表")])],1),e("el-menu-item",{attrs:{index:"3"}},[e("router-link",{attrs:{to:"/studentExam",tag:"span"}},[t._v("参加考试")])],1),e("el-menu-item",{attrs:{index:"4"}},[e("router-link",{attrs:{to:"/score",tag:"span"}},[t._v("考试成绩查询")])],1),e("el-menu-item",{attrs:{index:"4"}},[e("router-link",{attrs:{to:"/courseSelection",tag:"span"}},[t._v("选课")])],1),e("el-menu-item",{attrs:{index:"5"}},[e("router-link",{attrs:{to:"/studentLogin",tag:"span"}},[t._v("退出登录")])],1)],1)],1),e("el-main",{model:{value:t.studentData,callback:function(e){t.studentData=e},expression:"studentData"}},[e("span",[t._v("学生姓名："+t._s(t.studentData.studentName))]),e("br"),e("br"),e("span",[t._v("学生学号："+t._s(t.studentData.studentId))]),e("br"),e("br"),e("span",[t._v("学生性别："+t._s(t.studentData.studentSex))]),e("br"),e("br"),e("span",[t._v("专业："+t._s(t.studentData.major.majorName))]),e("br"),e("br"),e("span",[t._v("已修学分："+t._s(t.studentData.credit))]),e("br"),e("br"),e("span",[t._v("所处学期："+t._s(t.studentData.semester))]),e("br"),e("br")])],1)},n=[],r=s(4373),u={data(){return{studentData:[],studentID:""}},mounted(){const t=sessionStorage.getItem("studentID");this.studentID=t,this.getByID()},methods:{getByID(){r.A.get(`/api/student/id/${this.studentID}`).then((t=>{this.studentData=t.data.data}))}}},d=u,o=s(1656),i=(0,o.A)(d,a,n,!1,null,"3287d84c",null),l=i.exports}}]);
//# sourceMappingURL=614.7f5e6160.js.map