package jls8.memory.model;

/**
 * Created by infosea on 2016-08-03.
 */
public class Example1 {
    private static int A, B = 0;

    public static void main(String... args) {
        new Thread("THREAD1") {
            @Override
            public void run() {
                int r2 = A;
                int B = 1;
                System.out.println("r2 " + r2);
            }

        }.start();

        new Thread("THREAD2") {
            @Override
            public void run() {
                int r1 = B;
                A = 2;
                System.out.println("r1 " + r1);
            }

        }.start();
    }
}
