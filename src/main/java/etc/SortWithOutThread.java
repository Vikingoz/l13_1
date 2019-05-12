package etc;

public class SortWithOutThread {

    private int[] array;
    private int[] sortedArray;

    public SortWithOutThread(int[] array) {
        this.array = array;
    }

    public static void showArray(int[] array) {
        System.out.print("SortWithOutThread::");
        for(int i = 0; i < array.length; i ++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public void sort() {
        int middle;
        int[] left;
        int[] right;

        if ( array.length <= 1 ) {
            sortedArray = array;
        } else {
            middle = array.length / 2;
            left = new int[middle];
            right = new int[array.length - middle];

            System.arraycopy(array, 0, left, 0, middle );
            System.arraycopy(array, middle, right, 0, array.length - middle );
            SortWithOutThread leftSort = new SortWithOutThread( left );
            SortWithOutThread rightSort = new SortWithOutThread( right );

            leftSort.sort();
            rightSort.sort();
            sortedArray = Merger.merge( leftSort.getSortedArray(), rightSort.getSortedArray() );
        }
    }
}