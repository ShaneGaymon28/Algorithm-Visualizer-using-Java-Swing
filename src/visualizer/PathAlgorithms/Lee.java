package visualizer.PathAlgorithms;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import visualizer.PathFindingView;

/**
 * this class implements Lee's pathfinding algorithm
 *
 * Author: Shane Gaymon
 */

public class Lee implements IPathAlgorithm {

    private PathFindingView view;
    private List<Cell> nodes;
    private Cell[][] grid;

    private PriorityQueue<Cell> pQueue;

    private int numRows;
    private int numCols;

    private int cost = 1;

    private boolean stop = false;

    public Lee(PathFindingView v){
        view = v;
        pQueue = new PriorityQueue<>();
    }

    public void findPath(Cell start, Cell end) {
        start.updateDistFromStart(0);
        start.setVisited(true);
        pQueue.add(start);

        int minDist = Integer.MAX_VALUE;
        //while(!pQueue.isEmpty()){
        while(!stop){
            Cell cur = pQueue.poll();

            if(cur != start && cur != end){
                cur.setColor(Color.GREEN);
            }

            findEdges(cur);
            List<Cell> curEdges = cur.getEdges();

            for(int i = 0; i < curEdges.size(); i++){
                Cell next = curEdges.get(i);

                if(next.getId() == end.getId()){
                    end.setPred(cur);
                    stop = true;
                    break;
                }

                double dist = cur.getDistFromStart() + cost;
                next.updateDistFromStart(dist);
                next.setVisited(true);
                next.setPred(cur);
                pQueue.add(next);
            }

            cur.setVisited(true);
            view.updatePath(grid);

        }

        Cell current = end;
        while(current.getPred() != null){
            current = current.getPred();
            if(current != start){
                current.setColor(Color.YELLOW);
            }
        }
        view.updatePath(grid);

    }


    public void findEdges(Cell curCell) {
        if(curCell.getState()){
            return;
        }

        int curRow = curCell.getRow();
        int curCol = curCell.getCol();

        List<Cell> neighbors = new ArrayList<>();

        // check up down left and right, NO DIAGONALS!!!!!
        if(checkIndex(curRow, curCol + 1)){
            neighbors.add(grid[curRow][curCol + 1]);
        }

        if(checkIndex(curRow, curCol - 1)){
            neighbors.add(grid[curRow][curCol - 1]);
        }

        if(checkIndex(curRow + 1, curCol)){
            neighbors.add(grid[curRow + 1][curCol]);
        }

        if(checkIndex(curRow - 1, curCol)){
            neighbors.add(grid[curRow - 1][curCol]);
        }

        curCell.setEdges(neighbors);

    }


    public Cell[][] getNewGrid(double density, int rows, int cols) {
        numRows = rows;
        numCols = cols;
        grid = new Cell[rows][cols];
        nodes = new ArrayList<>();
        int id = 1;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(Math.random() <= density){
                    grid[i][j] = new Cell(i, j, true, id);
                }
                else {
                    grid[i][j] = new Cell(i, j, false, id);
                    nodes.add(grid[i][j]);
                }
                id++;

            }
        }

        return grid;
    }


    public boolean checkIndex(int r, int c) {
        if(r >= numRows || r < 0){
            return false;
        }
        else if(c >= numCols || c < 0){
            return false;
        }
        else if(grid[r][c].getState()){
            return false;
        }
        else if(grid[r][c].isVisited()){
            return false;
        }

        return true;
    }
}
