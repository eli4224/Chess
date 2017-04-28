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
public class King extends Piece {
    private boolean hasMoved = false;
    public King(PlayerColor c) {
        super(c);
    }
    public boolean hasMoved() {
        return hasMoved;
    }
    @Override
    public boolean canMoveTo(Location loc) {
        Grid<Piece> gr = getGrid();
        Location myloc = getLocation();
        return /* Close Enough */ (Math.abs(myloc.getRow() - loc.getRow()) <= 1 && Math.abs(myloc.getCol() - loc.getCol()) <= 1)
                && /* Valid */ gr.isValid(loc)
                && /* Right Color */ (gr.get(loc) == null || (gr.get(loc).getPlayerColor() != this.getPlayerColor()));
    }
    @Override
    public boolean moveTo(Location loc) { //TODO castling stuff
        if (super.moveTo(loc)) {
            this.hasMoved = true;
            return true;
        }
        return false;
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Piece> gr = getGrid();
        for (Location kingmove : gr.getValidAdjacentLocations(this.getLocation())) {
            if (gr.get(kingmove) == null || gr.get(kingmove).getPlayerColor() != this.getPlayerColor()) {
                locs.add(kingmove);
            }
        }
        if (!hasMoved) {
            if (canCastleLeft()) { //Left for player one
                Piece rookLeft = (Piece) gr.get(new Location(this.getLocation().getRow(), this.getLocation().getCol() - 4));
                if (rookLeft != null && rookLeft instanceof Rook && rookLeft.getPlayerColor() == this.getPlayerColor() && !((Rook) rookLeft).hasMoved()) {
                    locs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() - 2));
                }
            }
            if (canCastleRight()) { //Right for player one
                Piece rookRight = (Piece) gr.get(new Location(this.getLocation().getRow(), this.getLocation().getCol() + 3));
                if (rookRight != null && rookRight instanceof Rook && rookRight.getPlayerColor() == this.getPlayerColor() && !((Rook) rookRight).hasMoved()) {
                    locs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() + 2));
                }
            }
        }
        return locs;
    }
    private boolean canCastleLeft() {
        Grid<Piece> gr = getGrid();
        Location workingLocation = getLocation().getAdjacentLocation(Location.WEST);
        for (int i = 0; i < 3; i++) {
            if (gr.get(workingLocation) != null) {
                return false;
            }
            workingLocation = getLocation().getAdjacentLocation(Location.WEST);
        }
        return true;
    }
    private boolean canCastleRight() {
        Grid<Piece> gr = getGrid();
        Location workingLocation = getLocation().getAdjacentLocation(Location.EAST);
        for (int i = 0; i < 2; i++) {
            if (gr.get(workingLocation) == null) {
                workingLocation = getLocation().getAdjacentLocation(Location.EAST);
                continue;
            }
            return false;
        }
        return true;
    }
}
