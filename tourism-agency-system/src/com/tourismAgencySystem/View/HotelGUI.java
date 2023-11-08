package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Helper.Item;
import com.tourismAgencySystem.Model.HostelType;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.Season;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
//import java.sql.Array;


public class HotelGUI extends JFrame{

    private JPanel wrapper;
    private JPanel pnl_top;
    private JTable tbl_hotel_list;
    private JLabel lbl_welcome;
    private JButton btn_hotel_add;
    private JButton btn_hotel_back;
    private JTabbedPane tabbedPane1;
    private JTable tbl_hostel_type_list;
    private JButton btn_add_hostel_type;
    private JComboBox cmb_add_hotel_name;
    private JComboBox cmb_add_hostel_type;
    private JButton btn_hostel_type_add;
    private JTable tbl_season_list;
    private JComboBox cmb_season_hotel_name;
    private JTextField fld_season_start_date;
    private JTextField fld_season_end_date;
    private JButton btn_season_add;
    private final User ap;
    private DefaultTableModel mdl_hotel_list;
    private Object [] row_hotel_list;
    private JPopupMenu hotelMenu;
    private DefaultTableModel mdl_hostel_type_list;
    private Object[] row_hostel_type_list;
    private DefaultTableModel mdl_season_list;
    private Object[] row_season_list;
    private JPopupMenu hostelTypeMenu;
    private JPopupMenu seasonMenu;

    public HotelGUI(User ap){
        this.ap=ap;
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin "+ap.getName());
        //ModelHotelList
        hotelMenu=new JPopupMenu();
        JMenuItem deleteMenu=new JMenuItem("sil");
        hotelMenu.add(deleteMenu);
        mdl_hotel_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
                    if (Hotel.delete(select_id)){
                        Helper.showMsg("done");
                        loadHotelModel();
                        loadSeasonModel();
                        loadHostelTypeModel();
                    }
                    else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        Object[] col_hotel_list={"ID","Otel Adı","Otel Adresi","İl","Ülke","Otel e-maili","Telefon Numarası","Otel yıldızı","Tesis Özellikleri"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list=new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.setComponentPopupMenu(hotelMenu);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        //##ModelHotelList

        //ModelHostelTypeList
        hostelTypeMenu=new JPopupMenu();
        JMenuItem delMenu=new JMenuItem("sil");
        hostelTypeMenu.add(delMenu);
        mdl_hostel_type_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        delMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt(tbl_hostel_type_list.getValueAt(tbl_hostel_type_list.getSelectedRow(),0).toString());
                    if (HostelType.delete(select_id)){
                        Helper.showMsg("done");
                        loadHostelTypeModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        Object[] col_hostel_type_list={"ID","Otel Adı","Pansiyon Tipi"};
        mdl_hostel_type_list.setColumnIdentifiers(col_hostel_type_list);
        row_hostel_type_list=new Object[col_hostel_type_list.length];
        loadHostelTypeModel();
        tbl_hostel_type_list.setModel(mdl_hostel_type_list);
        tbl_hostel_type_list.setComponentPopupMenu(hostelTypeMenu);
        tbl_hostel_type_list.getTableHeader().setReorderingAllowed(false);
        loadHotelNameCombo();
        //##ModelHostelTypeList

        //ModelSeasonList
        seasonMenu=new JPopupMenu();
        JMenuItem deleMenu=new JMenuItem("sil");
        seasonMenu.add(deleMenu);
        mdl_season_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        deleMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt(tbl_season_list.getValueAt(tbl_season_list.getSelectedRow(),0).toString());
                    if (Season.delete(select_id)){
                        Helper.showMsg("done");
                        loadSeasonModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        Object[] col_season_list={"ID","Otel Adı","Dönem Başlangıç Tarihi","Dönem Bitiş Tarihi"};
        mdl_season_list.setColumnIdentifiers(col_season_list);
        row_season_list=new Object[col_season_list.length];
        loadSeasonModel();
        tbl_season_list.setModel(mdl_season_list);
        tbl_season_list.setComponentPopupMenu(seasonMenu);
        loadSeasonHotelNameCombo();
        tbl_season_list.getTableHeader().setReorderingAllowed(false);
        //##ModelSeasonList

        btn_hotel_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI m=new MainGUI(ap);
            }
        });
        btn_hotel_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddHotelGUI add=new AddHotelGUI(ap);
            }
        });
        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point point=e.getPoint();
                int selected_row=tbl_hotel_list.rowAtPoint(point);
                tbl_hotel_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        btn_hostel_type_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item hotel=(Item) cmb_add_hotel_name.getSelectedItem();
                if (HostelType.add(hotel.getKey(),cmb_add_hostel_type.getSelectedItem().toString())){
                    Helper.showMsg("done");
                    loadHostelTypeModel();
                    loadHotelNameCombo();
                }
            }
        });

        tbl_hostel_type_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point point=e.getPoint();
                int select_row=tbl_hostel_type_list.rowAtPoint(point);
                tbl_hostel_type_list.setRowSelectionInterval(select_row,select_row);
            }
        });
        tbl_season_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point point=e.getPoint();
                int selected_row=tbl_season_list.rowAtPoint(point);
                tbl_season_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });
        btn_season_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item hotel=(Item) cmb_season_hotel_name.getSelectedItem();
                if (Season.add(hotel.getKey(),fld_season_start_date.getText(),fld_season_end_date.getText())){
                    Helper.showMsg("done");
                    loadHotelModel();
                    loadSeasonModel();
                    loadSeasonHotelNameCombo();
                }
            }
        });
    }
    public void loadHotelModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        for (Hotel obj:Hotel.getList()){

            row_hotel_list[0]=obj.getId();
            row_hotel_list[1]=obj.getName();
            row_hotel_list[2]=obj.getAddress();
            row_hotel_list[3]=obj.getCity();
            row_hotel_list[4]= obj.getCountry();
            row_hotel_list[5]=obj.getE_mail();
            row_hotel_list[6]=obj.getPhone_num();
            row_hotel_list[7]=obj.getStar();
            row_hotel_list[8]=obj.getHotel_feat_str();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }
    public void loadHostelTypeModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hostel_type_list.getModel();
        clearModel.setRowCount(0);
        for (HostelType obj:HostelType.getList()) {
            row_hostel_type_list[0] = obj.getId();
            row_hostel_type_list[1] = obj.getHotel().getName();
            row_hostel_type_list[2] = obj.getName();
            mdl_hostel_type_list.addRow(row_hostel_type_list);
        }
    }
    public void loadSeasonModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_season_list.getModel();
        clearModel.setRowCount(0);
        for (Season obj:Season.getList()){
            row_season_list[0]=obj.getId();
            row_season_list[1]=obj.getHotel().getName();
            row_season_list[2]=obj.getStart_date();
            row_season_list[3]=obj.getEnd_date();
            mdl_season_list.addRow(row_season_list);
        }
    }
    public void loadHotelNameCombo() {
        cmb_add_hotel_name.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_add_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadSeasonHotelNameCombo(){
        cmb_season_hotel_name.removeAllItems();
        for (Hotel obj: Hotel.getList()){
            cmb_season_hotel_name.addItem(new Item(obj.getId(),obj.getName()));
        }
    }

    private void createUIComponents() {
        try {
            this.fld_season_start_date=new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.fld_season_end_date=new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
