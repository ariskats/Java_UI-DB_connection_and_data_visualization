import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/** @see https://stackoverflow.com/a/19215436/230513 */
public class Area extends JFrame {

    public Area() {
        super("Text Area");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField tf = new JTextField(12);
        add(tf, BorderLayout.NORTH);
        
        JTextArea ta = new JTextArea(24, 12);
        
        JScrollPane jp = new JScrollPane(ta);
        
        add(jp, BorderLayout.CENTER);
        pack();
        // arbitrary size to make vertical scrollbar appear
        setSize(240, 240);
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Area();
            }
        });
    }
}