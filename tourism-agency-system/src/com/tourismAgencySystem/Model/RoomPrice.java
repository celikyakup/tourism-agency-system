package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomPrice {
    private int id;
    private int hotel_id;
    private int hostel_type_id;
    private int season_id;
    private int room_id;
    private int stock;
    private int adult_price;
    private int child_price;
    private Hotel hotel;
    private HostelType ht;
    private Season season;
    private Room room;
    public RoomPrice(){}
    public RoomPrice(int id, int hotel_id, int hostel_type_id, int season_id, int room_id, int stock, int adult_price, int child_price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hostel_type_id = hostel_type_id;
        this.season_id = season_id;
        this.room_id = room_id;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel = Hotel.getFetch(hotel_id);
        this.ht = HostelType.getFetchByID(hostel_type_id);
        this.season = Season.getFetch(season_id);
        this.room = Room.getFetch(room_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getHostel_type_id() {
        return hostel_type_id;
    }

    public void setHostel_type_id(int hostel_type_id) {
        this.hostel_type_id = hostel_type_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HostelType getHt() {
        return ht;
    }

    public void setHt(HostelType ht) {
        this.ht = ht;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public static ArrayList<RoomPrice> getList() {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price");
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static ArrayList<RoomPrice> getList(int hotel_id) {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price WHERE hotel_id="+hotel_id);
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static ArrayList<RoomPrice> getListbyHostel(int hostel_type_id) {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price WHERE hostel_type_id="+hostel_type_id);
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static ArrayList<RoomPrice> getListbyRoom(int room_id) {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price WHERE room_id="+room_id);
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static ArrayList<RoomPrice> getListByHotel(int hotel_id,int season_id) {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price WHERE hotel_id="+hotel_id+" and season_id="+season_id);
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static ArrayList<RoomPrice> getListBySeason(int season_id) {
        ArrayList<RoomPrice> roomPriceList = new ArrayList<>();
        RoomPrice obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM room_price WHERE season_id="+season_id);
            while (rs.next()) {
                obj = new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
                roomPriceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPriceList;
    }
    public static RoomPrice getFetch(int id){
        RoomPrice obj=null;
        String sql="SELECT * FROM room_price WHERE id=? ";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new RoomPrice(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("hostel_type_id"), rs.getInt("season_id"), rs.getInt("room_id"), rs.getInt("stock"), rs.getInt("adult_price"), rs.getInt("child_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(int hotel_id, int hostel_type_id, int season_id, int room_id, int stock, int adult_price, int child_price) {
        String query = "INSERT INTO room_price (hotel_id,hostel_type_id,season_id,room_id,stock,adult_price,child_price) VALUES (?,?,?,?,?,?,?)";
        boolean key = true;
        try {
            PreparedStatement pr = DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setInt(2, hostel_type_id);
            pr.setInt(3, season_id);
            pr.setInt(4, room_id);
            pr.setInt(5, stock);
            pr.setInt(6, adult_price);
            pr.setInt(7, child_price);
            key = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static boolean update(int id,int stock){
        String query="UPDATE room_price SET stock=? WHERE id=?";
        boolean key=true;
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,stock);
            pr.setInt(2,id);
            key=pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static boolean delete (int id){
        String query="DELETE FROM room_price WHERE id=?";
        ArrayList<Booking> bookList=Booking.getListByPriceID(id);
        for (Booking b:bookList){
            Booking.delete(b.getId());
        }
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
}
