
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
public class Passenger {
    private String Passport_no;
    private String first_name;
    private String last_name;
    private String email;
    private int flight_number;
    private int row;
    private char seat;
    public Passenger(String Passport_no, String first_name, String last_name, String email, int flight_number, int row, char seat){
        
        this.Passport_no = Passport_no;
        this.first_name = first_name;
        this.last_name= last_name;
        this.email = email;
        this.flight_number = flight_number;
        this.row = row;
        this.seat = seat;
    }
    public void setPassport_no(String Passport_no){
        this.Passport_no = Passport_no;
        
    }
    public String getPassport_no(){
        return this.Passport_no;
    }
    
    public void setFirstName(String first_name){
        this.first_name = first_name;
    }
    
    public String getFirstName(){
        return this.first_name;
    }
    
    public void setlastName(String last_name){
        this.last_name = last_name;
    }
    public String getLastName(){
        return this.last_name;
    }
    
    public void setEmail(String Email){
        this.email = Email;
    }
    public String getEmail(){
        return this.email;
    }
    
    public void setFlightNumber(int flight_number){
        this.flight_number = flight_number;
    }
    
    public int getFlightNumber(){
        return this.flight_number;
        
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setSeat(char seat){
        this.seat = seat;
    }
    public char getSeat(){
        return this.seat;
    }
    
    public String printPassenger() {
        return String.format("%s,%s,%s,%s,%d,%d,%c\n", this.getPassport_no(), this.getFirstName(), this.getLastName(), this.getEmail(),
                this.getFlightNumber(), this.getRow(), this.getSeat());

    }

    public void wrtiePasserger() {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("Passenger.csv", true))) {
            pWriter.print(this.printPassenger());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FIlE NOT FOUND", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR OCCOUR WHILE FILE IS BEING WRITTING", "EROOR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
