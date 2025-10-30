package com.wichteln;
import java.util.List;


public class Runner {

    private static List<String> SPIELER = List.of(
            "Barbara",
            "Marin",
            "Brigitte",
            "Norbert",
            "Lotte",
            "Felix");

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        UserInteraction ui = new UserInteraction();
    }
}
