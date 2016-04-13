package com.esiea.tetris.utils;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestScoreUtil {
	
	String savedFilename;
	String filename="test.txt";

	@Before
	public void init()
	{
		// on récupère le nom du fichier de score d'origine
		savedFilename=ScoreUtil.getFilename();
		// on utilise un autre fichier de score factice pour les tests, afin de ne pas altérer l'original
		ScoreUtil.changeFilename(filename);
		
	}
	
	public void initFile()
	{
		BufferedWriter writer;
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filename);
            writer = new BufferedWriter(fileWriter);

            writer.write("1000");
            writer.newLine();
            writer.write("700");
            writer.newLine();
            writer.write("500");
            writer.newLine();
            writer.write("300");
            writer.newLine();
            writer.write("100");
            

            writer.close();
            fileWriter.close();
        } catch (Exception ex){
            Logger.getLogger(TestScoreUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void deleteFile()
	{
		// on supprime le fichier de score créé pour les tests
		(new File(filename)).delete();
	}
	
	
	@Test
	public void itShouldWriteTheContentOfTheFileInAnArray() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		int[] scores={1000,700,500,300,100};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
	}
	
	
	@Test
	public void itShouldNotInsertTheScoreOfThePlayerInTheFileWhenTheScoreIsNotGoodEnough() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		// On essaie d'insérer le score 50, mais celui-ci n'est pas assez bon pour s'insérer dans le classement
		ScoreUtil.writeHighScore(50);
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		int[] scores={1000,700,500,300,100};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
		
	}
	
	
	@Test
	public void itShouldInsertTheScoreOfThePlayerInTheFileWhenTheScoreIsGoodEnoughToBeAtFirstPlace() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		
		// On essait d'insérer le score 800, qui est assez bon pour entrer dans le classement
		ScoreUtil.writeHighScore(1500);
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		//int[] scores={1000,700,500,300,100};
		int[] scores={1500,1000,700,500,300};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
		
	}
	
	@Test
	public void itShouldInsertTheScoreOfThePlayerInTheFileWhenTheScoreIsGoodEnoughToBeAtSecondPlace() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		
		// On essait d'insérer le score 800, qui est assez bon pour entrer dans le classement
		ScoreUtil.writeHighScore(800);
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		//int[] scores={1000,700,500,300,100};
		int[] scores={1000,800,700,500,300};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
		
	}
	
	@Test
	public void itShouldInsertTheScoreOfThePlayerInTheFileEvenWhenTheScoreIsTheSameThanAnother() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		
		// On essait d'insérer le score 800, qui est assez bon pour entrer dans le classement
		ScoreUtil.writeHighScore(500);
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		//int[] scores={1000,700,500,300,100};
		int[] scores={1000,700,500,500,300};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
		
	}
	
	@Test
	public void itShouldInsertTheScoreOfThePlayerInTheFileWhenTheScoreIsGoodEnoughToBeAtLastPlace() {
		
		initFile(); // on réinitialise le fichier car on ne peut pas gèrer l'ordre d'exécution des tests
		
		
		// On essait d'insérer le score 800, qui est assez bon pour entrer dans le classement
		ScoreUtil.writeHighScore(150);
		
		int [] scoresToCheck=ScoreUtil.getHighScores();
		//int[] scores={1000,700,500,300,100};
		int[] scores={1000,700,500,300,150};
		
		for(int i=0;i<5;i++)
		{
			assertEquals(scores[i],scoresToCheck[i]);
		}
		
		deleteFile();
		
	}
	
	
	@After
	public void end()
	{
		// on réinitialise le nom du fichier de score
		ScoreUtil.changeFilename(savedFilename);
		
	}

}
