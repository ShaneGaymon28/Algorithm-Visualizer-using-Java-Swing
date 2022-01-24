package visualizer;

import javax.swing.SwingUtilities;

public class Algo {
    public static void main(String []args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                AlgorithmSetupView v = new AlgorithmSetupView();
            }
        });




    }
}
