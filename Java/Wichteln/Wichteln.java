import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class Wichteln {

    private String[] spieler;
    private int anzahlSpieler;
    private int anzahlGeschenke;

    public Wichteln (){
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the number of gifts
        System.out.print("Wie viele Geschenke sollen von jeder Person gekauft werden?: ");
        this.anzahlGeschenke = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline after nextInt()

        // Get the player names
        Stack<String> playerStack = new Stack<>();
        this.anzahlSpieler = 0;

        // Prompt the user for their name
        System.out.print("Name der ersten Mitspieler*In: ");
        String name = scanner.nextLine();  // Read a string input

        // Add the first participant
        playerStack.push(name);
        this.anzahlSpieler++;

        // Prompt the user for the other names
        while (true) {  // Infinite loop, will break when nothing is entered
            System.out.print("Name der naechsten Mitspieler*In (Enter falls niemand mehr teilnimmt): ");
            name = scanner.nextLine();
            if (name.equals("")) {
                break;  // Exit the loop if user enters "q"
            }
            playerStack.push(name);
            this.anzahlSpieler++;
        }

        this.spieler = new String[this.anzahlSpieler];

        int counter = 0;
        while (!playerStack.isEmpty()) {
            this.spieler[counter] = playerStack.pop();
            counter++;
        }
    }

    public ArrayList<String> shuffle() {
        int g = this.getAnzahlGeschenke();
        int s = this.getAnzahlSpieler();
        int[][] distribution = Zufallsziehung.distribute(s, g);
        int[][] distributionTranspose = new int[s][g];
        ArrayList<String> result = new ArrayList<>(s);
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < g; j++) {
                distributionTranspose[i][j] = distribution[j][i];
            }
        }
        for (int i = 0; i < s; i++) {
            String werBeschenktWen = this.spieler[i] + " beschenkt ";
            for (int j = 0; j < g; j++) {
                werBeschenktWen += this.spieler[distributionTranspose[i][j]] + ", ";
            }
            result.add(werBeschenktWen);
        }
        return result;
    }

    public int getAnzahlSpieler(){
        return this.anzahlSpieler;
    }


    public static void main(String[] args) {

        // Initialize the parameters
        Wichteln wichteln = new Wichteln();

        // Shuffle
        ArrayList<String> auslosung = wichteln.shuffle();

        // Print the result
        while (!auslosung.isEmpty()) {
            System.out.println(auslosung.getFirst());
            auslosung.removeFirst();
        }
    }

    public int getAnzahlGeschenke() {
        return anzahlGeschenke;
    }

    public void setAnzahlGeschenke(int anzahlGeschenke) {
        this.anzahlGeschenke = anzahlGeschenke;
    }
}



