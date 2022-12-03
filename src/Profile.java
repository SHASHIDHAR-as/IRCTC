import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame{
    Profile(){
        setTitle("IRCTC");
        setLayout(null);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public static void main(String[] args) {
        new Profile();
    }
}
