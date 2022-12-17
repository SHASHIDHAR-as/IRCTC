import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MyBookings extends JFrame implements ActionListener{
    JPanel panel=new JPanel(new FlowLayout());
    JButton back;
    String userName;
    
    MyBookings(String userName){
        this.userName=userName;
        setTitle("IRCTC");
        setLayout(null);

        JLabel heading=new JLabel("My Bookings");
        heading.setBounds(0,0,100,100);
        add(heading);

        panel.setBounds(100,100,200,400);
        panel.setBackground(Color.gray);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from bookings where user_name='"+userName+"';");
            while(rs.next()){
                String bookingId=rs.getString("booking_id");
                String pnrNo=rs.getString("pnr_no");
                String date=rs.getString("date");
                String ticket=rs.getString("ticket_cost");

                System.out.println(bookingId+" "+pnrNo+" "+date+" "+ticket);

                JLabel bookingIdl=new JLabel("Booking Id : "+bookingId);
                JLabel pnrNol=new JLabel("PNR Number : "+pnrNo);
                JLabel datel=new JLabel("Date of Travel : "+date);
                JLabel ticketl=new JLabel("Ticket Fare : "+ticket);
                JLabel lineBreak=new JLabel("           ");

                panel.add(bookingIdl);
                panel.add(pnrNol);
                panel.add(datel);
                panel.add(ticketl);
                panel.add(lineBreak);

                add(panel);
            }
        }catch(Exception e){
            System.out.println(e);
        }

        back=new JButton("Back");
        back.setBounds(100,600,100,50);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new HomePage(userName);
        }
        
    }
    public static void main(String args[])
    {
        new MyBookings("shashi");
    }

}