import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class AdminProfile extends JFrame implements ActionListener {
    JButton back;
    String loginId, Name, gender, address,email;
    JLabel admin,name,gen,add,emailid;
    AdminProfile(String loginId) {
        this.loginId=loginId;
        System.out.println("hahf;lafh");
        setTitle("IRCTC");
        setLayout(null);
                ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/AdminProfile.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        admin = new JLabel();
        admin.setBounds(400, 75, 500, 50);
        admin.setFont(new Font("Raleway", Font.BOLD, 20));
        admin.setBackground(Color.decode("#e87020"));
        admin.setForeground(Color.WHITE);
        admin.setBorder(null);
        admin.setOpaque(true);
        image.add(admin);

        emailid = new JLabel();
        emailid.setBounds(400, 125, 500, 50);
        emailid.setFont(new Font("Raleway", Font.BOLD, 20));
        emailid.setBackground(Color.decode("#e87020"));
        emailid.setForeground(Color.WHITE);
        emailid.setBorder(null);
        emailid.setOpaque(true);
        image.add(emailid);


        name = new JLabel();        
        name.setBounds(480, 214, 200, 50);
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBackground(Color.white);
        name.setForeground(Color.black);
        name.setBorder(null);
        name.setOpaque(false);
        image.add(name);

        gen = new JLabel();
        gen.setBounds(480, 252, 200, 50);
        gen.setFont(new Font("Raleway", Font.BOLD, 20));
        gen.setBackground(Color.white);
        gen.setForeground(Color.black);
        gen.setBorder(null);
        gen.setOpaque(false);
        image.add(gen);

        add = new JLabel();        
        add.setBounds(480, 289, 200, 50);
        add.setFont(new Font("Raleway", Font.BOLD, 20));
        add.setBackground(Color.white);
        add.setForeground(Color.black);
        add.setBorder(null);
        add.setOpaque(false);
        image.add(add);

        try{
            Conn c=new Conn();

            String query="select login_id ,concat(first_name,last_name) as Name,gender,address from admin where login_id='"+loginId+"';";
            System.out.println(query);
            ResultSet rs=c.s.executeQuery(query);

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
                ResultSet rsd = c.s.executeQuery("SELECT email_id FROM admin_login WHERE login_id='"+loginId+"';");
                while(rsd.next()){
                    email=rsd.getString("email_id");
                }
                emailid.setText(email);
        }
        catch (Exception error) {
            System.out.println(error);
        }

        back = new JButton("Back");
        back.setBounds(810, 530, 100, 30);
        back.setForeground(Color.decode("#e87020"));
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setBorder(null);
        back.setBackground(Color.white);
        back.addActionListener(this);

        add(back);
        setSize(1000, 700);
        setVisible(true);
        setLocation(280, 80);
    }

    public void actionPerformed( ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            new Admin(loginId);
    }
}

    public static void main(String[] args) {
        new AdminProfile("11111");
    }
}