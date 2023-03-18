package pers.jl.dao;

import pers.jl.pojo.Student;
import pers.jl.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    //currentPageNo代表一页显示的内容数，pageSize表示第几页
    public static List<Student> SelectAll() throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "select * from student ";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //执行sql,拿到结果集
        ResultSet res = pstmt.executeQuery();
        //处理结果集
        List<Student> list = Util.executeQuery(res, Student.class);
        return null;

    }

    //查
    public static Student Select(String name,String pwd) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "select * from student where stuName = ? and stuPwd = ?";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //设置参数
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);
        //执行sql,拿到结果集
        ResultSet res = pstmt.executeQuery();
        //处理结果集
        Student student = new Student();
        if (res.next()) {
            //封装对象
            student.setStuId(res.getInt(1)); //stuId;
            student.setStuName(res.getString(2));// stuName
            student.setStuNo(res.getString(3));//  stuNo
            student.setStuPwd(res.getString(4)); //stuPwd
        }
        return student;

    }


    //增(只能单条添加)
    public static boolean Add(String name,String stuNo,String pwd) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "insert into student (stuName, stuNo, stuPwd)values (?,?,?)";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //密码加密
        String p1 = Util.encrypByMd5(pwd);
        String p = Util.encrypByMd5(p1);
        //设置参数
        pstmt.setString(1, name);
        pstmt.setString(2, stuNo);
        pstmt.setString(3, p);
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


    //改
    public static boolean Update(String newpwd,Integer id) throws Exception {
        //获取Connect对象
        Connection conn = Util.getConnect();
        //定义sql
        String sql = "update student set stuPwd = ? where stuId = ?";
        //传入sql获取PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //密码加密
        String p = Util.encrypByMd5(newpwd);
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

}
