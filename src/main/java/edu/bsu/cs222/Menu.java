package edu.bsu.cs222;

import java.util.Scanner;

public class Menu {
    public void getMenu() throws Exception {

        ArtistByGenre artistByGenre = new ArtistByGenre();
        SongByGenre songByGenre = new SongByGenre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                    
                    acoustic | afrobeat | alt-rock | alternative | ambient | anime | \
                    
                    black-metal | bluegrass | blues | bossanova | brazil | breakbeat | \
                    
                    british | cantopop | chicago-house | children | chill | classical | \
                    
                    club | comedy | country | dance | dancehall | death-metal | \
                    
                    eep-house | detroit-techno | disco | disney | drum-and-bass | dub | \
                    
                    dubstep | edm | electro | electronic | emo | folk | \
                    
                    forro | french | funk | garage | german | gospel | \
                    
                    goth | grindcore | groove | grunge | guitar | happy | \
                    
                    hard-rock | hardcore | hardstyle | heavy-metal | hip-hop | holidays | \
                    
                    honky-tonk | house | idm | indian | indie | indie-pop | \
                    
                    industrial | iranian | j-dance | j-idol | j-pop | j-rock | \
                    
                    jazz | k-pop | kids | latin | latino | malay | \
                    
                    mandopop | metal | metal-misc | metalcore | minimal-techno | movies | \
                    
                    mpb | new-age | new-release | opera | pagode | party | \
                    
                    philippines-opm | piano | pop | pop-film | post-dubstep | power-pop | \
                    
                    progressive-house | psych-rock | punk | punk-rock | r-n-b | rainy-day | \
                    
                    reggae | reggaeton | road-trip | rock | rock-n-roll | rockabilly | \
                    
                    romance | sad | salsa | samba | sertanejo | show-tunes | \
                    
                    singer-songwriter | ska | sleep | songwriter | soul | \
                    
                    soundtracks | spanish | study | summer | swedish | synth-pop | \
                    
                    tango | techno | trance | trip-hop | turkish | work-out | world-music""");

        System.out.print("\nEnter a genre from list: ");
        String genre = scanner.nextLine().toLowerCase();

        boolean validGenre = false;
        while (!validGenre) {

            if (!genre.isEmpty()) {
                System.out.println("\nPlease pick an option to see results: ");
                System.out.println("1. Artists");
                System.out.println("2. Songs");
                System.out.println("3. Both");
                System.out.println("4. Change Genre");
                System.out.println("5. Exit");
                System.out.print(">> ");
                int num = scanner.nextInt();

                if (num == 1) {
                    artistByGenre.getArtistByGenre(genre);
                }
                else if (num == 2) {
                    songByGenre.getSongByGenre(genre);
                }
                else if (num == 3) {
                    artistByGenre.getArtistByGenre(genre);
                    songByGenre.getSongByGenre(genre);
                }
                else if (num == 4){
                    System.out.print("\nEnter a genre from list: ");
                    genre = scanner.next().toLowerCase();
                }
                else if (num == 5) {
                    validGenre = true;
                }
                else{
                    System.out.println("Not valid input");
                }
            }
        }
    }
}