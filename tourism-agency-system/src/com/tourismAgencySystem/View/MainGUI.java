package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_hotelGUI;
    private JButton btn_roomGUI;
    private JButton btn_searchGUI;
    private JButton btn_bookingGUI;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private User ap;
    public MainGUI(User ap){
        this.ap=ap;
        add(wrapper);
        setSize(300,300);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldin "+ ap.getName());
        btn_hotelGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HotelGUI h=new HotelGUI(ap);
            }
        });
        btn_roomGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RoomGUI r=new RoomGUI(ap);
            }
        });
        btn_searchGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SearchHotelGUI search=new SearchHotelGUI(ap);
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI s=new LoginGUI();

            }
        });
        btn_bookingGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BookingListGUI book=new BookingListGUI(ap);

            }
        });
    }
}
