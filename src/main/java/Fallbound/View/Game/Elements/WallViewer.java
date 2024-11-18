package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.View.Theme;

public class WallViewer implements ElementViewer<Wall> {

    @Override
    public void draw(GUI gui, Wall element) {
        char coverWallChar = '\u2593';
        String coverWallColor = Theme.FALLBOUND_WHITE;
        gui.drawText(element.getPosition().toPosition(), String.valueOf(coverWallChar), coverWallColor);
    }
}
