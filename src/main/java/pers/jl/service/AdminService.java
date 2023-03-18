package pers.jl.service;

import pers.jl.dao.AdminDao;
import pers.jl.dao.StudentDao;
import pers.jl.pojo.Admin;
import pers.jl.pojo.Student;

public class AdminService {
    //登录验证
    public static Admin login(String name, String pwd) throws Exception {
        Admin admin = AdminDao.Select(name, pwd);
        return admin;
    }

    //修改密码
    public static boolean update(String newpwd, Integer id) throws Exception {
        boolean update = AdminDao.Update(newpwd, id);
        return update;
    }

}
