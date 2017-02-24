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
    public Rook(PlayerColor c) {
        super(c);
    }
    public boolean hasMoved() {
        return hasMoved;
    }
    @Override
    public boolean moveTo(Location loc) {
        if (super.moveTo(loc)) {
            this.hasMoved = true;
            return true;
        }
        return false;
    }
    public final static int[] movableDirections = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
    @Override
    protected int[] getMovableDirections() {
        return movableDirections;
    }
}
