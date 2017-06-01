/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

/**
 *
 * @author elicowa
 */
public class ChessGrid extends BoundedGrid<Piece> implements Cloneable {
    public ChessGrid() {
        super(8, 8);
    }
    @Override
    public ChessGrid clone() {
        //ChessGrid cg = (ChessGrid) super.clone();
        ChessGrid cg = new ChessGrid();
        for (Location loc : this.getOccupiedLocations()) {
            cg.put(loc, this.get(loc).clone(cg));
        }
        return cg;
    }
}
