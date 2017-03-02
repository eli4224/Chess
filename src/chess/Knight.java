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
public class Knight extends Piece {
    private static int[][] offset = {{2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2}};
    public Knight(PlayerColor c) {
        super(c);
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Piece> gr = getGrid();
        Location loc;
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        for (int[] o : offset) {
            loc = new Location(r + o[0], c + o[1]);
            if (gr.isValid(loc) && (gr.get(loc) == null || gr.get(loc).getPlayerColor() != this.getPlayerColor())) {
                locs.add(loc);
            }
        }
        return locs;
    }
}
