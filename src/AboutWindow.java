import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {
    private JLabel aboutLabel;
    private JLabel image;

    public AboutWindow() {
        aboutLabel = new JLabel();
        image = new JLabel(new ImageIcon("scr.png"));
    }

    public void createUI() {
        aboutLabel.setText("<html>" +
                "<h2>Ticket Management Application</h2>" +
                "<br>Developer Name: <strong>Tsouchlakis Christos</strong>" +
                "<br>Date completed: <strong>12/6/2021</strong>" +
                "</html>");

        //change colors
        aboutLabel.setForeground(Color.white);
        this.getContentPane().setBackground(new Color(59, 89, 182)); //myBlue

        //setup frame
        this.setLayout(new FlowLayout());
        this.setSize(450, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);

        //add components
        this.add(aboutLabel);
        this.add(image); //add image
    }
}
