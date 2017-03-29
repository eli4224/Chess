/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Graphics by http://icon.mobanwang.com/2010/613.html
 */
package chess;

import static chess.Piece.PLAYER_ONE_COLOR;
import static chess.Piece.PLAYER_TWO_COLOR;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elicowa
 */
public class Chess {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Grid<Actor> grid = new BoundedGrid<>(8, 8);
        ChessWorld world = new ChessWorld();//Changed from World<Acter> world = new ActorWorld(grid)
        world.show();
        addPawns(world);
        addRooks(world);
        addKnights(world);
        addBishops(world);
        addQueens(world);
        addKings(world);
    }
    public static void addPawns(World<? super Pawn> world) {
        for (int i = 0; i < 8; i++) {
            world.add(new Location(6, i), new Pawn(PLAYER_ONE_COLOR));
            world.add(new Location(1, i), new Pawn(PLAYER_TWO_COLOR));
        }
    }
    public static void addRooks(World<? super Rook> world) {
        world.add(new Location(7, 7), new Rook(PLAYER_ONE_COLOR));
        world.add(new Location(7, 0), new Rook(PLAYER_ONE_COLOR));
        world.add(new Location(0, 7), new Rook(PLAYER_TWO_COLOR));
        world.add(new Location(0, 0), new Rook(PLAYER_TWO_COLOR));
    }
    public static void addKnights(World<? super Knight> world) {
        world.add(new Location(7, 1), new Knight(PLAYER_ONE_COLOR));
        world.add(new Location(7, 6), new Knight(PLAYER_ONE_COLOR));
        world.add(new Location(0, 1), new Knight(PLAYER_TWO_COLOR));
        world.add(new Location(0, 6), new Knight(PLAYER_TWO_COLOR));
    }
    public static void addBishops(World<? super Bishop> world) {
        world.add(new Location(7, 2), new Bishop(PLAYER_ONE_COLOR));
        world.add(new Location(7, 5), new Bishop(PLAYER_ONE_COLOR));
        world.add(new Location(0, 2), new Bishop(PLAYER_TWO_COLOR));
        world.add(new Location(0, 5), new Bishop(PLAYER_TWO_COLOR));
    }
    public static void addQueens(World<? super Queen> world) {
        world.add(new Location(7, 3), new Queen(PLAYER_ONE_COLOR));
        world.add(new Location(0, 3), new Queen(PLAYER_TWO_COLOR));
    }
    public static void addKings(World<? super King> world) {
        world.add(new Location(7, 4), new King(PLAYER_ONE_COLOR));
        world.add(new Location(0, 4), new King(PLAYER_TWO_COLOR));
    }
    public static boolean isInCheck(Grid<Piece> grid, Piece.PlayerColor playerColor) { //color of the calling piece
        List<Piece> pieceStream = grid.getOccupiedLocations().stream().parallel().map(grid::get).collect(Collectors.toList());
        Location king = pieceStream.parallelStream().filter(King.class::isInstance).filter(x -> x.getPlayerColor() == playerColor).findAny().get().getLocation();
        return pieceStream.parallelStream().filter(x -> x.getPlayerColor() != playerColor).anyMatch(x -> x.canMoveTo(king));
    }
    public static boolean isCheckmate(Grid<Piece> grid, Piece.PlayerColor playerColor) { //color of the threatened king
        return false;
    }
    public static boolean isStalemate(Grid<Piece> grid, Piece.PlayerColor playerColor) {
        return false;
    }
    public Grid<Piece> clone(Grid<Piece> prototype) {
        Grid<Piece> clone = prototype.clone();
    }
//    public static double rateBoard(Grid<Piece> grid, Piece.PlayerColor playerColor) {
//        Map<Class<? extends Piece>, Map<Piece.PlayerColor, Integer>> pieces = new HashMap<>();
////        return toReturn * (playerColor == PLAYER_ONE_COLOR ? 1 : -1);
//    }
}
