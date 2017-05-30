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
        super(new BoundedGrid<Piece>(8, 8));
    }
    @Override
    public boolean locationClicked(Location loc) {
        Grid<Piece> grid = getGrid();
        Piece clickedPiece = grid.get(loc);
        if (clickedPiece != null && clickedPiece.getPlayerColor() == whichTurn) {
            workingPiece = clickedPiece;
            return true;
        }
        if (workingPiece != null && workingPiece.moveTo(loc)) {
            whichTurn = whichTurn.getOtherColor();
            workingPiece = null;
        }
        return true;
    }
    @Override
    public void step() {
        runAI();
    }
    private void runAI() {
        Grid<Piece> grid = getGrid();
        ByteArrayWrapper workingSeed = new ByteArrayWrapper(new byte[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 3, 2, 3, 8, 4, 6});
        grid.getOccupiedLocations().stream().map(grid::get).map(x -> Chess.sha1(x.getLocation(), x.getClass(), x.getPlayerColor())).forEach(workingSeed::xor);
        State workingState = State.stateMap.get(workingSeed);
        if (workingState == null) {
            workingState = new State(workingSeed, grid);
            State.stateMap.put(workingSeed, workingState);
        }
        Result res = getBestMove(workingState, 5, whichTurn);
        State newState = res.next;
    }
    public static class Result {
        double score;
        State next;
        int analysisDepth;
        boolean whiteMate;
        boolean blackMate;
    }
    private Result getBestMove(State state, int layersRemaining, PlayerColor playerColor) {
        return new Result();
    }/*{
        Result cached = state.bestScore;
        if (cached != null && cached.analysisDepth == layersRemaining) {//only return cached analysis result if that analysis was done to the depth we currently want
            return cached;
        }
        //End case
        //TODO if either king is not on the board, set whiteMate or blackmate and return
        if (layersRemaining <= 0) {
            Result r = new Result();
            r.score = state.getScore();
            return r;
        }
        //Others
        //Get Child States
        //call get best move on child states
        List<ByteArrayWrapper> states = state.getMoves(playerColor);
        if (states.isEmpty()) {
            Result r = new Result();
            r.score = state.getScore();
            if (playerColor == PlayerColor.WHITE) {
                r.blackMate = true;
            } else {
                r.whiteMate = true;
            }
            return r;
        }
        PlayerColor otherColor = playerColor.getOtherColor();
        Result bestScore = new Result();
        bestScore.next = State.stateMap.get(states.get(0));
        bestScore.score = getBestMove(bestScore.next, layersRemaining - 1, otherColor).score;
        bestScore.analysisDepth = layersRemaining;
        for (int i = 1; i < states.size(); i++) {
            ByteArrayWrapper seed = states.get(i);
            State next = State.stateMap.get(seed);
            Result res = getBestMove(next, layersRemaining - 1, otherColor);
            double score = res.score;
            switch (playerColor) {
                case WHITE:
                    if (bestScore.score < score) {
                        bestScore.score = score;
                        bestScore.next = next;
                    }
                    //if (res.whiteMate) {//maybe replace this if statement with: if score is positive infinity
                      //  bestScore.whiteMate = true;
                        //state.bestScore = bestScore;
                        //return bestScore;
                    //}
                    break;
                case BLACK:
                    if (bestScore.score > score) {
                        bestScore.score = score;
                        bestScore.next = next;
                    }
                    if (res.blackMate) {
                        bestScore.blackMate = true;
                        state.bestScore = bestScore;
                        return bestScore;
                    }
                    break;
            }

            //if im white and this choice jhas possibleForWhiteToForceMate == true
              // then this is the best choice, regardless of score



        }
        state.bestScore = bestScore;
        //return min or max based on player color
        return bestScore;
    }*/
    //ActorWorld Methods
    @Override
    public void show() {
        if (getMessage() == null) {
            setMessage(DEFAULT_MESSAGE);
        }
        super.show();
    }
    @Override
    public void add(Location loc, Piece occupant) {
        occupant.putSelfInGrid(getGrid(), loc);
    }
    /**
     *
     * @param loc
     * @return
     */
    public Piece remove(Location loc) {
        Piece occupant = getGrid().get(loc);
        if (occupant == null) {
            return null;
        }
        occupant.removeSelfFromGrid();
        return occupant;
    }
    public class AI extends Thread {
        //public static void runAIs(){}
        public void run() {
        }
    }
}
