package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Helper.Item;
import com.tourismAgencySystem.Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomPriceGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_bot;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_hostel_type_name;
    private JComboBox cmb_season_start_date;
    private JComboBox cmb_season_end_date;
    private JPanel pnl_chck_room;
    private JButton btn_room_add;
    private JComboBox cmb_room_name;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_stock_add;
    private User ap;
    public AddRoomPriceGUI(User ap){
        this.ap=ap;
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);
        loadHotelCombe();
        loadHostelTypeCombo();
        loadSeasonStartDateCombo();
        loadSeasonEndDateCombo();
        loadRoomCombo();
        cmb_hotel_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHostelTypeCombo();
                loadSeasonStartDateCombo();
                loadSeasonEndDateCombo();
                loadRoomCombo();
            }
        });
        btn_room_add.addActionListener(new ActionListener() {
            //Otellerin fiyatlandırılmasında dönem yönetimi yapılması.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_adult_price)|| Helper.isFldEmpty(fld_child_price)){
                    Helper.showMsg("fill");
                }else {
                    Item hotel=(Item) cmb_hotel_name.getSelectedItem();
                    Item hostel_type=(Item) cmb_hostel_type_name.getSelectedItem();
                    Item season=(Item) cmb_season_start_date.getSelectedItem();
                    Item room=(Item) cmb_room_name.getSelectedItem();
                    int adultPrice=Integer.valueOf(fld_adult_price.getText().toString());
                    int childPrice=Integer.valueOf(fld_child_price.getText().toString());
                    int stock=Integer.valueOf(fld_stock_add.getText().toString());
                    if (RoomPrice.add(hotel.getKey(),hostel_type.getKey(),season.getKey(),room.getKey(),stock,adultPrice,childPrice)){
                        Helper.showMsg("done");
                    }
                    else {
                        Helper.showMsg("error");
                    }
                    dispose();
                    RoomGUI r=new RoomGUI(ap);
                }
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
    public void loadRoomCombo(){
        Item hotelItem=(Item) cmb_hotel_name.getSelectedItem();
        int hotelIntItem=hotelItem.getKey();
        cmb_room_name.removeAllItems();
        for (Room obj:Room.getList(hotelIntItem)){
            cmb_room_name.addItem(new Item(obj.getId(),obj.getName()));
        }
    }
}
