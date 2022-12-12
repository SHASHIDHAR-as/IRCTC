import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatPane extends JFrame {

    JPanel msg = null;
    JLabel sub = null;
    Box center = Box.createVerticalBox();
    JScrollPane jsp = new JScrollPane(center);
    JPanel ctrl = new JPanel(new FlowLayout());
    JButton send = new JButton("Send");
    JButton rec = new JButton("Recieve");
    JTextField firstName;

    public ChatPane() {
        firstName = new JTextField("First Name");
        firstName.setBounds(20,5,100,30);
        add(firstName);
        ctrl.add(send);
        ctrl.add(rec);
        Container cnt = getContentPane();

        cnt.add(jsp, BorderLayout.CENTER);
        cnt.add(ctrl, BorderLayout.SOUTH);
        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nam;
                if(e.getSource()== send){
                    nam=firstName.getText();
                
                msg = new JPanel(new BorderLayout());
                msg.setBorder(BorderFactory.createLineBorder(Color.black) );
                sub = new JLabel(nam);
                sub.setBorder(BorderFactory.createLineBorder(Color.red));
                msg.add(sub, BorderLayout.WEST);
                msg.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)msg.getPreferredSize().getHeight()));
                center.add(msg);
                validate();
                }
            }
        });
        rec.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                msg = new JPanel(new BorderLayout());
                msg.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3) );
                sub = new JLabel("Reciver. . . . Message");
                sub.setBorder(BorderFactory.createLineBorder(Color.black));
                msg.add(sub, BorderLayout.EAST);
                msg.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)msg.getPreferredSize().getHeight()));
                center.add(msg);
                validate();
            }
        });
        setPreferredSize(new Dimension(1000, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

    }

    public static void main(String[] args) {
        new ChatPane();
    }
}