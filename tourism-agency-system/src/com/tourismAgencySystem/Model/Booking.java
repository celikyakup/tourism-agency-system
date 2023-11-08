package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Queue;

public class Booking {
    private int id;
    private int room_price_id;
    private RoomPrice rPrice;
    private String name;
    private String phone_num;
    private String e_mail;
   private String book_note;
   private String book_check_in;
   private String book_check_out;
   private int price;
   private int guest;

    public Booking(int id, int room_price_id, String name, String phone_num, String e_mail, String book_note, String book_check_in, String book_check_out, int price, int guest) {
        this.id = id;
        this.room_price_id = room_price_id;
        this.rPrice = RoomPrice.getFetch(room_price_id);
        this.name = name;
        this.phone_num = phone_num;
        this.e_mail = e_mail;
        this.book_note = book_note;
        this.book_check_in = book_check_in;
        this.book_check_out = book_check_out;
        this.price = price;
        this.guest = guest;
    }

    public Booking(){
    }
    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getRoom_price_id() {
        return room_price_id;
    }

    public void setRoom_price_id(int room_price_id) {
        this.room_price_id = room_price_id;
    }

    public RoomPrice getrPrice() {
        return rPrice;
    }

    public void setrPrice(RoomPrice rPrice) {
        this.rPrice = rPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook_check_in(String book_check_in) {
        this.book_check_in = book_check_in;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getBook_note() {
        return book_note;
    }

    public void setBook_note(String book_note) {
        this.book_note = book_note;
    }

    public String getBook_check_in() {
        return book_check_in;
    }

    public void setBook_start_date(String book_check_in) {
        this.book_check_in = book_check_in;
    }

    public String getBook_check_out() {
        return book_check_out;
    }

    public void setBook_check_out(String book_check_out) {
        this.book_check_out = book_check_out;
    }
    public static boolean add(int room_price_id,String name,String phone_num,String e_mail,String book_note,String book_check_in,String book_check_out,int price,int guest){
        String query="INSERT INTO booking (room_price_id,name,phone,e_mail,book_note,book_check_in,book_check_out,price,guest) VALUES (?,?,?,?,?,?,?,?,?)";
        boolean key=true;
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,room_price_id);
            pr.setString(2,name);
            pr.setString(3,phone_num);
            pr.setString(4,e_mail);
            pr.setString(5,book_note);
            pr.setString(6,book_check_in);
            pr.setString(7,book_check_out);
            pr.setInt(8,price);
            pr.setInt(9,guest);
            int response=pr.executeUpdate();
            if (response==-1){
                Helper.showMsg("error");
            }
            key=response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static ArrayList<Booking> getList(){
        ArrayList<Booking> bookingList=new ArrayList<>();
        Booking obj;
        try {
            Statement st=DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM booking");
            while (rs.next()){
                obj=new Booking(rs.getInt("id"),rs.getInt("room_price_id"),rs.getString("name"),rs.getString("phone"),rs.getString("e_mail"),rs.getString("book_note"),rs.getString("book_check_in"),rs.getString("book_check_out"),rs.getInt("price"),rs.getInt("guest"));
                bookingList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }
    public static ArrayList<Booking> getListByPriceID(int room_price_id){
        ArrayList<Booking> bookingList=new ArrayList<>();
        Booking obj;
        try {
            Statement st=DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM booking WHERE room_price_id="+room_price_id);
            while (rs.next()){
                obj=new Booking(rs.getInt("id"),rs.getInt("room_price_id"),rs.getString("name"),rs.getString("phone"),rs.getString("e_mail"),rs.getString("book_note"),rs.getString("book_check_in"),rs.getString("book_check_out"),rs.getInt("price"),rs.getInt("guest"));
                bookingList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }
    public static boolean delete(int id){
        String query="DELETE FROM booking WHERE id=?";
        boolean key=true;
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            int response=pr.executeUpdate();
            if (response==-1){
                Helper.showMsg("error");
            }
            key=response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static Booking getFetch(int id){
        Booking obj=null;
        String query="SELECT * FROM booking WHERE id=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new Booking(rs.getInt("id"),rs.getInt("room_price_id"),rs.getString("name"),rs.getString("phone"),rs.getString("e_mail"),rs.getString("book_note"),rs.getString("book_check_in"),rs.getString("book_check_out"),rs.getInt("price"),rs.getInt("guest"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean update(int id,String name,String phone_num,String e_mail,String book_note){
        boolean key=true;
        String query="UPDATE booking SET name=? , phone=?, e_mail=?,book_note=? WHERE id=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,phone_num);
            pr.setString(3,e_mail);
            pr.setString(4,book_note);
            pr.setInt(5,id);
            key=pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
