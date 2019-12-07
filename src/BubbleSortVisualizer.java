import java.util.Random;

public class BubbleSortVisualizer extends SortingVisualizer {


    public BubbleSortVisualizer(Surface surface) {
        super(surface);
    }


    @Override
    public void sort() {
        super.sort();
        for(int i = 0; i < randomList.length-1; i++) {
            for(int j = i; j >= 0; j--) {
                arrayAccesses += 2;
                if(randomList[j] > randomList[j+1]) {
                    swap(j,j+1);
                }
            }
        }
        surface.sortComplete();
    }
}
