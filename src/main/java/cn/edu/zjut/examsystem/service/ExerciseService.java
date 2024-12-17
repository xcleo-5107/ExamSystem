package cn.edu.zjut.examsystem.service;

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
        //return exerciseDao.findAllByExerciseAnswerContaining(str);
    }

    @Transactional(readOnly = true)
    @Override
    public PoExercise findById(int num) {
        return exerciseDao.findById(num).orElse(null);
    }

    @Transactional
    @Override
    public PoExercise alter(PoExercise exercise) {
        //save时,如果不存在则增加并将其添加到上下文中,而如果已经存在,则只更新,不会添加实体到上下文,refresh会报错
        //entityManager.refresh(exercise);

        return exerciseDao.save(exercise);
    }

    @Transactional
    @Override
    public Boolean add(PoExercise exercise) {
        exerciseDao.save(exercise);
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
