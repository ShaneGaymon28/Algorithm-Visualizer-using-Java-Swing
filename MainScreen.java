package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class acts as the frame for the sort/pathfinding algorithm panel
 *
 * this frame will have 2 components: options panel and sort/pathfinding algorithm panel
 *
 * Author: Shane Gaymon
 */

public class MainScreen extends JFrame implements ActionListener {

    private SortingView sortContent;
    private OptionsPanel optionsPanel;
    private PathFindingView pathContent;

    private JPanel mainPanel;

    private JButton startBtn;
    private JButton resetBtn;
    private JButton clearBtn;
    private JButton backBtn;
    private JButton selectStartBtn;
    private JButton selectDestBtn;

    private int size;

    private boolean path = false;


    public MainScreen(OptionsPanel o){
        super("Algorithm Visualizer");

        optionsPanel = o;

        startBtn = optionsPanel.getStartBtn();
        startBtn.addActionListener(this);

        resetBtn = optionsPanel.getResetBtn();
        resetBtn.addActionListener(this);

        clearBtn = optionsPanel.getClearBtn();
        clearBtn.addActionListener(this);

        backBtn = optionsPanel.getBackBtn();
        backBtn.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        mainPanel.add(optionsPanel);

        Container pane = getContentPane();
        pane.add(mainPanel, BorderLayout.CENTER);

        this.setSize(1200,1200);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if(src.equals(startBtn)){
            if(path){
                pathContent.start();
            }
            else {
                sortContent.start();
            }
            repaint();
        }
        else if(src.equals(resetBtn)){
            size = optionsPanel.getArrSize();

            if(path){
                pathContent.reset(size);
            }
            else {
                sortContent.reset(size);
            }

            repaint();
        }
        else if(src.equals(clearBtn)){
            if(path){
                pathContent.clear();
            }
        }
        else if(src.equals(selectStartBtn)){
            pathContent.selectStart();
        }
        else if(src.equals(selectDestBtn)){
            pathContent.selectDest();
        }
        else if(src.equals(backBtn)){
            this.dispose();
        }
    }

    /**
     * sets the main panel of this frame for a sort algorithm
     */

    public void setSortContent(SortingView v){
        sortContent = v;
        sortContent.setup();

        optionsPanel.addSortComponents();

        mainPanel.add(sortContent);
        path = false;
    }

    /**
     * sets the main panel of this frame for a pathfinding algorithm
     */

    public void setPathContent(PathFindingView v){
        pathContent = v;
        pathContent.setup();

        optionsPanel.addPathComponents();
        selectStartBtn = optionsPanel.getSelectStartBtn();
        selectStartBtn.addActionListener(this);

        selectDestBtn = optionsPanel.getSelectDestBtn();
        selectDestBtn.addActionListener(this);

        mainPanel.add(pathContent);
        path = true;
    }



}
