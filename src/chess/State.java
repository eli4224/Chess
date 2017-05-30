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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author elicowa
 */
public class State {
    public static HashMap<ByteArrayWrapper, State> stateMap = new HashMap<ByteArrayWrapper, State>();
    public final ByteArrayWrapper seed;
    public final Grid<Piece> myGrid;
    List<ByteArrayWrapper> p1Moves = null; //player one
    List<ByteArrayWrapper> p2Moves = null; //player two
    private Double rawScore = null;
    ChessWorld.Result bestScore = null;
    public State(ByteArrayWrapper l, Grid<Piece> g) {
        seed = l;
        myGrid = g;
    }
    private void populateMoves(Piece.PlayerColor color) {
        List<ByteArrayWrapper> tempList = new ArrayList<ByteArrayWrapper>();
        for (Location loc : myGrid.getOccupiedLocations()) {
            Piece p = myGrid.get(loc);
            if (p.getPlayerColor() == color) {
                for (Location move : p.getMoves()) {
                    ByteArrayWrapper hash = seed.clone();
                    //Before Moving
                    hash.xor(Chess.sha1(loc, p.getClass(), color));
                    //After Moving
                    hash.xor(Chess.sha1(move, p.getClass(), color));
                    //Captured Piece
                    if (myGrid.get(move) != null) {
                        hash.xor(Chess.sha1(move, myGrid.get(move).getClass(), Piece.PlayerColor.values()[color.ordinal() ^ 1]));
                    }
                    tempList.add(hash);
                    if (!stateMap.containsKey(hash)) {
                        ChessGrid tempGr = ((ChessGrid) myGrid).clone();
                        tempGr.get(loc).moveTo(move);
                        stateMap.put(hash, new State(hash, tempGr));
                    }
                }
            }
        }
        if (color == PLAYER_ONE_COLOR) {
            p1Moves = tempList;
        } else {
            p2Moves = tempList;
        }
    }
    public List<ByteArrayWrapper> getMoves(Piece.PlayerColor color) {
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
    public double getScore() {
        if (rawScore == null) {
            double white = 0;
            double black = 0;
            for (Location loc : myGrid.getOccupiedLocations()) {
                Piece p = myGrid.get(loc);
                if (p.getPlayerColor() == Piece.PLAYER_ONE_COLOR) {
                    white += p.getValue();
                } else {
                    black += p.getValue();
                }
            }
            if (white < King.VALUE) {
                return (rawScore = Double.NEGATIVE_INFINITY);
            }
            if (black < King.VALUE) {
                return (rawScore = Double.POSITIVE_INFINITY);
            }
            rawScore = (white - black) / Math.sqrt(white + black - King.VALUE);
        }
        return rawScore;
    }
}
