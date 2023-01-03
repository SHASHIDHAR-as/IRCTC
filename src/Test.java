import java.awt.Color;

import javax.swing.*;

public class Test extends JFrame{
    Test(){
        setLayout(null);

        JLabel label=new JLabel();
        label.setBounds(100,100,580,180);
        label.setOpaque(true);
        label.setBackground(Color.black);
        add(label);

        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String args[])
    {
        new Test();
    }
}
