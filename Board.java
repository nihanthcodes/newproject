import javax.swing.JFrame;

public class Board extends JFrame{
    // properties
    private char[][] STATE = new char[][]{};
    
    // constructor
    public Board(){


        setTitle("yashna");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Location btn = new Location();
        this.add(btn);

        setVisible(true);
        setLocationRelativeTo(null);



        System.out.println("A new board!");
    }
}
