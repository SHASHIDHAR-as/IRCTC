import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Login extends JFrame implements ActionListener {

    JTextField userName, password,captchaText;
    JButton login,register,back;
    JLabel captchaValue;            //label for displaying selected captcha
    String captcha[]={"12345","lksdj","sldkj","kslfh","78dhr","*&ghg"};        //storing captcha values
    String selectedCaptcha;     //to store selected captcha

    Login() {
        setTitle("IRCTC");
        setLayout(null);

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/login.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1000, 700);
        add(image);

        //username text feild
        userName = new JTextField("User Name");
        userName.setBounds(257, 220, 500, 40);
        userName.setFont(new Font("Raleway", Font.BOLD, 20));
        userName.setForeground(Color.gray);
        userName.setBorder(null);
        image.add(userName);

        //password text feild
        password = new JTextField("Password");
        password.setBounds(257, 280, 500, 40);
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setForeground(Color.gray);
        password.setBorder(null);
        image.add(password);

        //login button
        login = new JButton("LOGIN");
        login.setBounds(689, 393, 80, 30);
        login.setForeground(Color.white);
        login.setBackground(Color.ORANGE);
        login.setFont(new Font("Raleway", Font.BOLD, 24));
        login.setBorder(null);
        login.setOpaque(false);
        login.addActionListener(this);
        image.add(login);

        //generate random captcha and display
        selectedCaptcha=getRandom(captcha);
        captchaValue=new JLabel(selectedCaptcha);
        captchaValue.setBounds(295,380,100,50);
        captchaValue.setForeground(Color.white);
        captchaValue.setFont(new Font("Raleway", Font.BOLD, 23));
        image.add(captchaValue);

        //enter captcha
        captchaText=new JTextField("Captcha");
        captchaText.setBounds(420, 385, 180, 40);
        captchaText.setFont(new Font("Raleway", Font.BOLD, 20));
        captchaText.setForeground(Color.gray);
        captchaText.setBorder(null);
        captchaText.addActionListener(this);
        image.add(captchaText);

        register=new JButton("Register User");
        register.setBounds(415, 470, 170, 30);
        register.setForeground(Color.decode("#E87020"));
        register.setFont(new Font("Raleway", Font.BOLD, 25));
        register.setBackground(Color.white);
        register.setBorder(null);
        // register.setOpaque(false);
        register.addActionListener(this);
        image.add(register);
        
        back=new JButton("BACK");
        back.setBounds(697,470,70,30);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    //function to pick random captcha values
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public void actionPerformed(ActionEvent e) {

        //check for login
        if(e.getSource()==login){
            //check if all the details are entered
            if(userName.getText().equals("") || password.getText().equals("") || captchaText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please fill all the details");  
            }
            //check for captcha validation
            else{
                String enteredCaptcha=captchaText.getText();
                if(selectedCaptcha.equals(enteredCaptcha)){
                    try{
                        Conn c=new Conn();
                        ResultSet rs=c.s.executeQuery("SELECT COUNT(user_name) as valid FROM user_login WHERE user_name='"+userName.getText()+"' and password='"+password.getText()+"';");
                        if(rs.next()){
                            if(rs.getInt("valid")!=0){
                                setVisible(false);
                                new HomePage(userName.getText()).setVisible(true);
                            }else{
                                JOptionPane.showMessageDialog(null,"Incorrect User Name or Password"); 
                                setVisible(false);
                                new Login().setVisible(true);
                            }
                        }
                    }catch(Exception error){
                        System.out.println(error);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Captcha");  
                    captchaText.setText("");
                }
            }
        }
        else if(e.getSource()==register){
            setVisible(false);
            new Register().setVisible(true);
        }
        else if(e.getSource()==back){
            setVisible(false);
            new Main().setVisible(true);
        }
        
    }

    public static void main(String args[]) {
        new Login();
    }

}