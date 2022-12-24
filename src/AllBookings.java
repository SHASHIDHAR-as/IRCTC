import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AllBookings extends JFrame implements ActionListener{
    String loginId;
    JPanel panel=new JPanel();
    JButton back=new JButton("back");
    
    AllBookings(String loginId){
        this.loginId=loginId;

        setTitle("IRCTC");
        setLayout(null);

        JLabel heading=new JLabel("VIEW BOOKINGS");
        heading.setBounds(400,50,300,50);
        add(heading);

        back.setBounds(400,600,100,50);
        back.addActionListener(this);
        add(back);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(100,100,700,400);
        panel.setBackground(Color.CYAN);
        add(panel);

        try{
            Conn c=new Conn();
            String query="select * from bookings;";
            ResultSet rs= c.s.executeQuery(query);
            while(rs.next()){
                String bookingId=rs.getString("booking_id");
                String pnrNO=rs.getString("pnr_no");
                String user_name=rs.getString("user_name");
                String date=rs.getString("date");
                String cost=rs.getString("ticket_cost");
                System.out.println(bookingId+" "+pnrNO+" "+user_name+" "+date+" "+cost);

                JLabel label = new JLabel("<html><p>booking id : "+bookingId+"<br>pnr number : "+pnrNO+"<br>user name : "+user_name+"<br>date of travel : "+date+"<br>cost : "+cost+"<hr></p></html>");
                // label.setBounds(100,100,500,300);
                panel.add(label);
            }
        }catch(Exception error){
            System.out.println(error);
        }

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);

    }
    public static void main(String args[])
    {
        new AllBookings("11111");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new Admin(loginId);
        }
    }
}
