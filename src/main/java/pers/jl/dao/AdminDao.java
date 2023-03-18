package pers.jl.dao;

import pers.jl.pojo.Admin;
import pers.jl.pojo.Student;
import pers.jl.pojo.Teacher;
import pers.jl.utils.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
    //查
    public static Admin Select(String name, String pwd) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "select * from admin where userName = ? and pwd = ?";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //设置参数
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);
        //执行sql,拿到结果集
        ResultSet res = pstmt.executeQuery();
        //处理结果集
        Admin admin = new Admin();
        if (res.next()) {
            //封装对象
            admin.setId(res.getString(1)); //stuId;
            admin.setUserName(res.getString(2));// stuName
            admin.setPwd(res.getString(3)); //stuPwd
            admin.setName(res.getString(4)); //name
        }
        return admin;

    }

    //改
    public static boolean Update(String newpwd,Integer id) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "update student set stuPwd = ? where stuId = ?";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //密码加密(两次)
        String p1 = Util.encrypByMd5(newpwd);
        String p = Util.encrypByMd5(p1);
        //设置参数
        pstmt.setString(1, p);
        pstmt.setInt(2, id);
        //执行sql,返回受影响的行数
        int i = pstmt.executeUpdate();
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }

    //删(只能单条删除)
    public static boolean Delete(String stuNo) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "delete from student where stuNo = ?;";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //设置参数
        pstmt.setString(1, stuNo);
        //执行sql,返回受影响的行数
        int i = pstmt.executeUpdate();
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }
}
