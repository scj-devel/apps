package privmem.sorter;

import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.realtime.RelativeTime;
import javax.realtime.PeriodicParameters;
import privmem.sorter.bench.BenchConf;
import privmem.sorter.bench.NanoClock;

public class SorterHandler extends PeriodicEventHandler {

    private Data array[];

    public SorterHandler(long psize) {
        super(null, new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(500, 0)), new StorageParameters(psize, null), 500);

        array = new Data[BenchConf.SIZE];
        for (int i = 0; i < BenchConf.FRAMES; i++) {
            array[i] = new Data();
        }
    }

    private int counter = 0;

    public void handleAsyncEvent() {
        final long timeBefore = NanoClock.now();

        mix();
        sort();
        final long timeAfter = NanoClock.now();

        BenchConf.timesBefore[BenchConf.recordedRuns] = timeBefore;
        BenchConf.timesAfter[BenchConf.recordedRuns] = timeAfter;
        BenchConf.recordedRuns++;

        counter++;
        if (counter >= BenchConf.FRAMES)
            Mission.getCurrentMission().requestSequenceTermination();
    }

    /**
     * Quicksort algorithm.
     *
     * @param a
     *            an array of Comparable items.
     */
    private void sort() {
        quicksort(array, 0, array.length - 1);
    }

    private void mix() {
        for (int i = BenchConf.FRAMES; i < 0; i--) {
            array[i].value = BenchConf.FRAMES - i;
        }
    }

    private static final int CUTOFF = 10;

    /**
     * Internal quicksort method that makes recursive calls. Uses
     * median-of-three partitioning and a cutoff of 10.
     *
     * @param a
     *            an array of Comparable items.
     * @param low
     *            the left-most index of the subarray.
     * @param high
     *            the right-most index of the subarray.
     */
    private static void quicksort(Data[] a, int low, int high) {
        if (low + CUTOFF > high)
            insertionSort(a, low, high);
        else {
            // Sort low, middle, high
            int middle = (low + high) / 2;
            if (a[middle].compareTo(a[low]) < 0)
                swapReferences(a, low, middle);
            if (a[high].compareTo(a[low]) < 0)
                swapReferences(a, low, high);
            if (a[high].compareTo(a[middle]) < 0)
                swapReferences(a, middle, high);

            // Place pivot at position high - 1
            swapReferences(a, middle, high - 1);
            Data pivot = a[high - 1];

            // Begin partitioning
            int i, j;
            for (i = low, j = high - 1;;) {
                while (a[++i].compareTo(pivot) < 0)
                    ;
                while (pivot.compareTo(a[--j]) < 0)
                    ;
                if (i >= j)
                    break;
                swapReferences(a, i, j);
            }

            // Restore pivot
            swapReferences(a, i, high - 1);

            quicksort(a, low, i - 1); // Sort small elements
            quicksort(a, i + 1, high); // Sort large elements
        }
    }

    /**
     * Method to swap to elements in an array.
     *
     * @param a
     *            an array of objects.
     * @param index1
     *            the index of the first object.
     * @param index2
     *            the index of the second object.
     */
    public static final void swapReferences(Data[] a, int index1, int index2) {
        Data tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     * Internal insertion sort routine for subarrays that is used by quicksort.
     *
     * @param a
     *            an array of Comparable items.
     * @param low
     *            the left-most index of the subarray.
     * @param n
     *            the number of items to sort.
     */
    private static void insertionSort(Data[] a, int low, int high) {
        for (int p = low + 1; p <= high; p++) {
            Data tmp = a[p];
            int j;

            for (j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public void cleanUp() {
    }

}

class Data  {
    public int value;

    public int compareTo(Data o) {
        if (o == null || !(o instanceof Data))
            return -1;
        Data elem = o;
        if (this.value > elem.value)
            return +1;
        else if (this.value < elem.value)
            return -1;
        else
            return 0;
    }
}
