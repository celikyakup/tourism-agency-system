package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SearchHotelGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_book_loc;
    private JTextField fld_book_check_in;
    private JTextField fld_book_check_out;
    private JSpinner spn_adult;
    private JSpinner spn_child;
    private JButton btn_book_search;
    private JTable tbl_search_list;
    private JButton btn_logout;
    private DefaultTableModel mdl_search_list;
    private Object[] row_search_list;
    SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
    public Date check_in_date=null;
    public Date check_out_date=null;
    int numAdult;
    int numChild;
    private JPopupMenu bookingMenu;
    String select_room_price_id;
    String  select_row_adult_price;
    String  select_row_child_price;
    String location;
    private User ap;

    public SearchHotelGUI(User ap) {
        this.ap=ap;
        add(wrapper);
        setSize(1300,600);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        bookingMenu=new JPopupMenu();
        JMenuItem booking=new JMenuItem("Rezervasyon Yap");
        bookingMenu.add(booking);

        mdl_search_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0||column==1||column==2||column==3||column==4||column==5||column==6){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        booking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BookingGUI book=new BookingGUI(RoomPrice.getFetch(Integer.parseInt(select_room_price_id)),fld_book_check_in.getText(),fld_book_check_out.getText(),ap,select_row_child_price,select_row_adult_price,numAdult,numChild);
            }
        });
        Object[] col_search_list={"ID","Otel Adı","Otel Özellikleri","Otel tipi","Oda Adı","Yatak Sayısı","Yetişkin Fiyat","Çocuk Fiyat"};
        mdl_search_list.setColumnIdentifiers(col_search_list);
        row_search_list=new Object[col_search_list.length];

        tbl_search_list.setModel(mdl_search_list);
        tbl_search_list.setComponentPopupMenu(bookingMenu);
        tbl_search_list.getColumnModel().getColumn(2).setMinWidth(570);
        tbl_search_list.getColumnModel().getColumn(1).setMinWidth(150);
        tbl_search_list.getColumnModel().getColumn(3).setMinWidth(120);
        tbl_search_list.getTableHeader().setReorderingAllowed(false);


        tbl_search_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point point=e.getPoint();
                int selected_row=tbl_search_list.rowAtPoint(point);
                select_room_price_id=tbl_search_list.getValueAt(tbl_search_list.getSelectedRow(),0).toString();
                select_row_adult_price=tbl_search_list.getValueAt(tbl_search_list.getSelectedRow(),6).toString();
                select_row_child_price=tbl_search_list.getValueAt(tbl_search_list.getSelectedRow(),7).toString();
                tbl_search_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        btn_book_search.addActionListener(e -> {
            //Acente çalışanı, tarih aralığına, bölgeye veya otellere ve misafir bilgisine göre odaları
            //başarılı bir şekilde arayabiliyor
            location=fld_book_loc.getText();
            String check_in=fld_book_check_in.getText().trim();
            String check_out=fld_book_check_out.getText().trim();
            this.numAdult=Integer.parseInt(spn_adult.getValue().toString());
            this.numChild=Integer.parseInt(spn_child.getValue().toString());

            try {
                check_in_date=formatter.parse(check_in);
                check_out_date=formatter.parse(check_out);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
                Helper.showMsg("Giriş çıkış tarihini giriniz.");
            }
            if (numChild<=0 && numAdult<=0){
                Helper.showMsg("Kişi sayısını belirtiniz!!");
            }
            else loadSearchModel();
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI main=new MainGUI(ap);
            }
        });
    }
    public void loadSearchModel(){
        //Arama sonucuna uygun otellerin bilgileri (adres, yıldız, otel imkanları vb) ve odaların
        //bilgileri (giriş, çıkış tarihi, yetişkin, çocuk sayısı, yatak sayısı, varsa, mini bar, TV vb)
        //kullanıcıya gösteriliyor.
        DefaultTableModel clearModel=(DefaultTableModel) tbl_search_list.getModel();
        clearModel.setRowCount(0);
        String query=Hotel.searchQuery(location);
        for (SearchHotelItem item:SearchHotelItem.getList(Hotel.searchList(query))){
            for (Season season:Season.getListByHotelID(item.getHotel().getId())){
                String season_start= season.getStart_date();
                Date season_start_date=null;
                String season_end=season.getEnd_date();
                Date season_end_date=null;
                try {
                    season_start_date=formatter.parse(season_start);
                    season_end_date=formatter.parse(season_end);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }

                if (season_start_date != null && season_start_date.before(check_in_date) && season_end_date!=null && season_end_date.after(check_out_date)) {

                    for (RoomPrice r : RoomPrice.getListByHotel(season.getHotel_id(), season.getId())) {
                        if (r.getStock()>0 && (numChild+numAdult)<=r.getRoom().getBed_number()){
                            row_search_list[0] = r.getId();
                            row_search_list[1] = r.getHotel().getName();
                            row_search_list[2] = r.getHotel().getHotel_feat_str();
                            row_search_list[3] = r.getHt().getName();
                            row_search_list[4] = r.getRoom().getName();
                            row_search_list[5]=r.getRoom().getBed_number();
                            //5. Misafir bilgisi, kalınacak gece sayısı ve pansiyon tipine göre konaklamaya ait fiyat başarılı bir şekilde hesaplanıyor.
                            row_search_list[6] = (calculateRezDate(check_in_date, check_out_date)) * (r.getAdult_price()) * numAdult;
                            row_search_list[7] = (calculateRezDate(check_in_date, check_out_date)) * r.getChild_price() * numChild;
                            mdl_search_list.addRow(row_search_list);
                        }
                    }
                }
            }
        }
    }
    public static long calculateRezDate(Date checkIn,Date checkOut){
        long diff= checkOut.getTime()-checkIn.getTime();
        return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
    }

    private void createUIComponents() throws ParseException {
        this.fld_book_check_in=new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_book_check_out=new JFormattedTextField(new MaskFormatter("##/##/####"));
    }


}
