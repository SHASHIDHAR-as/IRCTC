import javax.swing.*;

public class AddPassengers extends JFrame{
    AddPassengers(BookedTrain details){
        System.out.println(details.train_no);
        System.out.println(details.train_name);
        System.out.println(details.cost);

        setTitle("IRCTC");

        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String args[])
    {
        new AddPassengers(null);
    }
}
