/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.CheckData;
import DataBase.StudentDAO;
import java.text.Normalizer;
import java.util.List;
import model.Student;

/**
 *
 * @author truong
 */
public class StudentManager extends javax.swing.JFrame {

    DefaultTableModel tableModel = new DefaultTableModel();
    String linkImage = "./src/ImageMD/anh.png";
    CheckData data = new CheckData();
    StudentDAO dataList = new StudentDAO();

    Student sv;
    public int locationMouse = -1;
    List<Student> list = ListStudent();

    /**
     * Creates new form StudentManager
     */
    public StudentManager() {
        initComponents();
        initColumnTable();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lblImage.setIcon(reSizeImgae(String.valueOf(linkImage)));
        fillTable();

        this.setResizable(false);

    }

    private void initColumnTable() {
        String[] column = {"ID", "Full Name", "Email", "Phone", "Gender", "Address", "Image"};
        tableModel.setColumnIdentifiers(column);
        tableList.setModel(tableModel);

    }

    public String WriterEmail(String name) {
        // bo dau 
        String string = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        // thanh mang
        String[] arrStr = string.split(" ");
        // phan tu cuoi
        String ten = arrStr[arrStr.length - 1].toLowerCase();
        String email = txtID.getText().trim().toLowerCase();
        ten = ten + email + "@fpt.edu.vn";
        return ten;

    }

    // tra va list cau csdl
    public List<Student> ListStudent() {
        try {
            List<Student> lists = dataList.getAllStudnet();
            if (lists != null) {
                return lists;
            }
        } catch (Exception e) {
        }
        return null;
    }

