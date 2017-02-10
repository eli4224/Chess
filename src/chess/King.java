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
    private boolean hasMoved = false;
    public boolean hasMoved() {
        return hasMoved;
    }
    @Override
    public void moveTo(Location loc) { //TODO castling stuff
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
        if (!hasMoved) {
            if (canCastleLeft()) { //Left for player one
                Piece rookLeft = (Piece) gr.get(new Location(this.getLocation().getRow(), this.getLocation().getCol() - 4));
                if (rookLeft != null && rookLeft instanceof Rook && rookLeft.getPieceColor() == this.getPieceColor() && !((Rook) rookLeft).hasMoved()) {
                    locs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() - 2));
                }
            }
            if (canCastleRight()) { //Right for player one
                Piece rookRight = (Piece) gr.get(new Location(this.getLocation().getRow(), this.getLocation().getCol() + 3));
                if (rookRight != null && rookRight instanceof Rook && rookRight.getPieceColor() == this.getPieceColor() && !((Rook) rookRight).hasMoved()) {
                    locs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() + 2));
                }
            }
        }
        return locs;
    }
    private boolean canCastleLeft() {
        Location workingLocation = getLocation().getAdjacentLocation(Location.WEST);
        for (int i = 0; i < 3; i++) {
            if (gr.get(workingLocation) == null) {
                workingLocation = getLocation().getAdjacentLocation(Location.WEST);
                continue;
            }
            return false;
        }
        return true;
    }
    private boolean canCastleRight() {
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
