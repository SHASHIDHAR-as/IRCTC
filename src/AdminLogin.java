import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class AdminLogin extends JFrame implements ActionListener{
    JTextField loginId,password,otp;
    JButton login,back,getOTP;
    AdminLogin(){
        setTitle("IRCTC");
        setLayout(null);        

        loginId =new JTextField("Login Id");
        loginId.setBounds(100,100,200,50);
        add(loginId);

        password =new JTextField("Password");
        password.setBounds(100,200,200,50);
        add(password);

        otp =new JTextField("OTP");
        otp.setBounds(100,300,200,50);
        add(otp);

        getOTP =new JButton("Get OTP");
        getOTP.setBounds(300,300,80,50);
        add(getOTP);

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
        if(e.getSource()==getOTP){
        //     String login_id=loginId.getText();
        //     String passwordString=password.getText();
        //     String otpEntered=otp.getText();
        //     String genOtp=OTP(4).toString();
        //     System.out.println(genOtp);
        //     if(otp.equals(genOtp)){
        //         try{
        //             Conn c=new Conn();
        //             ResultSet rs=c.s.executeQuery("SELECT COUNT(login_id) as valid FROM admin_login WHERE login_id='"+login_id+"' and password='"+passwordString+"';");
        //             if(rs.next()){
        //                 if(rs.getInt("valid")!=0){
        //                     setVisible(false);
        //                     new Admin((loginId.getText()));
        //                 }else{
        //                     JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
        //                     setVisible(false);
        //                     new Login().setVisible(true);
        //                 }
        //             }
        //         }catch(Exception error){
        //             System.out.println(error);
        //         }
        //     }else{
        //         JOptionPane.showMessageDialog(null,"Invalid OTP..please try again");  
        //             otp.setText("");
        //     }
        
        //     setVisible(false);
        //     new Admin(loginId.getText()).setVisible(true);
        }
        else if(e.getSource()==login){
            String login_id=loginId.getText();
            String passwordString=password.getText();
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("SELECT COUNT(login_id) as valid FROM admin_login WHERE login_id='"+login_id+"' and password='"+passwordString+"';");
                if(rs.next()){
                    if(rs.getInt("valid")!=0){
                        setVisible(false);
                        new Admin((loginId.getText()));
                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                        loginId.setText("");
                        password.setText("");
                    }
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }
        else if(e.getSource()==back){
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
