package xml.find.string;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mbarker
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    public Gui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResult = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jToggleButtonGo = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XML Find String - By Matt Barker");

        jTextAreaResult.setColumns(20);
        jTextAreaResult.setLineWrap(true);
        jTextAreaResult.setRows(5);
        jTextAreaResult.setText("1. Copy the XML files into the \"xml\" folder.\n2. Enter search term.\n3. Click \"Search\"\n\nThanks ! :)");
        jScrollPane1.setViewportView(jTextAreaResult);

        jLabel1.setText("Search For:");

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jToggleButtonGo.setText("Search");
        jToggleButtonGo.setToolTipText("");
        jToggleButtonGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonGoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToggleButtonGo))
                        .addGap(0, 253, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButtonGo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jToggleButtonGoActionPerformed(java.awt.event.ActionEvent evt) {                                                

        String check = jTextFieldSearch.getText();
        
        if(check == null){
            
            infoBox("Search field blank", "Error");
            
        }else{
        
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultNum = fileChooser.showOpenDialog(this);
        if (resultNum == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File dirPath = selectedFile;

            String SEARCH_STRING = jTextFieldSearch.getText();
            String result = "";

            File[] files = dirPath.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    String name = pathname.getName().toLowerCase();
                    return name.endsWith(".xml") && pathname.isFile();
                }
            });
            String[] names = dirPath.list();
            ArrayList<String> fileContentsList = new ArrayList<String>();
            int fileCount = 0;
            String foundIn = "NONE";

            for (int i = 0; i < files.length; i++) {
                try {
                    System.out.println("PARSING FILE:   " + files[i]);
                    result = result + ("\nPARSING FILE:   " + files[i]);
                    jTextAreaResult.setText(result);
                    String fileContent = new String(Files.readAllBytes(Paths.get(files[i].toString())));
                    fileContentsList.add(fileContent);
                    fileCount++;
                    if (fileContentsList.get(i).contains(SEARCH_STRING)) {

                        foundIn = files[i].toString();

                    }
                } catch (IOException ex) {
                    Logger.getLogger(XMLFindString.class.getName()).log(Level.SEVERE, null, ex);
                } finally {

                }

            }

            System.out.println("\n\nSUCCESSFULLY PARSED " + fileCount + " FILES.");
            result = result + "\n\nSUCCESSFULLY PARSED " + fileCount + " FILES.";
            jTextAreaResult.setText(result);
            System.out.println("\n\nMATCH FOUND IN: " + foundIn);
            result = result + "\n\nMATCH FOUND IN: " + foundIn;
            jTextAreaResult.setText(result);

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        }
    }                                               

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
    
      public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JToggleButton jToggleButtonGo;
    // End of variables declaration                   
}
