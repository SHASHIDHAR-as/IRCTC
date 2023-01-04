import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Pnr extends JFrame implements ActionListener {
    boolean buttonPressed = false;
    JLabel label,label2,image;
    Box pnrPanel = Box.createVerticalBox();
    JTextField pnrNo;
    JButton search, back,SearchAgain;
    String userName;

    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);
    JPanel pnrPanel1,panel,detail;

    Pnr(String userName) {
        this.userName = userName;
        setTitle("IRCTC");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/Pnr.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        panel=new JPanel();
        panel.setLayout(null);

        pnrNo = new JTextField("PNR Number");
        pnrNo.setFont(new Font("Raleway", Font.PLAIN, 17));
        pnrNo.setBounds(270, 30, 400, 40);
        TextAnimator.textAnimator(pnrNo,"PNR NUMBER");
        pnrNo.setBorder(null);
        panel.add(pnrNo);

        search = new JButton("Search");
        search.setBounds(270, 100, 100, 40);
        search.setFont(new Font("Raleway", Font.BOLD, 23));
        search.setForeground(Color.decode("#E87020"));
        search.setBackground(Color.black);
        search.setBorder(null);
        search.setOpaque(false);
        search.addActionListener(this);
        panel.add(search);

        back = new JButton("Back");
        back.setBounds(800, 110, 100, 40);
        back.setFont(new Font("Raleway", Font.BOLD, 23));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        panel.add(back);
        panel.setBorder(null);
        panel.setForeground(Color.gray);
        panel.setBounds(0, 70, 1000, 190);
        image.add(panel);

        detail=new JPanel();
        detail.setLayout(null);
        String content1="<html><p>PNR details are :</p><br> </html>";
        label = new JLabel(content1);
        label.setBounds(20,0,200,50);
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        // label.setVisible(false);
        detail.setBounds(0, 260, 1000, 2500);
        detail.setVisible(false);
        detail.add(label);
        image.add(detail);

        pnrPanel.setBounds(200, 550, 500, 100);
        pnrPanel.setVisible(false);
        image.add(pnrPanel);

        pnrPanel1=new JPanel();
        String content="<html><p>NOTE : :</p><br><p>        \thi this is shashidhar<br>from chintamani</p> </html>";
        pnrPanel1.setLayout(null);
        label2 = new JLabel(content);
        label2.setBounds(20,10,200,100);
        label2.setFont(new Font("Raleway", Font.BOLD, 20));
        label2.setVisible(true);
        pnrPanel1.add(label2);
        pnrPanel1.setBounds(0, 260, 1000, 500);
        // pnrPanel1.setBackground(Color.gray);
        pnrPanel1.setBackground(Color.decode("#D9D9D9"));
        pnrPanel1.setVisible(true);
        pnrPanel1.setOpaque(true);
        image.add(pnrPanel1);
        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search& !buttonPressed) {
            label2.setVisible(false);
            pnrPanel1.setVisible(false);
            // label.setVisible(true);
            pnrPanel.setVisible(true);
            detail.setVisible(true);
            String pnr=pnrNo.getText();
            try {
                Conn c = new Conn();
                String query="select b.date,p.pnr_no,p.train_no,p.train_name,p.from_station,p.to_station from bookings b inner join pnr_status  p where p.pnr_no='"+pnr+"'and  b.pnr_no=p.pnr_no;";
                // System.out.println(query);
                ResultSet rs=c.s.executeQuery(query);
                while (rs.next()) {
                    String date=rs.getString("date");
                    String pnr_no = rs.getString("pnr_no");
                    // String ticket=rs.getString("ticket_cost");
                    int train_no = rs.getInt("train_no");
                    String train_name = rs.getString("train_name");
                    String from_station = rs.getString("from_station");
                    String to_station = rs.getString("to_station");
                    // int seat_num=rs.getInt("")
                    
                    JLabel pnr_noL = new JLabel("PNR Number : " + pnr_no);
                    pnr_noL.setFont(new Font("Raleway", Font.BOLD, 20));
                    pnr_noL.setBounds(50,40,500,30);
                    
                    JLabel train_noL = new JLabel("Train Number : " + train_no);
                    train_noL.setFont(new Font("Raleway", Font.BOLD, 20));
                    train_noL.setBounds(50,70,500,30);
                    train_noL.setBackground(Color.black);
                    train_noL.setOpaque(true);
                    
                    JLabel train_nameL = new JLabel("Train Name : " + train_name);
                    train_nameL.setFont(new Font("Raleway", Font.BOLD, 20));
                    train_nameL.setBounds(50,100,500,30);
                    train_nameL.setBackground(Color.orange);
                    train_nameL.setOpaque(true);
                    
                    JLabel from_stationL = new JLabel("From : " + from_station);
                    from_stationL.setFont(new Font("Raleway", Font.BOLD, 10));
                    train_nameL.setBounds(50,130,500,30);
                    
                    JLabel to_stationL = new JLabel("To : " + to_station);
                    to_stationL.setFont(new Font("Raleway", Font.BOLD, 10));
                    train_nameL.setBounds(50,190,500,30);

                    detail.add(pnr_noL);
                    detail.add(train_noL);
                    detail.add(train_nameL);
                    detail.add(from_stationL);
                    detail.add(to_stationL);
                    // image.add(detail);
                    
                }

                model.addColumn("Name");
                model.addColumn("Age");
                model.addColumn("Gender");
                model.addColumn("Seat_no");
                rs = c.s.executeQuery("select Name,Age,Gender,seat_no from passengers where Pnr_no='" + pnrNo.getText() + "';");
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getString("Name"), rs.getString("Age"), rs.getString("Gender"), rs.getString("Seat_no") });
                }
                JScrollPane pg = new JScrollPane(jtbl);
                pnrPanel.add(pg);
                buttonPressed = true;
                // pnrPanel.add(jtbl);
            } catch (Exception error) {
                System.out.println(error);
            }
        } 
        
        else if (e.getSource() == back) {
            setVisible(false);
            new HomePage(userName);
        }

    }

    public static void main(String args[]) {
        new Pnr("shashi");
    }

}