import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PNRStatus extends JFrame implements ActionListener {
    JLabel label,label2;
    Box pnrPanel = Box.createVerticalBox();
    Box pnrPanel1 = Box.createVerticalBox();
    JTextField pnrNo;
    JButton search, back;
    String userName;

    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);

    PNRStatus(String userName) {
        this.userName = userName;
        setTitle("IRCTC");
        setLayout(null);

        Container c=getContentPane();
        JPanel panel=new JPanel();
        panel.setLayout(null);

        pnrNo = new JTextField("PNR Number");
        pnrNo.setBounds(50, 60, 200, 40);
        panel.add(pnrNo);

        search = new JButton("Search");
        search.setBounds(50, 110, 100, 40);
        search.addActionListener(this);
        panel.add(search);

        back = new JButton("Back");
        back.setBounds(200, 110, 100, 40);
        back.addActionListener(this);
        panel.add(back);

        panel.setBackground(Color.yellow);
        panel.setBounds(0, 0, 1000, 180);
        c.add(panel);

        label = new JLabel("PNR details are :");
        label.setVisible(false);
        pnrPanel.add(label);

        pnrPanel.setBounds(50, 180, 500, 200);
        pnrPanel.setVisible(false);
        add(pnrPanel);


        label2 = new JLabel("PNR details are : shasdfhkashfjkhas");
        label2.setVisible(true);
        pnrPanel1.add(label2);

        pnrPanel1.setBounds(50, 180, 500, 200);
        pnrPanel1.setVisible(true);
        add(pnrPanel1);
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