package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Booking;
import com.tourismAgencySystem.Model.Hotel;
import com.tourismAgencySystem.Model.RoomPrice;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingListGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JTable tbl_booking_list;
    private JButton btn_logout;
    private User ap;
    private DefaultTableModel mdl_booking_list;
    private Object[] row_booking_list;
    private JPopupMenu bookingListMenu;
    private Booking book;

    public BookingListGUI(User ap) {
        this.ap = ap;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle(Config.PROJECT_TITTLE);
        lbl_welcome.setText("Hoşgeldin " + ap.getName());

        bookingListMenu=new JPopupMenu();
        JMenuItem deletemenu=new JMenuItem("sil");
        bookingListMenu.add(deletemenu);
        mdl_booking_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column==5 ||column==6) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        deletemenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt(tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),0).toString());
                    //Rezervasyon işlemi silindiği zaman oda azaltılan oda stoğu tekrar eski haline getiriliyor.
                    int roomPriceID=Booking.getFetch(select_id).getRoom_price_id();
                    int stock=RoomPrice.getFetch(roomPriceID).getStock()+1;
                    if (Booking.delete(select_id)){
                        Helper.showMsg("done");
                        RoomPrice.update(roomPriceID,stock);
                        loadBookingModel();
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        Object[] col_booking_list = {"ID", "İsim", "Telefon", "E-posta", "Rezervasyon Notu", "Başlangıç Tarihi", "Bitiş Tarihi","Fiyat","Misafir Sayısı"};
        mdl_booking_list.setColumnIdentifiers(col_booking_list);
        row_booking_list = new Object[col_booking_list.length];
        loadBookingModel();
        tbl_booking_list.setModel(mdl_booking_list);
        tbl_booking_list.setComponentPopupMenu(bookingListMenu);
        tbl_booking_list.getTableHeader().setReorderingAllowed(false);
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI main = new MainGUI(ap);
            }
        });
        tbl_booking_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point point=e.getPoint();
                int selected_row=tbl_booking_list.rowAtPoint(point);
                tbl_booking_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });
        tbl_booking_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType()==TableModelEvent.UPDATE){
                    int book_id=Integer.parseInt(tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),0).toString());
                    String name=tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),1).toString();
                    String phone=tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),2).toString();
                    String e_mail=tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),3).toString();
                    String bookNote=tbl_booking_list.getValueAt(tbl_booking_list.getSelectedRow(),4).toString();
                    if (Booking.update(book_id,name,phone,e_mail,bookNote)){
                        Helper.showMsg("done");
                        loadBookingModel();
                    }
                }
            }
        });
    }

    public void loadBookingModel() {
        //Acente çalışanları, sistem üzerinden yapılan rezervasyonları başarılı bir şekilde listeleyebiliyor.
        DefaultTableModel clearModel = (DefaultTableModel) tbl_booking_list.getModel();
        clearModel.setRowCount(0);
        for (Booking obj : Booking.getList()) {
            row_booking_list[0] = obj.getId();
            row_booking_list[1] = obj.getName();
            row_booking_list[2] = obj.getPhone_num();
            row_booking_list[3] = obj.getE_mail();
            row_booking_list[4] = obj.getBook_note();
            row_booking_list[5] = obj.getBook_check_in();
            row_booking_list[6] = obj.getBook_check_out();
            row_booking_list[7]=obj.getPrice();
            row_booking_list[8]=obj.getGuest();
            mdl_booking_list.addRow(row_booking_list);
        }
    }
}