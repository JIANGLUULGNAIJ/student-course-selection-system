import pers.jl.dao.StudentDao;
import pers.jl.pojo.Student;

public class Test {


    @org.junit.Test
    public void select(){
        try {
            Student stu = StudentDao.Select("lisa","fcea920f7412b5da7be0cf42b8c93759");
            System.out.println(stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void add(){
        try {
//            System.out.println(StudentDao.Add("蒋璐","0000","1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void delete(){
        try {
            System.out.println(StudentDao.Delete("0100"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//如何获取自增ID(未完成)
//    @org.junit.Test
//    public void update(){
//        try {
//            System.out.println(StudentDao.Update("张四","0010","1234567"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
