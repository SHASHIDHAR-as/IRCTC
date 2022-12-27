import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class AdminLogin extends JFrame implements ActionListener{
    String genOtp,email;
    JTextField loginId,password,otp;
    JButton login,back;
    AdminLogin(){
        setTitle("IRCTC");
        setLayout(null);    

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/adminlogin.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);    

        loginId =new JTextField("Admin Id");
        loginId.setBounds(250,240,490,30);
        loginId.setFont(new Font("Raleway", Font.BOLD, 20));
        loginId.setForeground(Color.gray);
        loginId.setBorder(null);
        image.add(loginId);

        password =new JTextField("Password");
        password.setBounds(250,318,490,30);
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setForeground(Color.gray);
        password.setBorder(null);
        image.add(password);

        login=new JButton("LOGIN");
        login.setBounds(662,408,90,40);
        login.setForeground(Color.white);
        login.setBackground(Color.decode("#e87020"));
        login.setFont(new Font("Raleway", Font.BOLD, 24));
        login.setBorder(null);
        login.addActionListener(this);
        image.add(login);

        back=new JButton("BACK");
        back.setBounds(250,410,70,30);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.white);
        back.setBorder(null);
        back.addActionListener(this);
        image.add(back);
        
        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login){
            genOtp=String.copyValueOf(OTP(4));
            System.out.println(genOtp);
            String login_id=loginId.getText();
            String passwordString=password.getText();
            try{
                Conn c=new Conn();

                String query="SELECT COUNT(login_id) as valid FROM admin_login WHERE login_id='"+login_id+"' and password='"+passwordString+"';";
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    if(rs.getInt("valid")!=0){
                        rs=c.s.executeQuery("select email_id from admin_login where login_id='"+loginId.getText()+"';");
                        if(rs.next()){
                            email=rs.getString("email_id");
                            System.out.println(email);
                            SendOTP.sendOTP(genOtp,email);
                            String enteredOtp= JOptionPane.showInputDialog("Enter the otp sent to your email "); 
                            System.out.println(enteredOtp);
                            if(genOtp.equals(enteredOtp)){
                                setVisible(false);
                                new Admin(login_id);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Incorrect OTP. please try again"); 
                            }
                        }
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                    loginId.setText("");
                    password.setText("");
                }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                    loginId.setText("");
                    password.setText("");
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }else if(e.getSource()==back){
            setVisible(false);
            new Main();
        }
    }

    static char[] OTP(int len)
    {
        System.out.print("You OTP is : ");
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] =numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
    public static void main(String args[])
    {
        new AdminLogin();
    }
}