package com.tourismAgencySystem.Model;

import com.tourismAgencySystem.Helper.DBConnecter;
import com.tourismAgencySystem.Helper.Helper;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String e_mail;
    private String phone_num;
    private String star;
    private ArrayList<String> hotel_feat;
    private String hotel_feat_str;
    public Hotel(){}

    public Hotel(int id, String name, String address,String city,String country, String e_mail, String phone_num, String star, ArrayList<String > hotel_feat) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city=city;
        this.country=country;
        this.e_mail = e_mail;
        this.phone_num = phone_num;
        this.star = star;
        this.hotel_feat = hotel_feat;
        hotel_feat_str= String.join( ",",hotel_feat);
    }
    public String getHotel_feat_str() {
        return hotel_feat_str;
    }

    public void setHotel_feat_str(String hotel_feat_str) {
        this.hotel_feat_str = hotel_feat_str;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }



    public void setHotel_feat(ArrayList<String > hotel_feat) {
        this.hotel_feat = hotel_feat;
    }

    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelList=new ArrayList<>();

        String query="SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement st= DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setCountry(rs.getString("country"));
                obj.setE_mail(rs.getString("e_mail"));
                obj.setPhone_num(rs.getString("phone_num"));
                obj.setStar(rs.getString("star"));
                obj.setHotel_feat_str(rs.getString("hotel_feat"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static Hotel getFetch(String name){
        Hotel obj=null;
        String sql="SELECT * FROM hotel WHERE name=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setString(1,name);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setCountry(rs.getString("country"));
                obj.setE_mail(rs.getString("e_mail"));
                obj.setPhone_num(rs.getString("phone_num"));
                obj.setStar(rs.getString("star"));
                obj.setHotel_feat_str(rs.getString("hotel_feat"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static Hotel getFetch(int id){
        Hotel obj=null;
        String sql="SELECT * FROM hotel WHERE id=?";
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj=new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setCountry(rs.getString("country"));
                obj.setE_mail(rs.getString("e_mail"));
                obj.setPhone_num(rs.getString("phone_num"));
                obj.setStar(rs.getString("star"));
                obj.setHotel_feat_str(rs.getString("hotel_feat"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean hotelAdd(String name,String address,String city,String country,String e_mail,String phone_num,String star,ArrayList<String>hotel_feat){
        String query="INSERT INTO hotel (name,address,city,country,e_mail,phone_num,star,hotel_feat) VALUES (?,?,?,?,?,?,?,?)";
        String hotel_featString = String.join(",",hotel_feat);
        boolean key=true;
        Hotel findHotel=Hotel.getFetch(name);
        if (findHotel!=null){
            Helper.showMsg("Bu otel daha önce eklenmiş.");
            return false;
        }
        try {
            PreparedStatement pr=DBConnecter.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,address);
            pr.setString(3,city);
            pr.setString(4,country);
            pr.setString(5,e_mail);
            pr.setString(6,phone_num);
            pr.setString(7,star);
            pr.setString(8,hotel_featString);

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
    public static boolean delete(int id){
        String query="DELETE FROM hotel WHERE id=?";
        ArrayList<HostelType> hostelTypeList=HostelType.getListbyHotelID(id);
        ArrayList<Season> seasonList=Season.getListByHotelID(id);
        ArrayList<Room> roomList=Room.getList(id);
        ArrayList<RoomPrice> rPriceList=RoomPrice.getList(id);
        for (HostelType h:hostelTypeList){
            HostelType.delete(h.getId());
        }
        for (Season s:seasonList){
            Season.delete(s.getId());
        }
        for (Room r:roomList){
            Room.delete(r.getId());
        }
        for (RoomPrice room:rPriceList){
            RoomPrice.delete(room.getId());
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
    public static String searchQuery(String location){
        String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' OR city LIKE '%{{city}}%' OR country LIKE '%{{region}}%'";
        query = query.replace("{{name}}",location);
        query = query.replace("{{city}}",location);
        query = query.replace("{{region}}",location);
        return query;
    }
    public static ArrayList<Hotel> searchList(String query){
        ArrayList<Hotel> hotelList=new ArrayList<>();
        Hotel obj;
        try {
            Statement st=DBConnecter.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                obj=new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setCountry(rs.getString("country"));
                obj.setE_mail(rs.getString("e_mail"));
                obj.setPhone_num(rs.getString("phone_num"));
                obj.setStar(rs.getString("star"));
                obj.setHotel_feat_str(rs.getString("hotel_feat"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

}
