import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Profile extends JFrame implements ActionListener {
    JButton back;
    String userName, Name, gender, address, nationality, dob, phone;
    JLabel user,na,gen,add,nat,bir,pho;
    Profile(String userName) {
        this.userName=userName;

        setTitle("IRCTC");
        setLayout(null);

        JLabel label1 = new JLabel("userName :");
        label1.setBounds(190, 20, 200, 50);
        add(label1);

        user = new JLabel();
        user.setBounds(300, 20, 200, 50);
        add(user);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(190, 40, 200, 50);
        add(label2);

        na = new JLabel();
        na.setBounds(300, 40, 200, 50);
        add(na);

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

        JLabel label5 = new JLabel("NATIONALITY:");
        label5.setBounds(190, 100, 200, 50);
        add(label5);

        nat = new JLabel();
        nat.setBounds(300, 100, 200, 50);
        add(nat);

        JLabel label6 = new JLabel("DOB:");
        label6.setBounds(190, 120, 200, 50);
        add(label6);

        bir  = new JLabel();
        bir.setBounds(300, 120, 200, 50);
        add(bir);

        JLabel label7 = new JLabel("PHONE:");
        label7.setBounds(190, 140, 200, 50);
        add(label7);

        pho= new JLabel();
        pho.setBounds(300, 140, 200, 50);
        add(pho);




        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select userName ,concat(first_name,last_name) as Name,gender,address,nationality,dob,phone from user where userName='"+userName+"';");

            System.out.println("the details are:");
                while(rs.next()){
                userName=rs.getString("userName");
                Name=rs.getString("Name");
                gender=rs.getString("gender");
                address=rs.getString("address");
                nationality=rs.getString("nationality");
                dob=rs.getString("dob");
                phone=rs.getString("phone");
                // System.out.println(userName+" "+Name+ " "+gender+" "+address+" "+nationality+" "+dob+ " "+phone);
                }
                user.setText(userName);
                na.setText(Name);
                gen.setText(gender);
                add.setText(address);
                nat.setText(nationality);
                bir.setText(dob);
                pho.setText(phone);
            // }
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
            new HomePage(userName).setVisible(true);
    }
}

    public static void main(String[] args) {
        new Profile("sf");
    }
}