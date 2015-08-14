/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import Student_Data_Access.Student_Data_Access;
import Student_Domain.Student;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.security.pkcs11.Secmod;

/**
 *
 * @author Dulanjaya Tennekoon
 */
public class EditStudentInformation extends javax.swing.JFrame {
    
    /**
     * Creates new form EditStudentInfromation
     */
    private String app_name = "NTS";
    private Student_Data_Access stuAccess;
    private Student stu;
    private MainGUIObserver GUIObserver;
    public EditStudentInformation() throws HeadlessException {
        initComponents();
        startupSettings();
    }

    public EditStudentInformation (MainGUIObserver GUIObserver,Student_Data_Access stuAccess, String stuId) {
        initComponents();
        startupSettings();
        this.GUIObserver = GUIObserver;
        this.stuAccess = stuAccess;
        try {
            stu = stuAccess.getProfile(Integer.parseInt(stuId));
            stu.setAccess(stuAccess);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Student ID", app_name, JOptionPane.WARNING_MESSAGE);
        }
        startUpSettingData(stuAccess, stuId);
    }
    
    public void startUpSettingData(Student_Data_Access stuAccess, String stuId) {
        Student stu;
        try {
            stu = stuAccess.getProfile(Integer.parseInt(stuId));
            stu.setAccess(stuAccess);
            txtStuID.setText(Integer.toString(stu.getID()));
            txtStuName.setText(stu.getName());
            dpDOB.setDate(stu.getDOB());
            txtBatch.setText(Integer.toString(stu.getBatch()));
            txtAddress.setText(stu.getAddress());
            txtNIC.setText(stu.getNIC());
            txtPhone.setText(Integer.toString(stu.getPhone()));
            dpDOReg.setDate(stu.getDate());
            txtGuardName1.setText(stu.getGuadian1Name());
            txtGuardAddress1.setText(stu.getGuadian1Address());
            txtGuardPhone1.setText(Integer.toString(stu.getGuadian1Telephone()));
            txtGuardName2.setText(stu.getGuadian2Name());
            txtGuardAddress2.setText(stu.getGuadian2Address());
            txtGuardPhone2.setText(Integer.toString(stu.getGuadian2Telephone()));
            chkHostel.setSelected(stu.isIsHostel());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Enter a Valid Index Please!", app_name, JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void startupSettings() {
        this.setLocationRelativeTo(null);
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
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlStuPersonalInfo = new javax.swing.JPanel();
        lblStuInfoHeading = new javax.swing.JLabel();
        lblStuID = new javax.swing.JLabel();
        txtStuID = new javax.swing.JTextField();
        lblStuName = new javax.swing.JLabel();
        txtStuName = new javax.swing.JTextField();
        lblBatch = new javax.swing.JLabel();
        txtBatch = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        jspAddress = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        lblIDNo = new javax.swing.JLabel();
        txtNIC = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        lblDOReg = new javax.swing.JLabel();
        canvasProfImage = new java.awt.Canvas();
        lblHostel = new javax.swing.JLabel();
        chkHostel = new javax.swing.JCheckBox();
        txtGuardAddress2 = new javax.swing.JTextField();
        lblGuardAddress2 = new javax.swing.JLabel();
        lblGuardPhone2 = new javax.swing.JLabel();
        lblGuardName2 = new javax.swing.JLabel();
        lblGuardAddress1 = new javax.swing.JLabel();
        lblGuardPhone1 = new javax.swing.JLabel();
        lblGuardName1 = new javax.swing.JLabel();
        txtGuardName1 = new javax.swing.JTextField();
        txtGuardPhone1 = new javax.swing.JTextField();
        txtGuardAddress1 = new javax.swing.JTextField();
        txtGuardPhone2 = new javax.swing.JTextField();
        txtGuardName2 = new javax.swing.JTextField();
        dpDOB = new org.jdesktop.swingx.JXDatePicker();
        dpDOReg = new org.jdesktop.swingx.JXDatePicker();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(405, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlStuPersonalInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStuInfoHeading.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblStuInfoHeading.setText("Edit Student Information");

        lblStuID.setText("Student ID");

        txtStuID.setEditable(false);

        lblStuName.setText("Student Name");

        lblBatch.setText("Batch");

        lblAddress.setText("Address");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jspAddress.setViewportView(txtAddress);

        lblIDNo.setText("NIC");

        lblPhone.setText("Phone Number");

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        lblDOB.setText("Date of Birth");

        lblDOReg.setText("Registration Date");

        canvasProfImage.setBackground(new java.awt.Color(153, 153, 255));

        lblHostel.setText("Hostel Student");

        chkHostel.setText("I am a hostel Student");
        chkHostel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHostelActionPerformed(evt);
            }
        });

        lblGuardAddress2.setText("Guardian2's Address");

        lblGuardPhone2.setText("Guardian2's Phone");

        lblGuardName2.setText("Guardian2's Name");

        lblGuardAddress1.setText("Guardian1's Address");

