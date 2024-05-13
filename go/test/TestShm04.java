package go.test;

import go.Channel;
import go.Factory;

/**
 * (out;out) | in | in
 */
public class TestShm04 {

    private static void quit(String msg) {
        System.out.println("TestShm03: " + msg);
        System.exit(msg.equals("ok") ? 0 : 1);
    }

    public static void main(String[] a) {
        Factory factory = new go.shm.Factory();
        Channel<Integer> c = factory.newChannel("c");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            quit("KO (deadlock)");
        }).start();

        new Thread(() -> {
            int v = c.in();
            if (v != 4) quit("KO");
            v = c.in();
            if (v != 5) quit("KO");
            v = c.in();
            quit(v == 6 ? "ok" : "KO");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            c.out(4);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            c.out(5);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            c.out(6);
        }).start();

    }
}
