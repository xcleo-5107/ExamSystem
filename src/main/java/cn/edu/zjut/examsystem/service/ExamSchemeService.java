package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.Enum.ExamReviewModel;
import cn.edu.zjut.examsystem.Enum.ExamType;
import cn.edu.zjut.examsystem.dao.*;
import cn.edu.zjut.examsystem.po.*;
import jakarta.persistence.EntityManager;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamSchemeService implements ExamSchemeServiceImpl{
    @Autowired
    private ExamSchemeDao examSchemeDao;
    @Autowired
    private TaskListTeacherExerciseModelDao tlteDao;

    @Autowired
    private TaskListTeacherAnswerSheetModelDao tltasDao;

    @Autowired CourseDao courseDao;

    @Autowired
    private AnswerSheetDao answerSheetDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ExamDao examDao;

    @Autowired
    private ClazzService clazzService;

    @Override
    public List<PoExamScheme> findAllByStr(String str) {

        return examSchemeDao.findAllByStr("%"+str+"%");
    }

    @Override
    public List<PoExamScheme> findAll() {
        return examSchemeDao.findAll();
    }

    @Override
    public List<PoExamScheme> findAllByStudentId(int studentId) {
        PoStudent student = studentDao.findById(studentId).orElse(null);
        if(student == null) return null;

        List<PoClazz> clazzes = student.getClazzes();
        List<PoExamScheme> examSchemes = new ArrayList<>();

        for(PoClazz clazz:student.getClazzes())
        {
            String clazzNum = Integer.toString(clazz.getClassNum());

            List<PoExamScheme> examSchemesTemp = examSchemeDao.findAllByClassNum(clazzNum);

            for(PoExamScheme examScheme:examSchemesTemp)
            {
                if(!examSchemes.contains(examScheme)) examSchemes.add(examScheme);
            }
        }

        return examSchemes;
    }

    @Transactional
    @Override
    public Boolean alter(PoExamScheme examScheme) {
        examSchemeDao.save(examScheme);
        return true;
    }

    @Transactional
    @Override
    public Boolean add(PoExamScheme examScheme) {
        entityManager.persist(examScheme);
        return true;
    }
/*
    @Scheduled(fixedRate = 10000)
    @Transactional
    @Override
    public void autoUpdateScheme() {
        List<PoExamScheme> examSchemes = examSchemeDao.findAllBySchemeEndBeforeAndEndedIsFalse(new Timestamp(System.currentTimeMillis()));
        if(examSchemes == null || examSchemes.isEmpty()) return;

        for(PoExamScheme examScheme:examSchemes)
        {
            //根据答题表分配任务
            //先获取该试卷的所有答题表,再平均分给教师们
            if(examScheme.getExamReviewModel().getModelNum() == ExamReviewModel.TeacherAnswerSheet.getValue())
            {
                System.out.println("TeacherAnswerSheet");
                String[] teachers = examScheme.getTeacherNum().split(",");
                if(teachers.length == 0) return;

                List<PoAnswerSheet> answerSheets = answerSheetDao.findAllBySchemeNum(examScheme.getSchemeNum());
                if(answerSheets == null || answerSheets.isEmpty())
                {
                    System.out.println("该场考试没有答题表");
                    return;
                }

                int avg = answerSheets.size()/teachers.length;
                int remainder = answerSheets.size()%teachers.length;
                int currentTaskIndex = 0;

                //根据平均数分配题目模块
                for(int i=0;i<teachers.length;i++)
                {
                    int teacherNum = Integer.parseInt(teachers[i]);

                    for(int j=0;j<avg;j++)
                    {
                        PoTaskListTeacherAnswerSheetModel tltas = new PoTaskListTeacherAnswerSheetModel();
                        tltas.setTeacherNum(teacherNum);
                        tltas.setAnswerSheetNum(answerSheets.get(currentTaskIndex).getSheetNum());
                        tltasDao.save(tltas);

                        currentTaskIndex++;
                    }
                }
                //如果有余数,说明还有模块没分配
                if(remainder != 0)
                {
                    for(int i=0;i<remainder;i++)
                    {
                        int teacherNum = Integer.parseInt(teachers[i]);

                        PoTaskListTeacherAnswerSheetModel tltas = new PoTaskListTeacherAnswerSheetModel();
                        tltas.setTeacherNum(teacherNum);
                        tltas.setAnswerSheetNum(answerSheets.get(currentTaskIndex).getSheetNum());
                        tltasDao.save(tltas);

                        currentTaskIndex++;
                    }
                }

                examScheme.setEnded(true);
            }

            //根据题目分配任务
            //先获取该试卷的所有小题,再平均分给教师们
            else if(examScheme.getExamReviewModel().getModelNum() == ExamReviewModel.TeacherExercise.getValue())
            {
                System.out.println("TeacherExerciseType");
                String[] teachers = examScheme.getTeacherNum().split(",");
                if(teachers.length == 0) return;

                PoExam exam = examDao.findById(examScheme.getExamNum()).orElse(null);
                if(exam == null)
                {
                    System.out.println("考试安排引用的试卷不存在");
                    return;
                }
                List<PoModuleExercise> moduleExercises = new ArrayList<>();
                for(PoExamModule module : exam.getModules())
                {
                    for(PoModuleExercise moduleExercise : module.getModuleExercises())
                    {
                        if(!moduleExercises.contains(moduleExercise)) moduleExercises.add(moduleExercise);
                    }
                }

                int avg = moduleExercises.size()/teachers.length;
                int remainder = moduleExercises.size()%teachers.length;
                int currentTaskIndex = 0;

                //根据平均数分配题目模块
                for(int i=0;i<teachers.length;i++)
                {
                    int teacherNum = Integer.parseInt(teachers[i]);

                    for(int j=0;j<avg;j++)
                    {
                        PoTaskListTeacherExerciseModel tlte = new PoTaskListTeacherExerciseModel();
                        tlte.setTeacherNum(teacherNum);
                        tlte.setModelExerciseId(moduleExercises.get(currentTaskIndex).getId());
                        tlte.setExamSchemeNum(examScheme.getSchemeNum());
                        tlteDao.save(tlte);

                        currentTaskIndex++;
                    }
                }
                //如果有余数,说明还有题目没分配
                if(remainder != 0)
                {
                    for(int i=0;i<remainder;i++)
                    {
                        int teacherNum = Integer.parseInt(teachers[i]);

                        PoTaskListTeacherExerciseModel tlte = new PoTaskListTeacherExerciseModel();
                        tlte.setTeacherNum(teacherNum);
                        tlte.setModelExerciseId(moduleExercises.get(currentTaskIndex).getId());
                        tlte.setExamSchemeNum(examScheme.getSchemeNum());
                        tlteDao.save(tlte);

                        currentTaskIndex++;
                    }
                }

                examScheme.setEnded(true);
            }
        }
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 5000)
    public void autoUpdateCredit() {
        List<PoExamScheme> examSchemes =
                examSchemeDao.findAllByExamType_TypeNumAndSettlementedIsFalseAndEndedIsTrue(ExamType.FinalExam.getValue());

        if(examSchemes == null || examSchemes.isEmpty())
        {
            System.out.println("未检测到需要结算的考试");
            return;
        }

        System.out.println("检测到需要结算的考试");
        for(PoExamScheme examScheme:examSchemes)
        {
            //如果考试安排都没有参加班级或指定试卷就说明数据有问题,不用结算
            List<PoClazz> clazzes = clazzService.findAllByExamSchemeNum(examScheme.getSchemeNum());
            if(clazzes == null || clazzes.isEmpty()) continue;

            PoExam exam = examDao.findById(examScheme.getExamNum()).orElse(null);
            if(exam == null) continue;

            //判断该考试的任务分配类型以确定是否有剩余任务
            if(examScheme.getExamReviewModel().getModelNum() == ExamReviewModel.TeacherExercise.getValue())
            {
                Object o = tlteDao.countAllByExamSchemeNum(examScheme.getSchemeNum());
                if(o == null) continue;

                int taskNum = (int)(long)o;
                if(taskNum != 0) continue;
            }
            else if(examScheme.getExamReviewModel().getModelNum() == ExamReviewModel.TeacherAnswerSheet.getValue())
            {
                int taskNum = 0;
                String[] teacherNumStr = examScheme.getTeacherNum().split(",");

                for (String s : teacherNumStr) {
                    int teacherNum = Integer.parseInt(s);

                    taskNum += tltasDao.countAllByTeacherNum(teacherNum);
                    if(taskNum != 0) break;
                }

                if(taskNum!=0) continue;
            }

            PoCourse course = examScheme.getCourse();
            if(course == null) continue;

            Double credit = course.getCourseCredit();
            for(PoClazz clazz:clazzes)
            {
                for(PoStudent student : clazz.getStudents())
                {
                    System.out.println("查询id:"+student.getStudentId());
                    PoAnswerSheet answerSheet = answerSheetDao.findByStudentIdAndSchemeNum(student.getStudentId(),examScheme.getSchemeNum());
                    if(answerSheet == null) continue;
                    System.out.println("查询id:"+student.getStudentId()+" 答题表:"+answerSheet.getSheetNum());

                    Double add = (double)answerSheet.getTotalScore()/ (double) exam.getTotalScore()*credit;
                    student.setCredit(student.getCredit()+add);

                    System.out.println("将id:"+student.getStudentId()+" 加:"+add);
                }
            }

            examScheme.setSettlemented(true);
        }

    }
*/

}
