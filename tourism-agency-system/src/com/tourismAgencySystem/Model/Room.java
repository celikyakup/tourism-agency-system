package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.View.RoomGUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private int hotel_id;
    private int hostel_type_id;
    private int season_id;
    private int bed_number;
    private String name;
    private String  room_feat;
    private Hotel hotel;
    private HostelType hostelType;
    private Season season;
    private String room_feat_str;

    public String getRoom_feat_str() {
        return room_feat_str;
    }

    public void setRoom_feat_str(String room_feat_str) {
        this.room_feat_str = room_feat_str;
    }
    public Room(){}
    public Room(int id, int hotel_id, int hostel_type_id, int season_id, String name, int bed_number, String room_feat) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hostel_type_id = hostel_type_id;
        this.season_id = season_id;
        this.name=name;
        this.bed_number = bed_number;
        this.room_feat = room_feat;
        this.hotel=Hotel.getFetch(hotel_id);
        this.hostelType=HostelType.getFetchByID(hostel_type_id);
        this.season=Season.getFetch(season_id);
        this.room_feat_str=String.join(",",room_feat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public String getRoom_feat() {
        return room_feat;
    }

    public void setRoom_feat(String room_feat) {
        this.room_feat = room_feat;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HostelType getHostelType() {
        return hostelType;
    }

    public void setHostelType(HostelType hostelType) {
        this.hostelType = hostelType;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
    public static ArrayList<Room> getlist(){
        ArrayList<Room> roomList=new ArrayList<>();
        String query="SELECT * FROM room";
        Room obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Room(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("hostel_type_id"),rs.getInt("season_id"),rs.getString("name"),rs.getInt("bed_number"),rs.getString("room_feat"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }
    public static ArrayList<Room> getList(int hotel_id){
        ArrayList<Room> hotelList=new ArrayList<>();

        String query=("SELECT * FROM room WHERE hotel_id="+hotel_id);
        Room obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Room(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("hostel_type_id"),rs.getInt("season_id"),rs.getString("name"),rs.getInt("bed_number"),rs.getString("room_feat"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static ArrayList<Room> getListbyHostel(int hostel_type_id){
        ArrayList<Room> hotelList=new ArrayList<>();

        String query=("SELECT * FROM room WHERE hostel_type_id="+hostel_type_id);
        Room obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Room(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("hostel_type_id"),rs.getInt("season_id"),rs.getString("name"),rs.getInt("bed_number"),rs.getString("room_feat"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static ArrayList<Room> getListbySeson(int season_id){
        ArrayList<Room> hotelList=new ArrayList<>();
        String query=("SELECT * FROM room WHERE season_id="+season_id);
        Room obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Room(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("hostel_type_id"),rs.getInt("season_id"),rs.getString("name"),rs.getInt("bed_number"),rs.getString("room_feat"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static Room getFetch(int id){
        Room obj=null;
        String sql="SELECT * FROM room WHERE id=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new Room(rs.getInt("id"),rs.getInt("hotel_id"),rs.getInt("hostel_type_id"),rs.getInt("season_id"),rs.getString("name"),rs.getInt("bed_number"),rs.getString("room_feat"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean add(int hotel_id,int hostel_type_id,int season_id,int bed_number,String name,String  room_feat){
        String query="INSERT INTO room (hotel_id,hostel_type_id,season_id,bed_number,name,room_feat) VALUES (?,?,?,?,?,?)";
        boolean key=true;
        String room_feat_str=String.join(",",room_feat);
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,hostel_type_id);
            pr.setInt(3,season_id);
            pr.setInt(4,bed_number);
            pr.setString(5,name);
            pr.setString(6,room_feat);
            key=pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static boolean delete(int id){
        String query="DELETE FROM room WHERE id=?";
        ArrayList<RoomPrice> roomList=RoomPrice.getListbyRoom(id);
        for (RoomPrice r:roomList){
            RoomPrice.delete(r.getId());
        }
        boolean key=true;
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            int response= pr.executeUpdate();
            if (response==-1){
                Helper.showMsg("error");
            }
            key=response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static void main(String[] args) {
        User ap=new User();
        RoomGUI room=new RoomGUI(ap);
    }
}
