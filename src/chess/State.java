/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Piece.PLAYER_ONE_COLOR;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elicowa
 */
public class State {
    public final long seed;
    public final Grid<Piece> myGrid;
    List<Long> p1Moves = null; //player one
    List<Long> p2Moves = null; //player two
    private double rawScore = Double.NaN;
    public State(long l, Grid<Piece> g) {
        seed = l;
        myGrid = g;
    }
    public void populateMoves(Piece.PlayerColor color) {
        List<Long> tempList = new ArrayList<Long>();
        for (Location loc : myGrid.getOccupiedLocations()) {
            Piece p = myGrid.get(loc);
            if (p.getPlayerColor() == color) {
                for (Location move : p.getMoves()) {
                }
            }
        }
    }
    public List<Long> getMoves(Piece.PlayerColor color) {
        if (color == PLAYER_ONE_COLOR) {
            if (p1Moves == null) {
                populateMoves(color);
            }
            return p1Moves;
        } else {
            if (p2Moves == null) {
                populateMoves(color);
            }
            return p2Moves;
        }
    }
}
