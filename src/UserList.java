import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UserList extends JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

    public UserList() {
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Name");
        model.addColumn("age");
        model.addColumn("gender");
        try {
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select name,age,pnr_no from passengers where pnr_no=1111111111");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        setTitle("Swing Example");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
    public static void main(String[] args) {
        new UserList();
    }
}