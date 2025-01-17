public class ThreadSum extends Thread {
    private int[] array;
    private int startIdx;
    private int endIdx;
    private int partialSum;

    public ThreadSum(int[] array, int startIdx, int endIdx) {
        this.array = array;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.partialSum = 0;
    }

    public int getPartialSum() {
        return partialSum;
    }

    @Override
    public void run() {
        for (int i = startIdx; i < endIdx; i++) {
            partialSum += array[i];
        }
    }
}
