package ee.mihkel;

public class Threads extends Thread {
    private final int threadNumber;

    public Threads(int threadNumber) {
        this.threadNumber = threadNumber;
        start();
    }

    @Override
    public void run() {
        if (threadNumber == 0) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } else if (threadNumber == 1) {
            for (int i = 5; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } else if (threadNumber == 2) {
            for (int i = 45; i > 10; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } else if (threadNumber == 3) {
            for (int i = 100; i < 150; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
}
