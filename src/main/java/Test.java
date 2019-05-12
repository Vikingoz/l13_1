import etc.SortWithOutThread;
import etc.SortWithThread;
import etc.Sorter;

public class Test {

    public static void showArray(int[] array) {
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
                ("end");
        /**######################################################**/
        System.out.println
                ("sort with 4 thread? without recursion. tst 2.0");
        int[] arraySorter = new int[] {1,0,2,9,3,8,4,7,5,6,3};
        Sorter srt = new Sorter(arraySorter);
        showArray(arraySorter);
        srt.sort();
        showArray(srt.getSortedArray());

        System.out.println("tst 2.1");
        arraySorter = new int[] {1,1,1,1,1,1,1,1,1,0};
        srt = new Sorter(arraySorter);
        showArray(arraySorter);
        srt.sort();
        showArray(srt.getSortedArray());

        System.out.println("tst 2.2");
        arraySorter = new int[] {8,2};
        srt = new Sorter(arraySorter);
        showArray(arraySorter);
        srt.sort();
        showArray(srt.getSortedArray());

        System.out.println("tst 2.3");
        arraySorter = new int[] {8,2,4};
        srt = new Sorter(arraySorter);
        showArray(arraySorter);
        srt.sort();
        showArray(srt.getSortedArray());


    }
}
