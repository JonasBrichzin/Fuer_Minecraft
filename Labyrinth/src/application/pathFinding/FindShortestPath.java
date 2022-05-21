package application.pathFinding;

import java.io.BufferedReader;
import java.nio.file.*;
import java.util.List;


public class FindShortestPath {

    public static int labyrinthSize = 21;
    public static int[][] labyrinth = new int[labyrinthSize][labyrinthSize];
    public static int[][] lab = new int[labyrinthSize][labyrinthSize];




    public static void main(String[] args) {
        labyrinth = labyrinthEinlesen();

        // Graph erstellen
        GraphKnoten[][] labyrinthKnoten = graphErstellen();

        int startZeile = 1;
        int startSpalte = 1;
        int zielZeile = 19;
        int zielSpalte = 19;

        //schnellsten Weg finden
        List<GraphKnoten> schnellsterWeg = wegFinden(startSpalte, startZeile, zielSpalte, zielZeile, labyrinthKnoten);

        System.out.println("schnellster Weg: " + schnellsterWeg);
        System.out.println("fertig");
    }


    public static int[][] labyrinthEinlesen (){
        try{
            Path labyrinthDatei = Paths.get("Labyrinth/res/labyrinth9.txt");

            //Einlesen
            BufferedReader meinReader = Files.newBufferedReader(labyrinthDatei);

            String zeile = meinReader.readLine();
            for (int col = 0; col < labyrinthSize; col++){
                //String zeile = meinReader.readLine();
                //System.out.println(" - - - - - - - - ");
                for(int row = 0; row < labyrinthSize; row++){
                    //System.out.println(zeile);
                    if (zeile != null){
                        lab [row][col] = Integer.parseInt(zeile);
                        zeile = meinReader.readLine();
                    }
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return lab;
    }

    //public void labyrinthSpeichern(){
    //    int [][] labyrinth = new int[][]{   {0,0,0,0,0,0},
    //                                        {0,1,1,1,1,0},
    //                                        {0,0,0,1,0,0},
    //                                        {0,1,1,1,1,0},
    //                                        {0,0,0,0,0,0} };
//
    //    for (int z = 0; z < labyrinth.length; z++) {
    //        String zeile = "";
    //        for (int s = 0; s < labyrinth.length; s++) {
    //            zeile = zeile + ""labyrinth[z][s]
//
    //        }
    //    }
    //}

    public static GraphKnoten[][] graphErstellen() {															// Graph wird generiert
        GraphKnoten[][] labyrinthKnoten = new GraphKnoten[labyrinthSize][labyrinthSize];                           // Labyrinth auslesen + in Graph einlesen
        for (int z = 0; z < labyrinthSize; z++) {
            for (int s = 0; s < labyrinthSize; s++) {
                int wert = labyrinth[z][s];
                if (wert == 1 ) {
                    GraphKnoten tmp = labyrinthKnoten[z][s];
                    if (tmp == null) {
                        tmp = new GraphKnoten("Position:" + s + "," + z);
                        labyrinthKnoten[z][s] = tmp;
                    }
                    pruefeNachbar(labyrinthKnoten, z, s, z - 1, s, labyrinthSize, labyrinthSize);
                    pruefeNachbar(labyrinthKnoten, z, s, z + 1, s, labyrinthSize, labyrinthSize);
                    pruefeNachbar(labyrinthKnoten, z, s, z, s - 1, labyrinthSize, labyrinthSize);
                    pruefeNachbar(labyrinthKnoten, z, s, z, s + 1, labyrinthSize, labyrinthSize);
                }
            }
        }
        return labyrinthKnoten;
    }


    public static void pruefeNachbar(GraphKnoten[][] labyrinthKnoten, int z, int s, int nachbarZ, int nachbarS, int maxZeilen,
                                     int maxSpalten) {
        if (nachbarZ >= 0 && nachbarS >= 0 && nachbarZ < maxZeilen && nachbarS < maxSpalten) {
            if (labyrinthKnoten[nachbarZ][nachbarS] != null) {
                labyrinthKnoten[z][s].addNachbar(labyrinthKnoten[nachbarZ][nachbarS]);
            }
        }
    }


    public static List<GraphKnoten> wegFinden(int startS, int startZ, int zielS, int zielZ, GraphKnoten[][] labyrinthKnoten){
        GraphKnoten ziel;
        GraphKnoten start;
        if (startZ == -1 || startS == -1) {                                                                     // Wenn kein Startpunkt definiert wurde -> Start = oben rechts
            start = labyrinthKnoten[labyrinthSize - 1][0];
        } else {
            start = labyrinthKnoten[startZ][startS];
        }
        if (zielZ == -1 || zielS == -1) {                                                                       // Wenn kein Zielpunkt definiert wurde -> Ziel = unten links
            ziel = labyrinthKnoten[0][labyrinthSize - 1];
        } else {
            ziel = labyrinthKnoten[zielZ][zielS];
        }

        // Aufruf der Methode aus der Übungsaufgabe
        List<GraphKnoten> path = BreitensucheGraph.findePfad(start, ziel);                                  // Pfad finden

        //System.out.println("Ziel: "+ziel+ " Pfad: "+path);
        return path;
    }



}
