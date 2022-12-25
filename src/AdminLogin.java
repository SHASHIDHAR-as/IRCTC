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

        loginId =new JTextField("Login Id");
        loginId.setBounds(100,100,200,50);
        add(loginId);

        password =new JTextField("Password");
        password.setBounds(100,200,200,50);
        add(password);

        login=new JButton("LOGIN");
        login.setBounds(100,400,100,50);
        login.addActionListener(this);
        add(login);

        back=new JButton("BACK");
        back.setBounds(200,400,100,50);
        back.addActionListener(this);
        add(back);
        
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

                ResultSet rs=c.s.executeQuery("SELECT COUNT(login_id) as valid FROM admin_login WHERE login_id='"+login_id+"' and password='"+passwordString+"';");
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
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                    loginId.setText("");
                    password.setText("");
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }
        if(e.getSource()==back){
            setVisible(false);
            new Main().setVisible(true);
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
