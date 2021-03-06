
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rajib Dey
 */
public class flight {
    private static int flight_id = 100;
    private int ids;
    private String origin;
    private String Destination;
    private int days;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    private int seats;
    private double flight_fare;
    
    public  flight(String origin,
            String Destination, int days, int month,
                    int year, int hours, int minutes, 
                            int seats, double flight_fare){
        
        this.origin = origin;
        this.Destination = Destination;
        this.days = days;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
        this.seats = seats;
        this.flight_fare = flight_fare;
        ++flight_id;
        
    }
    public flight(int flight_Id,String origin,
            String Destination, int days, int month,
            int year, int hours, int minutes,
            int seats, double flight_fare) {
        
        this.ids= flight_Id;

        this.origin = origin;
        this.Destination = Destination;
        this.days = days;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
        this.seats = seats;
        this.flight_fare = flight_fare;
        

    }
    public int getID(){
        return this.flight_id;
    }
    public int getIDs(){
        return this.ids;
    }
    public void setOrigin(String origin){
        this.origin = origin;
        
    }
    
    public String getOrigin(){
        return this.origin;
    }
    
    public void setDestination (String destination){
        this.Destination = destination;
    }
    
    public String getDestination(){
        return this.Destination;
        
    }
    
    public void setDays(int days){
        this.days = days;
        
    }
    public int getDays(){
        return this.days;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    
    public int getMonth(){
        return this.month;
    }
    
    public void setYear(int Year){
        this.year = Year;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public void setHours(int Hours){
        this.hours = Hours;
    }
    
    public int getHours(){
        return this.hours;
    }
    
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }
    
    public int getMinutes(){
        return this.minutes;
    }
    
    public void setSeats(int Seats){
        this.seats= Seats;
    }
    
    public int getSeats(){
        return this.seats;
    }
    
    public void setFlight_fare(double flight_fare){
        this.flight_fare = flight_fare;
    }
    
    public double getFlight_fare(){
        return this.flight_fare;
    }
    
     public String printFlight(){
        return String.format("%d,%s,%s,%d,%d,%d,%d,%d,%d,%.2f\n", this.getID(), this.getOrigin(), this.getDestination(), this.getDays(),
                this.getMonth(), this.getYear(), this.getHours(), this.getMinutes(), this.getSeats(), this.getFlight_fare());
        
    }
     
     public String printFlights() {
        return String.format("%d,%s,%s,%d,%d,%d,%d,%d,%d,%.2f\n", this.getIDs(), this.getOrigin(), this.getDestination(), this.getDays(),
                this.getMonth(), this.getYear(), this.getHours(), this.getMinutes(), this.getSeats(), this.getFlight_fare());

    }
    
    public void wrtieFlight(){
        File record = new File("Flight.CSV");
        try (BufferedWriter pWriter = new BufferedWriter(new FileWriter(record,true))) {
            pWriter.write(this.printFlight());
            pWriter.close();
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FIlE NOT FOUND", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR OCCOUR WHILE FILE IS BEING WRITTING", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "FILE HAS BEEN WRITTEN SUCCESSFULY", "FILE WRITTEN", JOptionPane.INFORMATION_MESSAGE);
    }
  
    public void wrtieFlights() {
        File record = new File ("Flight.CSV");
        
        try {
            BufferedWriter pWriter = new BufferedWriter(new FileWriter(record, true));
            pWriter.write(this.printFlights());
           
            pWriter.close();
           

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FIlE NOT FOUND", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR OCCOUR WHILE FILE IS BEING WRITTING", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
       
      
        
        
       
    }
    
    
                            
    
}
    
