package com.example.shrey.docs;

public class AddCenter {

    public String shop_name;
    public String shop_address;

    public AddCenter(String shop_name, String shop_address, String shop_pincode) {
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_pincode = shop_pincode;

    }

    public String shop_pincode;


    public AddCenter(){

    }


    public String getShop_name() {
        return shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }



    public String getShop_pincode() {
        return shop_pincode;
    }
}
