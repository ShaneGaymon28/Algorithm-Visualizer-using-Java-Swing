package visualizer;

import visualizer.PathAlgorithms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Currently supported pathfinding algorithms: Dijkstra, A*, Lee's Algorithm
 *
 * this class implements MouseListener to allow for the user to click in the panel to
 * select the start and destination cells
 *
 * Author: Shane Gaymon
 */

public class PathFindingView extends JPanel implements MouseListener {

    // object holding the type of pathfinding algorithm to run
    private IPathAlgorithm path;
    private OptionsPanel options;
    private Cell [][]grid;

    // start and destination cell
    private Cell start;
    private Cell dest;

    // boolean to represent if we are waiting for the user to select a start or destination cell
    private boolean selectingStart = false;
    private boolean selectingDest = false;

    private int windowWidth = 800;
    private int windowHeight = 800;
    private int numSteps = 0;

    // size of the grid
    private int rows = 25;
    private int cols = 25;

    // brick height and width
    private int brickH = windowHeight/cols;
    private int brickW = windowWidth/rows;

    private int delay;
    private double density = 0.25;

    private String name = "Dijkstra";


    public PathFindingView(OptionsPanel o){
        options = o;
        // set default to dijkstra
        path = new Dijkstra(this);

        addMouseListener(this);
        setFocusable(true);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setSize(new Dimension(windowWidth, windowHeight));
        setOpaque(true);
        setBackground(Color.WHITE);

        // default start and destination cell
        start = new Cell(0,0,false, 0);
        dest = new Cell(0,0,false, 0);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Cell tmpCell = grid[i][j];

                g2.setColor(tmpCell.getColor());
                g2.fillRect(j * brickW, i * brickH, brickW, brickH);

                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.BLACK);
                g2.drawRect(j * brickW, i * brickH, brickW, brickH);
            }
        }


    }

    /**
     * updates the current state of the cells found by the pathfinding algorithm and
     * repaints the panel
     */

    public void updatePath(Cell [][]curGrid){

        grid = curGrid.clone();
        numSteps++;
        repaint();

        delay = options.getDelay();

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        return;
    }

    /**
     * starts running the algorithm in the background so we can update the panel
     */

    public void start(){
        System.out.println("start");

        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                path.findPath(start, dest);
                return null;
            }
        };
        worker.execute();
        repaint();
    }

    /**
     * resets all options to the currently selected options
     */

    public void reset(int s){
        name = options.getAlgorithm();
        determineAlgorithm(name);

        density = 0.01 * options.getDensity();

        numSteps = 0;
        // for new size
        rows = s;
        brickW = windowWidth/rows;
        cols = s;
        brickH = windowHeight/cols;

        grid = path.getNewGrid(density, rows, cols);
        repaint();
    }

    /**
     * this method will "setup" the grid to be displayed to the user
     */

    public void setup(){
        grid = path.getNewGrid(density, rows, cols);
        repaint();
    }

    /**
     * sets the grid to an empty grid
     */

    public void clear(){
        grid = path.getNewGrid(0, rows, cols);
        repaint();
    }

    public void selectStart(){
        selectingStart = true;
    }

    public void selectDest(){
        selectingDest = true;
    }

    /**
     * determines which algorithm is selected by passing in the String name of the selection
     * @param s
     */

    public void determineAlgorithm(String s){
        System.out.println(s);
        if(s.equals("Dijkstra")){
            path = new Dijkstra(this);
        }
        else if(s.equals("A*")){
            path = new AStar(this);
        }
        else if(s.equals("Lee")){
            path = new Lee(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        int j = x/brickW;
        int i = y/brickH;

        if(selectingStart){
            start.setColor(Color.WHITE);
            start = grid[i][j];
            start.setColor(Color.RED);
            selectingStart = false;
            repaint();
        }
        else if(selectingDest){
            dest.setColor(Color.WHITE);
            dest = grid[i][j];
            dest.setColor(Color.BLUE);
            selectingDest = false;
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
