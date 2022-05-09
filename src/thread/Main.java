package thread;

public class Main {
    private static int value = 0;

    // 静态方法，就是使用的类锁，而如果是普通成员方法，就是使用的对象锁。
    public static void main(String[] args) throws InterruptedException {
        f2();
    }

    public static void f1() throws InterruptedException { // 未同步
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) value++;
            System.out.println("线程1完成");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) value++;
            System.out.println("线程2完成");
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);  //主线程停止1秒，保证两个线程执行完成
        System.out.println(value);
    }

    public static void f2() throws InterruptedException { // 同步
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) { // 作用于对象
                synchronized (Main.class){
                    value++;
                }
            }
            System.out.println("线程1完成");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (Main.class){
                    value++;
                }
            }
            System.out.println("线程2完成");
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);  //主线程停止1秒，保证两个线程执行完成
        System.out.println(value);
    }

    public static void f3() throws InterruptedException { // 未同步
        Main main1 = new Main();
        Main main2 = new Main();
        Thread t1 = new Thread(() -> { // 当对象不同时，获取到的是不同的锁，
            for (int i = 0; i < 10000; i++) {
                synchronized (main1){
                    value++;
                }
            }
            System.out.println("线程1完成");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (main2){
                    value++;
                }
            }
            System.out.println("线程2完成");
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);  //主线程停止1秒，保证两个线程执行完成
        System.out.println(value);
    }

    private static synchronized void add(){ // 作用于方法
        value++;
    }

    public static void f4() throws InterruptedException { // 同步
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) add();
            System.out.println("线程1完成");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) add();
            System.out.println("线程2完成");
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);  //主线程停止1秒，保证两个线程执行完成
        System.out.println(value);
    }
}
