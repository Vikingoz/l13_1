package etc;


public class Merger {

    public static int[] merge( int[] left, int[] right ) {
        int countLeft = 0;
        int countRight = 0;
        int count = 0;

        int[] merged = new int[left.length + right.length];

        while ( countLeft < left.length &&
                countRight < right.length )
        {
            if ( left[countLeft] <= right[countRight] ) {
                merged[count] = left[countLeft];
                countLeft++;
            } else {
                merged[count] = right[countRight];
                countRight++;
            }
            count++;
        }

        if ( countLeft < left.length ) {
            System.arraycopy( left, countLeft, merged, count, merged.length - count );
        }
        if ( countRight < right.length ) {
            System.arraycopy( right, countRight, merged, count, merged.length - count );
        }

        return merged;
    }

}
