import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PNRStatus extends JFrame implements ActionListener {
    JLabel label;
    Box pnrPanel = Box.createVerticalBox();
    JTextField pnrNo;
    JButton search, back;
    String userName;

    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);

    PNRStatus(String userName) {
        this.userName = userName;
        setTitle("IRCTC");
        setLayout(null);

        pnrNo = new JTextField("PNR Number");
        pnrNo.setBounds(50, 50, 200, 50);
        add(pnrNo);

        search = new JButton("Search");
        search.setBounds(50, 100, 100, 50);
        search.addActionListener(this);
        add(search);

        back = new JButton("Back");
        back.setBounds(200, 100, 100, 50);
        back.addActionListener(this);
        add(back);

        label = new JLabel("PNR details are :");
        label.setVisible(false);
        pnrPanel.add(label);

        pnrPanel.setBounds(50, 180, 500, 200);
        pnrPanel.setVisible(false);
        add(pnrPanel);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
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
                rs = c.s.executeQuery("select Name,Age,Gender from passengers where Pnr_no='" + pnrNo.getText() + "';");
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
        new PNRStatus("suchithkumar");
    }

}