import java.io.*;
import java.util.ArrayList;

public class Main {

    static ArrayList<Ticket> tickets; //holds all tickets

    public static void main(String[] args) {
        tickets = new ArrayList<Ticket>();
        createFile();
        deserialization();
        MainWindow mainWindow = new MainWindow();
        mainWindow.createUI();
    }

    private static void createFile() {
        try {
            File file = new File("ticketData.dat");
            if (file.createNewFile()) {
                System.out.println("ticketData.dat created.");
            } else {
                System.out.println("ticketData.dat exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserialization() { //Object Deserialization
        try {
            FileInputStream file = new FileInputStream("ticketData.dat");
            if (!(file.available() == 0)) { //if there is data to be read
                ObjectInputStream in = new ObjectInputStream(file);
                tickets = (ArrayList<Ticket>) in.readObject(); //don't know how to handle this warning
                in.close();
            }
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
