import java.util.Random;

public abstract class SortingVisualizer {
    protected Surface surface;
    int swaps = 0;
    int arrayAccesses = 0;
    Random rd = new Random();
    int[] randomList;

    public SortingVisualizer(Surface surface) {
        this.surface = surface;
        randomList = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255};
        // randomizeList();
        reverseList();
    }

    public int getSwaps() {
        return swaps;
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }

    public int[] getRandomList() {
        return randomList;
    }

    public void randomizeList() {
        for(int i = 0; i < randomList.length; i++) {
            int temp = randomList[i];
            int location = rd.nextInt(255);
            randomList[i] = randomList[location];
            randomList[location] = temp;
        }
    }

    public void reverseList() {
        for(int i = 0; i < randomList.length/2; i++) {
            int temp = randomList[i];
            randomList[i] = randomList[randomList.length-i-1];
            randomList[randomList.length-i-1] = temp;
        }
    }

    public String getPrintableArray(int[] arrayToPrint) {
        String arrayString = "";
        for(int i = 0; i < arrayToPrint.length; i++) {
            arrayString += arrayToPrint[i] + ", ";
        }

        return arrayString;
    }

    public boolean isSorted() {
        for(int i = 0; i < randomList.length; i++) {
            if(!(randomList[i] == i)) {
                return false;
            }
        }
        return true;
    }

    public void sort() {
        swaps = 0;
        arrayAccesses = 0;
    }

    public void swap(int index1, int index2) {
        if(index1 != index2) {
            int temp = randomList[index1];
            randomList[index1] = randomList[index2];
            randomList[index2] = temp;
            surface.repaint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                //don cayre
            }
            swaps++;
            arrayAccesses += 4;
        }
    }
}
