package com.example.program2;

import java.util.Date;

public class GetSet {
    int TicketID, EventID, ParticipantID, TicketTypeID, Price;
    Date PurchaseDate;

    public int getTicketID() {
        return TicketID;
    }

    public int getEventID() {
        return EventID;
    }

    public int getParticipantID() {
        return ParticipantID;
    }

    public int getTicketTypeID() {
        return TicketTypeID;
    }

    public int getPrice() {
        return Price;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setTicketID(int ticketID) {
        TicketID = ticketID;
    }

    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public void setParticipantID(int participantID) {
        ParticipantID = participantID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        TicketTypeID = ticketTypeID;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public GetSet(int ticketID, int eventID, int participantID, int ticketTypeID, int price, Date purchaseDate) {
        TicketID = ticketID;
        EventID = eventID;
        ParticipantID = participantID;
        TicketTypeID = ticketTypeID;
        Price = price;
        PurchaseDate = purchaseDate;
    }
}