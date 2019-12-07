public class QuickSortVisualizer extends SortingVisualizer {

    public QuickSortVisualizer(Surface surface) {
        super(surface);
    }


    @Override
    public void sort() {
        super.sort();
        quickSort(0,randomList.length-1);
        surface.sortComplete();
    }

    public void quickSort(int startIndex, int endIndex) {
        if (startIndex <= endIndex) {
            int midpoint = (startIndex + endIndex) / 2;
            int midValue = randomList[midpoint];
            int numsLess = 0;

            // get # of numbers less than midpoint value
            for (int i = startIndex; i <= endIndex; i++) {
                if (randomList[i] < midValue) {
                    numsLess++;
                }
            }
            int pivot = numsLess + startIndex;
            swap(midpoint, pivot);

            int startPointer = startIndex;
            int endPointer = pivot + 1;
            //make swaps from left and right sides of midValue
            while (startPointer < pivot && endPointer <= endIndex) {
                while (randomList[startPointer] < midValue) {
                    startPointer++;
                }
                if (startPointer == pivot) {
                    break;
                }
                while (endPointer < endIndex && randomList[endPointer] >= midValue) {
                    endPointer++;
                }
                swap(startPointer, endPointer);
                startPointer++;
                endPointer++;
            }
            //quicksort both chunks, excluding already-sorted number
            quickSort(startIndex, pivot - 1);
            quickSort(pivot + 1, endIndex);
        }
    }
}
