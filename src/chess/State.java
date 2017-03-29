/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Piece.PLAYER_ONE_COLOR;
import info.gridworld.grid.Grid;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elicowa
 */
class State {
    public final long seed;
    public final Grid<Piece> myGrid;
    List<Long> p1Moves = null; //player one
    List<Long> p2Moves = null; //player two
    private double rawScore = Double.NaN;
    public State(long l, Grid<Piece> g) {
        seed = l;
        myGrid = g;
    }
    public void populateMoves(Piece.PlayerColor Color) {
        List<Piece> pieceStream = myGrid.getOccupiedLocations().stream().parallel().map(myGrid::get).collect(Collectors.toList());
        int score = pieceStream.parallelStream().
    }
    public List<Long> getMoves(Piece.PlayerColor Color) {
        if (Color == PLAYER_ONE_COLOR) {
            if (p1Moves.isEmpty()) {
                populateMoves(Color);
            }
            return p1Moves;
        }
        if (p2Moves.isEmpty()) {
            populateMoves(Color);
        }
        return p2Moves;
    }
}
