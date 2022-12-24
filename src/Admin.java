import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Admin extends JFrame implements ActionListener{
    JButton profile,addTrain,bookings,logout;
    String loginId;
    Admin(String loginId){
        this.loginId=loginId;

        setTitle("IRCTC");
        setLayout(null);

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
        
        profile =new JButton("view profile");
        profile.setBounds(100,0,100,50);
        profile.addActionListener(this);
        add(profile);
        
        addTrain =new JButton("Add train");
        addTrain.setBounds(100,100,100,50);
        addTrain.addActionListener(this);
        add(addTrain);
        
        bookings =new JButton("view bookings");
        bookings.setBounds(100,200,100,50);
        bookings.addActionListener(this);
        add(bookings);
        
        logout =new JButton("Logout");
        logout.setBounds(100,500,100,50);
        logout.addActionListener(this);
        add(logout);

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
        }

        
    }
    public static void main(String args[])
    {
        new Admin("22222");
    }

}
