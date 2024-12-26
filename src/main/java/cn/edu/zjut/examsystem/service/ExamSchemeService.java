package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.Enum.ExamReviewModel;
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
    private ExamDao examDao;

    @Autowired
    private TaskListTeacherExerciseTypeModelDao tltetDao;

    @Autowired
    private TaskListTeacherAnswerSheetModelDao tltasDao;

    @Autowired
    private AnswerSheetDao answerSheetDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentDao studentDao;

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

            //根据题型分配任务
            //先获取该试卷的所有模块,再平均分给教师们
            else if(examScheme.getExamReviewModel().getModelNum() == ExamReviewModel.TeacherExerciseType.getValue())
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
                List<PoExamModule> examModules = exam.getModules();

                int avg = examModules.size()/teachers.length;
                int remainder = examModules.size()%teachers.length;
                int currentTaskIndex = 0;

                //根据平均数分配题目模块
                for(int i=0;i<teachers.length;i++)
                {
                    int teacherNum = Integer.parseInt(teachers[i]);

                    for(int j=0;j<avg;j++)
                    {
                        PoTaskListTeacherExerciseTypeModel tltet = new PoTaskListTeacherExerciseTypeModel();
                        tltet.setTeacherNum(teacherNum);
                        tltet.setExerciseType(examModules.get(currentTaskIndex).getExerciseType());
                        tltetDao.save(tltet);

                        currentTaskIndex++;
                    }
                }
                //如果有余数,说明还有模块没分配
                if(remainder != 0)
                {
                    for(int i=0;i<remainder;i++)
                    {
                        int teacherNum = Integer.parseInt(teachers[i]);

                        PoTaskListTeacherExerciseTypeModel tltet = new PoTaskListTeacherExerciseTypeModel();
                        tltet.setTeacherNum(teacherNum);
                        tltet.setExerciseType(examModules.get(currentTaskIndex).getExerciseType());
                        tltetDao.save(tltet);

                        currentTaskIndex++;
                    }
                }

                examScheme.setEnded(true);
            }
        }
    }
}
