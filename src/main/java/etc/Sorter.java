package etc;

public class Sorter {

    private int[] array;
    private int[] sortedArray;

    public Sorter(int[] array) {
        this.array = array;
    }
    private static void showArray(int[] array) {
        System.out.print("Sorter::");
        for(int i = 0; i < array.length; i ++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    private class LeftRightArrays {
        int[] left, right;

        public LeftRightArrays(int[] left, int[] right) {
            this.left = left;
            this.right = right;
        }

        public int[] getLeft() {
            return left;
        }

        public int[] getRight() {
            return right;
        }
    }


    private LeftRightArrays divideArray (final int[] array) {
        int middle;
        int[] left, right;
        middle = array.length / 2;

        left = new int[middle];
        right = new int[array.length - middle];

        System.arraycopy(array, 0, left, 0, middle );
        System.arraycopy(array, middle, right, 0, array.length - middle );

        return new LeftRightArrays(left, right);
    }


    public void sort() {
        int[] leftSorted = null;
        int[] rightSorted = null;
        SortInThread sortInThreadOne = null;
        SortInThread sortInThreadTwo = null;
        SortInThread sortInThreadThree = null;
        SortInThread sortInThreadFour = null;
        Thread threadOne = null;
        Thread threadTwo = null;
        Thread threadThree = null;
        Thread threadFour  = null;

        if ( array.length <= 1 ) {
            sortedArray = array;
        } else {
            LeftRightArrays lrArrays = divideArray(array);

            //левая часть на две
            if ( lrArrays.getLeft().length <= 1 ) {
                leftSorted = lrArrays.getLeft();
            } else {
                LeftRightArrays llrArrays = divideArray(lrArrays.getLeft());
                sortInThreadOne = new SortInThread(llrArrays.getLeft());
                sortInThreadTwo = new SortInThread(llrArrays.getRight());
                threadOne = new Thread(sortInThreadOne, "one");
                threadTwo = new Thread(sortInThreadTwo, "two");
                threadOne.start();
                threadTwo.start();
            }
            //правая часть на две

            if ( lrArrays.getRight().length <= 1 ) {
                rightSorted = lrArrays.getRight();
            } else {
                LeftRightArrays rlrArrays = divideArray(lrArrays.getRight());
                sortInThreadThree = new SortInThread(rlrArrays.getLeft());
                sortInThreadFour = new SortInThread(rlrArrays.getRight());
                threadThree = new Thread(sortInThreadThree, "three");
                threadFour = new Thread(sortInThreadFour, "four");
                threadThree.start();
                threadFour.start();
            }
            //Запускаем потоки
            if (leftSorted == null) {
                try {
                    threadOne.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    threadTwo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                leftSorted = Merger.merge(sortInThreadOne.getSortedArray(), sortInThreadTwo.getSortedArray());
            }
            if (sortInThreadThree != null && sortInThreadFour != null) {
                try {
                    threadThree.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    threadFour.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rightSorted = Merger.merge(sortInThreadThree.getSortedArray(), sortInThreadFour.getSortedArray());
            }
            sortedArray = Merger.merge( leftSorted, rightSorted );
        }
    }
}