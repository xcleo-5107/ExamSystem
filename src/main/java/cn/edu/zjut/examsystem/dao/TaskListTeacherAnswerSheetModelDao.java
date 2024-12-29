package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTaskListTeacherAnswerSheetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListTeacherAnswerSheetModelDao extends JpaRepository<PoTaskListTeacherAnswerSheetModel,Integer> {
    int countAllByTeacherNum(int teacherNum);

    List<PoTaskListTeacherAnswerSheetModel> findAllByTeacherNum(int teacherNum);
}
