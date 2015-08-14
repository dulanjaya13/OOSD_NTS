/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import Connection.DBConnector;
import Lecturer_Data_Access.Lecturer_Data_Access;
import Lecturer_Domain.Lecturer;
import Student_Data_Access.Student_Data_Access;
import Student_Domain.Student;
import java.awt.CardLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Dulanjaya Tennekoon
 */
public class MainGUI extends javax.swing.JFrame implements MainGUIObserver{

    private CardLayout layoutSubMain = new CardLayout();
    private CardLayout layoutSideBar = new CardLayout();
    private CardLayout layoutControl = new CardLayout();
    
    private DBConnector dbase = new DBConnector();
    private Student stu;
    private Lecturer lec;
    private Student_Data_Access stuAccess = new Student_Data_Access(dbase);
    private Lecturer_Data_Access lecAccess = new Lecturer_Data_Access(dbase);
    
    private String app_name = "NTS";
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form PrincipalWindow
     */
    //constructors
    public MainGUI() {
        initComponents();
        startUpSettings();
    }

    public MainGUI(int logId) {
        initComponents();
        startUpSettings();
        if (logId == 0) {
            layoutSideBar.show(pnlSideBar, "PrincipalPane");
            layoutControl.show(pnlControls, "pnlNull");
            layoutSubMain.show(pnlsubMain, "pnlWelcome");
        } else if(logId == 1) {
            layoutSideBar.show(pnlSideBar, "LecturerPane");
            layoutControl.show(pnlControls, "pnlStudentControlsofLecturer");
        } else if(logId == 2) {
            layoutSideBar.show(pnlSideBar, "NonAcademicPane");
            layoutControl.show(pnlControls, "NonAcademicPane");
        } else {
            System.exit(0);
        }
        
    }

    //start up settings
    public void startUpSettings() {
        setLayouts();
        frameSettings();
        addListeners();
    }

