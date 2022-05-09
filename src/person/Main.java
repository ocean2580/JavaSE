package person;

// JVM 对底层接口进行动态代理
public class Main {
    public static void main(String[] args) {
        go(new Worker());
    }

    public static void go(Person p) {
        if (p instanceof Test) {
            System.out.println("testing");
        } else {
            System.out.println("No");
        }
    }
}