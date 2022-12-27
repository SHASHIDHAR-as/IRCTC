import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class HomePage extends JFrame implements ActionListener{
 
    String userName;
    JButton Booking,BookTickets,PNRstatus,Profile,logout,askDisha;
 
    HomePage(String userName) {
        this.userName=userName;
        setTitle("IRCTC");
        setLayout(null);

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/homepage.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        //login button
        BookTickets = new JButton("");
        BookTickets.setBounds(55, 210, 170, 220);
        BookTickets.setBackground(Color.ORANGE);
        BookTickets.setOpaque(false);
        BookTickets.setFont(new Font("Raleway", Font.BOLD, 16));
        BookTickets.setBorder(null);
        BookTickets.addActionListener(this);
        image.add(BookTickets);
        
        Booking=new JButton("");
        Booking.setBounds(300, 200, 170, 225);
        Booking.setBackground(Color.ORANGE);
        Booking.setOpaque(false);
        Booking.setFont(new Font("Raleway", Font.BOLD, 16));
        Booking.setBorder(null);
        Booking.addActionListener(this);
        image.add(Booking);

        PNRstatus=new JButton("");
        PNRstatus.setBounds(540, 210, 170, 220);
        PNRstatus.setBackground(Color.ORANGE);
        PNRstatus.setOpaque(false);
        PNRstatus.setFont(new Font("Raleway", Font.BOLD, 16));
        PNRstatus.setBorder(null);
        PNRstatus.addActionListener(this);
        image.add(PNRstatus);

        Profile=new JButton("");
        Profile.setBounds(770, 210, 170, 220);
        Profile.setBackground(Color.ORANGE);
        Profile.setOpaque(false);
        Profile.setFont(new Font("Raleway", Font.BOLD, 16));
        Profile.setBorder(null);
        Profile.addActionListener(this);
        image.add(Profile);

        logout=new JButton("LOGOUT");
        logout.setBounds(816, 566, 100, 30);
        logout.setForeground(Color.decode("#e87020"));
        logout.setBackground(Color.white);
        logout.setFont(new Font("Raleway", Font.BOLD, 23));
        logout.setBorder(null);
        logout.addActionListener(this);
        image.add(logout);

        askDisha=new JButton("ASK DISHA");
        askDisha.setBounds(70,570,130,30);
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
            new Profile(userName).setVisible(true);
        }else if(e.getSource()==logout){
            setVisible(false);
            new Login().setVisible(true);
        }else if(e.getSource()==askDisha){
            try {
                Runtime.getRuntime().exec("cmd.exe /c start chrome https://irctc.corover.ai/eticket/");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }public static void main(String[] args) {
        new HomePage("suchith");
    }   
}