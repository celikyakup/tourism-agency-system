package com.tourismAgencySystem.Model;

import java.util.ArrayList;

public class SearchHotelItem {
    private Hotel hotel;
    private ArrayList<HostelType> hostelType;
    private ArrayList<Season> season;
    private ArrayList<Room> room;
    private ArrayList<RoomPrice> roomPrice;

    public SearchHotelItem(){

    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public ArrayList<HostelType> getHostelType() {
        return hostelType;
    }

    public void setHostelType(ArrayList<HostelType> hostelType) {
        this.hostelType = hostelType;
    }

    public ArrayList<Season> getSeason() {
        return season;
    }

    public void setSeason(ArrayList<Season> season) {
        this.season = season;
    }

    public ArrayList<Room> getRoom() {
        return room;
    }

    public void setRoom(ArrayList<Room> room) {
        this.room = room;
    }

    public ArrayList<RoomPrice> getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(ArrayList<RoomPrice> roomPrice) {
        this.roomPrice = roomPrice;
    }

    public static ArrayList<SearchHotelItem> getList(ArrayList<Hotel> hotelList){
        ArrayList<SearchHotelItem> searchHotelItems=new ArrayList<>();

        for (Hotel h:hotelList){
            SearchHotelItem obj=new SearchHotelItem();
            obj.setHotel(h);
            obj.setHostelType(HostelType.getListbyHotelID(h.getId()));
            obj.setSeason(Season.getListByHotelID(h.getId()));
            obj.setRoom(Room.getList(h.getId()));
            obj.setRoomPrice(RoomPrice.getList(h.getId()));
            searchHotelItems.add(obj);
        }
        return searchHotelItems;
    }
}
