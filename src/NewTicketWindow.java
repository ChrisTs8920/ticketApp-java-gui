import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class NewTicketWindow extends JFrame {
    private JLabel[] labels;
    private final String[] labelText = {"Ticket ID: ", "Issue Date: ", "Full Name: ", "Itinerary: ", "Ticket Cost in €: ",
            "Class of Service: ", "Boarding Time: ", "Number of Seat: ", "Airline Company: "};
    private JTextField[] fields;
    private JComboBox<String> classBox, companyBox;
    private final String[] ticketClasses = {"First class", "Business class", "Economy class"};
    private final String[] companyNames = {"Aegean", "Ryanair", "United"};
    private JButton confirmBtn, clearBtn;

    public NewTicketWindow() {
        labels = new JLabel[9];
        fields = new JTextField[7];
        int i;
        for (i = 0; i < 9; i++) {
            labels[i] = new JLabel("", SwingConstants.CENTER);
        }
        for (i = 0; i < 7; i++) {
            fields[i] = new JTextField();
        }
        classBox = new JComboBox<String>(ticketClasses);
        companyBox = new JComboBox<String>(companyNames);
        confirmBtn = new JButton("Confirm");
        clearBtn = new JButton("Clear fields");
    }

    public void createUI() {
        Color myBlue = new Color(59, 89, 182); //color for buttons
        //set text for labels
        int j = 0;
        for (String i: labelText) {
            labels[j].setText(i);
            j++;
        }
        //set color of buttons
        confirmBtn.setBackground(myBlue);
        confirmBtn.setForeground(Color.white);
        confirmBtn.setFocusPainted(false);
        clearBtn.setBackground(myBlue);
        clearBtn.setForeground(Color.white);
        clearBtn.setFocusPainted(false);

        //WindowListener for closeWindow()
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        //ActionListener for buttonClear()
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClear();
            }
        });

        //ActionListener for buttonConfirm()
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonConfirm();
            }
        });

        //setup frame
        this.setLayout(new GridLayout(19, 2));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Issue new ticket");
        this.setSize(350, 500);
        this.setVisible(true);

        //add components
        for (int i = 0; i < labels.length - 2; i++) {
            this.add(labels[i]);
            if (labels[i].getText().equals(labelText[5])) { //if label = "Class:", add classBox
                this.add(classBox);
            }
            else {
                this.add(fields[i]);
            }
            this.addPadding();
        }
        this.add(labels[7]); //"Number of seat: "
        this.add(fields[5]);
        this.addPadding();
        this.add(labels[8]); //"Airline Company: "
        this.add(companyBox);
        this.addPadding();
        this.add(clearBtn);
        this.add(confirmBtn);
    }

    private void closeWindow() {
        int returnVal = JOptionPane.showConfirmDialog(NewTicketWindow.this,
                "Are you sure you want to close this window?\nAny inputted data will be lost.",
                "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (returnVal == JOptionPane.YES_OPTION) {
            this.setVisible(false);
        }
    }

    private void buttonClear() {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void buttonConfirm() {
        boolean isEmpty = false;
        for (JTextField field : fields) { //check if any of the fields is empty
            if (field.getText().equals("")) {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            JOptionPane.showMessageDialog(NewTicketWindow.this, "A field is empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                //create a new temporary ticket
                Ticket tempTicket = new Ticket(fields[0].getText(), fields[1].getText(),
                        fields[2].getText(), fields[3].getText(), Double.parseDouble(fields[4].getText()),
                        classBox.getSelectedItem().toString(), fields[6].getText(), fields[5].getText(),
                        companyBox.getSelectedItem().toString());
                Main.tickets.add(tempTicket);
                this.serialize(); //save ticket to ticketData.dat

                //append Ticket to file Ticket.txt
                try {
                    BufferedWriter fileOut = new BufferedWriter(new FileWriter("Tickets.txt", true));
                    fileOut.write(tempTicket.toString());
                    fileOut.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                //show dialog that Ticket was saved successfully
                JOptionPane.showMessageDialog(NewTicketWindow.this,
                        "Ticket saved to Tickets.txt.");
                this.setVisible(false);

            } catch (NumberFormatException e) {
                //if a character is inputted in ticket cost field, popup error
                JOptionPane.showMessageDialog(NewTicketWindow.this,
                        "Need a number in field Ticket Cost",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addPadding() { //adds padding between components
        Dimension padding = new Dimension(0, 20);
        this.add(Box.createRigidArea(padding));
        this.add(Box.createRigidArea(padding));
    }

    private void serialize() { //Object Serialization
        try {
            FileOutputStream file = new FileOutputStream("ticketData.dat");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(Main.tickets);
            file.close();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
