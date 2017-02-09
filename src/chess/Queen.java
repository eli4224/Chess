/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author elicowa
 */
public class Queen extends WideMovingPiece {
    public final int[] movableDirections = {0, 45, 90, 135, 180, 225, 270, 315};
    @Override
    public ArrayList<Location> getMoves() {
        return super.getMoves(movableDirections);
    }
}
