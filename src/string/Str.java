package string;

// JVM 对底层接口进行动态代理
public class Str {
    public static void main(String[] args) {
        String str1="String";
        String str2="and";
        String result=(new StringBuilder(String.valueOf(str1))).append(str2).toString();
//使用StringBuilder，会采用类似于第一种实现，显然会更快！
        System.out.println(result);

        StringBuilder builder = new StringBuilder();
        builder
                .append("a")
                .append("bc")
                .append("d");   //链式调用
        String str = builder.toString();
        System.out.println(str);

        A a = System.out::println;

    }
}

interface A {
    void play(int a);
    default void c() {

    }
}
