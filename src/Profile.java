import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Profile extends JFrame implements ActionListener {
    JButton back;
    String userName, Name, gender, address, nationality, dob, phone,email;
    JLabel user,na,gen,add,nat,bir,emailid,pho;
    Profile(String userName) {
        this.userName=userName;

        setTitle("IRCTC");
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/UserProfile.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        user = new JLabel();
        user.setBounds(400, 75, 500, 50);
        user.setFont(new Font("Raleway", Font.BOLD, 20));
        user.setBackground(Color.decode("#e87020"));
        user.setForeground(Color.WHITE);
        user.setBorder(null);
        user.setOpaque(true);
        image.add(user);

        emailid = new JLabel();
        emailid.setBounds(400, 125, 500, 50);
        emailid.setFont(new Font("Raleway", Font.BOLD, 20));
        emailid.setBackground(Color.decode("#e87020"));
        emailid.setForeground(Color.WHITE);
        emailid.setBorder(null);
        emailid.setOpaque(true);
        image.add(emailid);

        na = new JLabel();
        na.setBounds(480, 214, 200, 50);
        na.setFont(new Font("Raleway", Font.BOLD, 20));
        na.setBackground(Color.white);
        na.setForeground(Color.black);
        na.setBorder(null);
        na.setOpaque(false);
        image.add(na);

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

        nat = new JLabel();
        nat.setBounds(480, 327, 200, 50);
        nat.setFont(new Font("Raleway", Font.BOLD, 20));
        nat.setBackground(Color.white);
        nat.setForeground(Color.black);
        nat.setBorder(null);
        nat.setOpaque(false);
        image.add(nat);


        bir  = new JLabel();
        bir.setBounds(480, 372, 200, 50);
        bir.setFont(new Font("Raleway", Font.BOLD, 20));
        bir.setBackground(Color.white);
        bir.setForeground(Color.black);
        bir.setBorder(null);
        bir.setOpaque(false);
        image.add(bir);

        pho= new JLabel();
        pho.setBounds(480, 406, 200, 50);
        pho.setFont(new Font("Raleway", Font.BOLD, 20));
        pho.setBackground(Color.white);
        pho.setForeground(Color.black);
        pho.setBorder(null);
        pho.setOpaque(false);
        image.add(pho);

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select user_name ,concat(first_name,last_name) as Name,gender,address,nationality,dob,phone from user where user_name='"+userName+"';");

            System.out.println("the details are:");
            while(rs.next()){
                userName=rs.getString("user_name");
                Name=rs.getString("Name");
                gender=rs.getString("gender");
                address=rs.getString("address");
                nationality=rs.getString("nationality");
                dob=rs.getString("dob");
                phone=rs.getString("phone");
            
            }
            ResultSet rsd = c.s.executeQuery("SELECT email  FROM user_login WHERE user_name='"+userName+"';");
                while(rsd.next()){
                    email=rsd.getString("email");
                }
                user.setText(userName);
                na.setText(Name);
                gen.setText(gender);
                add.setText(address);
                nat.setText(nationality);
                bir.setText(dob);
                pho.setText(phone);
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
        image.add(back);

        setSize(1000, 700);
        setVisible(true);
        setLocation(280, 80);
    }

    public void actionPerformed( ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            new HomePage(userName).setVisible(true);
    }
}

    public static void main(String[] args) {
        new Profile("shas");
    }
}