"use strict";(self["webpackChunkvue_edusystem"]=self["webpackChunkvue_edusystem"]||[]).push([[830],{9830:function(e,t,s){s.r(t),s.d(t,{default:function(){return d}});var a=function(){var e=this,t=e._self._c;return t("el-container",{staticStyle:{height:"500px",border:"1px solid #eee"}},[t("el-header",{staticClass:"el-icon-s-home",staticStyle:{"font-size":"40px","background-color":"#409EFF",color:"azure"}},[e._v(" 教考系统")]),t("el-footer",[t("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":e.activeIndex,mode:"horizontal"},on:{select:e.handleSelect}},[t("el-menu-item",{attrs:{index:"1"}},[t("router-link",{attrs:{to:"/student",tag:"span"}},[e._v("账号信息")])],1),t("el-menu-item",{attrs:{index:"2"}},[t("router-link",{attrs:{to:"/course",tag:"span"}},[e._v("课程表")])],1),t("el-menu-item",{attrs:{index:"3"}},[t("router-link",{attrs:{to:"/studentExam",tag:"span"}},[e._v("参加考试")])],1),t("el-menu-item",{attrs:{index:"4"}},[t("router-link",{attrs:{to:"/score",tag:"span"}},[e._v("考试成绩查询")])],1),t("el-menu-item",{attrs:{index:"4"}},[t("router-link",{attrs:{to:"/courseSelection",tag:"span"}},[e._v("选课")])],1)],1)],1),t("el-main",[t("div",[t("div",{staticStyle:{"margin-bottom":"20px"}},e._l(e.data.answerSheetDetails,(function(s){return t("el-card",{key:s.detailId},[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[t("span",[e._v("题目 "+e._s(s.moduleExercise.exerciseInModuleNum))])]),t("p",[t("strong",[e._v("题目描述:")]),e._v(" "+e._s(s.moduleExercise.exercise?.exerciseInfo))]),t("p",[t("strong",[e._v("题目选项:")]),e._v(" "+e._s(s.moduleExercise.exercise?.exerciseProblem))]),t("p",[t("strong",[e._v("学生答案:")]),e._v(" "+e._s(s.answer))]),t("p",[t("strong",[e._v("正确答案:")]),e._v(" "+e._s(s.moduleExercise.exercise?.exerciseAnswer))]),t("p",[t("strong",[e._v("评分:")]),e._v(" "+e._s(s.score)+" ")])])})),1)])])],1)},r=[],n=s(4373),i={props:{id:Number},data(){return{activeSheet:"1",data:null,studentID:""}},mounted(){const e=sessionStorage.getItem("studentID");this.studentID=e,this.getAll()},methods:{getAll(){n.A.get(`/api/AnswerSheet/examSchemeNum/${this.id}/studentId/${this.studentID}`).then((e=>{1==e.data.code&&null!=e.data.data&&(this.data=e.data.data)}))}}},l=i,o=s(1656),u=(0,o.A)(l,a,r,!1,null,"5fc5f9b5",null),d=u.exports}}]);
//# sourceMappingURL=830.df460409.js.map