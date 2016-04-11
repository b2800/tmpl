package com.esiea.tetris.graphics;

import com.esiea.tetris.utils.vec2;

public interface Drawable {
    String[] getDrawableText();
    vec2 getDrawableRelativePosition();
    int[][] getColorMap();
}
