package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HostelType {
    private int id;
    private int hotel_id;
    private String name;
    private Hotel hotel;


    public HostelType(int id, int hotel_id, String name) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.name = name;
        this.hotel= Hotel.getFetch(hotel_id);
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

    public String  getName() {
        return name;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<HostelType> getList(){
        ArrayList<HostelType> hostelTypeList=new ArrayList<>();
        String query="SELECT * FROM hostel_type";
        HostelType obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new HostelType(rs.getInt("id"),rs.getInt("hotel_id"),rs.getString("name"));
                hostelTypeList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }
    public static ArrayList<HostelType> getListbyHotelID(int hotel_id){
        ArrayList<HostelType> hostelTypeList=new ArrayList<>();
        HostelType obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM hostel_type WHERE hotel_id="+hotel_id);
            while (rs.next()){
                obj=new HostelType(rs.getInt("id"),rs.getInt("hotel_id"),rs.getString("name"));
                hostelTypeList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }
    public static HostelType getFetch(int hotel_id,String name){
        HostelType obj=null;
        String sql="SELECT * FROM hostel_type WHERE hotel_id=? and name=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            pr.setString(2,name);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new HostelType(rs.getInt("id"),rs.getInt("hotel_id"),rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static HostelType getFetchByID(int id){
        HostelType obj=null;
        String sql="SELECT * FROM hostel_type WHERE id=? ";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new HostelType(rs.getInt("id"),rs.getInt("hotel_id"),rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(int hotel_id,String name){
        String query="INSERT INTO hostel_type (hotel_id,name) VALUES (?,?)";
        boolean key=true;
        HostelType find=getFetch(hotel_id,name);

        if (find!=null){
            Helper.showMsg("Bu otele bu pansiyon tipi daha önce eklenmiş");
            return false;
        }
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setString(2,name);
            key=pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
    public static boolean delete(int id){
        boolean key=true;
        String query="DELETE FROM hostel_type WHERE id=?";
        ArrayList<Room> roomList=Room.getListbyHostel(id);
        ArrayList<RoomPrice> rPriceList=RoomPrice.getListbyHostel(id);
        for (Room r:roomList){
            Room.delete(r.getId());
        }
        for (RoomPrice room:rPriceList){
            RoomPrice.delete(room.getId());
        }
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            key=pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
