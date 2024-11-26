import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JFrame;

public class Board extends JFrame{
    // properties
    private Location[][] STATE = new Location[][]{
        {null, null, null},
        {null, null, null},
        {null, null, null}
    };
    
    // constructor
    public Board(){
        super();
        super.setTitle("BOARD!");
        super.setSize(400, 600);
        super.setTitle("yashna");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i=0; i<9; i++){
            this.STATE[i/3][i%3] = new Location(this, i%3, i/3);
            System.out.println(Arrays.deepToString(STATE));
        }
        super.setLayout(new FlowLayout());

        super.setVisible(true);
        super.setLocationRelativeTo(null);
        System.out.println("A new board!");
    }

    public void printState(){
        System.out.println(Arrays.deepToString(STATE));
    }
}
