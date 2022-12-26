import javax.swing.*;
import java.awt.*;

public class ScrollFrameExample {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Scroll Frame Example");
    frame.setSize(400, 400);

    // Create a panel and add it to the frame
    JPanel panel1 = new JPanel();
    frame.add(panel1, BorderLayout.WEST);
    
    JPanel panel2 = new JPanel();
    frame.add(panel2, BorderLayout.EAST);
    JTextArea textArea = new JTextArea();
panel1.add(textArea);
JScrollPane scrollPane = new JScrollPane(panel2);
frame.add(scrollPane, BorderLayout.EAST);
    frame.setVisible(true);
}
}
