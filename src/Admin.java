import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

public class Admin extends JFrame implements ActionListener{
    JButton profile,addTrain,bookings,logout,askDisha;
    String loginId;
    Admin(String loginId){
        this.loginId=loginId;

        setTitle("IRCTC");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/admin.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);  

        JLabel name=new JLabel(loginId);
        name.setBounds(440,14,300,40);
        name.setFont(new Font("Raleway", Font.BOLD, 24));
        name.setForeground(Color.white);
        name.setBackground(Color.decode("#e87020"));
        image.add(name);

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select first_name from admin where login_id='"+loginId+"';");

            if(rs.next()){
                JLabel label=new JLabel("welcome "+rs.getString("first_name"));
                label.setBounds(400,100,300,50);
                add(label);
            }
        }
        catch (Exception error) {
            System.out.println(error);
        }
        addTrain=new JButton("");
        addTrain.setBounds(105, 200, 170, 230);
        addTrain.setBackground(Color.ORANGE);
        addTrain.setOpaque(false);
        addTrain.setFont(new Font("Raleway", Font.BOLD, 16));
        addTrain.setBorder(null);
        addTrain.addActionListener(this);
        image.add(addTrain);
        
        bookings=new JButton("");
        bookings.setBounds(400, 200, 170, 225);
        bookings.setBackground(Color.ORANGE);
        bookings.setOpaque(false);
        bookings.setFont(new Font("Raleway", Font.BOLD, 16));
        bookings.setBorder(null);
        bookings.addActionListener(this);
        image.add(bookings);
        
        profile=new JButton("");
        profile.setBounds(705, 210, 170, 220);
        profile.setBackground(Color.ORANGE);
        profile.setOpaque(false);
        profile.setFont(new Font("Raleway", Font.BOLD, 16));
        profile.setBorder(null);
        profile.addActionListener(this);
        image.add(profile);

        logout=new JButton("LOGOUT");
        logout.setBounds(816, 566, 100, 30);
        logout.setForeground(Color.decode("#e87020"));
        logout.setBackground(Color.white);
        logout.setFont(new Font("Raleway", Font.BOLD, 23));
        logout.setBorder(null);
        logout.addActionListener(this);
        image.add(logout);

        askDisha=new JButton("ASK DISHA");
        askDisha.setBounds(65,568,130,30);
        askDisha.setForeground(Color.decode("#e87020"));
        askDisha.setFont(new Font("Raleway", Font.BOLD, 23));
        askDisha.setBorder(null);
        askDisha.setBackground(Color.white);
        askDisha.addActionListener(this);
        image.add(askDisha);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
        
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==profile){
            System.out.println("prilfe prwessd");
            setVisible(false);
            new AdminProfile(loginId);
        }
        else if(e.getSource()==addTrain){
            setVisible(false);
            new AddTrains(loginId);
        }
        else if(e.getSource()==bookings){
            setVisible(false);
            new AllBookings(loginId);
        }
        else if(e.getSource()==logout){
            setVisible(false);
            new AdminLogin();
        }else if(e.getSource()==askDisha){
            try {
                Runtime.getRuntime().exec("cmd.exe /c start chrome https://irctc.corover.ai/eticket/");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        new Admin("22222");
    }

}
