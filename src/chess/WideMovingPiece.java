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
public abstract class WideMovingPiece extends Piece {
    public WideMovingPiece(PlayerColor c) {
        super(c);
    }
    protected abstract int[] getMovableDirections();
    @Override
    public final ArrayList<Location> getMoves() {
        Grid<Piece> gr = getGrid();
        if (!gr.isValid(this.getLocation())) {
            throw new NullPointerException("Piece location not on grid");
        }
        Location workingLocation;
        ArrayList<Location> locs = new ArrayList();
        for (int direction : getMovableDirections()) {
            workingLocation = this.getLocation();
            while (true) {
                workingLocation = workingLocation.getAdjacentLocation(direction);
                if (!gr.isValid(workingLocation)) {
                    break;
                }
                if (gr.get(workingLocation) == null) {
                    locs.add(workingLocation);
                    continue;
                }
                if (gr.get(workingLocation).getPlayerColor() == this.getPlayerColor()) {
                    break;
                }
                if (gr.get(workingLocation).getPlayerColor() != this.getPlayerColor()) {
                    locs.add(workingLocation);
                    break;
                }
                throw new RuntimeException("The location (" + workingLocation.getRow() + "," + workingLocation.getCol() + ") on the grid caused an error");
            }
        }
        return locs;
    }
    public boolean canMoveTo(Location loc) {
        Grid<Piece> gr = this.getGrid();
        if (!gr.isValid(loc)) {
            return false;
        }
        int dR = Math.abs(this.getLocation().getRow() - loc.getRow());
        int dC = Math.abs(this.getLocation().getCol() - loc.getCol());
        if (dR != 0 && dC != 0 && dR != dC) {
            return false;
        }
        int direction = this.getLocation().getDirectionToward(loc);
        for (int movableDirection : this.getMovableDirections()) {
            if (direction != movableDirection) {
                continue;
            }
            for (Location workingLocation = this.getLocation().getAdjacentLocation(direction); !workingLocation.equals(loc); workingLocation = workingLocation.getAdjacentLocation(direction)) {
                if (gr.get(workingLocation) != null) {
                    return false;
                }
            }
            return gr.get(loc) == null || gr.get(loc).getPlayerColor() != this.getPlayerColor();
        }
        return false;
    }
}
