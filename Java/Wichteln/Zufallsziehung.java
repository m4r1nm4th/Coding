import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Zufallsziehung {

    public static void main(String[] args) {
        int[][] A = distribute(4,2);
        int[] B = shuffle(10);
        System.out.print(Arrays.deepToString(A));
    }

    public static int[][] distribute(int anzahlSpieler, int anzahlGeschenke){
//        initialize the result
        int[][] result = new int[anzahlGeschenke][anzahlSpieler];

//        initialize the list of columns which grow until the solution is found
        ArrayList<int[]> resultArrayList = new ArrayList<>(anzahlGeschenke);

        while (resultArrayList.size() < anzahlGeschenke) {
//            try add new column
            int[] nextShuffle = shuffle(anzahlSpieler);
            resultArrayList.add(nextShuffle);

//            check whether it worked

            if (!isValidDistribution(resultArrayList)) {
                resultArrayList.removeLast();
            }
//            repeat
        }
        result = resultArrayList.toArray(result);
        return result;
    }

    public static int[] shuffle(int deckSize){
//        create random object
        Random random = new Random();
        int counter = 0;
        int[] result = new int[deckSize];
        int[] pickNumberFrom = new int[deckSize];
        for (int i = 0; i < deckSize; i++) {
            pickNumberFrom[i] = i;
        }
        while (counter < deckSize) {
            int pickedNumber = random.nextInt(deckSize - counter);
            result[counter] = pickNumberFrom[pickedNumber];
            pickNumberFrom[pickedNumber] = pickNumberFrom[deckSize - 1 - counter];
            counter++;
        }
        return result;
    }

    public static boolean isValidDistribution(ArrayList<int[]> distribution) {
        int xLength = distribution.getFirst().length;
        int yLength = distribution.size();

        if (yLength >= xLength) {
            return false;
        }

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
//                no one should gift themselves
                if (distribution.get(j)[i] == i) {
                    return false;
                }
//                no double gifts
                for (int k = 0; k < j; k++) {
                    if (distribution.get(k)[i] == distribution.get(j)[i]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}