package com.example.swallow_app;

public class Card {
    private String cardName;
    private String cardNumber;
    private String imageUrl;
    private String owner;

    public Card(String cardName, String cardNumber, String imageUrl, String owner) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.imageUrl = imageUrl;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
