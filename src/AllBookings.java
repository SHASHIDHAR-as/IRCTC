import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class AllBookings extends JFrame implements ActionListener{
    
    JPanel mainPanel=new JPanel();
    
    static ArrayList<JPanel> panels=new ArrayList<JPanel>();
    JButton back;
    String loginId;
    AllBookings(String loginId){
        this.loginId=loginId;
        setTitle("IRCTC");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel=new JPanel();
        String content="<html><p>MY BOOKINGS</p><br> </html>";
        JLabel header=new JLabel(content,JLabel.CENTER);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerPanel.setBackground(Color.decode("#e87020"));
        header.setForeground(Color.white);
        header.setFont(new Font("Raleway", Font.BOLD, 20));
        headerPanel.add(header);
        headerPanel.setMaximumSize( new Dimension(  983, 200) );
        mainPanel.add(headerPanel);

        try{
            Conn c=new Conn();
            String query="select b.booking_id,b.user_name,p.pnr_no,b.date,b.ticket_cost,p.train_no,p.train_name from bookings b inner join pnr_status p where b.pnr_no=p.pnr_no;";
            // System.out.println(query);
            ResultSet rs=c.s.executeQuery(query);
            while(rs.next()){
                String bookingId=rs.getString("booking_id");
                String userName=rs.getString("user_name");
                String pnrNo=rs.getString("pnr_no");
                String date=rs.getString("date");
                String ticket=rs.getString("ticket_cost");
                String train_no=rs.getString("train_no");
                String train_name=rs.getString("train_name");
                
                System.out.println(bookingId+" "+pnrNo+" "+date+" "+ticket+ " "+train_no+" "+train_name);
    
                JLabel bookingLabel=new JLabel(bookingId);
                bookingLabel.setBounds(170,16,50,15);
                bookingLabel.setFont(new Font("Raleway", Font.BOLD, 14));

                JLabel userLabel=new JLabel("User Name:");
                userLabel.setBounds(86,39,85,15);
                userLabel.setFont(new Font("Raleway", Font.BOLD, 14));
                
                JLabel userNameLabel=new JLabel(userName);
                userNameLabel.setBounds(175,39,100,15);
                userNameLabel.setFont(new Font("Raleway", Font.BOLD, 14));
                userNameLabel.setForeground(Color.decode("#e87020"));
    
                JLabel pnrLabel=new JLabel(pnrNo);
                pnrLabel.setBounds(352,16,100,15);
                pnrLabel.setFont(new Font("Raleway", Font.BOLD, 14));
                pnrLabel.setForeground(Color.decode("#e87020"));

                JLabel bookedOn=new JLabel(date);
                bookedOn.setBounds(110,113,140,15);
                bookedOn.setFont(new Font("Raleway", Font.BOLD, 14));
                
                JLabel trianNameLabel=new JLabel(train_name.toUpperCase()+"("+train_no+")");
                trianNameLabel.setBounds(86,60,400,25);
                trianNameLabel.setFont(new Font("Raleway", Font.BOLD, 20));

                JLabel costLabel=new JLabel("₹ "+ticket);
                costLabel.setBounds(86,80,100,25);
                costLabel.setFont(new Font("Raleway", Font.BOLD, 14));

                JPanel panel=new JPanel();

                ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/bookingpanel.png"));
                Image i2 = i1.getImage().getScaledInstance(460, 150, Image.SCALE_DEFAULT);
                ImageIcon i3 = new ImageIcon(i2);
                JLabel image = new JLabel(i3);
                image.setBounds(0, 0, 983, 660);
                add(image);

                image.add(bookingLabel);
                image.add(pnrLabel);
                image.add(userLabel);
                image.add(userNameLabel);
                image.add(bookedOn);
                image.add(trianNameLabel);
                image.add(costLabel);

                panel.add(image);

                mainPanel.add(panel);
            }
        }catch(Exception e){
            System.out.println(e);
        }

        back=new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        mainPanel.add(back);
        
        JScrollPane Jscroll = new JScrollPane(mainPanel); 

        setLayout(new BorderLayout());
        add(Jscroll, BorderLayout.CENTER);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back)    {
            setVisible(false);
            new Admin(loginId);
        }
    }
    public static void main(String args[])
    {
        new AllBookings("11111");
    }
}
