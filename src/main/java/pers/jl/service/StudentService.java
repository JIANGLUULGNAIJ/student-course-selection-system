package pers.jl.service;

import pers.jl.dao.StudentDao;
import pers.jl.pojo.Student;

public class StudentService {
    //登录验证
    public static Student login(String name, String pwd) throws Exception {
        Student stu = StudentDao.Select(name, pwd);
        return stu;
    }

    //修改密码
    public static boolean update(String newpwd, Integer id) throws Exception {
        boolean update = StudentDao.Update(newpwd, id);
        return update;
    }


}
