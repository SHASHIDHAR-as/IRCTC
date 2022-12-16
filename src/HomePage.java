import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HomePage extends JFrame implements ActionListener{
 
    String userName;
    JButton Booking,BookTickets,PNRstatus,Profile,logout;
 
    HomePage(String userName) {
        this.userName=userName;
        setTitle("IRCTC");
        setLayout(null);

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/orange.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1000, 700);
        add(image);

       

        //password text feild
      

        //login button
        BookTickets = new JButton("Book Tickets");
        BookTickets.setBounds(100, 300, 150, 30);
        BookTickets.setForeground(Color.black);
        BookTickets.setBackground(Color.ORANGE);
        BookTickets.setFont(new Font("Raleway", Font.BOLD, 16));
        BookTickets.setBorder(null);
        BookTickets.addActionListener(this);
        image.add(BookTickets);

        Booking=new JButton("My Bookings");
        Booking.setBounds(300, 300, 150, 30);
        Booking.setForeground(Color.black);
        Booking.setBackground(Color.ORANGE);
        Booking.setFont(new Font("Raleway", Font.BOLD, 16));
        Booking.setBorder(null);
        Booking.addActionListener(this);
        image.add(Booking);

        PNRstatus=new JButton("PNR status");
        PNRstatus.setBounds(500, 300, 150, 30);
        PNRstatus.setForeground(Color.black);
        PNRstatus.setBackground(Color.ORANGE);
        PNRstatus.setFont(new Font("Raleway", Font.BOLD, 16));
        PNRstatus.setBorder(null);
        PNRstatus.addActionListener(this);
        image.add(PNRstatus);

        Profile=new JButton("Profile");
        Profile.setBounds(700, 300, 150, 30);
        Profile.setForeground(Color.black);
        Profile.setBackground(Color.ORANGE);
        Profile.setFont(new Font("Raleway", Font.BOLD, 16));
        Profile.setBorder(null);
        Profile.addActionListener(this);
        image.add(Profile);

        logout=new JButton("Log Out");
        logout.setBounds(700, 500, 150, 30);
        logout.setForeground(Color.black);
        logout.setBackground(Color.ORANGE);
        logout.setFont(new Font("Raleway", Font.BOLD, 16));
        logout.setBorder(null);
        logout.addActionListener(this);
        image.add(logout);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
 
    public void actionPerformed(ActionEvent e) {

        //check for login
        if(e.getSource()==BookTickets){
            setVisible(false);
            new SearchTrains(userName).setVisible(true);
        }
        else if(e.getSource()==Booking){
            setVisible(false);
            new MyBookings(userName).setVisible(true);
        }
        else if(e.getSource()==PNRstatus){
            setVisible(false);
            new PNRStatus(userName).setVisible(true);
        }
        else if(e.getSource()==Profile){
            setVisible(false);
            new Register().setVisible(true);
        }else if(e.getSource()==logout){
            setVisible(false);
            new Login().setVisible(true);
        }
    }public static void main(String[] args) {
        new HomePage("suchith");
    }   
}
