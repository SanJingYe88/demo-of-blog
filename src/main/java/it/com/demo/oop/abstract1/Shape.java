package it.com.demo.oop.abstract1;

// 抽象的图形类
abstract class Shape{
    //抽象类的成员属性
    String name;

    // 抽象类的构造方法
    public Shape(String name) {
        this.name = name;
    }

    // 抽象方法, (没有大括号{})
    public abstract void getArea();

    // 非抽象方法
    public void getLength() {
        System.out.println("getLength");
    }
}

