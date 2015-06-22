package com.example.zumoappname;

/**
 * Created by kdandang on 5/19/2015.
 */
public class UserIdentificationDetails {

    /**
     * Table Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * User's UserId
     */
    @com.google.gson.annotations.SerializedName("userid")
    private String mUserId;

    /**
     * User FirstName
     */
    @com.google.gson.annotations.SerializedName("firstname")
    private String mFirstName;

    /**
     * User LastName
     */
    @com.google.gson.annotations.SerializedName("lastname")
    private String mLastName;

    /**
     * User friends
     */
    @com.google.gson.annotations.SerializedName("userid")
    private String mFriend;

    public UserIdentificationDetails(){

    }

    public UserIdentificationDetails(String userId, String userName){
        this.mUserId = userId;
        this.mFirstName = userName;
    }

    public String getName(){return mFirstName;}

    public final void setName(String name) {mFirstName = name;}

    public String getUserId(){return mUserId;}

    public final void setUserId(String userId) {mUserId = userId;}

}