    // resize img
    private ImageIcon reSizeImgae(String path) {
        ImageIcon myImage = new ImageIcon(path);
        Image img = myImage.getImage();
        Image newimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
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
        }
        return true;
    }

    public boolean checkForm() {

        // check id
        if (!checkID(txtID)) {
            return false;
        }
        // check name
        if (!checkValiDate(txtName)) {
            JOptionPane.showMessageDialog(this, "Name not is empty");
            return false;
        } else if (!data.checkName(txtName.getText())) {
            JOptionPane.showMessageDialog(this, "Name Length longer than 10 charactor!");
            txtName.setBackground(Color.yellow);
            return false;
        } else {
            txtName.setBackground(Color.white);
        }
        // check email
        if (!checkValiDate(txtEmail)) {
            JOptionPane.showMessageDialog(this, "Email not is empty");
            return false;
        } else if (!data.checkEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email wrong format!");
            txtEmail.setBackground(Color.yellow);
            return false;
        } else {
            txtEmail.setBackground(Color.white);
        }
        //check phone
        if (!checkValiDate(txtPhone)) {
            JOptionPane.showMessageDialog(this, "Number phone not is empty");
            return false;
        } else if (!data.checkNumberPhone(txtPhone.getText())) {
            JOptionPane.showMessageDialog(this, "Number phone wrong format!");
            txtPhone.setBackground(Color.yellow);
            return false;
        } else {
            txtPhone.setBackground(Color.white);
        }
        //check addres
        if (txtAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address not is empty");
            txtAddress.setBackground(Color.yellow);
            return false;
        } else if (txtAddress.getText().length() < 10) {
            JOptionPane.showMessageDialog(this, "Address Length longer than 10 charactor");
            txtAddress.setBackground(Color.yellow);
            return false;
        } else {
            txtAddress.setBackground(Color.white);
        }
        // check img
        if (linkImage.equals("./src/ImageMD/anh.png")) {
            JOptionPane.showMessageDialog(this, "Please choose image");
            return false;
        }

        return true;
    }

    private void reset() {
        txtID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        linkImage = "./src/ImageMD/anh.png";
        lblImage.setIcon(reSizeImgae(String.valueOf(linkImage)));
        txtID.setBackground(Color.WHITE);
        txtName.setBackground(Color.WHITE);
        txtEmail.setBackground(Color.WHITE);
        txtPhone.setBackground(Color.WHITE);
        txtAddress.setBackground(Color.WHITE);

    }

    private boolean chooseGender() {
        return rdoMale.isSelected();
    }

    public String LinkImage(String img) {
        String newlink = img.substring(img.indexOf("src"), img.length());
        String link2 = newlink.replace("\\", "/");
        return "./" + link2;
    }

    public String Gender(boolean a) {
        if (a) {
            return "Nam";
        }
        return "Nu";
    }

    public void fillTable() {
        tableModel = (DefaultTableModel) tableList.getModel();
        tableModel.setRowCount(0);
        for (Student student : list) {
            tableModel.addRow(new Object[]{student.getID(), student.getName(), student.getEmail(), student.getPhone(), Gender(student.isGender()), student.getAddress(), student.getImg()});
        }

    }

    private void ShowForm(int i) {

        sv = list.get(i);
        txtID.setText(sv.getID());
        txtName.setText(sv.getName());
        txtEmail.setText(sv.getEmail());
        txtPhone.setText(sv.getPhone());
        if (sv.isGender()) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(false);
        }
        txtAddress.setText(sv.getAddress());
        linkImage = sv.getImg();
        lblImage.setIcon(reSizeImgae(String.valueOf(linkImage)));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnChooseImgae = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        btnlogout = new javax.swing.JButton();
        ChangePassword = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fpoly2.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Student Manager");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        jLabel3.setText("ID Student :");

        jLabel4.setText("Full Name :");

        jLabel5.setText("Email :");

        jLabel6.setText("Phone :");

        jLabel7.setText("Gender : ");

        buttonGroup1.add(rdoMale);
        rdoMale.setSelected(true);
        rdoMale.setText("Male");

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        jLabel8.setText("Address :");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDFocusLost(evt);
            }
        });
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtEmail.setForeground(new java.awt.Color(0, 255, 0));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID)
                    .addComponent(txtName)
                    .addComponent(txtEmail)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPhone)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rdoMale)
                    .addComponent(rdoFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-icon.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearch);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save-icon.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Text-Edit-icon.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(btnUpdate);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/new-file-icon.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel3.add(btnNew);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exit-Close-icon.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete);

        btnChooseImgae.setText("Choose Image");
        btnChooseImgae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImgaeActionPerformed(evt);
            }
        });

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
        ));
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableList);

        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/inside-logout-icon.png"))); // NOI18N
        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        ChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Text-Edit-icon.png"))); // NOI18N
        ChangePassword.setText("Change Pasword");
        ChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePasswordActionPerformed(evt);
            }
        });

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageMD/anh.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(348, 348, 348))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ChangePassword)
                                .addGap(18, 18, 18)
                                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnChooseImgae, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnChooseImgae, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlogout)
                    .addComponent(ChangePassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseImgaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgaeActionPerformed
        try {
            JFileChooser f = new JFileChooser();
            f.setCurrentDirectory(new File("./src/image"));
            f.setDialogTitle("Open File Image");
            f.showOpenDialog(null);
            File fnameImage = f.getSelectedFile();
            linkImage = fnameImage.getAbsolutePath();
            lblImage.setIcon(reSizeImgae(String.valueOf(linkImage)));
            JOptionPane.showMessageDialog(this, "Add Image successful");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please choose Image");

        }
    }//GEN-LAST:event_btnChooseImgaeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (checkForm()) {
            txtEmailFocusLost(null);
            for (Student student : list) {
                if (student.getID().equals(txtID.getText())) {
                    JOptionPane.showMessageDialog(this, "Error : Same student");
                    return;
                }
            }
            sv = new Student();
            sv.setID(txtID.getText());
            sv.setName(txtName.getText());
            sv.setEmail(txtEmail.getText());
            sv.setPhone(txtPhone.getText());
            sv.setGender(chooseGender());
            sv.setAddress(txtAddress.getText());
            sv.setImg(LinkImage(linkImage));
            list.add(sv);
            JOptionPane.showMessageDialog(this, "Student save successfull");
            fillTable();
            reset();

        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        boolean check = false;
        if (!checkID(txtID)) {
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID().equals(txtID.getText())) {
                    list.remove(i);
                    JOptionPane.showMessageDialog(this, "Delete Succcessfull");
                    check = true;
                    fillTable();
                    reset();
                    break;
                }
            }
            if (check == false) {
                JOptionPane.showMessageDialog(this, "ID Student is not exists");
            }

        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        reset();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        locationMouse = tableList.getSelectedRow();
        tableList.setRowSelectionInterval(locationMouse, locationMouse);
        ShowForm(locationMouse);
    }//GEN-LAST:event_tableListMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        boolean check = false;
        if (!checkID(txtID)) {
            return;
        } else {
            for (Student svfind : list) {
                if (svfind.getID().equals(txtID.getText())) {
                    txtID.setText(svfind.getID());
                    txtName.setText(svfind.getName());
                    txtEmail.setText(svfind.getEmail());
                    txtPhone.setText(svfind.getPhone());
                    if (svfind.isGender()) {
                        rdoMale.setSelected(true);
                    } else {
                        rdoFemale.setSelected(false);
                    }
                    txtAddress.setText(svfind.getAddress());
                    linkImage = svfind.getImg();
                    lblImage.setIcon(reSizeImgae(String.valueOf(linkImage)));
                    check = true;

                }
            }
            if (check == false) {
                JOptionPane.showMessageDialog(this, " ID Student is not found");
            }
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        OtptionLogin optLogin = new OtptionLogin();
        optLogin.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        boolean check = false;
        if (!checkID(txtID)) {
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID().equals(txtID.getText())) {
                    if (checkForm()) {
                        sv = new Student();
                        sv.setID(txtID.getText());
                        sv.setName(txtName.getText());
                        sv.setEmail(txtEmail.getText());
                        sv.setPhone(txtPhone.getText());
                        sv.setGender(chooseGender());
                        sv.setAddress(txtAddress.getText());
                        sv.setImg(LinkImage(linkImage));
                        list.set(i, sv);
                        reset();
                        fillTable();
                        check = true;
                        JOptionPane.showMessageDialog(this, "Update succseefull");
                        break;
                    }
                }
            }
            if (check == false) {
                JOptionPane.showMessageDialog(this, "ID Student is not exists");
            }

        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void ChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePasswordActionPerformed
        ChangePassword change = new ChangePassword();
        change.setVisible(true);
    }//GEN-LAST:event_ChangePasswordActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      int  choose = JOptionPane.showConfirmDialog(this, "Save change before existing", "Notify", JOptionPane.YES_NO_OPTION);
      if(choose == JOptionPane.YES_OPTION){
          try {            
             dataList.InsertAllData(list);
              dataList.ExcutePro();
              JOptionPane.showMessageDialog(this, "Save data to SQL successfull");     
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    }//GEN-LAST:event_formWindowClosing

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        if (checkID(txtID)) {
            if (!txtName.getText().isEmpty()) {
                txtEmail.setText(WriterEmail(txtName.getText()));
                txtEmail.setEditable(false);
            }
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusLost
        // txtEmailFocusLost(null);
    }//GEN-LAST:event_txtIDFocusLost

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        //txtEmailFocusLost(null);
    }//GEN-LAST:event_txtNameFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

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
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangePassword;
    private javax.swing.JButton btnChooseImgae;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnlogout;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTable tableList;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
