package etc;

public class SortInThread implements Runnable {

    private int[] array;
    private int[] sortedArray;

    public SortInThread(int[] array) {
        this.array = array;
    }


    public int[] getSortedArray() {
        return sortedArray;
    }

    @Override
    public void run() {
        SortWithOutThread sortWithOutThread = new SortWithOutThread( array );
        sortWithOutThread.sort();
        sortedArray = sortWithOutThread.getSortedArray();
    }

}
