package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Booking;
import com.tourismAgencySystem.Model.RoomPrice;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingGUI extends JFrame {

    private JLabel lbl_room_name;
    private JTextField fld_room_feat;
    private JTextField fld_hotel_feat;
    private JTextField fld_book_note;
    private JTextField fld_book_name;
    private JTextField fld_book_phone;
    private JTextField fld_book_email;
    private JPanel wrapper;
    private JButton btn_booking_add;
    private JLabel lbl_book_start_date;
    private JLabel lbl_book_finish_date;
    private JLabel lbl_stock;
    private JButton btn_logout;
    private JLabel lbl_price;
    private JLabel lbl_star;
    private RoomPrice roomPrice;
    private User ap;

    public BookingGUI(RoomPrice selected_room, String start_date, String end_date,User ap,String priceChild,String priceAdult,int numAdult,int numChild) {
        this.ap=ap;
        this.roomPrice = selected_room;
        add(wrapper);
        setSize(600, 500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setResizable(false);
        setVisible(true);
        //Acenta çalışanı müşterinin talebine uygun odayı müşteri bilgilerini girerek başarılı şekilde rezervasyon yapabiliyor.
        lbl_room_name.setText(this.roomPrice.getHotel().getName()+" / "+this.roomPrice.getRoom().getName()+" / "+this.roomPrice.getHt().getName()+" / "+this.roomPrice.getHotel().getStar());
        String roomFeat=this.roomPrice.getRoom().getRoom_feat().replace("["," ");
        fld_room_feat.setText(roomFeat.replace("]"," "));
        fld_hotel_feat.setText(this.roomPrice.getHotel().getHotel_feat_str());
        lbl_book_start_date.setText("Başlangıç Tarihi: "+start_date);
        lbl_book_finish_date.setText("Bitiş Tarihi: "+end_date);
        lbl_stock.setText("Stok: "+this.roomPrice.getStock());
        int price_adult=Integer.parseInt(priceAdult);
        int price_child=Integer.parseInt(priceChild);
        lbl_price.setText("Fiyat: "+String.valueOf(price_child+price_adult));
        btn_booking_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_book_name)||Helper.isFldEmpty(fld_book_phone)||Helper.isFldEmpty(fld_book_email)){
                    Helper.showMsg("fill");
                }else {
                    String name=fld_book_name.getText();
                    String phone=fld_book_phone.getText();
                    String e_mail=fld_book_email.getText();
                    String note=fld_book_note.getText();
                    int price=price_adult+price_child;
                    int guest=numAdult+numChild;
                    System.out.println(guest);

                    if (Booking.add(roomPrice.getId(),name,phone,e_mail,note,start_date,end_date,price,guest)){
                        Helper.showMsg("done");
                       // Rezervasyon yapılan odanın stoğu azalıyor
                        int stock=roomPrice.getStock()-1;
                        RoomPrice.update(roomPrice.getId(), stock);

                    }
                }
                dispose();
                SearchHotelGUI search=new SearchHotelGUI(ap);
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SearchHotelGUI search=new SearchHotelGUI(ap);
            }
        });
    }
}


