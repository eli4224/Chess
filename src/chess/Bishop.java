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
public class Bishop extends WideMovingPiece {
    public final int[] movableDirections = {Location.NORTHEAST, Location.NORTHWEST, Location.SOUTHEAST, Location.SOUTHWEST};
    @Override
    protected int[] getMovableDirections() {
        return movableDirections;
    }
}
