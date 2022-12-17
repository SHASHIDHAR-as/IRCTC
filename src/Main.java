import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    JButton user,admin;
    Main(){
        setTitle("IRCTC");
        setLayout(null);

        JLabel label=new JLabel("WELCOME TO IRCTC");    
        label.setBounds(350,50,400,100);
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        add(label);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/train.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(80, 100, 800, 500);
        add(image);
        
        admin=new JButton("ADMIN");
        admin.setBounds(300,600,100,50);
        admin.addActionListener(this);
        add(admin);

        user=new JButton("USER");
        user.setBounds(600,600,100,50);
        user.addActionListener(this);
        add(user);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==user){
            setVisible(false);
            new Login().setVisible(true);
        }
        else if(e.getSource()==admin){
            setVisible(false);
            new AdminLogin().setVisible(true);
        }
    }

    public static void main(String args[])
    {
        new Main();
    }
}
