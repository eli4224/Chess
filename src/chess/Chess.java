/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Piece.PLAYER_ONE_COLOR;
import static chess.Piece.PLAYER_TWO_COLOR;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

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
}
