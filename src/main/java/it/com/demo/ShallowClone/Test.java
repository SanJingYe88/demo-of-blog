package it.com.demo.ShallowClone;

public class Test {
    public static void main(String[] args) {
        User user = new User(110, "AA");
        User user2 = null;
        try {
            user2 = (User) user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(user == user2);       // false
        System.out.println(user);              // User [id=110, name=AA]
        System.out.println(user2);             // User [id=110, name=AA]
        user.setId(111);

        System.out.println(user == user2);       // false
        System.out.println(user);              // User [id=111, name=AA]
        System.out.println(user2);             // User [id=110, name=AA]
    }
}
