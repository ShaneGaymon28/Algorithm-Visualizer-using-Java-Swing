package visualizer.PathAlgorithms;

import java.util.*;
import java.awt.Color;

/**
 * this class represents a single cell on the grid for pathfinding algorithms
 *
 * Author: Shane Gaymon
 */

public class Cell implements Comparable<Cell>, Comparator<Cell> {

    private List<Cell> edges;
    private Cell pred = null;

    private int row;
    private int col;
    private int id;
    private double distFromStart = Double.MAX_VALUE;

    private boolean isWall;
    private boolean visited = false;

    private Color color;

    public Cell(int r, int c, boolean w, int i){
        row = r;
        col = c;
        isWall = w;
        id = i;

        if(isWall){
            color = Color.BLACK;
        }
        else {
            color = Color.WHITE;
        }

    }


    public void setEdges(List<Cell> e){
        edges = e;
    }

    public List<Cell> getEdges(){
        return edges;
    }

    public boolean getState(){
        return isWall;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color c){
        color = c;
    }

    public void updateDistFromStart(double d){
        distFromStart = d;
    }

    public double getDistFromStart(){
        return distFromStart;
    }

    public void setPred(Cell p){
        pred = p;
    }

    public Cell getPred(){
        return pred;
    }

    public void setVisited(boolean v){
        visited = v;
    }

    public boolean isVisited(){
        return visited;
    }

    public int getId(){
        return id;
    }

    @Override
    public int compareTo(Cell o) {
        return Double.compare(distFromStart, o.getDistFromStart());
    }

    @Override
    public int compare(Cell o1, Cell o2) {
        return Double.compare(o1.getDistFromStart(), o2.getDistFromStart());
    }

}
