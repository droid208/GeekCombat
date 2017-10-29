package com.example.manyamadan.steroids;

import java.util.List;
import java.util.Vector;

/**
 * Created by manyamadan on 17/07/16.
 */
public class GitModel {

    String mail;
    String user_id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;


    private static List<GitModel> orderList;


    public void setMail( String mail)
    {
        this.mail= mail;
    }

    public  void setUser_id( String user_id)
    {
        this.user_id= user_id;
    }





    public String getMail()
    {
        return mail;
    }

    public String getUser_id()
    {
        return user_id;
    }



    public static List<GitModel> getOrderList() {
        if(orderList== null) {
            orderList = new Vector<>();
        }

        return orderList;
    }


}
