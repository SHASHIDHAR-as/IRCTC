import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame implements ActionListener{
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

        otp =new JTextField("OTP");
        otp.setBounds(100,300,200,50);
        add(otp);

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
            setVisible(false);
            new Admin().setVisible(true);
        }
    }
    public static void main(String args[])
    {
        new AdminLogin();
    }
}
