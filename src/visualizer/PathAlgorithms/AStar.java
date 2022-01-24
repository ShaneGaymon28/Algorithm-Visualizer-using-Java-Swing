package visualizer.PathAlgorithms;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import visualizer.PathFindingView;

/**
 * this class implements the A* pathfinding algorithm
 *
 * Author: Shane Gaymon
 */

public class AStar implements IPathAlgorithm {

    private PathFindingView view;
    private List<Cell> nodes;
    private Cell[][] grid;
    private PriorityQueue<Cell> pQueue;
    private List<Cell> closedList;

    private int numRows;
    private int numCols;

    private int cost = 1;

    private boolean stop = false;


    public AStar(PathFindingView v){
        view = v;
        pQueue = new PriorityQueue<>();
        closedList = new ArrayList<>();
    }

    public void findPath(Cell start, Cell end){
        start.updateDistFromStart(0);
        start.setVisited(true);
        pQueue.add(start);

        while(!stop){
            Cell cur = pQueue.poll();
            closedList.add(cur);

            if(cur != start & cur != end){
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

                if(closedList.contains(next))
                    continue;


                if(!next.isVisited()){
                    double dist = cur.getDistFromStart() + cost;
                    double distToGoal = eDistance(next, end);
                    double estimate = dist + distToGoal;

                    if(!pQueue.contains(next) || estimate < next.getDistFromStart()){
                        next.updateDistFromStart(estimate);
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
            if(current != start){
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
        if(checkIndex(curRow, curCol + 1)){
            neighbors.add(grid[curRow][curCol + 1]);
        }

        if(checkIndex(curRow + 1, curCol + 1)){
            neighbors.add(grid[curRow + 1][curCol + 1]);
        }

        if(checkIndex(curRow + 1, curCol)){
            neighbors.add(grid[curRow + 1][curCol]);
        }

        if(checkIndex(curRow + 1, curCol - 1)){
            neighbors.add(grid[curRow + 1][curCol - 1]);
        }

        if(checkIndex(curRow, curCol - 1)){
            neighbors.add(grid[curRow][curCol - 1]);
        }

        if(checkIndex(curRow - 1, curCol - 1)){
            neighbors.add(grid[curRow - 1][curCol - 1]);
        }

        if(checkIndex(curRow - 1, curCol)){
            neighbors.add(grid[curRow - 1][curCol]);
        }

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


    // work some stuff around in here
    public double eDistance(Cell cell1, Cell cell2){
        int r1 = cell1.getRow();
        int r2 = cell2.getRow();
        int c1 = cell1.getCol();
        int c2 = cell2.getCol();

        double d = Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));

        return d;
    }
}
