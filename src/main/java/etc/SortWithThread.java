package etc;

public class SortWithThread extends Thread {

    private int[] array;
    private int[] sortedArray;
    private final int maxThread;

    public SortWithThread(int[] array, int maxThread) {
        this.array = array;
        this.maxThread = maxThread;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public void run() {
            int middle;
            int[] left;
            int[] right;

            System.out.println(super.getId());

            if ( array.length <= 1 ) {
                sortedArray = array;
            } else {
                middle = array.length / 2;

                left = new int[middle];
                right = new int[array.length - middle];

                System.arraycopy(array, 0, left, 0, middle );
                System.arraycopy(array, middle, right, 0, array.length - middle );

                if ( activeCount() < maxThread ) {
                    SortWithThread leftThread = new SortWithThread(left, this.maxThread);
                    SortWithThread rightThread = new SortWithThread(right, this.maxThread);
                    leftThread.start();
                    rightThread.start();
                    try {
                        leftThread.join();
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    try {
                        rightThread.join();
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    sortedArray = Merger.merge( leftThread.getSortedArray(), rightThread.getSortedArray() );
                } else {
                    SortWithOutThread leftSort = new SortWithOutThread( left );
                    SortWithOutThread rightSort = new SortWithOutThread( right );
                    leftSort.sort();
                    rightSort.sort();
                    sortedArray = Merger.merge( leftSort.getSortedArray(), rightSort.getSortedArray() );
                }

            }
        }
    }