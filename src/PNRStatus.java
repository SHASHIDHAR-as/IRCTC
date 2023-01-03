import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PNRStatus extends JFrame implements ActionListener {
    JLabel label,label2;
    Box pnrPanel = Box.createVerticalBox();
    // Box pnrPanel1 = Box.createVerticalBox();
    JTextField pnrNo;
    JButton search, back;
    String userName;

    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);
    JPanel pnrPanel1;

    PNRStatus(String userName) {
        this.userName = userName;
        setTitle("IRCTC");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/Pnr.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        // Container c=getContentPane();
        JPanel panel=new JPanel();
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
        // panel.setBackground(Color.decode("#e87020"));

        // panel.setBackground(Color.yellow);
        panel.setBounds(0, 70, 1000, 190);
        // c.add(panel);
        image.add(panel);

        label = new JLabel("PNR details are :");
        label.setVisible(false);
        pnrPanel.add(label);
        pnrPanel.setBounds(200, 350, 500, 200);
        pnrPanel.setVisible(false);
        image.add(pnrPanel);

        pnrPanel1=new JPanel();
        String content="<html><p>NOTE : :</p><br> </html>";
        pnrPanel1.setLayout(null);
        label2 = new JLabel(content);
        // label2.setAlignmentX(TOP_ALIGNMENT);
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
        setLocation(280, 80);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            label2.setVisible(false);
            pnrPanel1.setVisible(false);
            label.setVisible(true);
            pnrPanel.setVisible(true);
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from pnr_status where pnr_no='" + pnrNo.getText() + "';");
                while (rs.next()) {
                    String pnr_no = rs.getString("pnr_no");
                    int train_no = rs.getInt("train_no");
                    String train_name = rs.getString("train_name");
                    String from_station = rs.getString("from_station");
                    String to_station = rs.getString("to_station");
                    // int seat_num=rs.getInt("")

                    JLabel pnr_noL = new JLabel("PNR Number : " + pnr_no);
                    JLabel train_noL = new JLabel("Train Number : " + train_no);
                    JLabel train_nameL = new JLabel("Train Name : " + train_name);
                    JLabel from_stationL = new JLabel("From : " + from_station);
                    JLabel to_stationL = new JLabel("To : " + to_station);

                    pnrPanel.add(pnr_noL);
                    pnrPanel.add(train_noL);
                    pnrPanel.add(train_nameL);
                    pnrPanel.add(from_stationL);
                    pnrPanel.add(to_stationL);
                }

                model.addColumn("Name");
                model.addColumn("age");
                model.addColumn("gender");
                rs = c.s.executeQuery("select Name,Age,Gender from passenger where Pnr_num='" + pnrNo.getText() + "';");
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getString("Name"), rs.getString("Age"), rs.getString("Gender") });
                }
                JScrollPane pg = new JScrollPane(jtbl);
                pnrPanel.add(pg);
                // pnrPanel.add(jtbl);
            } catch (Exception error) {
                System.out.println(error);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new HomePage(userName);
        }

    }

    public static void main(String args[]) {
        new PNRStatus("shashi");
    }

}