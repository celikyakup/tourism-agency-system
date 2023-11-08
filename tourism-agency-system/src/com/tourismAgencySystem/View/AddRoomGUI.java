package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Helper.Item;
import com.tourismAgencySystem.Model.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddRoomGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bot;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_hostel_type_name;
    private JComboBox cmb_season_start_date;
    private JComboBox cmb_season_end_date;
    private JTextField fld_room_name;
    private JButton btn_logout;
    private JTextField fld_room_bed_num;
    private JPanel pnl_chck_room;
    private JCheckBox chck_room_tv;
    private JCheckBox chck_room_mini;
    private JCheckBox chck_game_con;
    private JCheckBox chck_room_case;
    private JCheckBox chck_room_pro;
    private JButton btn_room_add;
    private final ArrayList<String> room_feat=new ArrayList<>();
    private User ap;

    public AddRoomGUI(User ap){
        this.ap=ap;
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);
        loadHotelCombe();
        loadHostelTypeCombo();
        loadSeasonStartDateCombo();
        loadSeasonEndDateCombo();
        btn_room_add.addActionListener(new ActionListener() {
            //Acenta çalışanı sisteme kayıtlı otellere oda ekleyebiliyor mu? Oda eklerken oda tipi
            //(Single, Double, Suit vb.), oda özellikleri (Yatak Sayısı, Televizyon, Minibar vb.) ve stok
            //miktarı girişi dikkate alınmış
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_room_bed_num)||Helper.isFldEmpty(fld_room_name)){
                    Helper.showMsg("fill");
                }
                else {
                    String name=fld_room_name.getText();
                    int bedNum= Integer.valueOf(fld_room_bed_num.getText().toString());
                    Item hotel=(Item) cmb_hotel_name.getSelectedItem();
                    Item hostel_type=(Item) cmb_hostel_type_name.getSelectedItem();
                    Item season=(Item) cmb_season_start_date.getSelectedItem();
                    if (Room.add(hotel.getKey(),hostel_type.getKey(),season.getKey(),bedNum,name,room_feat.toString())){
                        Helper.showMsg("done");
                    }
                    else {
                        Helper.showMsg("error");
                    }
                    dispose();
                    RoomGUI room=new RoomGUI(ap);
                }
            }
        });
        chck_game_con.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()== ItemEvent.SELECTED){
                    room_feat.add(chck_game_con.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    room_feat.remove(chck_game_con);
                }
            }
        });
        chck_room_case.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    room_feat.add(chck_room_case.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    room_feat.remove(chck_room_case.getText());
                }
            }
        });
        chck_room_mini.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    room_feat.add(chck_room_mini.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    room_feat.remove(chck_room_mini.getText());
                }
            }
        });
        chck_room_tv.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    room_feat.add(chck_room_tv.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    room_feat.remove(chck_room_tv.getText());
                }
            }
        });
        chck_room_pro.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    room_feat.add(chck_room_pro.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    room_feat.remove(chck_room_pro.getText());
                }
            }
        });


        cmb_hotel_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHostelTypeCombo();
                loadSeasonStartDateCombo();
                loadSeasonEndDateCombo();
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RoomGUI room=new RoomGUI(ap);
            }
        });
    }
    public void loadHotelCombe(){
        cmb_hotel_name.removeAllItems();
        for (Hotel obj:Hotel.getList()){
            cmb_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadHostelTypeCombo(){
        Item hotelItem=(Item) cmb_hotel_name.getSelectedItem();
        int hotelIntItem=hotelItem.getKey();
        cmb_hostel_type_name.removeAllItems();
        for (HostelType obj:HostelType.getListbyHotelID(hotelIntItem)){
            cmb_hostel_type_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadSeasonStartDateCombo(){
        Item hotelItem=(Item) cmb_hotel_name.getSelectedItem();
        int hotelIntItem=hotelItem.getKey();
        cmb_season_start_date.removeAllItems();
        for (Season obj:Season.getListByHotelID(hotelIntItem)){
            cmb_season_start_date.addItem(new Item(obj.getId(),obj.getStart_date()));
        }
    }
    public void loadSeasonEndDateCombo(){
        Item hotelItem=(Item) cmb_hotel_name.getSelectedItem();
        int hotelIntItem=hotelItem.getKey();
        cmb_season_end_date.removeAllItems();
        for (Season obj:Season.getListByHotelID(hotelIntItem)){
            cmb_season_end_date.addItem(new Item(obj.getId(),obj.getEnd_date()));
        }
    }

    public static void main(String[] args) {
        User ap=new User();
        AddRoomGUI a=new AddRoomGUI(ap);
    }
}