    public void frameSettings() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("NTS Kurunegala");
    }

    public void setLayouts() {
        setLayoutSideBar();
        setLayoutSubMain();
        setLayoutControl();
    }
    
    public void setLayoutSubMain() {
        //sub main panel setting up
        pnlsubMain.setLayout(layoutSubMain);
        pnlsubMain.add("pnlStuInfo", pnlStuInfo);
        pnlsubMain.add("pnlLectInfo", pnlLectInfo);
        pnlsubMain.add("pnlRepeatWarnings", pnlRepeatWarnings);
        pnlsubMain.add("pnlSubjectInfo", pnlSubjectInformation);
        pnlsubMain.add("pnlODA", pnlOverallDailyAttendance);
        pnlsubMain.add("pnlUpdateDailyAttendance",pnlUpdateDailyAttendance);
        pnlsubMain.add("pnlUpdateSubjectAttendance", pnlUpdateSubjectAttendance);
        pnlsubMain.add("pnlUpdateStudentInformation", pnlUpdateStudentInformation);
        pnlsubMain.add("pnlUpdateLecturerInformation", pnlUpdateLecturerInformation);
        pnlsubMain.add("pnlUpdateStudentMarks", pnlUpdateStudentMarks);
        pnlsubMain.add("pnlWelcome", pnlWelcome);
    }

    public void setLayoutSideBar() {
        //sidebar panel setting up
        pnlSideBar.setLayout(layoutSideBar);
        pnlSideBar.add("PrincipalPane",PrincipalPane);
        pnlSideBar.add("LecturerPane",LecturerPane);
        pnlSideBar.add("NonAcademicPane",NonAcademicPane);
    }

    public void setLayoutControl() {
        //control setting up
        pnlControls.setLayout(layoutControl);
        pnlControls.add("pnlStudentControlsofPrincipal", pnlStudentControlsofPrincipal);
        pnlControls.add("pnlLecturerControlsOfPrincipal",pnlLecturerControlsOfPrincipal);
        pnlControls.add("pnlStudentControlsofLecturer",pnlStudentControlsofLecturer);
        pnlControls.add("pnlNull" , pnlNull);
        pnlControls.add("pnlUpdateStudentInformationControl",pnlUpdateStudentInformationControl);
        pnlControls.add("pnlUpdateMarksControl",pnlUpdateMarksControl);
    }
    
    //listeners
    private void addListeners() {
        //to enable all listeners
        addtextEditingListeners();
    }
    
    private void addtextEditingListeners() {
        txtStuID.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                btnEditInfo.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        
        txtLecID.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                btnEditInfo1.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        
        
//        txtSearchStudentbyName.getDocument().addDocumentListener(new DocumentListener() {
//            ArrayList<String> students = stuAccess.getStudentNames();
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                popUpSearchbyName.setVisible(false);
//                if (txtSearchStudentbyName.getText().length() > 3) {
//                    popUpSearchbyName.removeAll();
//                    for (String stu : students) {
//                        if (stu.toLowerCase().contains(txtSearchStudentbyName.getText().toLowerCase())) {
//                            JMenuItem i = new JMenuItem(stu);
//                            i.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    txtSearchStudentbyName.setText(i.getText());
//                                    popUpSearchbyName.setVisible(false);
//
//                                }
//                            });
//                            i.addKeyListener(new KeyAdapter() {
//                                @Override
//                                public void keyReleased(KeyEvent e) {
//                                    txtSearchStudentbyName.setText(i.getText());
//                                    popUpSearchbyName.setVisible(false);
//                                }
//
//                            });
//                            
//                            popUpSearchbyName.add(i);
//                        }
//                    }
//                    popUpSearchbyName.show(txtSearchStudentbyName, 0, 20);
//                    txtSearchStudentbyName.requestFocus();
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });
    }
    
    //gui service classes
    private void promptMsg(String msg, int type, int type2) {
        JOptionPane.showConfirmDialog(rootPane, msg, app_name, type, type2);
    }
    
    //gui logic classes
    @Override
    public void searchStudentbyID() {
        //this method is to set the information of searched student to the student information
        try {   
            stu = stuAccess.getProfile(Integer.parseInt(txtSearchStudentbyID.getText()));
            txtStuID.setText(Integer.toString(stu.getID()));
            txtStuName.setText(stu.getName());
            txtDOB.setText(sdf.format(stu.getDOB()));
            txtBatch.setText(Integer.toString(stu.getBatch()));
            txtAddress.setText(stu.getAddress());
            txtIDno.setText(stu.getNIC());
            txtPhone.setText(Integer.toString(stu.getPhone()));
            txtDOReg.setText(sdf.format(stu.getDate()));
            txtGuardName1.setText(stu.getGuadian1Name());
            txtGuardName2.setText(stu.getGuadian2Name());
            txtGuardAddress1.setText(stu.getGuadian1Address());
            txtGuardAddress2.setText(stu.getGuadian2Address());
            txtGuardPhone1.setText(Integer.toString(stu.getGuadian1Telephone()));
            txtGuardPhone2.setText(Integer.toString(stu.getGuadian1Telephone()));
            txtGuardAddress2.setText(Integer.toString(stu.getGuadian2Telephone()));
            chkHostel.setSelected(stu.isIsHostel());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            promptMsg("Enter a valid ID!",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            promptMsg("Database is Empty!", JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void searchStudentbyName() {
        Student stu;
        try {
            //method to get student searched by his/her name
            stu = stuAccess.getProfile(app_name, WIDTH, WIDTH);//?????
            txtStuID.setText(Integer.toString(stu.getID()));
            txtStuName.setText(stu.getName());
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            txtDOB.setText(s.format(stu.getDOB()));
            txtBatch.setText(Integer.toString(stu.getBatch()));
            txtAddress.setText(stu.getAddress());
            txtIDno.setText(stu.getNIC());
            txtPhone.setText(Integer.toString(stu.getPhone()));
            txtDOReg.setText(s.format(stu.getDate()));
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
            JOptionPane.showMessageDialog(this, "Enter a Valid Index Please!", app_name, JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void searchLecturerbyID() {
        Lecturer lec;
        try {
            lec = lecAccess.getLecturerProfile(Integer.parseInt(txtSearchLecbyID.getText()));
            txtLecID.setText(Integer.toString(lec.getID()));
            txtLecName2.setText(lec.getName());
            txtLecNIC.setText(lec.getNIC());
            txtLecAddress.setText(lec.getAddress());
            for (Object o : lec.getSubjects().toArray()) {
                System.out.println(o);
            }
            listSubjectsLecInfo.setListData(lec.getSubjects().toArray());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter a Valid Index Please!", app_name, JOptionPane.WARNING_MESSAGE);
        }
    }
//
//    public void searchLecturerbyName() {
//        Lecturer lec;
//        try {
//            lec = lecAccess.getLecturerProfile(txtSearchLecbyName.getText());
//            txtLecID.setText(Integer.toString(lec.getID()));
//            txtLecName2.setText(lec.getName());
//            txtLecNIC.setText(lec.getNIC());
//            txtLecAddress.setText(lec.getAddress());
//            for (String o : lecAccess.getLectureNames()) {
//                System.out.println(o);
//            }
//            listSubjectsLecInfo.setListData(lec.getSubjects().toArray());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "No Such Name!", app_name, JOptionPane.WARNING_MESSAGE);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUpSearchbyName = new javax.swing.JPopupMenu();
        pnlMain = new javax.swing.JPanel();
        pnlSideBar = new javax.swing.JPanel();
        PrincipalPane = new javax.swing.JPanel();
        pnlManualUpdatebtns = new javax.swing.JPanel();
        btnUpdateRepWar = new javax.swing.JButton();
        lblLastchk = new javax.swing.JLabel();
        lblLastChkDate = new javax.swing.JLabel();
        pnlControl = new javax.swing.JPanel();
        btnStuInfoP = new javax.swing.JButton();
        btnLectInfoP = new javax.swing.JButton();
        btnRepeatWarnP = new javax.swing.JButton();
        btnSubjInfoP = new javax.swing.JButton();
        btnOverallDailyAttendanceP = new javax.swing.JButton();
        lblPrincipal = new javax.swing.JLabel();
        LecturerPane = new javax.swing.JPanel();
        lblLecturer = new javax.swing.JLabel();
        pnlControl1 = new javax.swing.JPanel();
        btnStuInfoL = new javax.swing.JButton();
        btnLectInfoL = new javax.swing.JButton();
        btnRepeatWarnL = new javax.swing.JButton();
        btnSubjInfoL = new javax.swing.JButton();
        NonAcademicPane = new javax.swing.JPanel();
        lblPrincipal1 = new javax.swing.JLabel();
        pnlControl2 = new javax.swing.JPanel();
        btnStuInfoP1 = new javax.swing.JButton();
        btnLectInfoP1 = new javax.swing.JButton();
        btnRepeatWarnP3 = new javax.swing.JButton();
        btnRepeatWarnP4 = new javax.swing.JButton();
        btnRepeatWarnP5 = new javax.swing.JButton();
        pnlsubMain = new javax.swing.JPanel();
        pnlStuInfo = new javax.swing.JPanel();
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
        txtIDno = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        lblDOReg = new javax.swing.JLabel();
        txtDOReg = new javax.swing.JTextField();
        canvasProfImage = new java.awt.Canvas();
        lblGuardName1 = new javax.swing.JLabel();
        txtGuardName1 = new javax.swing.JTextField();
        lblGuardPhone1 = new javax.swing.JLabel();
        txtGuardPhone1 = new javax.swing.JTextField();
        lblHostel = new javax.swing.JLabel();
        chkHostel = new javax.swing.JCheckBox();
        lblGuardAddress1 = new javax.swing.JLabel();
        txtGuardAddress1 = new javax.swing.JTextField();
        txtGuardAddress2 = new javax.swing.JTextField();
        lblGuardAddress2 = new javax.swing.JLabel();
        lblGuardPhone2 = new javax.swing.JLabel();
        txtGuardPhone2 = new javax.swing.JTextField();
        txtGuardName2 = new javax.swing.JTextField();
        lblGuardName2 = new javax.swing.JLabel();
        pnlLectInfo = new javax.swing.JPanel();
        pnlsubLecturerInfo = new javax.swing.JPanel();
        lblLecturerInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblLecturerID = new javax.swing.JLabel();
        lblLecturerName = new javax.swing.JLabel();
        lblNIC = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLecID = new javax.swing.JTextField();
        txtLecName2 = new javax.swing.JTextField();
        txtLecNIC = new javax.swing.JTextField();
        txtLecAddress = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSubjectsLecInfo = new javax.swing.JList();
        canvas1 = new java.awt.Canvas();
        pnlRepeatWarnings = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlSubjectInformation = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoOFLectures = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbLecturers = new javax.swing.JComboBox();
        cmbSubject = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmbLevel = new javax.swing.JComboBox();
        pnlOverallDailyAttendance = new javax.swing.JPanel();
        pnlsubOverallDailyAttendance = new javax.swing.JPanel();
        lbldate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbldate1 = new javax.swing.JLabel();
        cmbyear1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        pnlUpdateDailyAttendance = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jXDatePicker5 = new org.jdesktop.swingx.JXDatePicker();
        jLabel20 = new javax.swing.JLabel();
        pnlUpdateSubjectAttendance = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jXDatePicker3 = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jXDatePicker4 = new org.jdesktop.swingx.JXDatePicker();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        pnlUpdateStudentInformation = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        pnlUpdateLecturerInformation = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        pnlUpdateStudentMarks = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        pnlWelcome = new javax.swing.JPanel();
        pnlControls = new javax.swing.JPanel();
        pnlStudentControlsofPrincipal = new javax.swing.JPanel();
        pnlStudSpeInfoP = new javax.swing.JPanel();
        btnDailyAttendance = new javax.swing.JButton();
        btnSubAttendance = new javax.swing.JButton();
        btnSubMarks = new javax.swing.JButton();
        btnPrintCV = new javax.swing.JButton();
        lblCurricular = new javax.swing.JLabel();
        pnlStuDbControlsP = new javax.swing.JPanel();
        btnPrevRecord = new javax.swing.JButton();
        btnNextRecord = new javax.swing.JButton();
        lblStuDbControls = new javax.swing.JLabel();
        btnEditInfo = new javax.swing.JButton();
        btnDeleteRecord = new javax.swing.JButton();
        pnlStudSearchP = new javax.swing.JPanel();
        lblSearchStudent = new javax.swing.JLabel();
        txtSearchStudentbyID = new javax.swing.JTextField();
        lblStudentID = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        txtSearchStudentbyName = new javax.swing.JTextField();
        btnSearchbyID = new javax.swing.JButton();
        btnSearchbyName = new javax.swing.JButton();
        pnlStudentControlsofLecturer = new javax.swing.JPanel();
        pnlStudSpeInfoL = new javax.swing.JPanel();
        btnSubAttendance1 = new javax.swing.JButton();
        btnSubMarks1 = new javax.swing.JButton();
        lblCurricular1 = new javax.swing.JLabel();
        pnlStuDbControlsL = new javax.swing.JPanel();
        btnPrevRecord2 = new javax.swing.JButton();
        btnNextRecord2 = new javax.swing.JButton();
        lblStuDbControls2 = new javax.swing.JLabel();
        pnlStudSearchL = new javax.swing.JPanel();
        lblSearchStudent1 = new javax.swing.JLabel();
        txtStudentID1 = new javax.swing.JTextField();
        lblStudentID1 = new javax.swing.JLabel();
        lblStudentName1 = new javax.swing.JLabel();
        txtStudentName1 = new javax.swing.JTextField();
        btnSearchbyID1 = new javax.swing.JButton();
        btnSearchbyName1 = new javax.swing.JButton();
        pnlNull = new javax.swing.JPanel();
        pnlUpdateStudentInformationControl = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        pnlUpdateMarksControl = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        pnlLecturerControlsOfPrincipal = new javax.swing.JPanel();
        pnlStuDbControls1 = new javax.swing.JPanel();
        btnPrevRecord1 = new javax.swing.JButton();
        btnNextRecord1 = new javax.swing.JButton();
        lblStuDbControls1 = new javax.swing.JLabel();
        btnEditInfo1 = new javax.swing.JButton();
        btnDeleteRecord1 = new javax.swing.JButton();
        pnlLecSearchP1 = new javax.swing.JPanel();
        lblSearchLec = new javax.swing.JLabel();
        txtSearchLecbyID = new javax.swing.JTextField();
        lblLecID = new javax.swing.JLabel();
        lblLecName = new javax.swing.JLabel();
        txtSearchLecbyName = new javax.swing.JTextField();
        btnSearchLecbyID = new javax.swing.JButton();
        btnSearchLecbyName = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlSideBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSideBar.setLayout(new java.awt.CardLayout());

        pnlManualUpdatebtns.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpdateRepWar.setText("Update Repeat Warnings");
        btnUpdateRepWar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRepWarActionPerformed(evt);
            }
        });

        lblLastchk.setText("Last Check On:");
        lblLastchk.setEnabled(false);

        lblLastChkDate.setText("1900.01.01");
        lblLastChkDate.setEnabled(false);

        javax.swing.GroupLayout pnlManualUpdatebtnsLayout = new javax.swing.GroupLayout(pnlManualUpdatebtns);
        pnlManualUpdatebtns.setLayout(pnlManualUpdatebtnsLayout);
        pnlManualUpdatebtnsLayout.setHorizontalGroup(
            pnlManualUpdatebtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManualUpdatebtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManualUpdatebtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateRepWar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlManualUpdatebtnsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLastchk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLastChkDate)))
                .addContainerGap())
        );
        pnlManualUpdatebtnsLayout.setVerticalGroup(
            pnlManualUpdatebtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManualUpdatebtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdateRepWar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(pnlManualUpdatebtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastchk)
                    .addComponent(lblLastChkDate))
                .addContainerGap())
        );

        pnlControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnStuInfoP.setText("Student Information");
        btnStuInfoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuInfoPActionPerformed(evt);
            }
        });

        btnLectInfoP.setText("Lecturer Information");
        btnLectInfoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectInfoPActionPerformed(evt);
            }
        });

        btnRepeatWarnP.setText("Repeat Warnings");
        btnRepeatWarnP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepeatWarnPActionPerformed(evt);
            }
        });

        btnSubjInfoP.setText("Subject Information");
        btnSubjInfoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubjInfoPActionPerformed(evt);
            }
        });

        btnOverallDailyAttendanceP.setText("Overall Daily Attendance");
        btnOverallDailyAttendanceP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverallDailyAttendancePActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRepeatWarnP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLectInfoP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(btnStuInfoP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubjInfoP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOverallDailyAttendanceP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStuInfoP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLectInfoP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepeatWarnP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubjInfoP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOverallDailyAttendanceP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPrincipal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblPrincipal.setText("Principal");

        javax.swing.GroupLayout PrincipalPaneLayout = new javax.swing.GroupLayout(PrincipalPane);
        PrincipalPane.setLayout(PrincipalPaneLayout);
        PrincipalPaneLayout.setHorizontalGroup(
            PrincipalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrincipal)
                .addGap(60, 60, 60))
            .addGroup(PrincipalPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlManualUpdatebtns, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PrincipalPaneLayout.setVerticalGroup(
            PrincipalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlManualUpdatebtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSideBar.add(PrincipalPane, "card2");

        lblLecturer.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblLecturer.setText("Lecturer");

        pnlControl1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnStuInfoL.setText("Student Information");
        btnStuInfoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuInfoLActionPerformed(evt);
            }
        });

        btnLectInfoL.setText("Lecturer Information");
        btnLectInfoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectInfoLActionPerformed(evt);
            }
        });

        btnRepeatWarnL.setText("Repeat Warnings");
        btnRepeatWarnL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepeatWarnLActionPerformed(evt);
            }
        });

        btnSubjInfoL.setText("Subject Information");
        btnSubjInfoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubjInfoLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControl1Layout = new javax.swing.GroupLayout(pnlControl1);
        pnlControl1.setLayout(pnlControl1Layout);
        pnlControl1Layout.setHorizontalGroup(
            pnlControl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRepeatWarnL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLectInfoL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(btnStuInfoL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubjInfoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlControl1Layout.setVerticalGroup(
            pnlControl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStuInfoL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLectInfoL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepeatWarnL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubjInfoL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LecturerPaneLayout = new javax.swing.GroupLayout(LecturerPane);
        LecturerPane.setLayout(LecturerPaneLayout);
        LecturerPaneLayout.setHorizontalGroup(
            LecturerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LecturerPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLecturer)
                .addGap(60, 60, 60))
            .addGroup(LecturerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        LecturerPaneLayout.setVerticalGroup(
            LecturerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LecturerPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblLecturer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSideBar.add(LecturerPane, "card3");

        lblPrincipal1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblPrincipal1.setText("Non Academic");

        pnlControl2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnStuInfoP1.setText("Update Student Information");
        btnStuInfoP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuInfoP1ActionPerformed(evt);
            }
        });

        btnLectInfoP1.setText("Update Lecturer Information");
        btnLectInfoP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectInfoP1ActionPerformed(evt);
            }
        });

        btnRepeatWarnP3.setText("Update Daily Attendance");
        btnRepeatWarnP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepeatWarnP3ActionPerformed(evt);
            }
        });

        btnRepeatWarnP4.setText("Update Subject Attendance");
        btnRepeatWarnP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepeatWarnP4ActionPerformed(evt);
            }
        });

        btnRepeatWarnP5.setText("Update Student Marks");
        btnRepeatWarnP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepeatWarnP5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControl2Layout = new javax.swing.GroupLayout(pnlControl2);
        pnlControl2.setLayout(pnlControl2Layout);
        pnlControl2Layout.setHorizontalGroup(
            pnlControl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControl2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLectInfoP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(btnStuInfoP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRepeatWarnP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRepeatWarnP4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRepeatWarnP5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlControl2Layout.setVerticalGroup(
            pnlControl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStuInfoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLectInfoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepeatWarnP3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepeatWarnP4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepeatWarnP5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NonAcademicPaneLayout = new javax.swing.GroupLayout(NonAcademicPane);
        NonAcademicPane.setLayout(NonAcademicPaneLayout);
        NonAcademicPaneLayout.setHorizontalGroup(
            NonAcademicPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NonAcademicPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NonAcademicPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlControl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(NonAcademicPaneLayout.createSequentialGroup()
                        .addComponent(lblPrincipal1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        NonAcademicPaneLayout.setVerticalGroup(
            NonAcademicPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NonAcademicPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblPrincipal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSideBar.add(NonAcademicPane, "card4");

        pnlsubMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlsubMain.setLayout(new java.awt.CardLayout());

        pnlStuPersonalInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStuInfoHeading.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblStuInfoHeading.setText("Student Information");

        lblStuID.setText("Student ID");

        txtStuID.setEditable(false);

        lblStuName.setText("Student Name");

        txtStuName.setEditable(false);

        lblBatch.setText("Batch");

        txtBatch.setEditable(false);

        lblAddress.setText("Address");

        txtAddress.setEditable(false);
        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jspAddress.setViewportView(txtAddress);

        lblIDNo.setText("NIC");

        txtIDno.setEditable(false);

        lblPhone.setText("Phone Number");

        txtPhone.setEditable(false);
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        lblDOB.setText("Date of Birth");

        txtDOB.setEditable(false);

        lblDOReg.setText("Registration Date");

        txtDOReg.setEditable(false);
        txtDOReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDORegActionPerformed(evt);
            }
        });

        canvasProfImage.setBackground(new java.awt.Color(153, 153, 255));

        lblGuardName1.setText("Guardian1's Name");

        txtGuardName1.setEditable(false);
        txtGuardName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuardName1ActionPerformed(evt);
            }
        });

        lblGuardPhone1.setText("Guardian1's Phone");

        txtGuardPhone1.setEditable(false);

        lblHostel.setText("Hostel Student");

        chkHostel.setText("hostel Student");
        chkHostel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHostelActionPerformed(evt);
            }
        });

        lblGuardAddress1.setText("Guardian1's Address");

        txtGuardAddress1.setEditable(false);

        txtGuardAddress2.setEditable(false);

        lblGuardAddress2.setText("Guardian2's Address");

        lblGuardPhone2.setText("Guardian2's Phone");

        txtGuardPhone2.setEditable(false);

        txtGuardName2.setEditable(false);
        txtGuardName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuardName2ActionPerformed(evt);
            }
        });

        lblGuardName2.setText("Guardian2's Name");

        javax.swing.GroupLayout pnlStuPersonalInfoLayout = new javax.swing.GroupLayout(pnlStuPersonalInfo);
        pnlStuPersonalInfo.setLayout(pnlStuPersonalInfoLayout);
        pnlStuPersonalInfoLayout.setHorizontalGroup(
            pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAddress)
                            .addComponent(lblPhone)
                            .addComponent(lblDOReg)
                            .addComponent(lblGuardName1)
                            .addComponent(lblGuardPhone1)
                            .addComponent(lblHostel)
                            .addComponent(lblGuardPhone2)
                            .addComponent(lblGuardName2)
                            .addComponent(lblGuardAddress2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGuardAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(chkHostel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStuPersonalInfoLayout.createSequentialGroup()
                                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGuardAddress2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardPhone2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardName2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardName1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDOReg, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardPhone1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGuardAddress1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDno))
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
                                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtStuName)
                                            .addComponent(txtStuID)
                                            .addComponent(txtDOB)
                                            .addComponent(txtBatch))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                                        .addComponent(jspAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                        .addGap(10, 10, 10)))
                                .addComponent(canvasProfImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlStuPersonalInfoLayout.setVerticalGroup(
            pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStuInfoHeading)
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStuID)
                            .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStuName)
                            .addComponent(txtStuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDOB)
                            .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
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
                    .addComponent(txtIDno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhone)
                    .addGroup(pnlStuPersonalInfoLayout.createSequentialGroup()
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDOReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDOReg))))
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addGroup(pnlStuPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkHostel)
                    .addComponent(lblHostel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlStuInfoLayout = new javax.swing.GroupLayout(pnlStuInfo);
        pnlStuInfo.setLayout(pnlStuInfoLayout);
        pnlStuInfoLayout.setHorizontalGroup(
            pnlStuInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStuPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlStuInfoLayout.setVerticalGroup(
            pnlStuInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStuPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlStuInfo, "card2");

        pnlsubLecturerInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLecturerInfo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblLecturerInfo.setText("Lecturer Information");

        lblLecturerID.setText("Lecturer ID");

        lblLecturerName.setText("Lecturer Name");

        lblNIC.setText("NIC");

        jLabel1.setText("Address");

        jLabel2.setText("Subjects");

        txtLecID.setEditable(false);

        txtLecName2.setEditable(false);
        txtLecName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLecName2ActionPerformed(evt);
            }
        });

        txtLecNIC.setEditable(false);

        txtLecAddress.setEditable(false);

        jScrollPane1.setViewportView(listSubjectsLecInfo);

        canvas1.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblLecturerID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(txtLecID, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLecturerName)
                            .addComponent(lblNIC)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLecAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLecNIC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLecName2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLecturerID)
                            .addComponent(txtLecID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLecturerName)
                            .addComponent(txtLecName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNIC)
                            .addComponent(txtLecNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtLecAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlsubLecturerInfoLayout = new javax.swing.GroupLayout(pnlsubLecturerInfo);
        pnlsubLecturerInfo.setLayout(pnlsubLecturerInfoLayout);
        pnlsubLecturerInfoLayout.setHorizontalGroup(
            pnlsubLecturerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsubLecturerInfoLayout.createSequentialGroup()
                .addGroup(pnlsubLecturerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlsubLecturerInfoLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(lblLecturerInfo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlsubLecturerInfoLayout.setVerticalGroup(
            pnlsubLecturerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsubLecturerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLecturerInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLectInfoLayout = new javax.swing.GroupLayout(pnlLectInfo);
        pnlLectInfo.setLayout(pnlLectInfoLayout);
        pnlLectInfoLayout.setHorizontalGroup(
            pnlLectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlsubLecturerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLectInfoLayout.setVerticalGroup(
            pnlLectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlsubLecturerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlLectInfo, "card3");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "StudentID", "Level", "Warning", "Probation"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("Repeat Warnings");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "StudentID", "Level", "Warning", "Probation"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 0));
        jLabel10.setText("New Warnings");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 0, 0));
        jLabel11.setText("Warnings");

        jButton1.setText("Print Warning List");

        jButton2.setText("Clear Notifications");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel10))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout pnlRepeatWarningsLayout = new javax.swing.GroupLayout(pnlRepeatWarnings);
        pnlRepeatWarnings.setLayout(pnlRepeatWarningsLayout);
        pnlRepeatWarningsLayout.setHorizontalGroup(
            pnlRepeatWarningsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRepeatWarningsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRepeatWarningsLayout.setVerticalGroup(
            pnlRepeatWarningsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRepeatWarningsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlsubMain.add(pnlRepeatWarnings, "card4");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Subject Information");

        jLabel4.setText("Subject");

        txtNoOFLectures.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNoOFLectures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoOFLecturesActionPerformed(evt);
            }
        });

        jLabel5.setText("No Of Lectures");

        jLabel6.setText("Lecturers");

        cmbLecturers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lecturer 1", "Lecturer 2", "Lecturer 3" }));

        cmbSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Subject", "Subject 1", "Subject 2", "Subject 3" }));

        jLabel7.setText("Level");

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Subject", "Level 1", "Level 2", "Level 3" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbLecturers, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNoOFLectures, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbSubject, javax.swing.GroupLayout.Alignment.TRAILING, 0, 276, Short.MAX_VALUE)
                                .addComponent(cmbLevel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel3)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNoOFLectures, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLecturers, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSubjectInformationLayout = new javax.swing.GroupLayout(pnlSubjectInformation);
        pnlSubjectInformation.setLayout(pnlSubjectInformationLayout);
        pnlSubjectInformationLayout.setHorizontalGroup(
            pnlSubjectInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubjectInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSubjectInformationLayout.setVerticalGroup(
            pnlSubjectInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubjectInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlSubjectInformation, "card5");

        pnlOverallDailyAttendance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlsubOverallDailyAttendance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldate.setText("Date");

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Index", "Name", "Status", "Leave"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        lbldate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldate1.setText("Level");

        cmbyear1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "1", "2", "3" }));
        cmbyear1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel8.setText("Daily Attendance");

        javax.swing.GroupLayout pnlsubOverallDailyAttendanceLayout = new javax.swing.GroupLayout(pnlsubOverallDailyAttendance);
        pnlsubOverallDailyAttendance.setLayout(pnlsubOverallDailyAttendanceLayout);
        pnlsubOverallDailyAttendanceLayout.setHorizontalGroup(
            pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsubOverallDailyAttendanceLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldate1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbyear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
            .addGroup(pnlsubOverallDailyAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlsubOverallDailyAttendanceLayout.setVerticalGroup(
            pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsubOverallDailyAttendanceLayout.createSequentialGroup()
                .addGroup(pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlsubOverallDailyAttendanceLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbldate)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlsubOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldate1)
                            .addComponent(cmbyear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlsubOverallDailyAttendanceLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOverallDailyAttendanceLayout = new javax.swing.GroupLayout(pnlOverallDailyAttendance);
        pnlOverallDailyAttendance.setLayout(pnlOverallDailyAttendanceLayout);
        pnlOverallDailyAttendanceLayout.setHorizontalGroup(
            pnlOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOverallDailyAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlsubOverallDailyAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlOverallDailyAttendanceLayout.setVerticalGroup(
            pnlOverallDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOverallDailyAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlsubOverallDailyAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlOverallDailyAttendance, "card6");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setText("Update Daily Attendance");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Student_ID", "Presence"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jButton3.setText("Update");

        jButton4.setText("Cancel");

        jLabel13.setText("Select Date");

        jLabel19.setText("Current No of Working Days");

        jLabel20.setText("Semester Started Date");

        javax.swing.GroupLayout pnlUpdateDailyAttendanceLayout = new javax.swing.GroupLayout(pnlUpdateDailyAttendance);
        pnlUpdateDailyAttendance.setLayout(pnlUpdateDailyAttendanceLayout);
        pnlUpdateDailyAttendanceLayout.setHorizontalGroup(
            pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jXDatePicker5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                                .addGroup(pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(0, 74, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlUpdateDailyAttendanceLayout.setVerticalGroup(
            pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateDailyAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpdateDailyAttendanceLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlsubMain.add(pnlUpdateDailyAttendance, "card7");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Student_ID", "Presence"
            }
        ));
        jScrollPane6.setViewportView(jTable5);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel14.setText("Update Subject Attendance");

        jButton5.setText("Cancel");

        jButton6.setText("Update");

        jLabel15.setText("Select Date");

        jLabel16.setText("Select Subject");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Subject", "Subject1", "Subject2", "Subject3" }));

        jLabel17.setText("Semester Started Date");

        jLabel18.setText("Current No of Lectures");

        javax.swing.GroupLayout pnlUpdateSubjectAttendanceLayout = new javax.swing.GroupLayout(pnlUpdateSubjectAttendance);
        pnlUpdateSubjectAttendance.setLayout(pnlUpdateSubjectAttendanceLayout);
        pnlUpdateSubjectAttendanceLayout.setHorizontalGroup(
            pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXDatePicker3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jXDatePicker4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                                .addGroup(pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlUpdateSubjectAttendanceLayout.setVerticalGroup(
            pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateSubjectAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpdateSubjectAttendanceLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlsubMain.add(pnlUpdateSubjectAttendance, "card8");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "StudentID", "StudentName", "Address", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(1).setHeaderValue("StudentName");
            jTable6.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel21.setText("Update Student Information");

        javax.swing.GroupLayout pnlUpdateStudentInformationLayout = new javax.swing.GroupLayout(pnlUpdateStudentInformation);
        pnlUpdateStudentInformation.setLayout(pnlUpdateStudentInformationLayout);
        pnlUpdateStudentInformationLayout.setHorizontalGroup(
            pnlUpdateStudentInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateStudentInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateStudentInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateStudentInformationLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUpdateStudentInformationLayout.setVerticalGroup(
            pnlUpdateStudentInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateStudentInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlUpdateStudentInformation, "card9");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "LecturerID", "LecturerName", "Address", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(1).setHeaderValue("StudentName");
            jTable7.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel23.setText("Update Lecturer Information");

        jButton7.setText("Update");

        javax.swing.GroupLayout pnlUpdateLecturerInformationLayout = new javax.swing.GroupLayout(pnlUpdateLecturerInformation);
        pnlUpdateLecturerInformation.setLayout(pnlUpdateLecturerInformationLayout);
        pnlUpdateLecturerInformationLayout.setHorizontalGroup(
            pnlUpdateLecturerInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateLecturerInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateLecturerInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateLecturerInformationLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateLecturerInformationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlUpdateLecturerInformationLayout.setVerticalGroup(
            pnlUpdateLecturerInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateLecturerInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addContainerGap())
        );

        pnlsubMain.add(pnlUpdateLecturerInformation, "card10");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "StudentID", "CA1", "CA2", "CA3", "CA4", "CA5", "FinalExam"
            }
        ));
        jScrollPane9.setViewportView(jTable8);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel24.setText("Update Student Marks");

        javax.swing.GroupLayout pnlUpdateStudentMarksLayout = new javax.swing.GroupLayout(pnlUpdateStudentMarks);
        pnlUpdateStudentMarks.setLayout(pnlUpdateStudentMarksLayout);
        pnlUpdateStudentMarksLayout.setHorizontalGroup(
            pnlUpdateStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateStudentMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateStudentMarksLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUpdateStudentMarksLayout.setVerticalGroup(
            pnlUpdateStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateStudentMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlsubMain.add(pnlUpdateStudentMarks, "card11");

        javax.swing.GroupLayout pnlWelcomeLayout = new javax.swing.GroupLayout(pnlWelcome);
        pnlWelcome.setLayout(pnlWelcomeLayout);
        pnlWelcomeLayout.setHorizontalGroup(
            pnlWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        pnlWelcomeLayout.setVerticalGroup(
            pnlWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        pnlsubMain.add(pnlWelcome, "card12");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlSideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlsubMain, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlsubMain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlSideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlControls.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlControls.setLayout(new java.awt.CardLayout());

        pnlStudentControlsofPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlStudSpeInfoP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnDailyAttendance.setText("View Daily Attendance");
        btnDailyAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDailyAttendanceActionPerformed(evt);
            }
        });

        btnSubAttendance.setText("View Subject Attendance");
        btnSubAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubAttendanceActionPerformed(evt);
            }
        });

        btnSubMarks.setText("View Subject Marks");
        btnSubMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubMarksActionPerformed(evt);
            }
        });

        btnPrintCV.setText("Print Character Certificate");

        lblCurricular.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCurricular.setText("Curricular Information");

        javax.swing.GroupLayout pnlStudSpeInfoPLayout = new javax.swing.GroupLayout(pnlStudSpeInfoP);
        pnlStudSpeInfoP.setLayout(pnlStudSpeInfoPLayout);
        pnlStudSpeInfoPLayout.setHorizontalGroup(
            pnlStudSpeInfoPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudSpeInfoPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStudSpeInfoPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDailyAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrintCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSpeInfoPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCurricular)
                .addGap(43, 43, 43))
        );
        pnlStudSpeInfoPLayout.setVerticalGroup(
            pnlStudSpeInfoPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSpeInfoPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurricular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDailyAttendance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubAttendance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubMarks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintCV)
                .addContainerGap())
        );

        pnlStuDbControlsP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPrevRecord.setText("Previous");
        btnPrevRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevRecordActionPerformed(evt);
            }
        });

        btnNextRecord.setText("Next");
        btnNextRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRecordActionPerformed(evt);
            }
        });

        lblStuDbControls.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblStuDbControls.setText("Student Database Controls");

        btnEditInfo.setText("Edit Information");
        btnEditInfo.setEnabled(false);
        btnEditInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditInfoActionPerformed(evt);
            }
        });

        btnDeleteRecord.setText("Delete");

        javax.swing.GroupLayout pnlStuDbControlsPLayout = new javax.swing.GroupLayout(pnlStuDbControlsP);
        pnlStuDbControlsP.setLayout(pnlStuDbControlsPLayout);
        pnlStuDbControlsPLayout.setHorizontalGroup(
            pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControlsPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrevRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeleteRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(btnNextRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlStuDbControlsPLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStuDbControls)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlStuDbControlsPLayout.setVerticalGroup(
            pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControlsPLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblStuDbControls)
                .addGap(18, 18, 18)
                .addGroup(pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevRecord)
                    .addComponent(btnNextRecord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStuDbControlsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditInfo)
                    .addComponent(btnDeleteRecord))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnlStudSearchP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSearchStudent.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSearchStudent.setText("Search Student");

        lblStudentID.setText("Student ID");

        lblStudentName.setText("Student Name");

        btnSearchbyID.setText("Search");
        btnSearchbyID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchbyIDActionPerformed(evt);
            }
        });

        btnSearchbyName.setText("Search");

        javax.swing.GroupLayout pnlStudSearchPLayout = new javax.swing.GroupLayout(pnlStudSearchP);
        pnlStudSearchP.setLayout(pnlStudSearchPLayout);
        pnlStudSearchPLayout.setHorizontalGroup(
            pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudSearchPLayout.createSequentialGroup()
                .addGroup(pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchbyName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                                .addComponent(lblStudentID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchStudentbyID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSearchbyID))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                                .addComponent(lblStudentName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchStudentbyName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSearchStudent)
                .addGap(80, 80, 80))
        );
        pnlStudSearchPLayout.setVerticalGroup(
            pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudSearchPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchStudentbyID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchbyID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudSearchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchStudentbyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchbyName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlStudentControlsofPrincipalLayout = new javax.swing.GroupLayout(pnlStudentControlsofPrincipal);
        pnlStudentControlsofPrincipal.setLayout(pnlStudentControlsofPrincipalLayout);
        pnlStudentControlsofPrincipalLayout.setHorizontalGroup(
            pnlStudentControlsofPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentControlsofPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStudentControlsofPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStudSpeInfoP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStuDbControlsP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStudSearchP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlStudentControlsofPrincipalLayout.setVerticalGroup(
            pnlStudentControlsofPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentControlsofPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStudSpeInfoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlStuDbControlsP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlStudSearchP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlControls.add(pnlStudentControlsofPrincipal, "card3");

        pnlStudentControlsofLecturer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlStudSpeInfoL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSubAttendance1.setText("View Subject Attendance");
        btnSubAttendance1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubAttendance1ActionPerformed(evt);
            }
        });

        btnSubMarks1.setText("View Subject Marks");
        btnSubMarks1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubMarks1ActionPerformed(evt);
            }
        });

        lblCurricular1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCurricular1.setText("Curricular Information");

        javax.swing.GroupLayout pnlStudSpeInfoLLayout = new javax.swing.GroupLayout(pnlStudSpeInfoL);
        pnlStudSpeInfoL.setLayout(pnlStudSpeInfoLLayout);
        pnlStudSpeInfoLLayout.setHorizontalGroup(
            pnlStudSpeInfoLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSpeInfoLLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(lblCurricular1)
                .addGap(43, 43, 43))
            .addGroup(pnlStudSpeInfoLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStudSpeInfoLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubAttendance1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubMarks1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlStudSpeInfoLLayout.setVerticalGroup(
            pnlStudSpeInfoLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSpeInfoLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurricular1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubAttendance1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubMarks1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlStuDbControlsL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPrevRecord2.setText("Previous");
        btnPrevRecord2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevRecord2ActionPerformed(evt);
            }
        });

        btnNextRecord2.setText("Next");
        btnNextRecord2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRecord2ActionPerformed(evt);
            }
        });

        lblStuDbControls2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblStuDbControls2.setText("Student Database Controls");

        javax.swing.GroupLayout pnlStuDbControlsLLayout = new javax.swing.GroupLayout(pnlStuDbControlsL);
        pnlStuDbControlsL.setLayout(pnlStuDbControlsLLayout);
        pnlStuDbControlsLLayout.setHorizontalGroup(
            pnlStuDbControlsLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControlsLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrevRecord2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNextRecord2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlStuDbControlsLLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStuDbControls2)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlStuDbControlsLLayout.setVerticalGroup(
            pnlStuDbControlsLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControlsLLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblStuDbControls2)
                .addGap(18, 18, 18)
                .addGroup(pnlStuDbControlsLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevRecord2)
                    .addComponent(btnNextRecord2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlStudSearchL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSearchStudent1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSearchStudent1.setText("Search Student");

        lblStudentID1.setText("Student ID");

        lblStudentName1.setText("Student Name");

        btnSearchbyID1.setText("Search");

        btnSearchbyName1.setText("Search");

        javax.swing.GroupLayout pnlStudSearchLLayout = new javax.swing.GroupLayout(pnlStudSearchL);
        pnlStudSearchL.setLayout(pnlStudSearchLLayout);
        pnlStudSearchLLayout.setHorizontalGroup(
            pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudSearchLLayout.createSequentialGroup()
                .addGroup(pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchbyName1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                                .addComponent(lblStudentID1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStudentID1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSearchbyID1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                                .addComponent(lblStudentName1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStudentName1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudSearchLLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSearchStudent1)
                .addGap(80, 80, 80))
        );
        pnlStudSearchLLayout.setVerticalGroup(
            pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudSearchLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchStudent1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudentID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentID1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchbyID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStudSearchLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudentName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentName1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchbyName1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlStudentControlsofLecturerLayout = new javax.swing.GroupLayout(pnlStudentControlsofLecturer);
        pnlStudentControlsofLecturer.setLayout(pnlStudentControlsofLecturerLayout);
        pnlStudentControlsofLecturerLayout.setHorizontalGroup(
            pnlStudentControlsofLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentControlsofLecturerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStudentControlsofLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStudentControlsofLecturerLayout.createSequentialGroup()
                        .addComponent(pnlStudSpeInfoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(pnlStudentControlsofLecturerLayout.createSequentialGroup()
                        .addComponent(pnlStuDbControlsL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlStudSearchL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlStudentControlsofLecturerLayout.setVerticalGroup(
            pnlStudentControlsofLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentControlsofLecturerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStudSpeInfoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStuDbControlsL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStudSearchL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlControls.add(pnlStudentControlsofLecturer, "card4");

        javax.swing.GroupLayout pnlNullLayout = new javax.swing.GroupLayout(pnlNull);
        pnlNull.setLayout(pnlNullLayout);
        pnlNullLayout.setHorizontalGroup(
            pnlNullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        pnlNullLayout.setVerticalGroup(
            pnlNullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlControls.add(pnlNull, "card5");

        pnlUpdateStudentInformationControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("Select Batch");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton8.setText("Update");

        javax.swing.GroupLayout pnlUpdateStudentInformationControlLayout = new javax.swing.GroupLayout(pnlUpdateStudentInformationControl);
        pnlUpdateStudentInformationControl.setLayout(pnlUpdateStudentInformationControlLayout);
        pnlUpdateStudentInformationControlLayout.setHorizontalGroup(
            pnlUpdateStudentInformationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateStudentInformationControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateStudentInformationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlUpdateStudentInformationControlLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, 0, 265, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlUpdateStudentInformationControlLayout.setVerticalGroup(
            pnlUpdateStudentInformationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateStudentInformationControlLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlUpdateStudentInformationControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(571, Short.MAX_VALUE))
        );

        pnlControls.add(pnlUpdateStudentInformationControl, "card6");

        jLabel25.setText("Select Batch");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setText("Select Subject");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton9.setText("Update");

        javax.swing.GroupLayout pnlUpdateMarksControlLayout = new javax.swing.GroupLayout(pnlUpdateMarksControl);
        pnlUpdateMarksControl.setLayout(pnlUpdateMarksControlLayout);
        pnlUpdateMarksControlLayout.setHorizontalGroup(
            pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateMarksControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateMarksControlLayout.createSequentialGroup()
                        .addGroup(pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, 0, 266, Short.MAX_VALUE)
                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateMarksControlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        pnlUpdateMarksControlLayout.setVerticalGroup(
            pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateMarksControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdateMarksControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        pnlControls.add(pnlUpdateMarksControl, "card7");

        pnlLecturerControlsOfPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlStuDbControls1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPrevRecord1.setText("Previous");
        btnPrevRecord1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevRecord1ActionPerformed(evt);
            }
        });

        btnNextRecord1.setText("Next");
        btnNextRecord1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRecord1ActionPerformed(evt);
            }
        });

        lblStuDbControls1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblStuDbControls1.setText("Lecturer Database Controls");

        btnEditInfo1.setText("Edit Information");
        btnEditInfo1.setEnabled(false);
        btnEditInfo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditInfo1ActionPerformed(evt);
            }
        });

        btnDeleteRecord1.setText("Delete");

        javax.swing.GroupLayout pnlStuDbControls1Layout = new javax.swing.GroupLayout(pnlStuDbControls1);
        pnlStuDbControls1.setLayout(pnlStuDbControls1Layout);
        pnlStuDbControls1Layout.setHorizontalGroup(
            pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControls1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrevRecord1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteRecord1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNextRecord1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStuDbControls1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStuDbControls1)
                .addGap(34, 34, 34))
        );
        pnlStuDbControls1Layout.setVerticalGroup(
            pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStuDbControls1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblStuDbControls1)
                .addGap(18, 18, 18)
                .addGroup(pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevRecord1)
                    .addComponent(btnNextRecord1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStuDbControls1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditInfo1)
                    .addComponent(btnDeleteRecord1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLecSearchP1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSearchLec.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSearchLec.setText("Search Lecturer");

        lblLecID.setText("Lecturer ID");

        lblLecName.setText("Lecturer Name");

        txtSearchLecbyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchLecbyNameActionPerformed(evt);
            }
        });

        btnSearchLecbyID.setText("Search");
        btnSearchLecbyID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLecbyIDActionPerformed(evt);
            }
        });

        btnSearchLecbyName.setText("Search");
        btnSearchLecbyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLecbyNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLecSearchP1Layout = new javax.swing.GroupLayout(pnlLecSearchP1);
        pnlLecSearchP1.setLayout(pnlLecSearchP1Layout);
        pnlLecSearchP1Layout.setHorizontalGroup(
            pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecSearchP1Layout.createSequentialGroup()
                .addGroup(pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchLecbyName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                                .addComponent(lblLecID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchLecbyID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSearchLecbyID))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                                .addComponent(lblLecName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchLecbyName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLecSearchP1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSearchLec)
                .addGap(80, 80, 80))
        );
        pnlLecSearchP1Layout.setVerticalGroup(
            pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecSearchP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchLec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchLecbyID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLecID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchLecbyID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLecSearchP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchLecbyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLecName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchLecbyName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLecturerControlsOfPrincipalLayout = new javax.swing.GroupLayout(pnlLecturerControlsOfPrincipal);
        pnlLecturerControlsOfPrincipal.setLayout(pnlLecturerControlsOfPrincipalLayout);
        pnlLecturerControlsOfPrincipalLayout.setHorizontalGroup(
            pnlLecturerControlsOfPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturerControlsOfPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLecturerControlsOfPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStuDbControls1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLecSearchP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLecturerControlsOfPrincipalLayout.setVerticalGroup(
            pnlLecturerControlsOfPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturerControlsOfPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStuDbControls1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLecSearchP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
        );

        pnlControls.add(pnlLecturerControlsOfPrincipal, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStuInfoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuInfoPActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlStuInfo");
        layoutControl.show(pnlControls, "pnlStudentControlsofPrincipal");
    }//GEN-LAST:event_btnStuInfoPActionPerformed

    private void btnSubjInfoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubjInfoPActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlSubjectInfo");
    }//GEN-LAST:event_btnSubjInfoPActionPerformed

    private void btnNextRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRecordActionPerformed

    }//GEN-LAST:event_btnNextRecordActionPerformed

    private void btnPrevRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevRecordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevRecordActionPerformed

    private void chkHostelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHostelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHostelActionPerformed

    private void txtGuardName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuardName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuardName1ActionPerformed

    private void txtDORegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDORegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDORegActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void btnLectInfoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectInfoPActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlLectInfo");
        layoutControl.show(pnlControls, "pnlLecturerControlsOfPrincipal");
    }//GEN-LAST:event_btnLectInfoPActionPerformed

    private void btnRepeatWarnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepeatWarnPActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlRepeatWarnings");
    }//GEN-LAST:event_btnRepeatWarnPActionPerformed

    private void btnEditInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditInfoActionPerformed
        new EditStudentInformation(this, stuAccess, txtStuID.getText()).show();
    }//GEN-LAST:event_btnEditInfoActionPerformed

    private void btnPrevRecord1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevRecord1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevRecord1ActionPerformed

    private void btnNextRecord1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRecord1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextRecord1ActionPerformed

    private void btnEditInfo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditInfo1ActionPerformed
        EditLecturerInformation editLecturerInformation = new EditLecturerInformation(this, lecAccess, Integer.parseInt(txtLecID.getText()));
        editLecturerInformation.setVisible(true);
    }//GEN-LAST:event_btnEditInfo1ActionPerformed

    private void btnSubMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubMarksActionPerformed
        SubjectMarks subjectMarks = new SubjectMarks();
        subjectMarks.setVisible(true);
    }//GEN-LAST:event_btnSubMarksActionPerformed

    private void btnOverallDailyAttendancePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverallDailyAttendancePActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlODA");
    }//GEN-LAST:event_btnOverallDailyAttendancePActionPerformed

    private void btnDailyAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDailyAttendanceActionPerformed
        DailyAttendance dailyAttendance = new DailyAttendance();
        dailyAttendance.setVisible(true);
    }//GEN-LAST:event_btnDailyAttendanceActionPerformed

    private void btnSubAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubAttendanceActionPerformed
        SubjectAttendance subjectAttendance = new SubjectAttendance();
        subjectAttendance.setVisible(true);
    }//GEN-LAST:event_btnSubAttendanceActionPerformed

    private void btnUpdateRepWarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRepWarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateRepWarActionPerformed

    private void txtNoOFLecturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoOFLecturesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoOFLecturesActionPerformed

    private void txtGuardName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuardName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuardName2ActionPerformed

    private void btnStuInfoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuInfoLActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlStuInfo");
        layoutControl.show(pnlControls, "pnlStudentControlsofLecturer");
    }//GEN-LAST:event_btnStuInfoLActionPerformed

    private void btnLectInfoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectInfoLActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlLectInfo");
        layoutControl.show(pnlControls, "pnlNull");
    }//GEN-LAST:event_btnLectInfoLActionPerformed

    private void btnRepeatWarnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepeatWarnLActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlRepeatWarnings");
        layoutControl.show(pnlControls, "pnlNull");
    }//GEN-LAST:event_btnRepeatWarnLActionPerformed

    private void btnSubjInfoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubjInfoLActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlSubjectInfo");
        layoutControl.show(pnlControls, "pnlNull");
    }//GEN-LAST:event_btnSubjInfoLActionPerformed

    private void btnSubAttendance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubAttendance1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubAttendance1ActionPerformed

    private void btnSubMarks1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubMarks1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubMarks1ActionPerformed

    private void btnPrevRecord2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevRecord2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevRecord2ActionPerformed

    private void btnNextRecord2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRecord2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextRecord2ActionPerformed

    private void btnStuInfoP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuInfoP1ActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlUpdateStudentInformation");
        layoutControl.show(pnlControls, "pnlUpdateStudentInformationControl");
    }//GEN-LAST:event_btnStuInfoP1ActionPerformed

    private void btnLectInfoP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectInfoP1ActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlUpdateLecturerInformation");
        layoutControl.show(pnlControls,"pnlNull");
    }//GEN-LAST:event_btnLectInfoP1ActionPerformed

    private void btnRepeatWarnP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepeatWarnP3ActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlUpdateDailyAttendance");
    }//GEN-LAST:event_btnRepeatWarnP3ActionPerformed

    private void btnRepeatWarnP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepeatWarnP4ActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlUpdateSubjectAttendance");
    }//GEN-LAST:event_btnRepeatWarnP4ActionPerformed

    private void btnRepeatWarnP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepeatWarnP5ActionPerformed
        layoutSubMain.show(pnlsubMain, "pnlUpdateStudentMarks");
        layoutControl.show(pnlControls,"pnlUpdateMarksControl");
    }//GEN-LAST:event_btnRepeatWarnP5ActionPerformed

    private void btnSearchbyIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchbyIDActionPerformed
        searchStudentbyID();
    }//GEN-LAST:event_btnSearchbyIDActionPerformed

    private void txtSearchLecbyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchLecbyNameActionPerformed
        System.out.println();
    }//GEN-LAST:event_txtSearchLecbyNameActionPerformed

    private void btnSearchLecbyIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLecbyIDActionPerformed
        searchLecturerbyID();
    }//GEN-LAST:event_btnSearchLecbyIDActionPerformed

    private void btnSearchLecbyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLecbyNameActionPerformed
        //searchLecturerbyName();
    }//GEN-LAST:event_btnSearchLecbyNameActionPerformed

    private void txtLecName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLecName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLecName2ActionPerformed

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
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LecturerPane;
    private javax.swing.JPanel NonAcademicPane;
    private javax.swing.JPanel PrincipalPane;
    private javax.swing.JButton btnDailyAttendance;
    private javax.swing.JButton btnDeleteRecord;
    private javax.swing.JButton btnDeleteRecord1;
    private javax.swing.JButton btnEditInfo;
    private javax.swing.JButton btnEditInfo1;
    private javax.swing.JButton btnLectInfoL;
    private javax.swing.JButton btnLectInfoP;
    private javax.swing.JButton btnLectInfoP1;
    private javax.swing.JButton btnNextRecord;
    private javax.swing.JButton btnNextRecord1;
    private javax.swing.JButton btnNextRecord2;
    private javax.swing.JButton btnOverallDailyAttendanceP;
    private javax.swing.JButton btnPrevRecord;
    private javax.swing.JButton btnPrevRecord1;
    private javax.swing.JButton btnPrevRecord2;
    private javax.swing.JButton btnPrintCV;
    private javax.swing.JButton btnRepeatWarnL;
    private javax.swing.JButton btnRepeatWarnP;
    private javax.swing.JButton btnRepeatWarnP3;
    private javax.swing.JButton btnRepeatWarnP4;
    private javax.swing.JButton btnRepeatWarnP5;
    private javax.swing.JButton btnSearchLecbyID;
    private javax.swing.JButton btnSearchLecbyName;
    private javax.swing.JButton btnSearchbyID;
    private javax.swing.JButton btnSearchbyID1;
    private javax.swing.JButton btnSearchbyName;
    private javax.swing.JButton btnSearchbyName1;
    private javax.swing.JButton btnStuInfoL;
    private javax.swing.JButton btnStuInfoP;
    private javax.swing.JButton btnStuInfoP1;
    private javax.swing.JButton btnSubAttendance;
    private javax.swing.JButton btnSubAttendance1;
    private javax.swing.JButton btnSubMarks;
    private javax.swing.JButton btnSubMarks1;
    private javax.swing.JButton btnSubjInfoL;
    private javax.swing.JButton btnSubjInfoP;
    private javax.swing.JButton btnUpdateRepWar;
    private java.awt.Canvas canvas1;
    private java.awt.Canvas canvasProfImage;
    private javax.swing.JCheckBox chkHostel;
    private javax.swing.JComboBox cmbLecturers;
    private javax.swing.JComboBox cmbLevel;
    private javax.swing.JComboBox cmbSubject;
    private javax.swing.JComboBox cmbyear1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker5;
    private javax.swing.JScrollPane jspAddress;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblCurricular;
    private javax.swing.JLabel lblCurricular1;
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
    private javax.swing.JLabel lblLastChkDate;
    private javax.swing.JLabel lblLastchk;
    private javax.swing.JLabel lblLecID;
    private javax.swing.JLabel lblLecName;
    private javax.swing.JLabel lblLecturer;
    private javax.swing.JLabel lblLecturerID;
    private javax.swing.JLabel lblLecturerInfo;
    private javax.swing.JLabel lblLecturerName;
    private javax.swing.JLabel lblNIC;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPrincipal;
    private javax.swing.JLabel lblPrincipal1;
    private javax.swing.JLabel lblSearchLec;
    private javax.swing.JLabel lblSearchStudent;
    private javax.swing.JLabel lblSearchStudent1;
    private javax.swing.JLabel lblStuDbControls;
    private javax.swing.JLabel lblStuDbControls1;
    private javax.swing.JLabel lblStuDbControls2;
    private javax.swing.JLabel lblStuID;
    private javax.swing.JLabel lblStuInfoHeading;
    private javax.swing.JLabel lblStuName;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblStudentID1;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JLabel lblStudentName1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbldate1;
    private javax.swing.JList listSubjectsLecInfo;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlControl1;
    private javax.swing.JPanel pnlControl2;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JPanel pnlLecSearchP1;
    private javax.swing.JPanel pnlLectInfo;
    private javax.swing.JPanel pnlLecturerControlsOfPrincipal;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlManualUpdatebtns;
    private javax.swing.JPanel pnlNull;
    private javax.swing.JPanel pnlOverallDailyAttendance;
    private javax.swing.JPanel pnlRepeatWarnings;
    private javax.swing.JPanel pnlSideBar;
    private javax.swing.JPanel pnlStuDbControls1;
    private javax.swing.JPanel pnlStuDbControlsL;
    private javax.swing.JPanel pnlStuDbControlsP;
    private javax.swing.JPanel pnlStuInfo;
    private javax.swing.JPanel pnlStuPersonalInfo;
    private javax.swing.JPanel pnlStudSearchL;
    private javax.swing.JPanel pnlStudSearchP;
    private javax.swing.JPanel pnlStudSpeInfoL;
    private javax.swing.JPanel pnlStudSpeInfoP;
    private javax.swing.JPanel pnlStudentControlsofLecturer;
    private javax.swing.JPanel pnlStudentControlsofPrincipal;
    private javax.swing.JPanel pnlSubjectInformation;
    private javax.swing.JPanel pnlUpdateDailyAttendance;
    private javax.swing.JPanel pnlUpdateLecturerInformation;
    private javax.swing.JPanel pnlUpdateMarksControl;
    private javax.swing.JPanel pnlUpdateStudentInformation;
    private javax.swing.JPanel pnlUpdateStudentInformationControl;
    private javax.swing.JPanel pnlUpdateStudentMarks;
    private javax.swing.JPanel pnlUpdateSubjectAttendance;
    private javax.swing.JPanel pnlWelcome;
    private javax.swing.JPanel pnlsubLecturerInfo;
    private javax.swing.JPanel pnlsubMain;
    private javax.swing.JPanel pnlsubOverallDailyAttendance;
    private javax.swing.JPopupMenu popUpSearchbyName;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtBatch;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtDOReg;
    private javax.swing.JTextField txtGuardAddress1;
    private javax.swing.JTextField txtGuardAddress2;
    private javax.swing.JTextField txtGuardName1;
    private javax.swing.JTextField txtGuardName2;
    private javax.swing.JTextField txtGuardPhone1;
    private javax.swing.JTextField txtGuardPhone2;
    private javax.swing.JTextField txtIDno;
    private javax.swing.JTextField txtLecAddress;
    private javax.swing.JTextField txtLecID;
    private javax.swing.JTextField txtLecNIC;
    private javax.swing.JTextField txtLecName2;
    private javax.swing.JTextField txtNoOFLectures;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearchLecbyID;
    private javax.swing.JTextField txtSearchLecbyName;
    private javax.swing.JTextField txtSearchStudentbyID;
    private javax.swing.JTextField txtSearchStudentbyName;
    private javax.swing.JTextField txtStuID;
    private javax.swing.JTextField txtStuName;
    private javax.swing.JTextField txtStudentID1;
    private javax.swing.JTextField txtStudentName1;
    // End of variables declaration//GEN-END:variables
}
