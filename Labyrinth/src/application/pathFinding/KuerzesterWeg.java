package application.pathFinding;

import java.io.BufferedReader;
import java.nio.file.*;
import java.util.List;


public class KuerzesterWeg {

    public static int labyrinthGroesse = 21;
    public static int[][] labyrinth = new int[labyrinthGroesse][labyrinthGroesse];


    public static void main(String[] args) {
        labyrinth = labyrinthEinlesen();

        // Graph erstellen
        GraphKnoten[][] labyrinthKnoten = graphErstellen();

        // Start und Ziel festlege
        int startZeile = 1;
        int startSpalte = 1;
        int zielZeile = 19;
        int zielSpalte = 19;

        //kürzesten Weg finden
        List<GraphKnoten> kuerzesterWeg = wegFinden(startSpalte, startZeile, zielSpalte, zielZeile, labyrinthKnoten);

        System.out.println("kürzester Weg: " + kuerzesterWeg);
        System.out.println("SIIIUUUUUUU");
    }


    public static int[][] labyrinthEinlesen (){
        try{
            Path labyrinthDatei = Paths.get("Labyrinth/res/labyrinth9.txt");

            //Einlesen
            BufferedReader reader = Files.newBufferedReader(labyrinthDatei);

            String zeile = reader.readLine();
            for (int spalte = 0; spalte < labyrinthGroesse; spalte++){
                for(int reihe = 0; reihe < labyrinthGroesse; reihe++){
                    //System.out.println(zeile);
                    if (zeile != null){
                        labyrinth [reihe][spalte] = Integer.parseInt(zeile);
                        zeile = reader.readLine();
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return labyrinth;
    }


    public static GraphKnoten[][] graphErstellen() {															// Graph wird generiert
        GraphKnoten[][] labyrinthKnoten = new GraphKnoten[labyrinthGroesse][labyrinthGroesse];                           // Labyrinth auslesen + in Graph einlesen
        for (int zeile = 0; zeile < labyrinthGroesse; zeile++) {
            for (int spalte = 0; spalte < labyrinthGroesse; spalte++) {
                int wert = labyrinth[zeile][spalte];
                if (wert == 1 ) {
                    GraphKnoten knoten = labyrinthKnoten[zeile][spalte];
                    if (knoten == null) {
                        knoten = new GraphKnoten("Position:" + spalte + "," + zeile);
                        labyrinthKnoten[zeile][spalte] = knoten;
                    }
                    pruefeNachbar(labyrinthKnoten, zeile, spalte, zeile - 1, spalte);
                    pruefeNachbar(labyrinthKnoten, zeile, spalte, zeile + 1, spalte);
                    pruefeNachbar(labyrinthKnoten, zeile, spalte, zeile, spalte - 1);
                    pruefeNachbar(labyrinthKnoten, zeile, spalte, zeile, spalte + 1);
                }
            }
        }
        return labyrinthKnoten;
    }


    public static void pruefeNachbar(GraphKnoten[][] labyrinthKnoten, int zeile, int spalte, int nachbarZeile, int nachbarSpalte) {
        if (nachbarZeile >= 0 && nachbarSpalte >= 0 && nachbarZeile < labyrinthGroesse && nachbarSpalte < labyrinthGroesse) {
            if (labyrinthKnoten[nachbarZeile][nachbarSpalte] != null) {
                labyrinthKnoten[zeile][spalte].addNachbar(labyrinthKnoten[nachbarZeile][nachbarSpalte]);                                // "add Nachbar" ändern
            }
        }
    }


    public static List<GraphKnoten> wegFinden(int startSpalte, int startZeile, int zielSpalte, int zielZeile, GraphKnoten[][] labyrinthKnoten){
        GraphKnoten ziel;
        GraphKnoten start;
        if (startZeile == -1 || startSpalte == -1) {                                                                     // Wenn kein Startpunkt definiert wurde -> Start = (1,1)
            start = labyrinthKnoten[1][1];
        } else {
            start = labyrinthKnoten[startZeile][startSpalte];
        }
        if (zielZeile == -1 || zielSpalte == -1) {                                                                       // Wenn kein Zielpunkt definiert wurde -> Ziel = gegenüber Start
            ziel = labyrinthKnoten[labyrinthGroesse - 2][labyrinthGroesse - 2];
        } else {
            ziel = labyrinthKnoten[zielZeile][zielSpalte];
        }

        return BreitensucheGraph.findePfad(start, ziel);
    }



}
