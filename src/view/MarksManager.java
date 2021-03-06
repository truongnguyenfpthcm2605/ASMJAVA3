/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DataBase.GradeDAO;
import DataBase.ShareDataUSer;
import DataBase.StudentDAO;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.CheckData;
import model.Grade;
import model.Student;

/**
 *
 * @author truong
 */
public class MarksManager extends javax.swing.JFrame {

    int locationMouse = -1;
    DefaultTableModel tablemodel;
    CheckData data = new CheckData();
    Grade gd;
    GradeDAO dataGrade = new GradeDAO();
    Student sv = new Student();
    StudentDAO studentDAO = new StudentDAO();
    

    /**
     * Creates new form MarksManager
     */
    public MarksManager() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addTitlleTable();
        fillTable();
        Role();
    }
    private void Role(){
        String role = ShareDataUSer.role;
        if(role.equals("gv")){
            btnSave.setEnabled(false);
            btnDelete.setEnabled(false);
            btnNew.setEnabled(false);
        }
    }

    

    private void addTitlleTable() {
        tablemodel = (DefaultTableModel) tableList.getModel();
        tablemodel.setColumnIdentifiers(new String[]{"Code", "Name", "Mark", "Literature", "English", "AvgMarks"});
    }

    private boolean checkValiDate(JTextField a) {
        if (a.getText().isEmpty()) {
            a.setBackground(Color.YELLOW);
            return false;
        } else {
            a.setBackground(Color.white);
        }
        return true;

    }

    private boolean checkID(JTextField txtID) {
        if (!checkValiDate(txtID)) {
            JOptionPane.showMessageDialog(this, "ID Students not is empty");
            return false;
        } else if (!data.checkMaSv(txtID.getText())) {
            JOptionPane.showMessageDialog(this, "ID Student wrong format!");
            txtID.setBackground(Color.yellow);
            return false;
        } else {
            txtID.setBackground(Color.white);
        }
        return true;
    }

    public boolean checkForm() {

        // check id
        if (!checkID(txtCodeStudent)) {
            return false;
        }
        // check Toan
        if (!checkValiDate(txtMath)) {
            JOptionPane.showMessageDialog(this, "Math is not empty");
            return false;
        } else if (!data.CheckPoint(txtMath.getText())) {
            JOptionPane.showMessageDialog(this, "Math is Number , (0-10)");
            txtMath.setBackground(Color.yellow);
            return false;
        } else {
            txtMath.setBackground(Color.WHITE);
        }
        // check ngu van
        if (!checkValiDate(txtIden)) {
            JOptionPane.showMessageDialog(this, "Indentifies is not empty");
            return false;
        } else if (!data.CheckPoint(txtIden.getText())) {
            JOptionPane.showMessageDialog(this, "Indentifies is Number , (0-10)");
            txtIden.setBackground(Color.yellow);
            return false;
        } else {
            txtIden.setBackground(Color.WHITE);
        }

        // check anh van
        if (!checkValiDate(txtEnglish)) {
            JOptionPane.showMessageDialog(this, "English is not empty");
            return false;
        } else if (!data.CheckPoint(txtEnglish.getText())) {
            JOptionPane.showMessageDialog(this, "English is Number , (0-10)");
            txtEnglish.setBackground(Color.yellow);
            return false;
        } else {
            txtEnglish.setBackground(Color.WHITE);
        }

        return true;
    }

    public void ShowFrom(int a) {
        try {
            if (a != -1) {
                List<Grade> list = dataGrade.getTop3Grade(3);
                Grade gd = list.get(a);
                txtCodeStudent.setText(gd.getIDStudent());
                txtMath.setText(String.valueOf(gd.getMark()));
                txtIden.setText(String.valueOf(gd.getIdentifiers()));
                txtEnglish.setText(String.valueOf(gd.getEnglish()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        txtID.setText("");
        txtCodeStudent.setText("");
        txtMath.setText("");
        txtIden.setText("");
        txtEnglish.setText("");
        lblName.setText("");
        txtID.setBackground(Color.white);
        txtCodeStudent.setBackground(Color.white);
        txtMath.setBackground(Color.white);
        txtIden.setBackground(Color.white);
        txtEnglish.setBackground(Color.white);

    }

    private void fillTable() {
        try {
            tablemodel = (DefaultTableModel) tableList.getModel();
            tablemodel.setRowCount(0);
            for (Grade gd : dataGrade.getTop3Grade(3)) {
                tablemodel.addRow(new Object[]{gd.getId(), gd.getIDStudent(), gd.getMark(), gd.getIdentifiers(), gd.getEnglish(),
                    (gd.getMark() + gd.getIdentifiers() + gd.getEnglish()) / 3});
            }
        } catch (Exception e) {
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCodeStudent = new javax.swing.JTextField();
        txtMath = new javax.swing.JTextField();
        txtIden = new javax.swing.JTextField();
        txtEnglish = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAvg = new javax.swing.JLabel();
        lblName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Mark Manager");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel2.setText("ID :");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-icon.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel2)))
                    .addComponent(btnSearch))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        jLabel3.setText("Full Name :");

        jLabel4.setText("ID :");

        jLabel5.setText("Math :");

        jLabel6.setText("Identifiers");

        jLabel7.setText("English :");

        txtCodeStudent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodeStudentFocusLost(evt);
            }
        });
        txtCodeStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeStudentActionPerformed(evt);
            }
        });

        txtMath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMathFocusLost(evt);
            }
        });

        txtIden.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMathFocusLost(evt);
            }
        });

        txtEnglish.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMathFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 51, 255));
        jLabel12.setText("AverageMarks");

        lblAvg.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAvg.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIden, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(txtMath, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEnglish)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblName)
                            .addComponent(txtCodeStudent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAvg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodeStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIden, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvg))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEnglish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/new-file-icon.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save-icon.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exit-Close-icon.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Text-Edit-icon.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnNew)
                .addGap(20, 20, 20)
                .addComponent(btnSave)
                .addGap(20, 20, 20)
                .addComponent(btnDelete)
                .addGap(20, 20, 20)
                .addComponent(btnUpdate)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10);
        flowLayout1.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout1);

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Back-icon.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel4.add(btnFirst);

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back-icon (1).png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel4.add(btnback);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/forward-icon (1).png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Next-icon.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel4.add(btnLast);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 255));
        jLabel14.setText("Top 3 Student Marks Highest");

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fpoly2.png"))); // NOI18N

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/inside-logout-icon.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Text-Edit-icon.png"))); // NOI18N
        btnChange.setText("Change Password");
        btnChange.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnChangeMouseDragged(evt);
            }
        });
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChange)
                        .addGap(28, 28, 28)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(62, 62, 62))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(437, 437, 437))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnChange))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtCodeStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeStudentActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        reset();

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        OtptionLogin opLogin = new OtptionLogin();
        opLogin.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (checkForm()) {
                gd = new Grade();
                gd.setIDStudent(txtCodeStudent.getText());
                gd.setMark(Float.parseFloat(txtMath.getText()));
                gd.setIdentifiers(Float.parseFloat(txtIden.getText()));
                gd.setEnglish(Float.parseFloat(txtEnglish.getText()));
                if (dataGrade.FindbyIDGrade(txtCodeStudent.getText()) != null) {
                    int choose = JOptionPane.showConfirmDialog(this, "ID Student is exist,Do you want update this grade student", "Nodify", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    } else {
                        dataGrade.Update(gd);
                        JOptionPane.showMessageDialog(this, "Update Successfull");
                        fillTable();
                    }
                } else {
                    if (dataGrade.Insert(gd)) {
                        JOptionPane.showMessageDialog(this, "Save successfull");
                        fillTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Save Fail");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "You can't new Student");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtCodeStudentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodeStudentFocusLost
        try {
            StudentDAO sv = new StudentDAO();
            Student sc = sv.findStudent(txtCodeStudent.getText());
            if (sc != null) {
                lblName.setText(sc.getName());
            } else {
                lblName.setText("No exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtCodeStudentFocusLost

    private void txtMathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMathFocusLost
        if (txtMath.getText().isEmpty() || txtIden.getText().isEmpty() || txtEnglish.getText().isEmpty()) {
            return;
        }
        float math = Float.parseFloat(txtMath.getText());
        float Indentifiers = Float.parseFloat(txtIden.getText());
        float English = Float.parseFloat(txtEnglish.getText());
        float avg = (math + Indentifiers + English) / 3;
        String pointavg = String.format("%.2f", avg);
        lblAvg.setText(pointavg);

    }//GEN-LAST:event_txtMathFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            if (!checkID(txtID)) {
                return;
            }
            gd = dataGrade.FindbyIDGrade(txtID.getText());
            if (gd != null) {
                txtCodeStudent.setText(gd.getIDStudent());
                txtMath.setText(String.valueOf(gd.getMark()));
                txtIden.setText(String.valueOf(gd.getIdentifiers()));
                txtEnglish.setText(String.valueOf(gd.getEnglish()));
                txtMathFocusLost(null);
                txtCodeStudentFocusLost(null);

            } else {
                JOptionPane.showMessageDialog(this, "Student not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        btnSaveActionPerformed(evt);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (!checkID(txtCodeStudent)) {
            return;
        } else {
            try {
                if (studentDAO.findStudent(txtCodeStudent.getText()) != null) {
                    JOptionPane.showMessageDialog(this, "You can't Delete it,Because you not role");
                } else {
                    if (dataGrade.DeleteByID(txtCodeStudent.getText())) {
                        JOptionPane.showMessageDialog(this, "Delete  successfull");
                        fillTable();
                        txtMathFocusLost(null);
                        txtCodeStudentFocusLost(null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        locationMouse = tableList.getSelectedRow();
        ShowFrom(locationMouse);
    }//GEN-LAST:event_tableListMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        try {
            locationMouse = tableList.getSelectedRow();
            if (locationMouse != -1) {
                locationMouse = dataGrade.getTop3Grade(3).size() - 1;
                ShowFrom(locationMouse);
                tableList.setRowSelectionInterval(locationMouse, locationMouse);
                txtMathFocusLost(null);
                txtCodeStudentFocusLost(null);

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        try {
            locationMouse = tableList.getSelectedRow();
            if (locationMouse != -1) {
                locationMouse++;
                if (locationMouse > dataGrade.getTop3Grade(3).size() - 1) {
                    locationMouse = 0;
                }
                ShowFrom(locationMouse);
                tableList.setRowSelectionInterval(locationMouse, locationMouse);
                txtMathFocusLost(null);
                txtCodeStudentFocusLost(null);

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        try {
            locationMouse = tableList.getSelectedRow();
            if (locationMouse != -1) {
                locationMouse--;
                if (locationMouse < 0) {
                    locationMouse = dataGrade.getTop3Grade(3).size() - 1;
                }
                ShowFrom(locationMouse);
                tableList.setRowSelectionInterval(locationMouse, locationMouse);
                txtMathFocusLost(null);
                txtCodeStudentFocusLost(null);

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnbackActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        try {
            locationMouse = tableList.getSelectedRow();
            if (locationMouse != -1) {
                locationMouse = 0;
                ShowFrom(locationMouse);
                tableList.setRowSelectionInterval(locationMouse, locationMouse);
                txtMathFocusLost(null);
                txtCodeStudentFocusLost(null);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnChangeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChangeMouseDragged

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        ChangePassword change = new ChangePassword();
        change.setVisible(true);
    }//GEN-LAST:event_btnChangeActionPerformed

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
            java.util.logging.Logger.getLogger(MarksManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarksManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarksManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarksManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarksManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvg;
    private javax.swing.JTextField lblName;
    private javax.swing.JTable tableList;
    private javax.swing.JTextField txtCodeStudent;
    private javax.swing.JTextField txtEnglish;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIden;
    private javax.swing.JTextField txtMath;
    // End of variables declaration//GEN-END:variables

}
