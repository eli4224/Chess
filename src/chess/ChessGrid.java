/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            ChessGrid cg = (ChessGrid) super.clone();
            for (Location loc : cg.getOccupiedLocations()) {
                cg.put(loc, this.get(loc).clone(cg));
            }
            return cg;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ChessGrid.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
