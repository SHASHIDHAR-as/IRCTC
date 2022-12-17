
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HomePage extends JFrame implements ActionListener{

    JButton Booking,BookTickets, PNRstatus,Profile;
 
    String user_name;
    HomePage(String user_name) {
        this.user_name=user_name;
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

        Booking=new JButton("Bookings");
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

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {

        //check for login
        if(e.getSource()==BookTickets){
            setVisible(false);
            new SearchTrains(user_name).setVisible(true);
        }
        else if(e.getSource()==Booking){
            setVisible(false);
            new MyBookings(user_name).setVisible(true);
        }
        else if(e.getSource()==PNRstatus){
            setVisible(false);
            new PNRStatus(user_name).setVisible(true);
        }
        else if(e.getSource()==Profile){
            setVisible(false);
            new Profile(user_name).setVisible(true);
        }
    }public static void main(String[] args) {
        new HomePage("shashi");
    }   
}
