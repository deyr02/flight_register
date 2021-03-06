
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


public class Search_Flight extends javax.swing.JFrame {

    /**
     * Creates new form Search_Flight
     */
    private String Origin;
    private String Destination;
    private int Days;
    private int month;
    private int year;
    private int Hours;
    private int Minutes;
    private int Seats;
    private double Flight_fare;
    
    int rowcount;
     private  ArrayList<Passenger> PGA = new ArrayList<>();
    private ArrayList<flight> FT = new ArrayList<>();
    private BufferedReader BR;
    public Search_Flight() {
        initComponents();
        this.readFlight();
        this.readPassenger();
        txt_id.setEnabled(false);
        txt_Orgin.setEnabled(false);
        txt_destination.setEnabled(false);
        txt_Days.setEnabled(false);
        txt_months.setEnabled(false);
        txt_year.setEnabled(false);
        txt_hours.setEnabled(false);
        txt_minutes.setEnabled(false);
        txt_seat_no.setEnabled(false);
        txt_flight_fare.setEnabled(false);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
    }
    private void readFlight() {
        try {
            String line;
            File record = new File("Flight.csv");
            BR = new BufferedReader(new FileReader(record));

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
            record.delete();

        } catch (IOException | NumberFormatException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        } // end of Catch
    }
    private void readPassenger() {
        try {
            String line;
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

        } catch (IOException | NumberFormatException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        } // end of Catch
    }
    
