
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rajib Dey
 */
public class Search_passenger extends javax.swing.JFrame {

    /**
     * Creates new form Search_passenger
     */
    private String Passprot_Number;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private int Flight_number;
    private int Row;
    private char Seat;
    private Passenger PG;
    private  ArrayList<Passenger> PGA = new ArrayList<>();
    private  ArrayList<flight> FT = new ArrayList<>();
    private BufferedReader BR;
    public Search_passenger() {
        initComponents();
        this.readPassenger();
        this.readFlight();
        txt_passport.setEnabled(false);
        txt_first.setEnabled(false);
        txt_last.setEnabled(false);
        txt_email.setEnabled(false);
        combo1.setEnabled(false);
        txt_row.setEnabled(false);
        txt_seat.setEnabled(false);
        
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        
        
        for (int i = 0; i < FT.size(); i++) {
            int item = FT.get(i).getIDs();
            System.out.println(FT.get(i).printFlight());
            combo1.addItem(Integer.toString(item));
        }
    }
    
    private void readPassenger() {
        try {
            String line;
            File rec = new File("Passenger.csv");
            BR = new BufferedReader(new FileReader("Passenger.csv"));

            while ((line = BR.readLine()) != null) {

                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                while (stringTokenizer.hasMoreElements()) {
                    String PN = stringTokenizer.nextElement().toString();
                    String First = stringTokenizer.nextElement().toString();
                    String Last = stringTokenizer.nextElement().toString();
                    String Email1 = stringTokenizer.nextElement().toString();
                    int Flight_Number = Integer.parseInt(stringTokenizer.nextElement().toString());
                    int Rows = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String SS = stringTokenizer.nextElement().toString();
                    char cc = SS.charAt(0);

                    Passenger pp = new Passenger(PN, First, Last, Email1, Flight_Number, Rows, cc);
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
    private void writePassangers(){
        for (int i=0; i< PGA.size(); i++){
            PGA.get(i).wrtiePasserger();
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
    
    private void search(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (!(search.getText().equalsIgnoreCase(""))) {

            if (combo.getSelectedItem().toString().equalsIgnoreCase("Passport Number")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < PGA.size(); i++) {
                    if (PGA.get(i).getPassport_no().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                            PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (combo.getSelectedItem().toString().equalsIgnoreCase("Last Name")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < PGA.size(); i++) {
                    if (PGA.get(i).getLastName().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                            PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (combo.getSelectedItem().toString().equalsIgnoreCase("ALL Passengers")) {

            int counter = 0;
            for (int i = 0; i < PGA.size(); i++) {
                model.addRow(new Object[]{
                    PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                    PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                });
                ++counter;
            }
            if (counter == 0) {
                JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please input Text in this search  text box", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Clear(){
        txt_passport.setText("");
        txt_first.setText("");
        txt_last.setText("");
        txt_email.setText("");
        combo1.setSelectedItem("");
        txt_row.setText("");
        txt_seat.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_delete = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_Return = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        help = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        txt_first = new javax.swing.JTextField();
        txt_seat = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_last = new javax.swing.JTextField();
        combo1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_passport = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_row = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("For Passenger");

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_Return.setText("Return");
        btn_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReturnActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Passport Number", "First Name", "Last Name", "Email", "Flight Number", "Row", "Seat"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        help.setText("Origin Airport should be consist of three characters");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Search Option");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passport Number", "Last Name", "ALL Passengers", " ", " ", " " }));
        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        txt_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_firstActionPerformed(evt);
            }
        });

        jLabel14.setText("Passport Number");

        jLabel20.setText("Seat");

        jLabel15.setText("First Name");

        jLabel16.setText("Last Name");

        jLabel17.setText("Email");

        jLabel18.setText("Flight Number");

        jLabel19.setText("Row");

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_Return, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_update, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(btn_search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
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
                                        .addComponent(combo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_passport, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(btn_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(btn_save))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_search)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Return)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
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
                            .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txt_row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txt_seat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (!(txt_passport.getText().equalsIgnoreCase(""))){
            int counter = 0;
            for (int i = 0; i < PGA.size(); i++) {
                if ((PGA.get(i).getPassport_no().equals(txt_passport.getText())) && (PGA.get(i).getFirstName().equalsIgnoreCase(txt_first.getText())) &&
                    (PGA.get(i).getLastName().equalsIgnoreCase(txt_last.getText()))) {
                    counter = i;

                }
            }

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record", "Title on Box", dialogButton);
            if (dialogResult == 0) {
                PGA.remove(PGA.get(counter));
                System.out.println(counter);

                JOptionPane.showMessageDialog(this, "The selected Passanger has been deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                this.Clear();

                this.search();
            } else {
                System.out.println("No Option");
            }
        }else {
            JOptionPane.showMessageDialog(this, "No flight has been selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_deleteActionPerformed

    public void validatePassportNumber(String passport_number) {
        int counter = 0;
        for (int i = 0; i < PGA.size(); i++) {
            String PN = PGA.get(i).getPassport_no();
            if (passport_number.equalsIgnoreCase(PN)) {
                counter = counter + 1;
            }
        }

        if ((passport_number.length() == 8)) {
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

            } else if ((counter > 0)) {
                JOptionPane.showMessageDialog(this, "This passport number is already Registered", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.Passprot_Number = passport_number.toUpperCase();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Passport number should be consist of 8 characters", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void validateFirstName(String fn) {
        int counter = 0;
        char c;
        for (int i = 0; i < fn.length(); i++) {
            c = fn.charAt(i);
            if (Character.isDigit(c)) {
                counter = counter + 1;
            }
        }

        if (fn.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Invalid first name", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(counter == 0)) {
            JOptionPane.showMessageDialog(this, "Name should not have any digits", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
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
        } else if (!(counter == 0)) {
            JOptionPane.showMessageDialog(this, "Name should not have any digits", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            this.Last_Name = ln;
        }
    }

    public void validateEmail(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (email.matches(EMAIL_REGEX) == false) {
            JOptionPane.showMessageDialog(this, "Invalid Email address", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        this.Email = email;

    }

    public void validateRow(String row) {
        try {
            int rows = Integer.parseInt(row);
            if ((rows < 1) || (rows > 100)) {
                JOptionPane.showMessageDialog(this, "Row should be between 0 and 100", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            this.Row = rows;

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Please input a number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void validateSeats(String S) {
        String c = S.toUpperCase();
        char ca = c.charAt(0);
        if (c.length() > 1) {
            JOptionPane.showMessageDialog(this, "Seat should be A, B, or C", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (!((c.equalsIgnoreCase("B") || (c.equalsIgnoreCase("C")) | (c.equalsIgnoreCase("A"))))) {
            JOptionPane.showMessageDialog(this, "Seat should be A, B, or C", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            this.Seat = c.charAt(0);
            System.out.println(Seat);
        }
    }
    
    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:

        try {
            
            if (!(txt_passport.getText().length() == 0)) {
               
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
                                    Flight_number = Integer.parseInt(combo1.getSelectedItem().toString());
                                    if ((First_Name.length() != 0) && (Last_Name.length() != 0) && (Email.length() != 0)
                                            && (Row != 0) && ((Seat == 'A') || (Seat == 'B') || (Seat == 'C'))) {
                                        
                                        
                                        
                                        if (!(txt_passport.getText().equalsIgnoreCase(""))) {
                                            int counter = 0;
                                            for (int i = 0; i < PGA.size(); i++) {
                                                if ((PGA.get(i).getPassport_no().equals(txt_passport.getText()))) {
                                                    counter = i;

                                                }
                                            }

                                            int dialogButton = JOptionPane.YES_NO_OPTION;
                                            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to Modify this record", "Title on Box", dialogButton);
                                            if (dialogResult == 0) {

                                                PGA.get(counter).setPassport_no(txt_passport.getText());
                                                PGA.get(counter).setFirstName(First_Name);
                                                PGA.get(counter).setlastName(Last_Name);
                                                PGA.get(counter).setEmail(Email);
                                                PGA.get(counter).setFlightNumber(Flight_number);
                                                PGA.get(counter).setRow(Row);
                                                PGA.get(counter).setSeat(Seat);
                                                System.out.println(First_Name);
                                                System.out.println(Last_Name);
                                                System.out.println(Email);
                                                System.out.println(Flight_number);
                                                System.out.println(Row);
                                                System.out.println(Seat);

                                                btn_save.setVisible(false);
                                                btn_cancel.setVisible(false);
                                                btn_delete.setEnabled(true);
                                                btn_update.setEnabled(true);
                                                btn_search.setEnabled(true);
                                                btn_Return.setEnabled(true);
                                                table.setEnabled(true);
                                                txt_passport.setEnabled(false);
                                                txt_first.setEnabled(false);
                                                txt_last.setEnabled(false);
                                                txt_email.setEnabled(false);
                                                combo1.setEnabled(false);
                                                txt_row.setEnabled(false);
                                                txt_seat.setEnabled(false);
                                                btn_save.setVisible(false);
                                                btn_cancel.setVisible(false);

                                                JOptionPane.showMessageDialog(this, "The selected flight has been modified successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                this.Clear();

                                                this.search();

                                            } else {
                                                System.out.println("No Option");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "No flight has been selected", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                        
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
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(this, "Flight Fare should be only numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid Seat", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println(First_Name);
        System.out.println(Last_Name);
        System.out.println(Email);
        System.out.println(Flight_number);
        System.out.println(Row);
        System.out.println(Seat);

    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        btn_delete.setEnabled(true);
        btn_update.setEnabled(true);
        btn_search.setEnabled(true);
        btn_Return.setEnabled(true);
        table.setEnabled(true);
        txt_passport.setEnabled(false);
        txt_first.setEnabled(false);
        txt_last.setEnabled(false);
        txt_email.setEnabled(false);
        combo1.setEnabled(false);
        txt_row.setEnabled(false);
        txt_seat.setEditable(false);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        String Passport_Number = table.getValueAt(table.getSelectedRow(), 0).toString();
        String F_N= table.getValueAt(table.getSelectedRow(), 1).toString();
        String L_N= table.getValueAt(table.getSelectedRow(), 2).toString();
        String Emails = table.getValueAt(table.getSelectedRow(), 3).toString();
        String FLN = table.getValueAt(table.getSelectedRow(), 4).toString();
        String RO= table.getValueAt(table.getSelectedRow(), 5).toString();
        String SE= table.getValueAt(table.getSelectedRow(), 6).toString();
       

        txt_passport.setText(Passport_Number);
        txt_first.setText(F_N);
        txt_last.setText(L_N);
        txt_email.setText(Emails);
        combo1.setSelectedItem(FLN);
        txt_row.setText(RO);
        txt_seat.setText(SE);

    }//GEN-LAST:event_tableMouseClicked

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged
        // TODO add your handling code here:
        switch (combo.getSelectedIndex()) {
            case 1:
            help.setText("Destination Airport code should be consist of\n three characters");
            break;
            case 2:
            help.setText("Date format: DAY/Month/Year : 00/00/0000");
            break;
            case 3:
            search.setText("");
            break;
            default:
            break;
        }

    }//GEN-LAST:event_comboItemStateChanged

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (!(search.getText().equalsIgnoreCase(""))){

            if (combo.getSelectedItem().toString().equalsIgnoreCase("Passport Number")){
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < PGA.size(); i++) {
                    if (PGA.get(i).getPassport_no().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                            PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (combo.getSelectedItem().toString().equalsIgnoreCase("Last Name")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < PGA.size(); i++) {
                    if (PGA.get(i).getLastName().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                            PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            

        }else if (combo.getSelectedItem().toString().equalsIgnoreCase("ALL Passengers")) {
            
            int counter = 0;
            for (int i = 0; i < PGA.size(); i++) {
                model.addRow(new Object[]{
                    PGA.get(i).getPassport_no(), PGA.get(i).getFirstName(), PGA.get(i).getLastName(), PGA.get(i).getEmail(),
                            PGA.get(i).getFlightNumber(), PGA.get(i).getRow(), PGA.get(i).getSeat()
                });
                ++counter;
            }
            if (counter == 0) {
                JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please input Text in this search  text box", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:

        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
        btn_search.setEnabled(false);
        table.setEnabled(false);

        btn_Return.setEnabled(false);

        txt_passport.setEnabled(false);
        txt_first.setEnabled(true);
        txt_last.setEnabled(true);
        txt_email.setEnabled(true);
        combo1.setEnabled(true);
        txt_row.setEnabled(true);
        txt_seat.setEnabled(true);
        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
    }//GEN-LAST:event_btn_updateActionPerformed

    private void txt_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_firstActionPerformed
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.writePassangers();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReturnActionPerformed
        // TODO add your handling code here:
        Passenger_Details PD = new Passenger_Details ();
        PD.setVisible(true);
        
        this.close();
    }//GEN-LAST:event_btn_ReturnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.writePassangers();
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
            java.util.logging.Logger.getLogger(Search_passenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search_passenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search_passenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search_passenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search_passenger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Return;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JLabel help;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_first;
    private javax.swing.JTextField txt_last;
    private javax.swing.JTextField txt_passport;
    private javax.swing.JTextField txt_row;
    private javax.swing.JTextField txt_seat;
    // End of variables declaration//GEN-END:variables
}
