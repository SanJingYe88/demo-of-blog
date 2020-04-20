package it.com.demo.oop.interface2;

class CcDaoImpl implements CcDao{
    public void printAA() {
        // 输出接口CC中的成员变量AA
        System.out.println("AA = " + AA);
        System.out.println("AA接口中: AA = " + AaDao.AA);
    }
}
