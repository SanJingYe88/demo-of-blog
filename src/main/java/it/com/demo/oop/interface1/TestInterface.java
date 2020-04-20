package it.com.demo.oop.interface1;

// 测试
public class TestInterface {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.print();
        // 通过 接口名.常量名 来直接访问
        System.out.println(UserDao.ID);
    }
}
