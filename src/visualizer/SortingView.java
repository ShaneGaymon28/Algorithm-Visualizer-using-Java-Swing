package visualizer;

import javax.swing.*;
import java.awt.*;

import visualizer.SortAlgorithms.*;

/**
 * Currently supported sorting algorithms: Bubble Sort, Quicksort, Selection Sort, Merge Sort,
 * Insertion Sort, and Heap Sort
 *
 * Author: Shane Gaymon
 */

public class SortingView extends JPanel {

    // object holding the type of sort to run
    private ISortAlgorithm sort;
    private OptionsPanel options;

    private Color backGround = Color.BLACK;
    private Color barColor = Color.BLUE;

    private int []toSort;
    private int numSteps = 0;
    // default size set to 25
    private int size = 25;

    private int windowWidth = 800;
    private int windowHeight = 800;

    // current value of indexes being compared to be highlighted
    private int curIndex1 = 0;
    private int curIndex2 = 1;
    private int delay;

    // height scale
    private double hScale;

    private String name = "Bubble Sort";

    // change the string
    public SortingView(OptionsPanel o){
        options = o;
        // set the default to bubble sort
        sort = new BubbleSort(this);

        setFocusable(true);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setSize(windowWidth, windowHeight);
        setOpaque(true);
        setBackground(Color.BLACK);

    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(backGround);
        g2.drawRect(0, 0, windowWidth, windowHeight);


        int w = windowWidth/size;
        hScale = (windowHeight/size);

        for(int i = 0; i < toSort.length; i++){
            int val = toSort[i];
            int h = (int) (val * hScale) - 10;
            int x = i + (w - 1) * i;
            int y = getHeight() - h;

            if(i == curIndex1 || i == curIndex2) {
                g2.setColor(Color.YELLOW);
            }
            else {
                g2.setColor(barColor);
            }
            g2.fillRect(x, y, w, h);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("serif", Font.BOLD, 15));
        g2.drawString(name, 20, 20);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("serif", Font.BOLD, 15));
        g2.drawString("Swap #: " + numSteps, 700, 20);

        g.dispose();

    }

    /**
     * updates the state of the array to be sorted, the two current indexes being compared, and
     * repaints the panel
     */

    public void updateStep(int []arr, int id1, int id2){

        curIndex1 = id1;
        curIndex2 = id2;
        toSort = arr.clone();

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
     * updates a single value of the array to be sorted and repaints the panel
     */

    public void updateSingle(int index, int value){
        curIndex1 = curIndex2 = index;
        toSort[index] = value;

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

        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                sort.runSort(toSort);
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
        // get all the new values from options panel/screen
        name = options.getAlgorithm();
        determineAlgorithm(name);

        numSteps = 0;
        size = s;
        toSort = sort.getUnsortedBoard(size);
        repaint();
    }

    /**
     * this method will "setup" the board to be displayed to the user
     */

    public void setup(){
        toSort = sort.getUnsortedBoard(size);
        repaint();
    }

    /**
     * determines which algorithm is selected by passing in the String name of the selection
     */

    public void determineAlgorithm(String s){

        if(s.equals("Bubble Sort")){
            sort = new BubbleSort(this);
        }
        else if(s.equals("Quicksort")){
            sort = new Quicksort(this);
        }
        else if(s.equals("Selection Sort")){
            sort = new SelectionSort(this);
        }
        else if(s.equals("Merge Sort")){
            sort = new MergeSort(this);
        }
        else if(s.equals("Insertion Sort")){
            sort = new InsertionSort(this);
        }
        else if(s.equals("Heap Sort")){
            sort = new HeapSort(this);
        }
        //else if(s.equals("Counting Sort")){
        //    sort = new CountingSort(this);
        //}

    }

}
