package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Room;
import com.tourismAgencySystem.Model.RoomPrice;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomGUI extends JFrame{
    private JPanel pnl_top;
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_room_list;
    private JButton btn_add_room;
    private JTabbedPane tabbedPane1;
    private JTable tbl_room_price_list;
    private JButton btn_room_price_add;
    private User ap;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private JPopupMenu roomMenu;
    private JPopupMenu roomPriceMenu;
    private DefaultTableModel mdl_room_price_list;
    private Object[] row_room_price_list;
    public RoomGUI(User ap){
        this.ap=ap;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Hoşşgeldin "+ ap.getName());
        //RoomModelList
        roomMenu=new JPopupMenu();
        JMenuItem deleteMenu=new JMenuItem("sil");
        roomMenu.add(deleteMenu);
        mdl_room_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        Object[] col_room_list={"ID","Otel Adı","Pansiyon Tipi","Sezon Başlangıcı","Sezon Bitişi","Oda Adı","Yatak Sayısı","Oda Özellikleri"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list=new Object[col_room_list.length];
        loadRoomModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.setComponentPopupMenu(roomMenu);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(100);
        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int selected_id=Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
                    if (Room.delete(selected_id)){
                        Helper.showMsg("done");
                        loadRoomModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        //##RoomModelList

        //RoomPriceModelList
        roomPriceMenu=new JPopupMenu();
        JMenuItem delMenu=new JMenuItem("sil");
        roomPriceMenu.add(delMenu);
        mdl_room_price_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0 && column==1 && column==2 &&column==3 && column==4 && column==5){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        Object[] col_room_price_list={"ID","Otel Adı","Pansiyon Tipi","Sezon Başlangıcı","Sezon Bitişi","Oda Adı","Yatak Sayısı","Stok","Yetişkin Fiyat","Çocuk Fiyat"};
        mdl_room_price_list.setColumnIdentifiers(col_room_price_list);
        row_room_price_list=new Object[col_room_price_list.length];
        loadRoomPriceModel();
        tbl_room_price_list.setModel(mdl_room_price_list);
        tbl_room_price_list.setComponentPopupMenu(roomPriceMenu);
        tbl_room_price_list.getTableHeader().setReorderingAllowed(false);
        delMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int selected_id=Integer.parseInt(tbl_room_price_list.getValueAt(tbl_room_price_list.getSelectedRow(),0).toString());
                    if (RoomPrice.delete(selected_id)){
                        Helper.showMsg("done");
                        loadRoomPriceModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        //##RoomPriceModelList
        btn_add_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomGUI add=new AddRoomGUI(ap);
                dispose();
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI m=new MainGUI(ap);
                dispose();
            }
        });
        btn_room_price_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomPriceGUI add=new AddRoomPriceGUI(ap);
                dispose();
            }
        });
    }
    private void loadRoomModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        for (Room obj:Room.getlist()){
            row_room_list[0]=obj.getId();
            row_room_list[1]=obj.getHotel().getName();
            row_room_list[2]=obj.getHostelType().getName();
            row_room_list[3]=obj.getSeason().getStart_date();
            row_room_list[4]=obj.getSeason().getEnd_date();
            row_room_list[5]=obj.getName();
            row_room_list[6]=obj.getBed_number();
            row_room_list[7]=obj.getRoom_feat();
            mdl_room_list.addRow(row_room_list);
        }
    }
    private void loadRoomPriceModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_room_price_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (RoomPrice obj:RoomPrice.getList()){
            i=0;
            row_room_price_list[i++]=obj.getRoom_id();
            row_room_price_list[i++]=obj.getHotel().getName();
            row_room_price_list[i++]=obj.getHt().getName();
            row_room_price_list[i++]=obj.getSeason().getStart_date();
            row_room_price_list[i++]=obj.getSeason().getEnd_date();
            row_room_price_list[i++]=obj.getRoom().getName();
            row_room_price_list[i++]=obj.getRoom().getBed_number();
            row_room_price_list[i++]=obj.getStock();
            row_room_price_list[i++]=obj.getAdult_price();
            row_room_price_list[i++]=obj.getChild_price();
            mdl_room_price_list.addRow(row_room_price_list);
        }
    }
}
