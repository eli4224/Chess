/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author elicowa
 */
public abstract class Piece {
    //Static Variables
    public final static PlayerColor PLAYER_ONE_COLOR = PlayerColor.WHITE;
    public final static PlayerColor PLAYER_TWO_COLOR = PlayerColor.BLACK;
    public static enum PlayerColor {
        WHITE(new Color(238, 161, 68), Location.NORTH), BLACK(Color.BLACK, Location.NORTH);
        private Color rgb;
        private int direction;
        private PlayerColor(Color rbgvalue, int dir) {
            direction = dir;
            rgb = rbgvalue;
        }
        public int getDirection() {
            return direction;
        }
        public Color getColor() {
            return rgb;
        }
    }
    //Instance Variables
    protected Grid<Piece> grid;
    private Location location;
    private int direction;
    private final PlayerColor playerColor;
    private final Color color;
    //Constructors
    public Piece(PlayerColor c) {
        direction = c.getDirection();
        grid = null;
        location = null;
        playerColor = c;
        color = c.getColor();
    }
    //Abstract Methods
    public abstract ArrayList<Location> getMoves();
    //Instance Methods
    public boolean moveTo(Location loc) { //change to boolean
        if (!canMoveTo(loc)) {
            return false;
        }
        grid.remove(location);
        Piece other = grid.get(loc);
        if (other != null) {
            other.removeSelfFromGrid();
        }
        location = loc;
        grid.put(location, this);
        return true;
    }
    public boolean canMoveTo(Location loc) { //TODO: If I have time make this abstract and implement it in each subclass
        for (Location l : getMoves()) {
            if (l.equals(loc)) {
                return true;
            }
        }
        return false;
    }
    public PlayerColor getPlayerColor() {
        return playerColor;
    }
    //Methods from Actor
    public String toString() {
        return getClass().getSimpleName() + "[location=" + location + ",direction="
                + direction + ",color=" + playerColor + "]";
    }
    public void putSelfInGrid(Grid<Piece> gr, Location loc) {
        if (grid != null) {
            throw new IllegalStateException(
                    "This actor is already contained in a grid.");
        }
        Piece actor = gr.get(loc);
        if (actor != null) {
            actor.removeSelfFromGrid();
        }
        gr.put(loc, this);
        grid = gr;
        location = loc;
    }
    public void removeSelfFromGrid() {
        if (grid == null) {
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
        }
        if (grid.get(location) != this) {
            throw new IllegalStateException(
                    "The grid contains a different actor at location "
                    + location + ".");
        }
        grid.remove(location);
        grid = null;
        location = null;
    }
    public void act() {
        //setDirection(getDirection() + Location.HALF_CIRCLE);
    }
    public Location getLocation() {
        return location;
    }
    public Grid<Piece> getGrid() {
        return grid;
    }
    public Color getColor() {
        return playerColor.getColor();
    }
    public void setDirection(int newDirection) {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0) {
            direction += Location.FULL_CIRCLE;
        }
    }
    public int getDirection() {
        return direction;
    }
}
