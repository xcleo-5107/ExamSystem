package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.CourseDao;
import cn.edu.zjut.examsystem.dao.ExamDao;
import cn.edu.zjut.examsystem.dao.ExerciseDao;
import cn.edu.zjut.examsystem.dao.TeacherDao;
import cn.edu.zjut.examsystem.dto.AutoCreateExamDto;
import cn.edu.zjut.examsystem.dto.AutoCreateExamModule;
import cn.edu.zjut.examsystem.po.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.loading.PrivateClassLoader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements ExamServiceImpl{
    @Autowired
    private ExamDao examDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ExerciseDao exerciseDao;


    @Transactional(readOnly = true)
    @Override
    public PoExam findById(int id) {
        return examDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Boolean alter(PoExam exam) {
        examDao.save(exam);
        return true;
    }

    @Transactional
    public Boolean add(PoExam exam)
    {
        entityManager.persist(exam);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int id) {
        if(examDao.existsById(id))
        {
            examDao.deleteById(id);
            return true;
        }
        else return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PoExam> findAllByStr(String str) {
        return examDao.findAllByStr("%"+str+"%");
    }

    @Transactional(readOnly = true)
    public List<PoExam> findAll()
    {
        return examDao.findAll();
    }

    @Override
    public PoExam autoCreateExam(AutoCreateExamDto autoCreateExamDto) {
        //初始化试卷
        int difficultyAvg = autoCreateExamDto.getExerciseDifficultyAvg();
        int totalScore = 0;

        PoExam exam = new PoExam();
        exam.setIsReleased(autoCreateExamDto.getIsReleased());

        PoTeacher teacher = teacherDao.findById(autoCreateExamDto.getTeacherNum()).orElse(null);
        if(teacher == null) return null;
        exam.setTeacher(teacher);

        exam.setModules(new ArrayList<>());

        PoCourse course = courseDao.findById(autoCreateExamDto.getCourseNum()).orElse(null);
        if(course == null) return null;
        exam.setCourse(course);

        //根据设置创建大题模块
        for(AutoCreateExamModule createExamModule:autoCreateExamDto.getModules())
        {
            if(createExamModule.getExerciseNumber() == 0) continue;

            //根据大题分值求平均,余数加给最后一题
            int moduleTotalScore = createExamModule.getModuleTotalScore();
            totalScore += moduleTotalScore;
            int exerciseMaxScore = moduleTotalScore/createExamModule.getExerciseNumber();
            int remainder = moduleTotalScore%createExamModule.getExerciseNumber();

            //获取随机题目,一会用来创建小题
            List<PoExercise> allExercise = exerciseDao.findAllByCourse_CourseNumAndExerciseType_TypeNum(autoCreateExamDto.getCourseNum(), createExamModule.getExerciseType());
            List<PoExercise> currentModuleExercises = new ArrayList<>();
            //如果设定的题目数量太大就直接返回
            if(createExamModule.getExerciseNumber()>allExercise.size()) return null;

            //开始在复合条件的题目里面随机选择
            SecureRandom secureRandom = new SecureRandom();
            int lowerBound = 0; // 范围的下界
            int upperBound = allExercise.size()-1; // 范围的上界

            for(int i=0;i<createExamModule.getExerciseNumber();)
            {
                // 生成一个在 [lowerBound, upperBound) 范围内的随机整数
                int randomNumber = lowerBound + secureRandom.nextInt(upperBound - lowerBound);

                PoExercise exercise = allExercise.get(randomNumber);
                if(!currentModuleExercises.contains(exercise))
                {
                    currentModuleExercises.add(exercise);
                    i++;
                }
            }

            //初始化大题模块
            PoExamModule examModule = new PoExamModule();
            examModule.setModuleExercises(new ArrayList<>());
            examModule.setModuleInExamNum(createExamModule.getModuleInExamNum());
            examModule.setExerciseType(createExamModule.getExerciseType());

            //往大题模块里加题目
            for(int i=0;i<createExamModule.getExerciseNumber();i++)
            {
                PoModuleExercise moduleExercise = new PoModuleExercise();
                moduleExercise.setExerciseInModuleNum(i+1);
                moduleExercise.setExercise(currentModuleExercises.get(i));

                if(i == createExamModule.getExerciseNumber()-1) moduleExercise.setMaxScore(exerciseMaxScore+remainder);
                else moduleExercise.setMaxScore(exerciseMaxScore);

                examModule.getModuleExercises().add(moduleExercise);
            }
            exam.getModules().add(examModule);
        }
        exam.setTotalScore(totalScore);

        return exam;
    }


}
