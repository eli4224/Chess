/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

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
    public Pawn(PlayerColor c) {
        super(c);
    }
    @Override
    public boolean moveTo(Location loc) {
        if (super.moveTo(loc)) {
            this.hasMoved = true;
            if (this.getLocation().getRow() != loc.getRow()) {
                this.homeRow = false;
            }
            if ((loc.getRow() == 0 && this.getPlayerColor() == PLAYER_ONE_COLOR) || loc.getRow() == 7 && this.getPlayerColor() == PLAYER_TWO_COLOR) {
                new Queen(this.getPlayerColor()).putSelfInGrid(grid, loc);
            }
            return true;
        }
        return false;
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Piece> gr = getGrid();
        int rowDirection = this.getPlayerColor() == Piece.PLAYER_ONE_COLOR ? Location.NORTH : Location.SOUTH;
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
            if (gr.isValid(take) && gr.get(take) != null && gr.get(take).getPlayerColor() != this.getPlayerColor()) {
                locs.add(take);
            }
        }
        return locs;
    }
}
