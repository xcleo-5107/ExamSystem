package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ClazzDao;
import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClazzService implements ClazzServiceImpl{
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public List<PoClazz> findAllByClazzesStr(String targets) {
        List<PoClazz> clazzes = new ArrayList<>();
        String[] targetSplited = targets.split(" ");

        for(String s:targetSplited)
        {
            //ifPresent表示一个对象是否是有值的,有则传递给后面的接口实现
            clazzDao.findById(Integer.parseInt(s)).ifPresent(clazzes::add);
        }

        if(clazzes.isEmpty()) return null;
        else return clazzes;
    }
}
