package visualizer;

import javax.swing.*;
import java.awt.*;

/**
 * This class acts as an options panel for the sort/pathfinding algorithm screen
 *
 * Components:
 *      - start button
 *      - reset button
 *      - clear button
 *      - Algorithm dropdown
 *      - size dropdown
 *      - delay spinner
 *      - density spinner (pathfinding only)
 *      - select start button (pathfinding only)
 *      - select destination button (pathfinding only)
 *
 * Author: Shane Gaymon
 */

public class OptionsPanel extends JPanel {

    private JPanel mainPanel;
    // start, reset, algorithm select
    private JPanel topPanel;
    //size, delay, density
    private JPanel sizePanel;
    // changing of colors
    private JPanel specificAlgorithmPanel;

    private JButton backBtn;
    private JButton startBtn;
    private JButton resetBtn;
    private JButton clearBtn;
    private JButton selectStartBtn;
    private JButton selectDestBtn;

    private JLabel optionsLbl;
    private JLabel sizeLbl;
    private JLabel delayLbl;
    private JLabel densityLbl;
    private JLabel algorithmLbl;

    private JComboBox algorithmCb;
    private String [] pathAlgorithms = {"Dijkstra", "A*", "Lee"};
    private String [] sortAlgorithms = {"Bubble Sort", "Quicksort", "Selection Sort", "Merge Sort", "Insertion Sort", "Heap Sort"};

    private JComboBox sizeCb;
    private String []sizeOptions = {"10", "25", "50"};

    private JSpinner delaySpinner;
    private JSpinner densitySpinner;

    private int delay;
    private int density;


    public OptionsPanel(){
        // setting up the main panel that all components will be added to
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel veryTopPanel = new JPanel();
        veryTopPanel.setLayout(new GridLayout(2, 1));
        optionsLbl = new JLabel("Options");
        optionsLbl.setForeground(Color.BLACK);
        optionsLbl.setHorizontalAlignment(SwingConstants.CENTER);

        backBtn = new JButton("Back");
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        veryTopPanel.add(backBtn);
        veryTopPanel.add(optionsLbl);

        mainPanel.add(veryTopPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(400, 50)));

        // setting up panel that will hold start btn, reset btn, and algorithm dropdown
        topPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        topPanel.setSize(new Dimension(400, 400));

        startBtn = new JButton("Start");
        startBtn.setPreferredSize(new Dimension(200, 75));

        resetBtn = new JButton("Reset");
        resetBtn.setPreferredSize(new Dimension(200, 75));

        clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(new Dimension(200,75));

        JPanel algoPanel = new JPanel(new GridLayout(2, 1));
        algorithmLbl = new JLabel("Algorithm: ");
        algorithmLbl.setHorizontalAlignment(SwingConstants.CENTER);
        algorithmCb = new JComboBox();
        algoPanel.add(algorithmLbl);
        algoPanel.add(algorithmCb);

        topPanel.add(startBtn);
        topPanel.add(resetBtn);
        topPanel.add(clearBtn);
        topPanel.add(algoPanel);


        mainPanel.add(topPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(400, 50)));

        // setting up panel with size, delay, and density
        sizePanel = new JPanel(new GridLayout(6, 1));
        sizePanel.setSize(new Dimension(400,400));

        sizeLbl = new JLabel("Size: ");
        sizeLbl.setHorizontalAlignment(SwingConstants.CENTER);

        sizeCb = new JComboBox(sizeOptions);
        sizeCb.setPreferredSize(new Dimension(200,25));
        sizeCb.setSelectedItem(sizeOptions[1]); // default selection size is size of 25

        sizePanel.add(sizeLbl);
        sizePanel.add(sizeCb);

        delayLbl = new JLabel("Delay (ms): ");
        delayLbl.setHorizontalAlignment(SwingConstants.CENTER);

        delaySpinner = new JSpinner(new SpinnerNumberModel(150,1,500,1));
        delaySpinner.setPreferredSize(new Dimension(200,25));
        delay = (Integer) delaySpinner.getValue();
        delaySpinner.addChangeListener((event) -> {
            delay = (Integer) delaySpinner.getValue();
        });

        sizePanel.add(delayLbl);
        sizePanel.add(delaySpinner);

        mainPanel.add(sizePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(400, 50)));

        specificAlgorithmPanel = new JPanel();
        specificAlgorithmPanel.setSize(new Dimension(400,400));
        mainPanel.add(specificAlgorithmPanel);

        this.add(mainPanel);

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(400,1200));
        this.setSize(new Dimension(400,1200));
    }

    /**
     * adds panel components that are specific to pathfinding algorithms
     */

    public void addPathComponents(){
        // buttons to select the start and stop cells
        specificAlgorithmPanel.setLayout(new GridLayout(2, 1, 5, 5));
        selectStartBtn = new JButton("Select Start");
        selectDestBtn = new JButton("Select Destination");
        specificAlgorithmPanel.add(selectStartBtn);
        specificAlgorithmPanel.add(selectDestBtn);

        // density label and spinner
        densityLbl = new JLabel("Density: ");
        densityLbl.setHorizontalAlignment(SwingConstants.CENTER);
        densitySpinner = new JSpinner(new SpinnerNumberModel(25,1,100,1));
        densitySpinner.setPreferredSize(new Dimension(200,25));
        density = (Integer) densitySpinner.getValue();
        densitySpinner.addChangeListener((event) -> {
            density = (Integer) densitySpinner.getValue();
        });

        sizePanel.add(densityLbl);
        sizePanel.add(densitySpinner);

        // add list of supported path algorithms to algorithm cb
        for(int i = 0; i < pathAlgorithms.length; i++) {
            algorithmCb.addItem(pathAlgorithms[i]);
        }
        algorithmCb.setSelectedIndex(0);

        mainPanel.add(Box.createRigidArea(new Dimension(400, 75)));
        repaint();
    }

    /**
     * adds panel components that are specific to sorting algorithms
     */

    public void addSortComponents(){

        for(int i = 0; i < sortAlgorithms.length; i++){
            algorithmCb.addItem(sortAlgorithms[i]);
        }
        algorithmCb.setSelectedIndex(0);

        mainPanel.add(Box.createRigidArea(new Dimension(400, 75)));

        repaint();
    }

    public String getAlgorithm(){
        return algorithmCb.getSelectedItem().toString();
    }

    public JButton getStartBtn(){
        return startBtn;
    }

    public JButton getResetBtn(){
        return resetBtn;
    }

    public JButton getClearBtn(){
        return clearBtn;
    }

    public JButton getBackBtn(){
        return backBtn;
    }

    public JButton getSelectStartBtn(){
        return selectStartBtn;
    }

    public JButton getSelectDestBtn(){
        return selectDestBtn;
    }

    public int getDelay(){
        return delay;
    }

    public int getDensity(){
        return density;
    }

    public int getArrSize(){
        return Integer.parseInt(sizeCb.getSelectedItem().toString());
    }


}
