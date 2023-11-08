package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class AddHotelGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JButton btn_hotel_add_logout;
    private JTextField fld_hotel_add_name;
    private JTextArea area_hotel_add_address;
    private JTextField fld_hotel_add_city;
    private JTextField fld_hotel_add_country;
    private JTextField fld_hotel_add_post;
    private JTextField fld_hotel_add_phone;
    private JComboBox cmb_hotel_add_hostel_type;
    private JCheckBox chck_free_park;
    private JCheckBox chck_7_24_service;
    private JCheckBox chck_free_wifi;
    private JCheckBox chck_spa;
    private JCheckBox chck_swimming_pool;
    private JCheckBox chck_fitness_center;
    private JCheckBox chck_hotel_concierge;
    private JComboBox cmb_hotel_add_star;
    private JButton btn_hotel_add;
    private final ArrayList<String> hotel_feat = new ArrayList<>();

    public AddHotelGUI(User ap){
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);

        btn_hotel_add.addActionListener(new ActionListener() {
            //Acente çalışanı anlaşmalı olduğu otelleri sisteme otel adı, adres, E-posta, telefon, yıldız,
            //tesis özellikleri, pansiyon tipleri bilgilerini girerek kaydedebiliyor.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_hotel_add_name)||Helper.isFldEmpty(fld_hotel_add_city)||Helper.isFldEmpty(fld_hotel_add_country)||Helper.isFldEmpty(fld_hotel_add_phone)||Helper.isFldEmpty(fld_hotel_add_post)){
                    Helper.showMsg("fill");
                }
                else {
                    String name=fld_hotel_add_name.getText();
                    String  city=fld_hotel_add_city.getText();
                    String country=fld_hotel_add_country.getText();
                    String phone=fld_hotel_add_phone.getText();
                    String mail=fld_hotel_add_post.getText();
                    String star=cmb_hotel_add_star.getSelectedItem().toString();
                    String address=area_hotel_add_address.getText();
                    System.out.println(hotel_feat);

                    if (Hotel.hotelAdd(name,address,city,country,mail,phone,star,hotel_feat)){
                        Helper.showMsg("done");
                    }
                    else {
                        Helper.showMsg("error");
                    }
                    dispose();
                    HotelGUI ho=new HotelGUI(ap);
                }
            }
        });
        chck_free_park.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_free_park.getText());
                }else if (e.getStateChange()==ItemEvent.DESELECTED){
                    hotel_feat.remove(chck_free_park.getText());
                }
            }
        });

        chck_swimming_pool.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_swimming_pool.getText());
                }else if (e.getStateChange()==ItemEvent.DESELECTED){
                    hotel_feat.remove(chck_swimming_pool.getText());
                }
            }
        });

        chck_fitness_center.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_fitness_center.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    hotel_feat.remove(chck_fitness_center.getText());
                }
            }
        });
        chck_free_wifi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_free_wifi.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    hotel_feat.remove(chck_free_wifi.getText());
                }
            }
        });
        chck_hotel_concierge.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_hotel_concierge.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    hotel_feat.remove(chck_hotel_concierge.getText());
                }
            }
        });
        chck_spa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_spa.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    hotel_feat.remove(chck_spa.getText());
                }
            }
        });
        chck_7_24_service.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    hotel_feat.add(chck_7_24_service.getText());
                } else if (e.getStateChange()==ItemEvent.DESELECTED) {
                    hotel_feat.remove(chck_7_24_service.getText());
                }
            }
        });
        btn_hotel_add_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HotelGUI ho=new HotelGUI(ap);
            }
        });
    }

}
