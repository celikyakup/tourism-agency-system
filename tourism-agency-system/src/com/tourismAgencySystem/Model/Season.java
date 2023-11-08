package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {
    private int id;
    private int hotel_id;
    private String start_date;
    private String end_date;
    private Hotel hotel;

    public Season(int id, int hotel_id, String start_date, String end_date) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.hotel = Hotel.getFetch(hotel_id);
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<Season> getList() {
        ArrayList<Season> seasonList = new ArrayList<>();
        Season obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM season");
            while (rs.next()) {
                int id = rs.getInt("id");
                int hotel_id = rs.getInt("hotel_id");
                String startSeason = rs.getString("season_start_date");
                String endSeason = rs.getString("season_end_date");
                obj = new Season(id, hotel_id, startSeason, endSeason);
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static ArrayList<Season> getListByHotelID(int hotel_id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        Season obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM season WHERE hotel_id=" + hotel_id);
            while (rs.next()) {
                int id = rs.getInt("id");
                int hotelID = rs.getInt("hotel_id");
                String startSeason = rs.getString("season_start_date");
                String endSeason = rs.getString("season_end_date");
                obj = new Season(id, hotelID, startSeason, endSeason);
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }
    public static ArrayList<Season> getListByHotelID(int hotel_id,int id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        Season obj;
        try {
            Statement st = DBConnecter.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM season WHERE hotel_id=" + hotel_id+"and id="+id);
            while (rs.next()) {
                int season_id = rs.getInt("id");
                int hotelID = rs.getInt("hotel_id");
                String startSeason = rs.getString("season_start_date");
                String endSeason = rs.getString("season_end_date");
                obj = new Season(season_id, hotelID, startSeason, endSeason);
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static Season getFetch(int id) {
        Season obj = null;
        String sql = "SELECT * FROM season WHERE id=? ";
        try {
            PreparedStatement pr = DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Season(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("season_start_date"), rs.getString("season_end_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Season getFetchbySeason(int id,String start_date,String end_date) {
        Season obj = null;
        String sql = "SELECT * FROM season WHERE id=? and season_start_date=? and season_end_date=? ";
        try {
            PreparedStatement pr = DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1, id);
            pr.setString(2,start_date);
            pr.setString(3,end_date);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Season(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("season_start_date"), rs.getString("season_end_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean add(int hotel_id, String start_date, String end_date) {
        String query = "INSERT INTO season (hotel_id,season_start_date,season_end_date) VALUES (?,?,?)";
        boolean key = true;
        Season find=getFetchbySeason(hotel_id,start_date,end_date);
        if (find!=null){
            Helper.showMsg("Bu otele ait sezon aralığı önceden girilmiş !");
            return false;
        }

        try {
            PreparedStatement pr = DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, start_date);
            pr.setString(3, end_date);
            key = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static boolean delete(int id) {
        boolean key = true;
        String query = "DELETE FROM season WHERE id=?";
        ArrayList<Room> roomList=Room.getListbySeson(id);
        ArrayList<RoomPrice> rPriceList=RoomPrice.getListBySeason(id);
        for (Room r:roomList){
            Room.delete(r.getId());
        }
        for (RoomPrice room:rPriceList){
            RoomPrice.delete(room.getId());
        }
        try {
            PreparedStatement pr = DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            key = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}

