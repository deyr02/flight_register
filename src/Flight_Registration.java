
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
public class Flight_Registration extends javax.swing.JFrame {

    /**
     * Creates new form Flight_Registration
     */
    
    private String Origin;
    private String Destination;
    private int Days ;
    private int month;
    private int year;
    private int Hours;
    private int Minutes;
    private int Seats;
    private double Flight_fare;
    
    private flight F;
    
    private ArrayList<flight> FT = new ArrayList<>();
    private BufferedReader BR;
    
    public Flight_Registration() {
     
        initComponents();
        this.readFile();
    }
    private void validateOrgin(String Org){
        if (Org.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Origin Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(Org.length() == 3)) {
            JOptionPane.showMessageDialog(this, "Orgin Airport should be consist of three Character", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            this.Origin = Org.toUpperCase();
        }
         
        
    }
    private void readFile(){
        try 
        {
            String line;
            File record = new File("Flight.csv");
            BR = new BufferedReader(new FileReader(record));
                                                                                                            
            while ((line = BR.readLine()) != null) {

                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                while (stringTokenizer.hasMoreElements()) {
                    int ID = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String Org = stringTokenizer.nextElement().toString();
                    String Dest= stringTokenizer.nextElement().toString();
                    int D = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int MT = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int YA = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int HA = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int MIN = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int S = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Double FF =Double.parseDouble(stringTokenizer.nextElement().toString());
                    flight rv = new flight(ID, Org, Dest, D, MT, YA, HA, MIN, S, FF);
                    FT.add(rv);
                    
                    

                }
               

            }
             BR.close();
             record.delete();
                

        }
        catch (IOException | NumberFormatException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        } // end of Catch
    }
    
    private void validateDestination(String Dest){
        if (Dest.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Destination Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(Dest.length() == 3)) {
            JOptionPane.showMessageDialog(this, "Destination Airport should be consist of three character", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            this.Destination = Dest.toUpperCase();
        }
    }
    
    private void validateDays(String Da){
        try {
            int Day = Integer.parseInt(Da);
            if ((Day < 1) || (Day > 31)) {
                JOptionPane.showMessageDialog(this, "Days should be between 1 and 31", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{
                this.Days = (int)Day;
            }
            
            

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Days should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    private void validateMonth(String Mon){
        try {
             int Month= Integer.parseInt(Mon);
            if ((Month < 1) || (Month > 12)) {
                JOptionPane.showMessageDialog(this, "Month should be between 1 and 12", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.month = (int)Month;
            }

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Month should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void validateYear(String Yea){
        try {
            int Years = Integer.parseInt(Yea);
            if ((Years < 2016) || (Years > 2099)) {
                JOptionPane.showMessageDialog(this, "Days should be between 2016 and 2099", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else {
                this.year = (int)Years;
            }
            

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Year should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void validateHours(String H){
        try {
            int Hour = Integer.parseInt(H);
            if ((Hour < 0) || (Hour > 23)) {
                JOptionPane.showMessageDialog(this, "Hours should be between 0 and 23", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.Hours = (int)Hour;
            }

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Hours should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void validateMinutes(String M){
        try {
            int Minute = Integer.parseInt(M);
            if ((Minute < 0) || (Minute > 59)) {
                JOptionPane.showMessageDialog(this, "Minutes should be between 0 and 59", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.Minutes =(int) Minute;
            }

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Minutes should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void validateSeats(String S){
        try {
            int Seat = Integer.parseInt(S);
            if ((Seat < 0) || (Seat > 30)) {
                JOptionPane.showMessageDialog(this, "Seats number should be between 0 and 23", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.Seats = (int)Seat;
            }

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Seats number should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void validateFlightFare(String F){
        try {
            
            double Flight_fares = Double.parseDouble(F);
            
            if ((Flight_fares < 1) || (Flight_fares > 2000)) {
                JOptionPane.showMessageDialog(this, "flight fare should be between 1 and 2000", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.Flight_fare =(double) Flight_fares;
            }

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Flight Fare should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid flight fare", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Clear(){
        txt_Orgin.setText("");
        txt_destination.setText("");
        txt_Days.setText("");
        txt_months.setText("");
        txt_year.setText("");
        txt_hours.setText("");
        txt_minutes.setText("");
        txt_seat_no.setText("");
        txt_flight_fare.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Orgin = new javax.swing.JTextField();
        txt_destination = new javax.swing.JTextField();
        txt_Days = new javax.swing.JTextField();
        txt_months = new javax.swing.JTextField();
        txt_year = new javax.swing.JTextField();
        txt_hours = new javax.swing.JTextField();
        txt_minutes = new javax.swing.JTextField();
        txt_flight_fare = new javax.swing.JTextField();
        txt_seat_no = new javax.swing.JTextField();
        btn_register = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        btn_return = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setText("Orgin");

        jLabel3.setText("Destination");

        jLabel4.setText("Days");

        jLabel5.setText("Month");

        jLabel6.setText("Year:");

        jLabel7.setText("Hours:");

        jLabel8.setText("Minutes");

        jLabel9.setText("Seats No:");

        jLabel10.setText("Flight Fare:");

        btn_register.setText("Register");
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_return.setText("Return");
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_flight_fare, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_seat_no, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_months, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Days, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_destination, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Orgin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(btn_return)
                        .addGap(81, 81, 81)
                        .addComponent(btn_register)
                        .addGap(115, 115, 115)
                        .addComponent(btn_exit)))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Orgin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_destination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_Days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_seat_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_flight_fare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register)
                    .addComponent(btn_exit)
                    .addComponent(btn_return))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        // TODO add your handling code here:
        try {
           
            
            if (!(txt_Orgin.getText().length() == 0)) {
                String Org = txt_Orgin.getText();
                if (Org.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Origin Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (!(Org.length() == 3)) {
                    JOptionPane.showMessageDialog(this, "Orgin Airport should be consist of three Character", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.Origin = Org.toUpperCase();
                }
                if (!(txt_destination.getText().length() == 0)) {
                   String Dest = txt_destination.getText();
                    if (Dest.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(this, "Destination Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else if (!(Dest.length() == 3)) {
                        JOptionPane.showMessageDialog(this, "Destination Airport should be consist of three character", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        this.Destination = Dest.toUpperCase();
                    }
                    if (!(txt_Days.getText().length() == 0)) {
                        //validating days
                        try {
                            int Day = Integer.parseInt(txt_Days.getText());
                            if ((Day < 1) || (Day > 31)) {
                                JOptionPane.showMessageDialog(this, "Days should be between 1 and 31", "ERROR", JOptionPane.ERROR_MESSAGE);
                            } 
                            Days = Day;

                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(this, "Days should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);

                        }
                        
                    
                        
                        //end of validation days
                      
                        if (!(txt_months.getText().length() == 0)) {
                            //Validating month
                            try {
                                int Month = Integer.parseInt(txt_months.getText());
                                if ((Month < 1) || (Month > 12)) {
                                    JOptionPane.showMessageDialog(this, "Month should be between 1 and 12", "ERROR", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    this.month = (int) Month;
                                }

                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(this, "Month should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            //Ende of validation of month
                           
                            if (!(txt_year.getText().length() == 0)) {
                                //validating Year
                                try {
                                    int Years = Integer.parseInt(txt_year.getText());
                                    if ((Years < 2016) || (Years > 2099)) {
                                        JOptionPane.showMessageDialog(this, "Days should be between 2016 and 2099", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        this.year = (int) Years;
                                    }

                                } catch (NumberFormatException n) {
                                    JOptionPane.showMessageDialog(this, "Year should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                                //end of year
                                if (!(txt_hours.getText().length() == 0)) {
                                    //Validating hours
                                    try {
                                        int Hour = Integer.parseInt(txt_hours.getText());
                                        if ((Hour < 0) || (Hour > 23)) {
                                            JOptionPane.showMessageDialog(this, "Hours should be between 0 and 23", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            this.Hours = (int) Hour;
                                        }

                                    } catch (NumberFormatException n) {
                                        JOptionPane.showMessageDialog(this, "Hours should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                    //end of validation of hours
                                    
                                    if (!(txt_minutes.getText().length() == 0)) {
                                        //minutes validation
                                        try {
                                            int Minute = Integer.parseInt(txt_minutes.getText());
                                            if ((Minute < 0) || (Minute > 59)) {
                                                JOptionPane.showMessageDialog(this, "Minutes should be between 0 and 59", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            } else {
                                                this.Minutes = (int) Minute;
                                            }

                                        } catch (NumberFormatException n) {
                                            JOptionPane.showMessageDialog(this, "Minutes should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                        //end of minutes
                                        
                                        if (!(txt_seat_no.getText().length() == 0)) {
                                            //Sear validation
                                            try {
                                                int Seat = Integer.parseInt(txt_seat_no.getText());
                                                if ((Seat < 0) || (Seat > 30)) {
                                                    JOptionPane.showMessageDialog(this, "Seats number should be between 0 and 23", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                } else {
                                                    this.Seats = (int) Seat;
                                                }

                                            } catch (NumberFormatException n) {
                                                JOptionPane.showMessageDialog(this, "Seats number should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                            //end of seats
                                            if (!(txt_flight_fare.getText().length() == 0)) {
                                                //validating flight
                                                try {

                                                    double Flight_fares = Double.parseDouble(txt_flight_fare.getText());

                                                    if ((Flight_fares < 1) || (Flight_fares > 2000)) {
                                                        JOptionPane.showMessageDialog(this, "flight fare should be between 1 and 2000", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    } else {
                                                        this.Flight_fare = (double) Flight_fares;
                                                    }

                                                } catch (NumberFormatException n) {
                                                    JOptionPane.showMessageDialog(this, "Flight Fare should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                } catch (NullPointerException e) {
                                                    JOptionPane.showMessageDialog(this, "Invalid flight fare", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                }
                                                //validating flight
                                                
                                                //writing file
                                                if ((Origin.length()==3) &&(Destination.length()==3)){
                                                    if (!((Days == 0) || (month == 0) || (year == 0) || (Seats == 0) || (Flight_fare == 0))) {
                                                        int size = 100;
                                                        for (int i =0; i< FT.size(); i++){
                                                            if(FT.get(i).getIDs() > size){
                                                                size = FT.get(i).getIDs();
                                                            }
                                                        }
                                                        int IDS =size +1;
                                                        F = new flight(IDS,Origin, Destination, Days, month, year, Hours, Minutes, Seats, Flight_fare);
                                                        FT.add(F);
                                                        JOptionPane.showMessageDialog(this, "The flight has been registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                        
                                                        this.Clear();
                                                    }
                                                }
                                                
                                                    
                                                
                                                //writing file
                                                
                                                
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Input Flight Fare ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(this, "Input A Seat Number ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(this, "Input minutes ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Input Hours ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(this, "Input A Year", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Input A Month ", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Input A date ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Input Destination Airport", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Input Origin Airport", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

           
               

            
        

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Flight Fare should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid flight fare", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       
        

    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < FT.size(); i++) {
            FT.get(i).wrtieFlights();
            System.out.println(FT.get(i).printFlights());
        }
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_returnActionPerformed
        // TODO add your handling code here:
        Filight_Details FD = new Filight_Details();
        FD.setVisible(true);
        this.close();   
    }//GEN-LAST:event_btn_returnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        for (int i = 0; i < FT.size(); i++) {
            FT.get(i).wrtieFlights();
            System.out.println(FT.get(i).printFlights());
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flight_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Flight_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Flight_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Flight_Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Flight_Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_register;
    private javax.swing.JButton btn_return;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_Days;
    private javax.swing.JTextField txt_Orgin;
    private javax.swing.JTextField txt_destination;
    private javax.swing.JTextField txt_flight_fare;
    private javax.swing.JTextField txt_hours;
    private javax.swing.JTextField txt_minutes;
    private javax.swing.JTextField txt_months;
    private javax.swing.JTextField txt_seat_no;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
