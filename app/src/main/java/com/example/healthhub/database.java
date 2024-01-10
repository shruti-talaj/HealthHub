package com.example.healthhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query ="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(query);

        String query2 ="create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(query2);

        String query3 ="create table orderplace(username text,fullname text,address text,pincode text,contact text,price float,otype String)";
        sqLiteDatabase.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String email, String password){

        SQLiteDatabase db =getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put("username",username);
            cv.put("email",email);
            cv.put("password",password);
            db.insert("users",null,cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public int login(String username,String password){
        int result =0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username =? and password = ? ",str);
        if(c.moveToFirst()){
            result=1;
        }

        c.close();
        db.close();
        return result;
    }


    public void addcart(String username,String product,float price,String otype) {

        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("product",product);
            cv.put("price",price);
            cv.put("otype",otype);
            db.insert("cart",null,cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public int checkCart(String username,String product){
        int result =0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery("select *from cart where username = ? and product = ?",str);
        if(c.moveToFirst()){
            result=1;
        }

        c.close();
        db.close();
        return result;

    }


    public void removeCart(String username, String otype){
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.delete("cart","username=? and otype=?",str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    public ArrayList<String> getcartdata(String username,String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c =db.rawQuery("select * from cart where username=? and otype= ?",str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while(c.moveToNext());
        }

        c.close();
        db.close();
        return arr;
    }

    public void addorder(String username, String fullname,String address, String pincode, String contact, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("pincode",pincode);
        cv.put("contact",contact);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();

        try{
            db.insert("orderplace",null,cv);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();

    }


    public ArrayList<String> getorderdata(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try {

            String[] str = new String[1];
            str[0] = username;
            Cursor c = db.rawQuery("select * from orderplace where username=?" , str);
            if (c.moveToFirst()) {
                do {
                    arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6));

                } while (c.moveToNext());
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return arr;
    }

    public int checkappointexist(String username,String title,String fullname,String address,String contact){
        int result =0;
        String[] str = new String[5];
        str[0] = username;
        str[1] = title;
        str[2] = fullname;
        str[3] = address;
        str[4] = contact;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from appointment where username = ? and title=? and fullname =? and address = ? and contact =?",str);
        if(c.moveToFirst()){
            result =1;
        }
        c.close();
        db.close();
        return result;
    }




}

