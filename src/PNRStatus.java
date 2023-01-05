import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.sql.*;

public class PNRStatus extends JFrame implements ActionListener {
    boolean buttonPressed = false;
    JLabel label,label2;
    JTextField pnrNo;
    JButton search, back,SearchAgain;
    String userName;
    JPanel mainPanel=new JPanel();
    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);
    JPanel pnrPanel1,searchpanel;

    PNRStatus(String userName) {
        this.userName = userName;
        setTitle("IRCTC");
        setLayout(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/Pnr.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        searchpanel=new JPanel();
        searchpanel.setLayout(null);

        pnrNo = new JTextField("PNR Number");
        pnrNo.setFont(new Font("Raleway", Font.PLAIN, 17));
        pnrNo.setBounds(270, 30, 400, 40);
        TextAnimator.textAnimator(pnrNo,"PNR NUMBER");
        pnrNo.setBorder(null);
        searchpanel.add(pnrNo);

        search = new JButton("SEARCH");
        search.setBounds(270, 100, 100, 40);
        search.setFont(new Font("Raleway", Font.BOLD, 23));
        search.setForeground(Color.decode("#E87020"));
        search.setBackground(Color.black);
        search.setBorder(null);
        search.setOpaque(false);
        search.addActionListener(this);
        searchpanel.add(search);

        back = new JButton("BACK");
        back.setBounds(650, 110, 100, 40);
        back.setFont(new Font("Raleway", Font.BOLD, 23));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        searchpanel.add(back);
        searchpanel.setBorder(null);
        searchpanel.setForeground(Color.gray);
        searchpanel.setBounds(0, 70, 1000, 190);

        image.add(searchpanel);

        String content1="<html><p>PNR details are :</p><br> </html>";
        label = new JLabel(content1);
        // label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(0,10,200,100);
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        label.setVisible(false);

        mainPanel.add(label);
        mainPanel.setBounds(0, 260, 1000, 400);
        mainPanel.setVisible(false);
        image.add(mainPanel);

        pnrPanel1=new JPanel();
        String content="<html><p>NOTE : :</p><br><p>        1. No refund shall be granted on the confirmed ticket after four hours before the scheduled departure of the train.<br>2.No refund shall be granted on the RAC or Waitlisted ticket after thirty minutes before the scheduled departure of the train.<brIn case,on a party e_ticket or a family e-ticket issued for travel of more than one passenger,some passengers have confirmed reservation and others are on RAC or waiting list,full refund of fare,less clerkaage,shall be admissible for confirmed passengers also subject to the condition that the ticket shall be cancelled online or online TDR shall be filled for all the passengers up to thirty minutes before the scheduled departure of the train.</p> </html>";
        pnrPanel1.setLayout(null);
        label2 = new JLabel(content);
        label2.setBounds(20,0,950,300);
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
        setLocation(280, 80);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search& !buttonPressed) {
            label2.setVisible(false);
            pnrPanel1.setVisible(false);
            label.setVisible(true);
            mainPanel.setVisible(true);
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
                    System.out.println(date);

                    JLabel pnr_noL = new JLabel("PNR Number : " + pnr_no);
                    // JLabel pnr_noL = new JLabel("<html><p>PNR Number :</p><br> </html> : " + pnr_no);
                    pnr_noL.setFont(new Font("Raleway", Font.BOLD, 20));
                    JLabel dates = new JLabel("DATE : " + date);
                    // JLabel pnr_noL = new JLabel("<html><p>PNR Number :</p><br> </html> : " + pnr_no);
                    dates.setFont(new Font("Raleway", Font.BOLD, 20));

                    JLabel train_noL = new JLabel("TRAIN NUMBER : " + train_no);
                    train_noL.setFont(new Font("Raleway", Font.BOLD, 20));
                    // JLabel dates = new JLabel("Date : " + date);
                    // dates.setFont(new Font("Raleway", Font.BOLD, 20));

                    JLabel train_nameL = new JLabel("TRAIN NAME: " + train_name);
                    train_nameL.setFont(new Font("Raleway", Font.BOLD, 20));

                    JLabel from_stationL = new JLabel("FROM : " + from_station);
                    from_stationL.setFont(new Font("Raleway", Font.BOLD, 20));

                    JLabel to_stationL = new JLabel("TO : " + to_station);
                    to_stationL.setFont(new Font("Raleway", Font.BOLD, 20));

                    mainPanel.add(pnr_noL);
                    mainPanel.add(train_noL);
                    mainPanel.add(dates);
                    mainPanel.add(train_nameL);
                    mainPanel.add(from_stationL);
                    mainPanel.add(to_stationL);
                }

                model.addColumn("Name");
                model.addColumn("Age");
                model.addColumn("Gender");
                model.addColumn("Seat_no");
                rs = c.s.executeQuery("select Name,Age,Gender,seat_no from passenger where Pnr_num='" + pnrNo.getText() + "';");
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getString("Name"), rs.getString("Age"), rs.getString("Gender"), rs.getString("Seat_no") });
                }
                JScrollPane pg = new JScrollPane(jtbl);
                mainPanel.add(pg);
                JTableHeader Theader=jtbl.getTableHeader();
                Theader.setBackground(Color.decode("#e87020"));
                Theader.setFont(new Font("Raleway", Font.BOLD, 20));
                jtbl.setFont(new Font("Raleway", Font.PLAIN, 16));
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
        new PNRStatus("shashi");
    }

}