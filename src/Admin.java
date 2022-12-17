import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements ActionListener{
    String loginId;

    Admin(String loginId){
        this.loginId=loginId;

        setTitle("IRCTC");
        setLayout(null);
        
        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
        
    public void actionPerformed(ActionEvent e) {
        
        
    }
    public static void main(String args[])
    {
        new Admin("11111");
    }

}
