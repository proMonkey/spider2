package com.lmy.spider2.model;

public class Item {
    private String itemName;
    private String itemId;
    private String itemUrl;
    private String itemImg;
    private String itemPrice;
    private String itemShop;
    private String itemCommentNum;
    private String itemSelfShop;
    private String itemReduction;
    private String itemCoupon;
    private String itemOrigin;

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", itemImg='" + itemImg + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemShop='" + itemShop + '\'' +
                ", itemCommentNum='" + itemCommentNum + '\'' +
                ", itemSelfShop='" + itemSelfShop + '\'' +
                ", itemReduction='" + itemReduction + '\'' +
                ", itemCoupon='" + itemCoupon + '\'' +
                ", itemOrigin='" + itemOrigin + '\'' +
                '}';
    }

    public Item(String itemId, String itemName, String itemUrl, String itemImg, String itemPrice, String itemShop, String itemCommentNum, String itemSelfShop, String itemReduction, String itemCoupon, String itemOrigin) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.itemImg = itemImg;
        this.itemPrice = itemPrice;
        this.itemShop = itemShop;
        this.itemCommentNum = itemCommentNum;
        this.itemSelfShop = itemSelfShop;
        this.itemReduction = itemReduction;
        this.itemCoupon = itemCoupon;
        this.itemOrigin = itemOrigin;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemShop() {
        return itemShop;
    }

    public void setItemShop(String itemShop) {
        this.itemShop = itemShop;
    }

    public String getItemCommentNum() {
        return itemCommentNum;
    }

    public void setItemCommentNum(String itemCommentNum) {
        this.itemCommentNum = itemCommentNum;
    }

    public String getItemSelfShop() {
        return itemSelfShop;
    }

    public void setItemSelfShop(String itemSelfShop) {
        this.itemSelfShop = itemSelfShop;
    }

    public String getItemReduction() {
        return itemReduction;
    }

    public void setItemReduction(String itemReduction) {
        this.itemReduction = itemReduction;
    }

    public String getItemCoupon() {
        return itemCoupon;
    }

    public void setItemCoupon(String itemCoupon) {
        this.itemCoupon = itemCoupon;
    }

    public String getItemOrigin() {
        return itemOrigin;
    }

    public void setItemOrigin(String itemOrigin) {
        this.itemOrigin = itemOrigin;
    }
}
