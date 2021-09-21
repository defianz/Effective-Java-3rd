package main;

public class Main {

    private static volatile int number;
    private static volatile Object lock = new Object();

    public static synchronized void addNumber(){
//        synchronized(lock){
            number++;
//        }

    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {

                addNumber();
//                number++;
                System.out.println("Thread1 number = " + number);

            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {

//                number++;
                addNumber();
                System.out.println("Thread2 number = " + number);
            }
        });

        thread1.start();
        thread2.start();
    }
}
