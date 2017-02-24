/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Piece.PLAYER_ONE_COLOR;
import chess.Piece.PlayerColor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 *
 * @author elicowa
 */
public class ChessWorld extends World<Piece> {
    private static final String DEFAULT_MESSAGE = "Click on a grid location to construct or manipulate an actor.";
    PlayerColor whichTurn = PLAYER_ONE_COLOR;
    Piece workingPiece = null;
    public ChessWorld() {
        super(new BoundedGrid<>(8, 8));
    }
    @Override
    public boolean locationClicked(Location loc) {
        Grid<Piece> grid = getGrid();
        Piece clickedPiece = grid.get(loc);
        if (clickedPiece != null && clickedPiece.getPlayerColor() == whichTurn) {
            workingPiece = clickedPiece;
        }
        if (workingPiece != null && workingPiece.moveTo(loc)) {
            whichTurn = PlayerColor.values()[whichTurn.ordinal() ^ 1];
            workingPiece = null;
        }
        return true;
    }
    public void show() {
        if (getMessage() == null) {
            setMessage(DEFAULT_MESSAGE);
        }
        super.show();
    }
    public void add(Location loc, Piece occupant) {
        occupant.putSelfInGrid(getGrid(), loc);
    }
    public Piece remove(Location loc) {
        Piece occupant = getGrid().get(loc);
        if (occupant == null) {
            return null;
        }
        occupant.removeSelfFromGrid();
        return occupant;
    }
    /*public void step() {
       Chess.main(null);
    }*/
    /**
     * Adds an occupant at a random empty location.
     *
     * @param occupant the occupant to add
     */
}
