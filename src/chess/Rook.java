/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author elicowa
 */
public class Rook extends WideMovingPiece {
    boolean hasMoved = false;
    @Override
    public void moveTo(Location loc) {
        super.moveTo(loc);
        this.hasMoved = true;
    }
    public final int[] movableDirections = {0, 90, 180, 270};
    @Override
    public ArrayList<Location> getMoves() {
        return super.getMoves(movableDirections);
    }
}
