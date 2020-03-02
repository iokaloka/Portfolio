
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//Tällä luokalla luodaan ja päivitetään korkeimpien pisteiden taulukkoa (Highest scores)

public class Records {

    private String scores = "highscores.txt";
    private String[][] scoreboard;
    private Background bg;
    private Font font;
    private Game game;
    private Scanner s;

    public Records(Game game) {
        scoreboard = new String[10][3];
        bg = new Background(this.game, "/scores1.png");
        this.font = new Font("Menu", Font.BOLD, 30);
        this.game = game;
        this.s = new Scanner(System.in);
    }
    
//    try {
//			Path path = Paths.get("C:\\resolvethis", "testfile.txt");
//			Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
//			if (hidden != null && !hidden) {
//				Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
//				System.out.println("File is now hidden!");
//			}
//		} catch (IOException ex) {
//			System.err.println("Things went wrong: " + ex.getMessage());
//			ex.printStackTrace();
//		}

    public boolean checkSaveFile() {
        boolean flag = false;
        try {
            File file = new File(scores);
            if (file.createNewFile()) {
                System.out.println("Done"); //Ei ollut aikaisemmin, luotiin nyt
                
                flag = true;
                
            } else {
                System.out.println("Not done"); //Tiedosto oli aiemmin olemassa
            }
        } catch (Exception e) {
            System.out.println("Failed creating file");
        }
        return flag;
    }

    public void formate() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    scoreboard[i][j] = i + 1 + " ";
                } else if (j == 1) {
                    scoreboard[i][j] = 0 + " ";
                } else if (j == 2) {
                    scoreboard[i][j] = "- ";
                }
            }
        }
    }
    
    
    //Tallennetaan ennätystulokset
    public void save() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"));
            writer.write(scoreboard[0][0].trim() + " " + scoreboard[0][1].trim() + " " + scoreboard[0][2].trim() + " "
                    + scoreboard[1][0].trim() + " " + scoreboard[1][1].trim() + " " + scoreboard[1][2].trim() + " "
                    + scoreboard[2][0].trim() + " " + scoreboard[2][1].trim() + " " + scoreboard[2][2].trim() + " "
                    + scoreboard[3][0].trim() + " " + scoreboard[3][1].trim() + " " + scoreboard[3][2].trim() + " "
                    + scoreboard[4][0].trim() + " " + scoreboard[4][1].trim() + " " + scoreboard[4][2].trim() + " "
                    + scoreboard[5][0].trim() + " " + scoreboard[5][1].trim() + " " + scoreboard[5][2].trim() + " "
                    + scoreboard[6][0].trim() + " " + scoreboard[6][1].trim() + " " + scoreboard[6][2].trim() + " "
                    + scoreboard[7][0].trim() + " " + scoreboard[7][1].trim() + " " + scoreboard[7][2].trim() + " "
                    + scoreboard[8][0].trim() + " " + scoreboard[8][1].trim() + " " + scoreboard[8][2].trim() + " "
                    + scoreboard[9][0].trim() + " " + scoreboard[9][1].trim() + " " + scoreboard[9][2].trim());
            writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    
    //Käydään läpi tulostaulukko kun uusi peli on pelattu
    public void read() {
        try {
            File file = new File(scores);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String score = sc.nextLine();
                String[] words = score.split(" ");
                int index = 0;
                int row = 0;
                for (String word : words) {
                    scoreboard[row][index] = word;
                    if (index < 2) {
                        index++;
                    } else {
                        index = 0;
                        row++;
                    }
                }
            }
            System.out.println("Read succesfull");
            sc.close();
        } catch (Exception e) {
            System.out.println("Read failed");
        }
    }
    
    //Piirretään ennätystulosten taulukko
    public void render(Graphics g) {
        g.drawImage(bg.getImage(), 0, 0, game.getW(), game.getH(), null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                g.drawString(scoreboard[i][j], 850 + 50 * j * j, 300 + 50 * i);
            }
        }
        g.drawString("Back to Menu", 850, 900);
    }
    
    //Katsotaan, onko saatu pelitulos ennätystulos
    public boolean checkIfRecord(int score) {
        if (score > Integer.parseInt(scoreboard[9][1].trim())) {
            return true;
        } else {
            return false;
        }
    }
    
    //Tarkistetaan mille sijalle tulos nousee
    public int checkIndex(int score) {
        int index = 9;
        for (int i = 8; i >= 0; i--) {
            if (score > Integer.parseInt(scoreboard[i][1].trim())) {
                index--;
            }
        }
        return index;
    }
    
    
    //Tehdään tilaa taulukkoon siirtämällä uuden tuloksen jälkeen tulevia tuloksia alaspäin
    public void makeRoom(int index) {
        for (int i = 8; i >= index; i--) {
            for (int j = 1; j < 3; j++) {
                scoreboard[i + 1][j] = scoreboard[i][j];
            }
        }  
    }
    
    //Tallennetaan uusi ennätystulos taulukkoon
    public void saveNewScore(int index, int score, String name) {
        scoreboard[index][1] = score + "";
        scoreboard[index][2] = name;
    }

    
    //Päivitetään uudet tulokset
    public void update() {
        if (checkIfRecord(game.getHud().getScore())) {
            int index = checkIndex(game.getHud().getScore());
            makeRoom(index);
            saveNewScore(index, game.getHud().getScore(), game.getSummary().getName());
            System.out.println("Your record has been saved!");
            save();
        }
    }
}
