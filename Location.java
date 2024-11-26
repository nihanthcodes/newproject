import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Location extends JButton implements ActionListener{
    // properties
    private int x;
    private int y;
    private Board b;
    private char activeSymbol;
    private String symbol;

    public Location(){
        super();
        super.setText("asdfsadf");
    }
    // constructor
    public Location(Board b, int x, int y){
        this.b = b;
        this.x = x;
        this.y = y;
        b.add(this);
        super.setPreferredSize(new Dimension(60, 60));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.symbol = "x";
        this.setText("YEAH");
        b.printState();        
    }

    @Override
    public String toString(){
        return symbol;  
    }  
    

}
