package com.esiea.tetris.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreUtil {
    public static int[] getHighScores(){
        int[] scores = new int[5];
        String filename = "scores.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 5){
              scores[i] = Integer.parseInt(line);
              i++;
            }
            reader.close();
        } catch (Exception ex){
            Logger.getLogger(ScoreUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }
    
    public static void writeHighScore(int score){
        // Récupérer les scores déja existant
        // Vérifier si le score a écrire fait bien partit des 5 meilleurs
        // Retrier le nouveau tableau
        // Réécrire les nouveaux scores dans le fichier.
    }
}
