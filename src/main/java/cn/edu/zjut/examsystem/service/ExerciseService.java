package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ExamDao;
import cn.edu.zjut.examsystem.dao.ExerciseDao;
import cn.edu.zjut.examsystem.po.PoExercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Set;

@Service
public class ExerciseService implements ExerciseServiceImpl {
    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<PoExercise> findAllByCourseNum(int num) {
        return exerciseDao.findAllByCourse_CourseNum(num);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PoExercise> findAll() {
        return exerciseDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<PoExercise> findAllByStr(String str)
    {
        return exerciseDao.findAllByExerciseInfoContainingOrExerciseProblemContainingOrExerciseAnswerContainingOrExerciseType_TypeNameContainingOrCourse_CourseName(str,str,str,str,str);
    }

    @Transactional(readOnly = true)
    @Override
    public PoExercise findById(int num) {
        return exerciseDao.findById(num).orElse(null);
    }

    @Transactional
    @Override
    public Boolean alter(PoExercise exercise) {
        exerciseDao.save(exercise);
        return true;
    }

    @Transactional
    @Override
    public Boolean add(PoExercise exercise) {
        entityManager.persist(exercise);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int num) {
        if(exerciseDao.existsById(num))
        {
            exerciseDao.deleteById(num);
            return true;
        }
        else return false;
    }
}
