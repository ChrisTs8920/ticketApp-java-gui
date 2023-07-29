import java.io.Serializable;

public class Ticket implements Serializable {
    private String id;
    private String issueDate;
    private String clientName;
    private String itinerary;
    private double cost;
    private String ticketClass; //first class, business class, economy class
    private String boardingTime; //time in Hours:Minutes format. Example: 11:15
    private String seat; //No of seat
    private String airlineCompany; //airline name

    public Ticket() {
        //default constructor
        this.id = "NULL";
        this.issueDate = "NULL";
        this.clientName = "NULL";
        this.itinerary = "NULL";
        this.cost = 0;
        this.ticketClass = "NULL";
        this.boardingTime = "NULL";
        this.seat = "NULL";
        this.airlineCompany = "NULL";
    }

    public Ticket(String id, String issueDate, String clientName, String itinerary, double cost,
                  String ticketClass, String boardingTime, String seat, String airlineCompany) {
        this.id = id;
        this.issueDate = issueDate;
        this.clientName = clientName;
        this.itinerary = itinerary;
        this.cost = cost;
        this.ticketClass = ticketClass;
        this.boardingTime = boardingTime;
        this.seat = seat;
        this.airlineCompany = airlineCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    @Override
    public String toString() {
        return //separator used: double space
                "Ticket ID: " + id + "  " +
                " Issue Date: " + issueDate + "  " +
                " Full Name: " + clientName + "  " +
                " Itinerary: " + itinerary + "  " +
                " Ticket Cost in €: " + cost + "  " +
                " Class of Service: " + ticketClass + "  " +
                " Boarding Time: " + boardingTime + "  " +
                " Number of Seat: " + seat + "  " +
                " Airline Company: " + airlineCompany + "  " + '\n';
    }
}
