/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.Location;

/**
 *
 * @author elicowa
 */
public class Rook extends WideMovingPiece {
    private boolean hasMoved = false;
    public boolean hasMoved() {
        return hasMoved;
    }
    @Override
    public void moveTo(Location loc) {
        super.moveTo(loc);
        this.hasMoved = true;
    }
    public final static int[] movableDirections = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
    @Override
    protected int[] getMovableDirections() {
        return movableDirections;
    }
}
