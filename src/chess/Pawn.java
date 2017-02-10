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
public class Pawn extends Piece {
    boolean homeRow = true;
    boolean hasMoved = false;
    @Override
    public void moveTo(Location loc) {
        super.moveTo(loc);
        this.hasMoved = true;
        if (this.getLocation().getRow() != loc.getRow()) {
            this.homeRow = false;
        }
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Actor> gr = getGrid();
        int rowDirection = getPieceColor() == Piece.PLAYER_ONE_COLOR ? Location.NORTH : Location.SOUTH;
        Location stPawnMove = this.getLocation().getAdjacentLocation(rowDirection);
        if (gr.isValid(stPawnMove) && gr.get(stPawnMove) == null) {
            locs.add(stPawnMove);
            if (!hasMoved) {
                Location dbMove = stPawnMove.getAdjacentLocation(rowDirection);
                if (gr.isValid(dbMove) && gr.get(dbMove) == null) {
                    locs.add(dbMove);
                }
            }
        }
        for (int direction : new int[]{Location.LEFT, Location.RIGHT}) {
            Location take = stPawnMove.getAdjacentLocation(direction);
            if (gr.isValid(take) && gr.get(take) != null && gr.get(take) instanceof Piece && ((Piece) gr.get(take)).getPieceColor() != this.getPieceColor()) {
                locs.add(take);
            }
        }
        return locs;
    }
}
