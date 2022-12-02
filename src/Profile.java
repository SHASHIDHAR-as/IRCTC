import javax.swing.*;
import org.jdesktop.swingx.prompt.PromptSupport;

public class Profile extends JFrame{
    Profile(){
        setTitle("IRCTC");
        setLayout(null);

        JTextArea ex = new JTextArea();  
        PromptSupport.setPrompt("01197585960,01197585961",ex);
        ex.setBounds(100,300,100,40);
        add(ex);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public static void main(String[] args) {
        new Profile();
    }
}
