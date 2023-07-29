import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu actionMenu;
    private JMenu helpMenu;
    private JMenuItem newTicketItem;
    private JMenuItem ticketListItem;
    private JMenuItem aboutItem;
    private JMenuItem exitItem;

    private JButton newTicketBtn;
    private JButton ticketListBtn;
    private JButton aboutBtn;
    private JButton exitBtn;

    private final JLabel appVersion;

    public MainWindow() {
        //creating components
        menuBar = new JMenuBar();
        actionMenu = new JMenu("Actions");
        helpMenu = new JMenu("Help");
        newTicketItem = new JMenuItem("Issue new ticket");
        ticketListItem = new JMenuItem("View ticket list");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");
        newTicketBtn = new JButton("Issue new ticket");
        ticketListBtn = new JButton("View ticket list");
        aboutBtn = new JButton("About");
        exitBtn = new JButton("Exit");
        appVersion = new JLabel("App Version: 0.1");
    }

    public void createUI() {
        Dimension spacing = new Dimension(0, 30);
        //setup menu bar
        menuBar.add(actionMenu);
        menuBar.add(helpMenu);
        actionMenu.add(newTicketItem);
        actionMenu.add(ticketListItem);
        actionMenu.add(exitItem);
        helpMenu.add(aboutItem);

        Color myBlue = new Color(59, 89, 182);
        //center components
        newTicketBtn.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        ticketListBtn.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        aboutBtn.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        appVersion.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        //change colors
        menuBar.setBackground(myBlue);
        actionMenu.setForeground(Color.white);
        helpMenu.setForeground(Color.white);
        newTicketBtn.setBackground(myBlue);
        newTicketBtn.setForeground(Color.white);
        ticketListBtn.setBackground(myBlue);
        ticketListBtn.setForeground(Color.white);
        aboutBtn.setBackground(myBlue);
        aboutBtn.setForeground(Color.white);
        exitBtn.setBackground(myBlue);
        exitBtn.setForeground(Color.white);
        newTicketBtn.setFocusPainted(false);
        ticketListBtn.setFocusPainted(false);
        aboutBtn.setFocusPainted(false);
        exitBtn.setFocusPainted(false);

        //ActionListeners for newTicketWin()
        newTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTicketWin();
            }
        });
        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTicketWin();
            }
        });

        //ActionListeners for viewTicketList()
        ticketListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTicketList();
            }
        });
        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTicketList();
            }
        });

        //ActionListeners for aboutApp()
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutApp();
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutApp();
            }
        });

        //ActionListeners/WindowListener for exitApp()
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });

        //setup frame
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Ticket Management App");
        this.setSize(640, 360);

        //add components
        this.add(Box.createRigidArea(spacing)); //add spacing between components
        this.add(newTicketBtn);
        this.add(Box.createRigidArea(spacing));
        this.add(ticketListBtn);
        this.add(Box.createRigidArea(spacing));
        this.add(aboutBtn);
        this.add(Box.createRigidArea(spacing));
        this.add(exitBtn);
        this.add(Box.createRigidArea(spacing));
        this.add(appVersion);
        this.setVisible(true);
    }

    private void newTicketWin() {
        NewTicketWindow window = new NewTicketWindow();
        window.createUI();
    }

    private void viewTicketList() {
        ViewTicketsWindow viewTickets = new ViewTicketsWindow();
        viewTickets.createUI();
    }

    private void aboutApp() {
        AboutWindow about = new AboutWindow();
        about.createUI();
    }

    private void exitApp() {
        int returnVal = JOptionPane.showConfirmDialog(MainWindow.this,
                "Are you sure you want to exit?", "Select an option", JOptionPane.YES_NO_OPTION);
        if (returnVal == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
