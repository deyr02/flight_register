
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
public class Passenger_registration extends javax.swing.JFrame {

    /**
     * Creates new form Passenger_registration
     */
    private String Passprot_Number;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private int Flight_number;
    private int Row;
    private char Seat;
    private Passenger PG;
    private ArrayList<Passenger> PGA = new ArrayList<>();
    private ArrayList<flight> FT = new ArrayList<>();
    private BufferedReader BR;
    public Passenger_registration() {
        initComponents();
        this.readFlight();
        this.readPassenger();
        
        for (int i = 0; i< FT.size(); i++){
            int item = FT.get(i).getIDs();
            System.out.println(FT.get(i).printFlight());
            combo.addItem(Integer.toString(item));
        }
    }
    private void readFlight() {
        try {
            String line;
            BR = new BufferedReader(new FileReader("Flight.csv"));

            while ((line = BR.readLine()) != null) {

                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                while (stringTokenizer.hasMoreElements()) {
                    int ID = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String Org = stringTokenizer.nextElement().toString();
                    String Dest = stringTokenizer.nextElement().toString();
                    int D = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int MT = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int YA = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int HA = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int MIN = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int S = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Double FF = Double.parseDouble(stringTokenizer.nextElement().toString());
                    flight rv = new flight(ID, Org, Dest, D, MT, YA, HA, MIN, S, FF);
                    FT.add(rv);

                }

            }
            BR.close();

        } catch (IOException | NumberFormatException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        } // end of Catch
    }
    
    private void readPassenger() {
        try {
            String line;
            File rec = new File("Passenger.csv");
            BR = new BufferedReader(new FileReader(rec));

            while ((line = BR.readLine()) != null) {

                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                while (stringTokenizer.hasMoreElements()) {
                    String PN = stringTokenizer.nextElement().toString();
                    String First = stringTokenizer.nextElement().toString();
                    String Last = stringTokenizer.nextElement().toString();
                    String Email = stringTokenizer.nextElement().toString();
                    int Flight_Number = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int Rows = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String SS = stringTokenizer.nextElement().toString();
                    char cc = SS.charAt(0);
                    
                    Passenger pp = new Passenger(PN, First, Last, Email, Flight_Number, Rows, cc);
                    PGA.add(pp);

                }

            }
            BR.close();
            rec.delete();
            

        } catch (IOException | NumberFormatException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        } // end of Catch
    }
    
    public void Clear(){
        txt_passport.setText("");
        txt_first.setText("");
        txt_last.setText("");
        txt_email.setText("");
        combo.setSelectedIndex(0);
        txt_row.setText("");
        txt_seat.setText("");
        
    }
    
