public class HelloThread extends Thread{
    private static int number = 0;
    private int threadNumber;
    public HelloThread(){
        this.threadNumber = ++number;
    }
    @Override
    public void run() {
        System.out.println("Hello from Thread " + this.threadNumber);
    }
}