        lblGuardPhone1.setText("Guardian1's Phone");

        lblGuardName1.setText("Guardian1's Name");

        txtGuardName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuardName1ActionPerformed(evt);
            }
        });

        txtGuardName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuardName2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStuPersonalInfoLayout = new javax.swing.GroupLayout(pnlStuPersonalInfo);
        pnlStuPersonalInfo.setLayout(pnlStuPersonalInfoLayout);
        pnlStuPersonalInfoLayout.setHorizontalGroup(
            pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStuPersonalInfoLayout.createSequentialGroup()
                                    .addComponent(lblGuardPhone2)
                                    .addGap(13, 13, 13))
                                .addComponent(lblGuardName1))
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAddress)
                                    .addComponent(lblPhone)
                                    .addComponent(lblDOReg)
                                    .addComponent(lblHostel)
                                    .addComponent(lblGuardPhone1)
                                    .addComponent(lblGuardAddress1)
                                    .addComponent(lblGuardName2)
                                    .addComponent(lblGuardAddress2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addComponent(chkHostel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGuardAddress2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardPhone2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardName2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardPhone1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardAddress1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardName1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(169, 169, 169))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNIC)
                                    .addComponent(dpDOReg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(168, 168, 168))))
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDOB)
                            .addComponent(lblBatch)
                            .addComponent(lblStuName)
                            .addComponent(lblStuID)
                            .addComponent(lblIDNo))
                        .addGap(40, 40, 40)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addComponent(lblStuInfoHeading)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtStuName)
                                    .addComponent(txtStuID)
                                    .addComponent(txtBatch)
                                    .addComponent(jspAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                    .addComponent(dpDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(canvasProfImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlStuPersonalInfoLayout.setVerticalGroup(
            pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStuInfoHeading)
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStuID)
                                    .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStuName)
                                    .addComponent(txtStuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDOB)
                                    .addComponent(dpDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBatch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAddress)
                                    .addComponent(jspAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(canvasProfImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDNo)
                            .addComponent(txtNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhone)
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDOReg)
                                    .addComponent(dpDOReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(txtGuardName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblGuardName1))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuardPhone1))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuardAddress1))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuardName2))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuardPhone2))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuardAddress2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostel)
                    .addComponent(chkHostel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStuPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlStuPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void chkHostelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHostelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHostelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        stu.setAccess(stuAccess);
        stu.setName(txtStuName.getText());
        stu.setDate(dpDOB.getDate());
        stu.setBatch(Integer.parseInt(txtBatch.getText()));
        stu.setAddress(txtAddress.getText());
        stu.setNIC(txtNIC.getText());
        stu.setPhone(Integer.parseInt(txtPhone.getText()));
        stu.setDate(dpDOReg.getDate());
        stu.setGuadian1Name(txtGuardName1.getText());
        stu.setGuadian1Address(txtGuardAddress1.getText());
        stu.setGuadian1Telephone(Integer.parseInt(txtGuardPhone1.getText()));
        stu.setGuadian2Name(txtGuardName2.getText());
        stu.setGuadian2Address(txtGuardAddress2.getText());
        stu.setGuadian2Telephone(Integer.parseInt(txtGuardPhone2.getText()));
        stu.setIsHostel(chkHostel.isSelected());
        try {
            stu.updateStudent();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
        GUIObserver.searchStudentbyID();
        this.setVisible(false);
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtGuardName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuardName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuardName1ActionPerformed

    private void txtGuardName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuardName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuardName2ActionPerformed

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
            java.util.logging.Logger.getLogger(EditStudentInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditStudentInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditStudentInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditStudentInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditStudentInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private java.awt.Canvas canvasProfImage;
    private javax.swing.JCheckBox chkHostel;
    private org.jdesktop.swingx.JXDatePicker dpDOB;
    private org.jdesktop.swingx.JXDatePicker dpDOReg;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jspAddress;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDOReg;
    private javax.swing.JLabel lblGuardAddress1;
    private javax.swing.JLabel lblGuardAddress2;
    private javax.swing.JLabel lblGuardName1;
    private javax.swing.JLabel lblGuardName2;
    private javax.swing.JLabel lblGuardPhone1;
    private javax.swing.JLabel lblGuardPhone2;
    private javax.swing.JLabel lblHostel;
    private javax.swing.JLabel lblIDNo;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblStuID;
    private javax.swing.JLabel lblStuInfoHeading;
    private javax.swing.JLabel lblStuName;
    private javax.swing.JPanel pnlStuPersonalInfo;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtBatch;
    private javax.swing.JTextField txtGuardAddress1;
    private javax.swing.JTextField txtGuardAddress2;
    private javax.swing.JTextField txtGuardName1;
    private javax.swing.JTextField txtGuardName2;
    private javax.swing.JTextField txtGuardPhone1;
    private javax.swing.JTextField txtGuardPhone2;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtStuID;
    private javax.swing.JTextField txtStuName;
    // End of variables declaration//GEN-END:variables
}
