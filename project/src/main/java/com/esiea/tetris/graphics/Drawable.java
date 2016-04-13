package com.esiea.tetris.graphics;

import com.esiea.tetris.utils.Vec2;

public interface Drawable {
    String[] getDrawableText();
    Vec2 getDrawableRelativePosition();
    int[][] getColorMap();
}
