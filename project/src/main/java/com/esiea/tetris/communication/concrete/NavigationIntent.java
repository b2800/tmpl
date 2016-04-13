package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;
import com.esiea.tetris.model.Layout;

public class NavigationIntent extends Message{
    public Layout nextLayout;
}
