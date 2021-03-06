/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Einkaufsliste;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author passe
 */
@XmlRootElement
public class Einkaufsliste extends javax.swing.JFrame {

    private HashMap<String, ArrayList<String>> m = new HashMap();
    private DefaultTableModel tm = new DefaultTableModel();
    Connection con = null;
    Statement stmt = null;
 
    ResultSet rs = null;

    /**
     * Creates new form Einkaufsliste
     */
    public Einkaufsliste() {
        initComponents();
        readCSV();
        readIn();
        tm.addColumn("Artikel");
        tm.addColumn("Menge");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        kategorie = new javax.swing.JComboBox();
        artikel = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        ArtikelFeld = new javax.swing.JTextField();
        menge = new javax.swing.JTextField();
        hinzufügen = new javax.swing.JButton();
        löschen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        neuButton = new javax.swing.JMenuItem();
        speichern = new javax.swing.JMenuItem();
        laden = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        kategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategorieActionPerformed(evt);
            }
        });
        jPanel1.add(kategorie);

        artikel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artikelActionPerformed(evt);
            }
        });
        jPanel1.add(artikel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        ArtikelFeld.setText("Artikel");
        jPanel2.add(ArtikelFeld);

        menge.setText("Menge");
        jPanel2.add(menge);

        hinzufügen.setText("Hinzufügen");
        hinzufügen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hinzufügenActionPerformed(evt);
            }
        });
        jPanel2.add(hinzufügen);

        löschen.setText("Löschen");
        löschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                löschenActionPerformed(evt);
            }
        });
        jPanel2.add(löschen);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Artikel", "Menge"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tab);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Datei");

        neuButton.setText("Neu");
        neuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neuButtonActionPerformed(evt);
            }
        });
        jMenu1.add(neuButton);

        speichern.setText("Speichern");
        speichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernActionPerformed(evt);
            }
        });
        jMenu1.add(speichern);

        laden.setText("Laden");
        jMenu1.add(laden);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hinzufügenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hinzufügenActionPerformed
        Object[] o = new Object[2];
        if (ArtikelFeld.getText().equals("Artikel") || ArtikelFeld.getText().equals("")) {
            o[0] = artikel.getSelectedItem();
            if (menge.getText().equalsIgnoreCase("Menge") || menge.getText().equals("")) {
                o[1] = 1;
            } else {
                o[1] = menge.getText();
            }
        } else {
            o[0] = ArtikelFeld.getText();
            if (menge.getText().equals("Menge") || menge.getText().equals("")) {
                o[1] = 1;
            } else {
                o[1] = menge.getText();
            }
        }
        /**
         * for(int i=0;i<o.length;i++){ tm.setValueAt(o[i], tm.getRowCount(),
         * i); }
         */
        tm.addRow(o);
        tab.setModel(tm);
    }//GEN-LAST:event_hinzufügenActionPerformed

    private void kategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategorieActionPerformed
        changeCombo();
    }//GEN-LAST:event_kategorieActionPerformed

    private void löschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_löschenActionPerformed
        int[] rows = tab.getSelectedRows();
        while (rows.length > 0) {
            ((DefaultTableModel) tm).removeRow(tab.convertRowIndexToModel(rows[0]));
            rows = tab.getSelectedRows();
        }
        tab.clearSelection();
    }//GEN-LAST:event_löschenActionPerformed

    private void artikelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artikelActionPerformed
        ArtikelFeld.setText("Artikel");
        menge.setText("Menge");
    }//GEN-LAST:event_artikelActionPerformed

    private void neuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neuButtonActionPerformed
        tm = new DefaultTableModel();
        tm.addColumn("Artikel");
        tm.addColumn("Menge");
        tab.setModel(tm);
        ArtikelFeld.setText("Artikel");
        menge.setText("Menge");
    }//GEN-LAST:event_neuButtonActionPerformed

    private void speichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernActionPerformed
        JFileChooser fdia = new JFileChooser();      
        fdia.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileFilter filter = new FileNameExtensionFilter("Java files", "java");
        fdia.addChoosableFileFilter(filter);
        int ret = fdia.showDialog(this, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File path = fdia.getSelectedFile();
            File file=new File("Einkausliste.xml");
            Einkaufslistengenerator g= new Einkaufslistengenerator();
            try {
                g.writeList(this, file);
            } catch (JAXBException ex) {
                Logger.getLogger(Einkaufsliste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_speichernActionPerformed

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
            java.util.logging.Logger.getLogger(Einkaufsliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Einkaufsliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Einkaufsliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Einkaufsliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Einkaufsliste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ArtikelFeld;
    private javax.swing.JComboBox artikel;
    private javax.swing.JButton hinzufügen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox kategorie;
    private javax.swing.JMenuItem laden;
    private javax.swing.JButton löschen;
    private javax.swing.JTextField menge;
    private javax.swing.JMenuItem neuButton;
    private javax.swing.JMenuItem speichern;
    private javax.swing.JTable tab;
    // End of variables declaration//GEN-END:variables
 public void readCSV() {
        try {
            java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("Produkte.csv")));
            String zeile = "";
            while (null != (zeile = FileReader.readLine())) {
                String[] split = zeile.split(";");
                if (!m.containsKey(split[0])) {
                    m.put(split[0], new ArrayList<>());
                    m.get(split[0]).add(split[1]);
                } else {
                    m.get(split[0]).add(split[1]);
                }
            }
            Object[] k = m.keySet().toArray();
            for (Object ka : k) {
                kategorie.addItem(ka);
            }
            changeCombo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeCombo() {
        artikel.removeAllItems();
        String k = (String) kategorie.getSelectedItem();
        for (String s : m.get(k)) {
            artikel.addItem(s);
        }
    }

    public void readIn(){
        try {
 
      /** Schritt 1: JDBC-Treiber registrieren */
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      /** Schritt 2: Connection zum Datenbanksystem herstellen */
      con = DriverManager.getConnection(
        "jdbc:derby://localhost:1527/Einkaufsliste");
      /** Schritt 3: Statement erzeugen */
      stmt =con.createStatement();
      /** Schritt 4: Statement direkt ausfuehren */
      rs = stmt.executeQuery("select * from APP.PRODUKTGRUPPE");
      /** Schritt 5: Ergebnis der Anfrage verwenden */
      while (rs.next()) {
        System.out.println(rs.getString("Index") + " "
        + rs.getString("Gruppe"));
      }
    } catch (Exception e) {
    System.out.println(e.getMessage());
  } finally {
      /** Schritt 6: Ressourcen freigeben */
      try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    }
}
