package it.com.demo.oop.abstract1;

class Circle extends Shape{
    private static final double PI = 3.14;
    private int r;

    public Circle(String name,int r) {
        super(name);
        this.r = r;
    }

    // 必须实现抽象方法, 非抽象方法可以不用实现
    @Override
    public void getArea() {
        System.out.println(name + "的面积为:" + PI * r * r);
    }
}
