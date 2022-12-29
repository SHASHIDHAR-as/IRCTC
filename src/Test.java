import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.*;

public class Test extends JFrame implements ActionListener{
    Test(){
        setTitle("IRCTC");

        setLayout(null);
        JPanel panel=new JPanel();
        String htmlContent="""
                <html>
                <div>
                    <div>
                        <p>HAMPI EXPRESS (16592)</p>
                    </div>
                    <hr>
                    <p>Yeshwantpur Jn   Ballari Jn</p>
                    <p>22:02    05:50</p>
                    <p>ticket cost : 40</p>
                    <p>seats available : 20</p>
                <div>
                </html>
                """;
        JLabel label=new JLabel(htmlContent);
        panel.add(label);
        panel.setBounds(135,100,700,200);
        panel.setBackground(Color.white);
        add(panel);
        
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        
    }
    public static void main(String args[])
    {
        new Test();
    }
}
