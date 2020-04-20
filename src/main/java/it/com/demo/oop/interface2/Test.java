package it.com.demo.oop.interface2;

public class Test {
    public static void main(String[] args) {
        CcDao cc = new CcDaoImpl();
        cc.printAA();
    }
}

/*输出:
        AA = 30
        AA接口中: AA = 10
*/
