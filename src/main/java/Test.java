import etc.SortWithThread;

public class Test {

    private static void showArray(int[] array) {
        for(int i = 0; i < array.length; i ++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] array = new int[] {1,0,2,9,3,8,4,7,5,6,3};

        SortWithThread sorter = new SortWithThread(array, 4 );
        sorter.start();
        // Ждем завершения потока
        try {
            sorter.join();
        } catch ( Exception e ) {

        }
        showArray(array);
        showArray(sorter.getSortedArray());

        System.out.println
                ("test 2 ");

        array = new int[] {1,1,1,1,1,1,1,1,1,0};

        sorter = new SortWithThread(array, 4 );
        sorter.start();
        // Ждем завершения потока
        try {
            sorter.join();
        } catch ( Exception e ) {

        }
        showArray(array);
        showArray(sorter.getSortedArray());


        System.out.println
                ("end");}
}
