import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Rajiv vs Rajiv");
        frame.setLocation(300, 300);

        // Status panel displaying if the match is ongoing or if a side has won
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        JLabel status = new JLabel("Match in Progress");
        status_panel.add(status);
        
        // displays the scores of each side and the moves
        JLabel turnCount = new JLabel("Green Score: " + 0 + "          " +
	    		"Moves: " + 0 + "          " + 
	    		"Red Score: " + 0);

        // Main playing area
        final ChessBoard board = new ChessBoard(turnCount, status);
        frame.add(board, BorderLayout.CENTER);

        // this panel holds the turncount and other data 
        final JPanel count_panel = new JPanel();
        frame.add(count_panel, BorderLayout.NORTH);
        count_panel.add(turnCount);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

   // Start game
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}