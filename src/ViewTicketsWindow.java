import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class ViewTicketsWindow extends JFrame {
    private JPanel buttonsPanel, statisticsPanel;
    private JTextArea ticketsList;
    private JLabel noOfTicketsLbl, totalCostLbl, minCostLbl, maxCostLbl;
    private JButton newTicketBtn, refreshBtn, closeBtn;
    private int noOfTickets = 0;
    private double totalCost = 0;
    private double minCost = Double.POSITIVE_INFINITY;
    private String minID;
    private double maxCost = 0;
    private String maxID;
    private ArrayList<Ticket> tempTickets; //sort this ticket list and keep main ticket list unchanged

    public ViewTicketsWindow() {
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statisticsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ticketsList = new JTextArea();
        noOfTicketsLbl = new JLabel("No. of Tickets: 0   ");
        totalCostLbl = new JLabel("Total Cost: 0€   ");
        minCostLbl = new JLabel("Min. Cost: 0€ from ID: 0   ");
        maxCostLbl = new JLabel("Max. Cost: 0€ from ID: 0   ");
        newTicketBtn = new JButton("New ticket");
        refreshBtn = new JButton("Refresh");
        closeBtn = new JButton("Close");
        tempTickets = new ArrayList<>();
    }

    public void createUI() {
        Color myBlue = new Color(59, 89, 182); //myBlue
        Color myBlue2 = new Color(50, 75, 154); //darker myBlue for buttons
        //set color of buttons
        newTicketBtn.setBackground(myBlue2);
        newTicketBtn.setForeground(Color.white);
        newTicketBtn.setFocusPainted(false);
        refreshBtn.setBackground(myBlue2);
        refreshBtn.setForeground(Color.white);
        refreshBtn.setFocusPainted(false);
        closeBtn.setBackground(myBlue2);
        closeBtn.setForeground(Color.white);
        closeBtn.setFocusPainted(false);
        //set color of panels
        buttonsPanel.setBackground(myBlue);
        statisticsPanel.setBackground(myBlue);
        //set color of JLabels
        noOfTicketsLbl.setForeground(Color.white);
        totalCostLbl.setForeground(Color.white);
        minCostLbl.setForeground(Color.white);
        maxCostLbl.setForeground(Color.white);

        //ActionListener for newTicketWin()
        newTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTicketWin();
            }
        });

        //ActionListener for refresh()
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });

        //WindowListener/ActionListener for closeWindow()
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        //setup frame
        ticketsList.setEditable(false);
        this.showTickets();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(true);
        this.setTitle("Ticket list");
        this.setVisible(true);

        //add components to buttonsPanel
        buttonsPanel.add(newTicketBtn);
        buttonsPanel.add(refreshBtn);
        buttonsPanel.add(closeBtn);

        //add components to statisticsPanel
        statisticsPanel.add(noOfTicketsLbl);
        statisticsPanel.add(totalCostLbl);
        statisticsPanel.add(minCostLbl);
        statisticsPanel.add(maxCostLbl);

        //add components to main frame
        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(ticketsList, BorderLayout.CENTER);
        this.add(statisticsPanel, BorderLayout.SOUTH);
    }

    private void newTicketWin() {
        NewTicketWindow window = new NewTicketWindow();
        window.createUI();
    }

    private void refresh() {
        showTickets();
    }

    private void closeWindow() {
        this.setVisible(false);
    }

    private void showTickets() {
        ticketsList.setText("");
        tempTickets.clear();
        tempTickets.addAll(Main.tickets);
        sort();
        for (Ticket i : tempTickets) {
            ticketsList.append(String.valueOf(i));
            ticketsList.append("\n");
        }
        calcStatistics();
    }

    private void calcStatistics() {
        if (!Main.tickets.isEmpty()) {
            //calculate and show number of tickets
            noOfTickets = Main.tickets.size();
            noOfTicketsLbl.setText("No. of Tickets: " + noOfTickets + "  ");

            //Calculate totalCost, minCost and maxCost
            double tempCost = 0;
            for (Ticket i : Main.tickets) {
                tempCost += i.getCost();
                if (i.getCost() < minCost) {
                    minCost = i.getCost();
                    minID = i.getId();
                }
                if (i.getCost() > maxCost) {
                    maxCost = i.getCost();
                    maxID = i.getId();
                }
            }

            //show totalCost
            totalCost = tempCost;
            totalCostLbl.setText("Total Cost: " + totalCost + "€   ");
            //show min cost
            minCostLbl.setText("Min. Cost: " + minCost + "€  from ID: " + minID + "   ");
            //show max cost
            maxCostLbl.setText("Max. Cost: " + maxCost + "€  from ID: " + maxID + "   ");
        }
    }

    private void sort() {
        tempTickets.sort(new Comparator<Ticket>() { //sorting tickets with custom comparator
            @Override
            public int compare(Ticket o1, Ticket o2) {
                return Double.compare(o1.getCost(), o2.getCost());
            }
        });
    }
}