    public void validatePassportNumber(String passport_number){
        int counter = 0;
        for(int i = 0; i< PGA.size(); i++){
            String PN = PGA.get(i).getPassport_no();
            if (passport_number.equalsIgnoreCase(PN)){
                counter = counter + 1;
            }
        }
        
        if ((passport_number.length()==8)){
            char a1 = passport_number.charAt(0);
            char a2 = passport_number.charAt(1);
            char a3 = passport_number.charAt(2);
            char a4 = passport_number.charAt(3);
            char a5 = passport_number.charAt(4);
            char a6 = passport_number.charAt(5);
            char a7 = passport_number.charAt(6);
            char a8 = passport_number.charAt(7);
            
            
           
           
            if ((Character.isLetter(a1) == false) || (Character.isLetter(a2) == false)) {
                JOptionPane.showMessageDialog(this, "The first Two character should be letters", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if ((Character.isDigit(a3) == false) || (Character.isDigit(a4) == false) || (Character.isDigit(a5) == false)
                    || (Character.isDigit(a6) == false) || (Character.isDigit(a7) == false) || (Character.isDigit(a8) == false)) {
                JOptionPane.showMessageDialog(this, "The first two characters should be letters and rest of the character should be number", "ERROR", JOptionPane.ERROR_MESSAGE);

            } else if ((counter>0)){
                JOptionPane.showMessageDialog(this, "This passport number is already Registered", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.Passprot_Number = passport_number.toUpperCase();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Passport number should be consist of 8 characters", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       
        
        
    
    }
    public void validateFirstName(String fn){
        int counter=0;
        char c;
        for(int i =0; i< fn.length();i ++){
            c = fn.charAt(i);
            if(Character.isDigit(c)){
                counter = counter +1;
            }
        }
        
        if (fn.matches("[0-9]+")){
            JOptionPane.showMessageDialog(this, "Invalid first name", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(counter==0)){
            JOptionPane.showMessageDialog(this, "Name should not have any digits", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else {
            this.First_Name = fn;
        }
    }
    
    public void validateLastName(String ln) {
        int counter = 0;
        char c;
        for (int i = 0; i < ln.length(); i++) {
            c = ln.charAt(i);
            if (Character.isDigit(c)) {
                counter = counter + 1;
            }
        }
        if (ln.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Invalid last name", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else if (!(counter == 0)) {
            JOptionPane.showMessageDialog(this, "Name should not have any digits", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            this.Last_Name = ln;
        }
    }
    
    public void validateEmail(String email){
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (email.matches(EMAIL_REGEX)== false){
            JOptionPane.showMessageDialog(this, "Invalid Email address", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        this.Email = email;
        
    }
    public void validateRow(String row){
        try {
            int rows= Integer.parseInt(row);
            if ((rows < 1) || (rows > 100)) {
                JOptionPane.showMessageDialog(this, "Row should be between 0 and 100", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            this.Row= rows;

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Please input a number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void validateSeats(String S) {
        String c = S.toUpperCase();
        char ca = c.charAt(0);
        if (c.length()>1){
            JOptionPane.showMessageDialog(this, "Seat should be A, B, or C", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } else if (!((c.equalsIgnoreCase("B")||(c.equalsIgnoreCase("C"))|(c.equalsIgnoreCase("A"))))){
            JOptionPane.showMessageDialog(this, "Seat should be A, B, or C", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else {
            this.Seat =c.charAt(0);
            System.out.println(Seat);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_minutes = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_flight_fare = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_seat_no = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_register = new javax.swing.JButton();
        txt_Orgin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_destination = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Days = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_months = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_flight_ID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_year = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_hours = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_register1 = new javax.swing.JButton();
        txt_first = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_last = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_passport = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_row = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_seat = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        btn_return = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();

        jLabel8.setText("Minutes");

        jLabel9.setText("Seats No:");

        jLabel10.setText("Flight Fare:");

        btn_register.setText("Register");
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        jLabel1.setText("Flight ID");

        jLabel2.setText("Orgin");

        jLabel3.setText("Destination");

        jLabel4.setText("Days");

        jLabel5.setText("Month");

        jLabel6.setText("Year:");

        jLabel7.setText("Hours:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btn_register1.setText("Register");
        btn_register1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_register1ActionPerformed(evt);
            }
        });

        txt_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_firstActionPerformed(evt);
            }
        });

        jLabel14.setText("Passport Number");

        jLabel15.setText("First Name");

        jLabel16.setText("Last Name");

        jLabel17.setText("Email");

        jLabel18.setText("Flight Number");

        jLabel19.setText("Row");

        jLabel20.setText("Seat");

        btn_return.setText("Return");
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_seat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_row, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_first, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_passport, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(btn_return)
                        .addGap(85, 85, 85)
                        .addComponent(btn_register1)
                        .addGap(111, 111, 111)
                        .addComponent(btn_exit)))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_passport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_seat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_exit)
                    .addComponent(btn_return)
                    .addComponent(btn_register1))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_register1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_register1ActionPerformed
        // TODO add your handling code here:
        try {
            this.readPassenger();
            if (!(txt_passport.getText().length() == 0)) {
                this.validatePassportNumber(txt_passport.getText());
                if (!(txt_first.getText().length() == 0)) {
                    this.validateFirstName(txt_first.getText());
                    if (!(txt_last.getText().length() == 0)) {
                        this.validateLastName(txt_last.getText());
                        if (!(txt_email.getText().length() == 0)) {
                            this.validateEmail(txt_email.getText());
                            if (!(txt_row.getText().length() == 0)) {
                                this.validateRow(txt_row.getText());
                                if (!(txt_seat.getText().length() == 0)) {
                                    this.validateSeats(txt_seat.getText());

                                    if ((Passprot_Number.length() == 8) && (First_Name.length() != 0) && (Last_Name.length() != 0) && (Email.length() != 0)
                                            && (Row != 0) &&((Seat =='A')|| (Seat =='B') || (Seat =='C'))) {
                                        Flight_number = Integer.parseInt(combo.getSelectedItem().toString());
                                        PG = new Passenger(this.Passprot_Number, this.First_Name, this.Last_Name, this.Email, this.Flight_number,
                                                this.Row, this.Seat);
                                        PGA.add(PG);
                                        JOptionPane.showMessageDialog(this, "New passenger added successfully", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                        this.Clear();
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Input a seat", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Input row number", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Email Address", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Input Last Name", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Input First Name", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Input Passport Number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Flight Fare should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid Seat", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

               
    }//GEN-LAST:event_btn_register1ActionPerformed

    private void txt_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_firstActionPerformed
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_returnActionPerformed
        // TODO add your handling code here:
        Passenger_Details PD = new Passenger_Details();
        PD.setVisible(true);
       // this.writePassengers();
        this.close();
    }//GEN-LAST:event_btn_returnActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        this.writePassengers();
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void writePassengers(){
        for (int i = 0; i<PGA.size(); i++){
            PGA.get(i).wrtiePasserger();
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.writePassengers();
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
            java.util.logging.Logger.getLogger(Passenger_registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Passenger_registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Passenger_registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Passenger_registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Passenger_registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_register;
    private javax.swing.JButton btn_register1;
    private javax.swing.JButton btn_return;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_first;
    private javax.swing.JTextField txt_flight_ID;
    private javax.swing.JTextField txt_flight_fare;
    private javax.swing.JTextField txt_hours;
    private javax.swing.JTextField txt_last;
    private javax.swing.JTextField txt_minutes;
    private javax.swing.JTextField txt_months;
    private javax.swing.JTextField txt_passport;
    private javax.swing.JTextField txt_row;
    private javax.swing.JTextField txt_seat;
    private javax.swing.JTextField txt_seat_no;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
