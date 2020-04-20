package it.com.demo.deepclone;

import java.io.*;

public class DeepClone {
    // 对象的写入
    public static void writeObj(People people) throws IOException {
        // 建立文件输出流对象
        FileOutputStream fos = new FileOutputStream("E:\\obj.txt");
        // 建立对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 把对象写入文件
        oos.writeObject(people);
        oos.close();
        fos.close();
    }

    // 对象的读取
    public static People readObj() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("E:\\obj.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // 读取对象
        People obj = (People) ois.readObject();
        ois.close();
        fis.close();
        return obj;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Address address = new Address("北京","朝阳");
        People people = new People(110,"AA",address);
        // 把 people写入文件
        writeObj(people);
        // 从文件中读取
        People people2 = readObj();
        System.out.println(people == people2);    // false
        people.setId(111);
        System.out.println(people == people2);    // false
    }
}

