package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class acts as a setup frame to allow users to choose between path algorithms or sort algorithms
 *
 * Author: Shane Gaymon
 */

public class AlgorithmSetupView extends JFrame implements ActionListener {

    private JPanel mainPnl;

    private JLabel welcomeLbl;
    private JLabel selectLbl;

    private JButton sortBtn;
    private JButton pathBtn;

    public AlgorithmSetupView(){
        super("Algorithm Visualizer");
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4,1));
        mainPnl.setBackground(Color.BLACK);

        welcomeLbl = new JLabel("Algorithm Visualizer");
        welcomeLbl.setSize(new Dimension(300, 150));
        welcomeLbl.setForeground(Color.WHITE);
        welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);

        selectLbl = new JLabel("Choose between a Sorting algorithm or Pathfinding algorithm below");
        selectLbl.setForeground(Color.WHITE);
        selectLbl.setHorizontalAlignment(SwingConstants.CENTER);

        sortBtn = new JButton("Sort Algorithms");
        sortBtn.addActionListener(this);

        pathBtn = new JButton("Path Algorithms");
        pathBtn.addActionListener(this);

        mainPnl.add(welcomeLbl);
        mainPnl.add(selectLbl);
        mainPnl.add(sortBtn);
        mainPnl.add(pathBtn);

        this.setSize(700,600);
        this.add(mainPnl);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent event){
        Object src = event.getSource();

        if(src.equals(sortBtn)){
            OptionsPanel o = new OptionsPanel();
            SortingView s = new SortingView(o);
            MainScreen screen = new MainScreen(o);
            screen.setSortContent(s);
        }
        else if(src.equals(pathBtn)){
            OptionsPanel o = new OptionsPanel();
            PathFindingView v = new PathFindingView(o);
            MainScreen screen = new MainScreen(o);
            screen.setPathContent(v);
        }

    }

}
