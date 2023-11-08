package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Admin;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private final Admin admin;
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_user_list;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private JPopupMenu userMenu;
    public AdminGUI(Admin admin){
        this.admin=admin;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin "+admin.getName());
        userMenu=new JPopupMenu();
        JMenuItem deleteMenu=new JMenuItem("sil");
        userMenu.add(deleteMenu);
        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                    if (User.delete(select_id)){
                        Helper.showMsg("done");
                        loadUserModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        mdl_user_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        Object[] col_user_list={"ID","Ad Soyad","Kullanıcı Adı","Şifre","Kullanıcı Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list=new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.setComponentPopupMenu(userMenu);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType()==TableModelEvent.UPDATE){
                    int user_id=Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                    String user_name=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                    String user_uname=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                    String user_password=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                    String user_type=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();
                    if (User.update(user_id,user_name,user_uname,user_password,user_type)){
                        Helper.showMsg("done");
                        loadUserModel();
                    }
                    loadUserModel();
                }
            }
        });
        btn_user_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_user_name) || Helper.isFldEmpty(fld_user_uname)|| Helper.isFldEmpty(fld_user_pass)){
                    Helper.showMsg("fill");
                }else {
                    String name=fld_user_name.getText();
                    String uname=fld_user_uname.getText();
                    String password=fld_user_pass.getText();
                    String type=cmb_user_type.getSelectedItem().toString();
                    if (User.add(name,uname,password,type)){
                        Helper.showMsg("done");
                        loadUserModel();
                        fld_user_name.setText(null);
                        fld_user_uname.setText(null);
                        fld_user_pass.setText(null);
                    }
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI log=new LoginGUI();
            }
        });
    }
    public void loadUserModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj: User.getList()){
            row_user_list[0]=obj.getId();
            row_user_list[1]=obj.getName();
            row_user_list[2]=obj.getUname();
            row_user_list[3]=obj.getPassword();
            row_user_list[4]=obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }
}