    private void writeFlights(){
        for (int i =0; i<FT.size(); i++){
            FT.get(i).wrtieFlights();
            System.out.println(FT.get(i).printFlights());
        }
    }
    private void Clear() {
        txt_id.setText("");
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
    
    private void validateOrgin(String Org) {
        if (Org.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Origin Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(Org.length() == 3)) {
            JOptionPane.showMessageDialog(this, "Orgin Airport should be consist of three Character", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            this.Origin = Org.toUpperCase();
        }

    }
    
    private void validateDestination(String Dest) {
        if (Dest.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Destination Airport should be consist of letters", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!(Dest.length() == 3)) {
            JOptionPane.showMessageDialog(this, "Destination Airport should be consist of three character", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            this.Destination = Dest.toUpperCase();
        }
    }
 
    public void SearchRecord(){
        
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (!(search.getText().equalsIgnoreCase(""))) {

            if (combo.getSelectedItem().toString().equalsIgnoreCase("Origin Airport Code")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < FT.size(); i++) {
                    if (FT.get(i).getOrigin().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                            FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                            FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (combo.getSelectedItem().toString().equalsIgnoreCase("Destination Airport Code")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < FT.size(); i++) {
                    if (FT.get(i).getDestination().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                            FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                            FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (combo.getSelectedItem().toString().equalsIgnoreCase("Date")) {
                String ST = search.getText();
                int counter = 0;
                if (ST.length() == 10) {
                    String DA = Character.toString(ST.charAt(0)) + Character.toString(ST.charAt(1));
                    String F1 = Character.toString(ST.charAt(2));
                    String MO = Character.toString(ST.charAt(3)) + Character.toString(ST.charAt(4));
                    String F2 = Character.toString(ST.charAt(5));
                    String YEA = Character.toString(ST.charAt(6)) + Character.toString(ST.charAt(7)) + Character.toString(ST.charAt(8)) + Character.toString(ST.charAt(9));

                    try {
                        int day = Integer.parseInt(DA);
                        int Month = Integer.parseInt(MO);
                        int YE = Integer.parseInt(YEA);
                        if ((F1.equalsIgnoreCase("/")) && (F2.equalsIgnoreCase("/"))) {

                            for (int i = 0; i < FT.size(); i++) {
                                if ((FT.get(i).getDays() == day) && (FT.get(i).getMonth() == Month) && (FT.get(i).getYear() == YE)) {
                                    ++counter;

                                    model.addRow(new Object[]{
                                        FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                                        FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                                        FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                                    });

                                }
                            }
                            if (counter == 0) {
                                JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid date Format", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(this, "Invalid date Format", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Date format", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else if (combo.getSelectedItem().toString().equalsIgnoreCase("ALL Flight")) {
            String ST = search.getText();
            int counter = 0;
            for (int i = 0; i < FT.size(); i++) {
                model.addRow(new Object[]{
                    FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                    FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                    FT.get(i).getSeats(), FT.get(i).getFlight_fare()
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_Return = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_flight_fare = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_seat_no = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_Orgin = new javax.swing.JTextField();
        txt_destination = new javax.swing.JTextField();
        txt_Days = new javax.swing.JTextField();
        txt_months = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_year = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_hours = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_minutes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        help = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Search Option");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Origin Airport Code", "Destination Airport Code", "Date", "ALL Flight", " " }));
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

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_Return.setText("Return");
        btn_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReturnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Minutes");

        txt_flight_fare.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Seats No:");

        txt_seat_no.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Flight Fare:");

        txt_Orgin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_destination.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_Days.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_months.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_year.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Flight ID");

        txt_hours.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Orgin");

        txt_minutes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Destination");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Days");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Month");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Year:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Hours:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight ID", "Orgin ", "Destination", "Days", "Month", "Year", "Hours", "Minutes", "Seat", "Fare"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        help.setText("Origin Airport should be consist of three characters");

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("For FLIGHT");

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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Return, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_update, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(btn_search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_months, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_Days, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_destination, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_Orgin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_Orgin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_destination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_Days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btn_cancel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btn_save))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        if (!(search.getText().equalsIgnoreCase(""))){
            
            if (combo.getSelectedItem().toString().equalsIgnoreCase("Origin Airport Code")){
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < FT.size(); i++) {
                    if (FT.get(i).getOrigin().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                            FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                            FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (combo.getSelectedItem().toString().equalsIgnoreCase("Destination Airport Code")) {
                String ST = search.getText();
                int counter = 0;
                for (int i = 0; i < FT.size(); i++) {
                    if (FT.get(i).getDestination().equalsIgnoreCase(ST)) {
                        ++counter;

                        model.addRow(new Object[]{
                            FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                            FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                            FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                        });

                    }
                }
                if (counter == 0) {
                    JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            else if (combo.getSelectedItem().toString().equalsIgnoreCase("Date")) {
                String ST = search.getText();
                int counter = 0;
                if (ST.length()==10){
                    String DA = Character.toString(ST.charAt(0)) + Character.toString(ST.charAt(1));
                    String F1 = Character.toString(ST.charAt(2));
                    String MO = Character.toString(ST.charAt(3)) + Character.toString(ST.charAt(4));
                    String F2 = Character.toString(ST.charAt(5));
                    String YEA = Character.toString(ST.charAt(6)) + Character.toString(ST.charAt(7)) + Character.toString(ST.charAt(8)) + Character.toString(ST.charAt(9));
                    
                    try {
                        int day = Integer.parseInt(DA);
                        int Month = Integer.parseInt(MO);
                        int YE = Integer.parseInt(YEA);
                        if ((F1.equalsIgnoreCase("/")) && (F2.equalsIgnoreCase("/"))){
                            
                            for (int i = 0; i < FT.size(); i++) {
                                if ((FT.get(i).getDays()==day) && (FT.get(i).getMonth()==Month) && (FT.get(i).getYear()== YE)) {
                                    ++counter;

                                    model.addRow(new Object[]{
                                        FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                                        FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                                        FT.get(i).getSeats(), FT.get(i).getFlight_fare()
                                    });

                                }
                            }
                            if (counter == 0) {
                                JOptionPane.showMessageDialog(this, "No Record has found", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid date Format", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                                

                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(this, "Invalid date Format", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                    
                    
                    
                }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid Date format", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            
            
        }else if (combo.getSelectedItem().toString().equalsIgnoreCase("ALL Flight")) {
            String ST = search.getText();
            int counter = 0;
            for (int i = 0; i < FT.size(); i++) {
                model.addRow(new Object[]{
                    FT.get(i).getIDs(), FT.get(i).getOrigin(), FT.get(i).getDestination(), FT.get(i).getDays(),
                    FT.get(i).getMonth(), FT.get(i).getYear(), FT.get(i).getHours(), FT.get(i).getMinutes(),
                    FT.get(i).getSeats(), FT.get(i).getFlight_fare()
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
        
        txt_id.setEnabled(false);
        txt_Orgin.setEnabled(true);
        txt_destination.setEnabled(true);
        txt_Days.setEnabled(true);
        txt_months.setEnabled(true);
        txt_year.setEnabled(true);
        txt_hours.setEnabled(true);
        txt_minutes.setEnabled(true);
        txt_seat_no.setEnabled(true);
        txt_flight_fare.setEnabled(true);
        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
    }//GEN-LAST:event_btn_updateActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        String ID1 = table.getValueAt(table.getSelectedRow(), 0).toString();
        String o = table.getValueAt(table.getSelectedRow(), 1).toString();
        String D = table.getValueAt(table.getSelectedRow(), 2).toString();
        String D1 = table.getValueAt(table.getSelectedRow(), 3).toString();
        String M = table.getValueAt(table.getSelectedRow(), 4).toString();
        String y = table.getValueAt(table.getSelectedRow(), 5).toString();
        String H = table.getValueAt(table.getSelectedRow(), 6).toString();
        String min = table.getValueAt(table.getSelectedRow(), 7).toString();
        String Se = table.getValueAt(table.getSelectedRow(), 8).toString();
        String fare = table.getValueAt(table.getSelectedRow(), 9).toString();
        
       

        txt_id.setText(ID1);
        txt_Orgin.setText(o);
        txt_destination.setText(D);
        txt_Days.setText(D1);
        txt_months.setText(M);
        txt_year.setText(y);
        txt_hours.setText(H);
        txt_minutes.setText(min);
        txt_seat_no.setText(Se);
        txt_flight_fare.setText(fare);
        
    }//GEN-LAST:event_tableMouseClicked

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (!(txt_id.getText().equalsIgnoreCase(""))){
            
            int count1 = 0;
            for(int j = 0; j<PGA.size(); j++){
                if (Integer.parseInt(txt_id.getText())== PGA.get(j).getFlightNumber()){
                    ++count1;
                }
            }
            if (count1== 0){
                int counter = 0;
                for (int i = 0; i < FT.size(); i++) {
                    if ((FT.get(i).getIDs() == Integer.parseInt(txt_id.getText())) && (FT.get(i).getOrigin().equalsIgnoreCase(txt_Orgin.getText()))
                            && (FT.get(i).getDestination().equalsIgnoreCase(txt_destination.getText()))) {
                        counter = i;

                    }
                }

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record", "Title on Box", dialogButton);
                if (dialogResult == 0) {
                    FT.remove(counter);

                    JOptionPane.showMessageDialog(this, "The selected flight has been deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    this.Clear();

                    this.SearchRecord();
                } else {
                    System.out.println("No Option");
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Some passengers have been registered for this flight", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }else {
            JOptionPane.showMessageDialog(this, "No flight has been selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
       
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
       
        
        try {

            if (!(txt_Orgin.getText().length() == 0)) {
                this.validateOrgin(txt_Orgin.getText());
                if (!(txt_destination.getText().length() == 0)) {
                    this.validateDestination(txt_destination.getText());
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
                                                    if (!((Origin == "") || (Destination == "") || (Days == 0) || (month == 0) || (year == 0) || (Seats == 0) || (Flight_fare == 0.0))) {
                                                   
                                                    
                                                    //modifying record
                                                    if (!(txt_id.getText().equalsIgnoreCase(""))) {
                                                        int counter = 0;
                                                        for (int i = 0; i < FT.size(); i++) {
                                                            if ((FT.get(i).getIDs() == Integer.parseInt(txt_id.getText()))) {
                                                                counter = i;

                                                            }
                                                        }

                                                        int dialogButton = JOptionPane.YES_NO_OPTION;
                                                        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to Modify this record", "Title on Box", dialogButton);
                                                        if (dialogResult == 0) {
                                                            
                                                            FT.get(counter).setOrigin(Origin);
                                                            FT.get(counter).setDestination(Destination);
                                                            FT.get(counter).setDays(Days);
                                                            FT.get(counter).setMonth(month);
                                                            FT.get(counter).setYear(year);
                                                            FT.get(counter).setHours(Hours);
                                                            FT.get(counter).setMinutes(Minutes);
                                                            FT.get(counter).setSeats(Seats);
                                                            FT.get(counter).setFlight_fare(Flight_fare);
                                                           
                                                           
                                                            btn_save.setVisible(false);
                                                            btn_cancel.setVisible(false);
                                                            btn_delete.setEnabled(true);
                                                            btn_update.setEnabled(true);
                                                            btn_search.setEnabled(true);
                                                            btn_Return.setEnabled(true);
                                                            table.setEnabled(true);
                                                            txt_id.setEnabled(false);
                                                            txt_Orgin.setEnabled(false);
                                                            txt_destination.setEnabled(false);
                                                            txt_Days.setEnabled(false);
                                                            txt_months.setEnabled(false);
                                                            txt_year.setEnabled(false);
                                                            txt_hours.setEnabled(false);
                                                            txt_minutes.setEnabled(false);
                                                            txt_seat_no.setEnabled(false);
                                                            txt_flight_fare.setEnabled(false);
                                                            btn_save.setVisible(false);
                                                            btn_cancel.setVisible(false);
                                                            
                                                           
                                                            
                                                            
                                                            
                                                            JOptionPane.showMessageDialog(this, "The selected flight has been modified successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                            this.Clear();

                                                            this.SearchRecord();
                                                            
                                                        } else {
                                                            System.out.println("No Option");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(this, "No flight has been selected", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    //end of modifying record
                                                    
                                                    
                                                    
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
        txt_id.setEnabled(false);
        txt_Orgin.setEnabled(false);
        txt_destination.setEnabled(false);
        txt_Days.setEnabled(false);
        txt_months.setEnabled(false);
        txt_year.setEnabled(false);
        txt_hours.setEnabled(false);
        txt_minutes.setEnabled(false);
        txt_seat_no.setEnabled(false);
        txt_flight_fare.setEnabled(false);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.writeFlights();
        
    }//GEN-LAST:event_formWindowClosing

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowDeactivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.writeFlights();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    private void btn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReturnActionPerformed
        // TODO add your handling code here:
        
        Filight_Details FD = new Filight_Details();
        FD.setVisible(true);
        this.close();
        
    }//GEN-LAST:event_btn_ReturnActionPerformed

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
            java.util.logging.Logger.getLogger(Search_Flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search_Flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search_Flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search_Flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search_Flight().setVisible(true);
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
    private javax.swing.JLabel help;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_Days;
    private javax.swing.JTextField txt_Orgin;
    private javax.swing.JTextField txt_destination;
    private javax.swing.JTextField txt_flight_fare;
    private javax.swing.JTextField txt_hours;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_minutes;
    private javax.swing.JTextField txt_months;
    private javax.swing.JTextField txt_seat_no;
    private javax.swing.JTextField txt_year;
    // End of variables declaration//GEN-END:variables
}
