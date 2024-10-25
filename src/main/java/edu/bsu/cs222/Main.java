package edu.bsu.cs222;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArtistByGenre artistByGenre = new ArtistByGenre();
        Scanner scanner = new Scanner(System.in);
        boolean validGenre = false;
        while (!validGenre) {
            try {
                System.out.print("\nEnter a genre: ");
                String genre = scanner.nextLine();
                artistByGenre.getArtistByGenre(genre.toLowerCase());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
