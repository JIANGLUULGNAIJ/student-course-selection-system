package pers.jl.utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.ConvertUtils;
import pers.jl.pojo.Student;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {

    //获取数据库连接
    public static Connection getConnect() throws Exception {

        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("E:\\idea\\projects\\school\\StudentCourseSelectionSystem\\src\\main\\resources\\druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取Connection对象
        Connection connection = dataSource.getConnection();
        return connection;

    }

    //密码加密
    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());// update处理
            byte[] encryContext = md.digest();// 调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  List<Student> executeQuery(ResultSet res, Class<Student> cla) throws Exception {
        //创建一个List对象
        List<Student> list=new ArrayList<>();
        //获得数据库的元文件
            ResultSetMetaData rsmd = res.getMetaData();
            while (res.next()) {
                //创建类对象
                Student t = cla.getDeclaredConstructor().newInstance();
                //遍历rs集合
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    //根据元文件获得对应的列名
                    String columnName = rsmd.getColumnName(i + 1);
                    //根据列的编号获得每列数据
                    Object val = res.getObject(i + 1);
                    //获得属性值
                    Field fieldName = cla.getDeclaredField(columnName);
                    //拼接set方法名
                    String methodName = "set" + columnName.substring(0, 1).toUpperCase()
                            + columnName.substring(1);
                    //获取set方法
                    Method method = cla.getDeclaredMethod(methodName, fieldName.getType());
                    //调用set方法
                    method.invoke(t, ConvertUtils.convert(val, fieldName.getType()));
                }
                list.add(t);
            }

        return list;
}

}
