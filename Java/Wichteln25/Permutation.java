public class Permutation {

    private int size;

    public Permutation(int n) {
        this.size = n;
    }

    public int[] generateShift() {
        int[] cycle = new int[size];
        for (int i = 0; i < size; i++) {
            cycle[i] = (i + 1) % size;
        }
        return cycle;
    }

    public int[] generateRandomPermutation() {
        int[] cycle = new int[size];
        for (int i = 0; i < size; i++) {
            cycle[i] = i;
        }

        // Shuffle the array to create a random permutation
        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            // Swap cycle[i] with cycle[j]
            int temp = cycle[i];
            cycle[i] = cycle[j];
            cycle[j] = temp;
        }

        return cycle;
    }

}