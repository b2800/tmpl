package com.esiea.tetris.utils;

import java.util.ArrayList;

public class StringUtil {
    
    // Convertit les lignes qui contienent des '\n' en tableau de plusieurs
    // lignes. 
    public static String[] linesToArray(String text){
        ArrayList<String> lines = new ArrayList<>();
        
        String current = "";
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == '\n'){
                lines.add(current);
                current = "";
            } else {
                current += text.charAt(i);
            }
        }
        if(lines.isEmpty()){  // s'il n'y avait pas de \n dans la variable text
            lines.add(current);
        }
        return lines.toArray(new String[lines.size()]);
    }
}
