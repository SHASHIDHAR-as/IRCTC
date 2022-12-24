import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminProfile extends JFrame implements ActionListener {
    JButton back;
    String loginId, Name, gender, address;
    JLabel admin,name,gen,add;
    AdminProfile(String loginId) {
        this.loginId=loginId;

        setTitle("IRCTC");
        setLayout(null);

        JLabel label1 = new JLabel("loginId :");
        label1.setBounds(190, 20, 200, 50);
        add(label1);

        admin = new JLabel();
        admin.setBounds(300, 20, 200, 50);
        add(admin);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(190, 40, 200, 50);
        add(label2);

        name = new JLabel();
        name.setBounds(300, 40, 200, 50);
        add(name);

        JLabel label3 = new JLabel("Gender :");
        label3.setBounds(190, 60, 200, 50);
        add(label3);

        gen = new JLabel();
        gen.setBounds(300, 60, 200, 50);
        add(gen);

        JLabel label4 = new JLabel("ADDRESS:");
        label4.setBounds(190, 80, 200, 50);
        add(label4);

        add = new JLabel();
        add.setBounds(300, 80, 200, 50);
        add(add);

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select login_id ,concat(first_name,last_name) as Name,gender,address from admin where login_id='"+loginId+"';");

            System.out.println("the details are:");
                while(rs.next()){
                    loginId=rs.getString("login_id");
                    Name=rs.getString("Name");
                    gender=rs.getString("gender");
                    address=rs.getString("address");

                    System.out.println(loginId+" "+Name+ " "+gender+" "+address+" ");
                }
                admin.setText(loginId);
                name.setText(Name);
                gen.setText(gender);
                add.setText(address);
        }
        catch (Exception error) {
            System.out.println(error);
        }

        back = new JButton("Back");
        back.setBounds(100, 600, 100, 30);
        back.addActionListener(this);
        add(back);
        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed( ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            new Admin(loginId);
    }
}

    public static void main(String[] args) {
        new AdminProfile("sf");
    }
}