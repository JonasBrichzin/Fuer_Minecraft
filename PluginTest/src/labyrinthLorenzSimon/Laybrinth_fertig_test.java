package labyrinthLorenzSimon;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Laybrinth_fertig_test {
    private int pointX; //Eine Markierung zum Platzieren und Löschen von Blöcken.
    private int pointY;
    private int width; //Breite und Höhe.
    private int height;
    private byte[][] map; //Array zum Speichern der Karte
    public Laybrinth_fertig_test(int w, int h) { //Konstrukteur
        width = w;
        height = h;
        if (w % 2 != 0 && h % 2 != 0 && 5 <= w && 5 <= h) {
            map = new byte[width][height];
            make();
        } else {
            System.out.println("Erstellen Sie eine ungerade Zahl von 5 oder mehr vertikal und horizontal.");
        }
    }

    int randomPos(int muki) { //x,Gibt ungerade Zufallskoordinaten für beide y-Koordinaten zurück
        int result = 1 + 2 * (int) Math.floor((Math.random() * (muki - 1)) / 2);
        return result;
    }

    private void make() { //Erstellen Sie eine Karte

        pointX = randomPos(width);
        pointY = randomPos(height);

        for (int y = 0; y < height; y++) { //Fülle alles mit einer Wand.
            for (int x = 0; x < width; x++) {
                map[x][y] = 1;
            }
        }
        map[pointX][pointY] = 0;
        dig();

    }

    private void dig() {
        if (isAbleContinueDig() && map[pointX][pointY] == 0) {
            map[pointX][pointY] = 0;
            int direction = (int) Math.floor(Math.random() * 4);
            switch (direction) {
                case 0:
                    if (pointY != 1) {
                        if (map[pointX][pointY - 2] == 1) {
                            map[pointX][pointY - 1] = 0;
                            pointY -= 2;
                            break;//u
                        }
                    }
                case 1:
                    if (pointY != height - 2) {
                        if (map[pointX][pointY + 2] == 1) {
                            map[pointX][pointY + 1] = 0;
                            pointY += 2;
                            break;//d
                        }
                    }
                case 2:
                    if (pointX != 1) {
                        if (map[pointX - 2][pointY] == 1) {
                            map[pointX - 1][pointY] = 0;
                            pointX -= 2;
                            break;//l
                        }
                    }
                case 3:
                    if (pointX != width - 2) {
                        if (map[pointX + 2][pointY] == 1) {
                            map[pointX + 1][pointY] = 0;
                            pointX += 2;
                            break;//r
                        }
                    }
            }
            map[pointX][pointY] = 0;
            dig();
        } else if (isAbleDig()) {
            pointX = randomPos(width);
            pointY = randomPos(height);
            dig();
        }

    }

    private boolean isAbleDig() { //Sehen Sie nach, ob es noch einen Platz zum Graben gibt
        boolean result;
        int cnt = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % 2 != 0 && y % 2 != 0) {

                    if (map[x][y] != 0) {
                        cnt++;
                    }
                }
            }
        }
        if (cnt == 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private boolean isAbleContinueDig() {//Stellen Sie fest, ob noch Platz zum Graben in alle Richtungen vorhanden ist

        if (pointY != 1) {
            if (map[pointX][pointY - 2] == 1) {
                return true;
            }
        }
        if (pointY != height - 2) {
            if (map[pointX][pointY + 2] == 1) {
                return true;
            }
        }
        if (pointX != 1) {
            if (map[pointX - 2][pointY] == 1) {
                return true;
            }
        }
        if (pointX != width - 2) {
            if (map[pointX + 2][pointY] == 1) {
                return true;
            }
        }
        return false;
    }

    public void build() {
        try {
            Path labyrinth = Paths.get("PluginTest/res/labyrinth10.mcfunction");
            // Datei anlegen, wenn sie noch nicht existiert
            if (!Files.exists(labyrinth))
                Files.createFile(labyrinth);
            else {
                BufferedWriter cleaner = Files.newBufferedWriter(labyrinth);
                cleaner.close();
            }
            //Boden des Labyrinths erzeugen und den Kasten in dem Sich das Labyrinth befindet durch Luft ersetzen, damit es nicht in anderen Struktuern hineingeneriert
            // Und Aus- und Eingang erschaffen
            BufferedWriter meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
            meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~-1 ~0 minecraft:chiseled_sandstone" + "\n");
            meinWriter.write("fill ~" + (height-1) + " ~ ~" + (width-1) + " ~0 ~2 ~0 air" + "\n");
            meinWriter.close();


            for (int y = 0; y < map[0].length; y++) {

                System.out.println("");
                for (int x = 0; x < map.length; x++) {

                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
                    if (map[x][y] == 1) {
                        meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " minecraft:moss_block" + "\n");
                        meinWriter.close();
                        System.out.print("#");
                    } else {
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~0 ~-1 ~0 dirt" + "\n");
                        //meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~0 ~" + y + " air" + "\n");
                        meinWriter.write("fill ~" + x + " ~ ~" + y + " ~" + x + " ~2 ~" + y + " air" + "\n");
                        meinWriter.close();
                        System.out.print(" ");
                    }
                    // Macht Ein- und Ausgang
                    meinWriter = Files.newBufferedWriter(labyrinth, StandardOpenOption.APPEND);
                    meinWriter.write("fill ~" + 0 + " ~ ~" + 1 + " ~" + 0 + " ~2 ~" + 1 + " air" + "\n");
                    meinWriter.write("fill ~" + (width-1) + " ~ ~" + (height-2) + " ~" + (width-1) + " ~2 ~" + (height-2) + " air" + "\n");
                    meinWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[][] getMaze() {
        return map;
    }

    public static void main(String[] args) {
        Laybrinth_fertig_test maze=new Laybrinth_fertig_test(91,91);
        maze.build();
        System.out.println(maze.getMaze());
    }

}
