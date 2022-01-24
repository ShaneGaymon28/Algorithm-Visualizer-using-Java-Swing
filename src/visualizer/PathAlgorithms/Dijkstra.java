package visualizer.PathAlgorithms;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import visualizer.PathFindingView;

/**
 * this class implements Dijkstra's shortest path algorithm
 *
 * Author: Shane Gaymon
 */

public class Dijkstra implements IPathAlgorithm {

    private PathFindingView view;
    // nodes will represent the cells in the graph that are traversable
    private List<Cell> nodes;
    // all cells on the board including walls
    private Cell[][] grid;

    private PriorityQueue<Cell> pQueue;

    private int numRows;
    private int numCols;

    private int cost = 1;

    private boolean stop = false;


    public Dijkstra(PathFindingView v){
        view = v;
        pQueue = new PriorityQueue<>();
    }

    public void findPath(Cell start, Cell end){

        start.updateDistFromStart(0);
        start.setVisited(true);
        pQueue.add(start);


        // search until we find the end cell
        while(!stop){
            Cell cur = pQueue.poll();

            // set visited nodes to green
            if(cur != start && cur != end){
                cur.setColor(Color.GREEN);
            }

            // find the valid edges to look at and add to a list
            findEdges(cur);
            List<Cell> curEdges = cur.getEdges();

            for(int i = 0; i < curEdges.size(); i++){
                Cell next = curEdges.get(i);

                if(next.getId() == end.getId()){
                    end.setPred(cur);
                    stop = true;
                    break;
                }

                if(!next.isVisited()) {
                    double dist = cur.getDistFromStart() + cost;

                    if (dist < next.getDistFromStart()) {
                        pQueue.remove(next);
                        next.updateDistFromStart(dist);
                        next.setPred(cur);
                        pQueue.add(next);
                    }
                }

            }

            cur.setVisited(true);
            view.updatePath(grid);
        }

        Cell current = end;

        while(current.getPred() != null){
            current = current.getPred();
            if(current != start) {
                current.setColor(Color.YELLOW);
            }
        }

        view.updatePath(grid);

    }



    public void findEdges(Cell curCell){

        if(curCell.getState()){
            return;
        }

        int curRow = curCell.getRow();
        int curCol = curCell.getCol();

        List<Cell> neighbors = new ArrayList<>();

        // has to be within array bounds and not a wall
        // right
        if(checkIndex(curRow, curCol + 1)){
            neighbors.add(grid[curRow][curCol + 1]);
        }

        // right up diagonal
        if(checkIndex(curRow + 1, curCol + 1)){
            neighbors.add(grid[curRow + 1][curCol + 1]);
        }

        // down
        if(checkIndex(curRow + 1, curCol)){
            neighbors.add(grid[curRow + 1][curCol]);
        }

        // left down diagonal
        if(checkIndex(curRow + 1, curCol - 1)){
            neighbors.add(grid[curRow + 1][curCol - 1]);
        }

        // left
        if(checkIndex(curRow, curCol - 1)){
            neighbors.add(grid[curRow][curCol - 1]);
        }

        // left up diagonal
        if(checkIndex(curRow - 1, curCol - 1)){
            neighbors.add(grid[curRow - 1][curCol - 1]);
        }

        // up
        if(checkIndex(curRow - 1, curCol)){
            neighbors.add(grid[curRow - 1][curCol]);
        }

        // right up diagonal
        if(checkIndex(curRow - 1, curCol + 1)){
            neighbors.add(grid[curRow - 1][curCol + 1]);
        }

        curCell.setEdges(neighbors);

    }

    public Cell[][] getNewGrid(double density, int rows, int cols){
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

    public boolean checkIndex(int r, int c){
        if(r >= numRows || r < 0){
            return false;
        }
        else if(c >= numCols || c < 0){
            return false;
        }
        else if(grid[r][c].getState()){
            return false;
        }


        return true;
    }





}
