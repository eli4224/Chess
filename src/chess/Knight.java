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
    public Knight(PlayerColor c) {
        super(c);
    }
    @Override
    public ArrayList<Location> getMoves() {
        ArrayList<Location> locs = new ArrayList();
        Grid<Piece> gr = getGrid();
        for (int counter = 0; counter < 2; counter++) {
            for (int direction = 2; direction >= -2; direction -= 4) {
                for (int i = -1; i <= 1; i = i + 2) {
                    Location knightmove;
                    if (counter != 0) {
                        knightmove = new Location(this.getLocation().getRow() + i, this.getLocation().getCol() + direction);
                    } else {
                        knightmove = new Location(this.getLocation().getRow() + direction, this.getLocation().getCol() + i);
                    }
                    if (gr.isValid(knightmove) && (gr.get(knightmove) == null || gr.get(knightmove).getPlayerColor() != this.getPlayerColor())) {
                        locs.add(knightmove);
                    }
                }
            }
        }
        return locs;
    }
}
