package com.esiea.tetris.model.concrete.console.command;

import com.esiea.tetris.utils.ScoreUtil;

public class HighScoresCommand implements Command{

    @Override
    public String execute(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("Meilleurs scores : \n");
        for(int score : ScoreUtil.getHighScores()){
            builder.append(Integer.toString(score));
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String getName() {
        return "scores";
    }
}
