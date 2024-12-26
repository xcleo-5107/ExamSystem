"use strict";(self["webpackChunkvue_edusystem"]=self["webpackChunkvue_edusystem"]||[]).push([[580],{4580:function(e,t,a){a.r(t),a.d(t,{default:function(){return c}});var s=function(){var e=this,t=e._self._c;return t("el-container",{staticStyle:{border:"1px solid #eee"}},[t("el-header",{staticClass:"el-icon-s-home",staticStyle:{"font-size":"40px","background-color":"#409EFF",color:"azure"}},[e._v(" 教考系统")]),t("el-footer",[t("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":e.activeIndex,mode:"horizontal"},on:{select:e.handleSelect}},[t("el-menu-item",{attrs:{index:"1"}},[t("router-link",{attrs:{to:"/teacher",tag:"span"}},[e._v("账号信息")])],1),t("el-menu-item",{attrs:{index:"2"}},[t("router-link",{attrs:{to:"/questionBank",tag:"span"}},[e._v("题库管理")])],1),t("el-menu-item",{attrs:{index:"3"}},[t("router-link",{attrs:{to:"/examPaper",tag:"span"}},[e._v("试卷管理")])],1),t("el-menu-item",{attrs:{index:"4"}},[t("router-link",{attrs:{to:"/examSchedule",tag:"span"}},[e._v("考试安排")])],1),t("el-menu-item",{attrs:{index:"4"}},[t("router-link",{attrs:{to:"/examGrading",tag:"span"}},[e._v("阅卷")])],1),t("el-menu-item",{attrs:{index:"4"}},[t("router-link",{attrs:{to:"/class",tag:"span"}},[e._v("班级管理")])],1)],1)],1),t("el-main",[t("div",e._l(e.examPaper.modules,(function(a,s){return t("el-card",{key:s,staticClass:"module-card"},[t("h4",[e._v("模块 "+e._s(a.moduleNum)+" - 题型: "+e._s(e.getExerciseTypeName(a.exerciseType)))]),t("el-table",{staticStyle:{width:"100%"},attrs:{data:a.moduleExercises}},[t("el-table-column",{attrs:{prop:"exerciseInModuleNum",label:"题号",width:"80"}}),t("el-table-column",{attrs:{prop:"exercise.exerciseInfo",label:"题目描述"}}),t("el-table-column",{attrs:{prop:"exercise.exerciseProblem",label:"题目内容"}}),t("el-table-column",{attrs:{prop:"exercise.exerciseAnswer",label:"正确答案"}})],1)],1)})),1)])],1)},r=[],l=(a(8992),a(4520),a(2577),a(4373)),i={props:{id:Number},data(){return{formLabelWidth:"120px",exerciseTypes:[{typeNum:1,typeName:"选择题"},{typeNum:2,typeName:"填空题"},{typeNum:3,typeName:"简答题"}],examPaper:null}},methods:{handleClick(e){console.log(e)},onSubmit(){console.log("submit!")},getExerciseTypeName(e){const t=this.exerciseTypes.find((t=>t.typeNum===e));return t?t.typeName:"未知题型"},getAll(){l.A.get(`/api/exam/id/${this.id}`).then((e=>{1==e.data.code&&null!=e.data.data?(this.examPaper=e.data.data,this.open2()):this.open3()})).catch((e=>{console.error("Error fetching exam data:",e),this.$message({message:"获取试卷数据失败，请稍后再试。",type:"error"})}))},goBack(){this.$router.back()},deleteById(e){l.A.delete(`/api/exam/${e}`).then((t=>{1==t.code?(this.open2(),this.tableData=this.tableData.filter((t=>t.id!==e))):this.open3()}))},insertExam(){l.A.post("/api/paper",this.form2).then((e=>{1==e.code?this.open2():this.open3()}))},reset(){this.form="",this.dialogFormVisible=!1},open2(){this.$message({message:"恭喜你，这是一条成功消息",type:"success"})},open3(){this.$message({message:"警告哦，这是一条警告消息",type:"warning"})}},mounted(){this.getAll()}},n=i,o=a(1656),m=(0,o.A)(n,s,r,!1,null,"32478cc2",null),c=m.exports}}]);
//# sourceMappingURL=580.46b76c14.js.map