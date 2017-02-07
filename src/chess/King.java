/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author elicowa
 */
public class King extends Piece {
    boolean hasMoved = false;
    @Override
    public void moveTo(Location loc) {
        super.moveTo(loc);
        this.hasMoved = true;
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Actor> gr = getGrid();
        for (Location kingmove : gr.getValidAdjacentLocations(this.getLocation())) {
            if (gr.get(kingmove) == null || (gr.get(kingmove) instanceof Piece && ((Piece) gr.get(kingmove)).getPieceColor() != this.getPieceColor())) {
                locs.add(kingmove);
            }
        }
        return locs;
    }
}
