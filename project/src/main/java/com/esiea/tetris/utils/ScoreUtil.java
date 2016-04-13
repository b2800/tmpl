package com.esiea.tetris.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreUtil {
	
    private static final String filename = "scores.txt";
	
    public static int[] getHighScores(){
        int[] scores = new int[]{0,0,0,0,0};
        BufferedReader reader;
        FileReader fileReader;
        try {
            fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 5){
              scores[i] = Integer.parseInt(line);
              i++;
            }
            fileReader.close();
            reader.close();
        } catch (IOException | NumberFormatException ex){
            Logger.getLogger(ScoreUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }
    
    public static void writeHighScore(int score){
    	
    	int[] scores = getHighScores();
    	
    	String content="";
    	
    	int indiceToChange = 0;
    	boolean decalerScore=false;
    	
    	for(int i=0; i<5;i++) // on parcourt le tableau dans l'ordre des scores décroissants
    	{
            if(decalerScore) // si le score du joueur doit s'insérer dans le classement, il faut décaler les autres
            {
                scores[i]=scores[i-1];
            }
            else if(score>scores[i]) // si le score testé est moins bon que celui du joueur
            {
                indiceToChange=i; // on sauvegarde l'emplacement du score du joueur
                decalerScore=true;
            }
    	}
    	
    	
    	if(decalerScore){
            scores[indiceToChange]=score;

            for(int i=0; i<5;i++){
                System.out.println(scores[i]);
            }

            // Réécriture du contenu du fichier avec le nouveau score

            BufferedWriter writer;
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(filename);
                writer = new BufferedWriter(fileWriter);
                String line;

                for(int i=0; i<5;i++){
                    writer.write(String.valueOf(scores[i]));
                    writer.newLine();
                }
                
                fileWriter.close();
                writer.close();
            } catch (Exception ex){
                Logger.getLogger(ScoreUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
}
    

