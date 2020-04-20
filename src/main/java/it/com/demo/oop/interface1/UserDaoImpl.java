package it.com.demo.oop.interface1;

// 实现类
class UserDaoImpl implements UserDao{

    public void print() {
        // 在接口的实现类中, 也可以直接访问到接口定义的常量
        System.out.println("UserDaoImpl: ID = " + ID );
    }
}
