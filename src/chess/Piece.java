/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author elicowa
 */
public abstract class Piece extends Actor {
    public final static Color PLAYER_ONE_COLOR = Color.WHITE;
    public final static Color PLAYER_TWO_COLOR = Color.BLACK;
    public boolean canMoveTo(Location loc) { //TODO: If I have time make this abstract and implement it in each subclass
        for (Location l : getMoves()) {
            if (l.equals(loc)) {
                return true;
            }
        }
        return false;
    }
    public abstract ArrayList<Location> getMoves();
    @Override
    public void moveTo(Location loc) {
        if (!canMoveTo(loc)) {
            throw new IllegalArgumentException();
        }
        super.moveTo(loc);
    }
    private Color color;
    public Color getPieceColor() {
        return color;
    }
    public static enum Color {
        WHITE, BLACK;
    }
}
