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
	
    private static String filename = "scores.txt";
    
    public static void changeFilename(String name)
    {
    	filename=name;
    }
    
    public static String getFilename()
    {
    	return filename;
    }
	
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
    	
    	boolean decalerScore=true;
    	
    	if(score>scores[4]) // on vérifie rapidement si le score peut entrer dans le classement
    	{
    		
	    	for(int i=4; i>=0;i--) // on parcourt le tableau dans l'ordre des scores croissants
	    	{
	    		if(decalerScore)
	    		{
		    		if(i>0)
		    		{
		    			if(scores[i-1]<score) // Si le score au-dessus est moins bon que celui du joueur, on le décale en bas
		    			{
		    				scores[i]=scores[i-1];
		    			}
		    			else // si au-dessus est meilleur que celui du joueur, on insère le score du joueur dans le tableau, et on arrête la procédure de décalage
		    			{
		    				scores[i]=score;
		    				decalerScore=false;
		    			}
		    		}
		    		else scores[0]=score;
	    		}
	    		
	    	}
	
	
	            // Réécriture du contenu du fichier avec le nouveau score
	
	            BufferedWriter writer;
	            FileWriter fileWriter;
	            try {
	                fileWriter = new FileWriter(filename);
	                writer = new BufferedWriter(fileWriter);
	
	                for(int i=0; i<5;i++){
	                    writer.write(String.valueOf(scores[i]));
	                    writer.newLine();
	                }
	
	                writer.close();
	                fileWriter.close();
	            } catch (Exception ex){
	                Logger.getLogger(ScoreUtil.class.getName()).log(Level.SEVERE, null, ex);
	            }

        }

    }
    
}
    

