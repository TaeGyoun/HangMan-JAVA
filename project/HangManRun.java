package project;

import java.io.*;

public class HangManRun {
    public static void main(String[] args) throws IOException {
        RandomWord rand = new RandomWord();
        rand.ClearScreen();
        rand.ExactRandWord();
    }
}