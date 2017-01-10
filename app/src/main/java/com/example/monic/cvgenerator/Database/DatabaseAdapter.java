package com.example.monic.cvgenerator.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monic.cvgenerator.Classes.Profile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DatabaseAdapter(Context context) {
        this.context = context;
        helper=new DatabaseHelper(context,"cvDB",null,1);
    }

    public void openConnection(){

        db=helper.getWritableDatabase();
    }

    public void closeConnection(){

        helper.close();
    }

    public void insertUser(String firstName, String lastName){
        ContentValues contentValues=new ContentValues();
        contentValues.put("firstName",firstName);
        contentValues.put("lastName",lastName);
        db.insert("users",null,contentValues);
    }

    public List<String> getUsers(){
        List<String> usersList=new ArrayList<>();
        Cursor cursor=db.query("users",new String[] {"firstName","lastName"},null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            do {
                String user = cursor.getString(0)+" ; "+ cursor.getString(1);
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }

    public void deleteUsers(){
        db.execSQL("DELETE FROM users;");
    }

    public Integer getUserIdByNames(String firstName,String lastName){
        Cursor cursor=db.rawQuery("Select id from users where firstName='"+firstName+"' and lastName='"+lastName+"';",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){   //daca exista user-ul in baza de date
            return cursor.getInt(0);
        }
        return null;
    }

    public String getCV(int userId){
        String cv=null;
        Cursor cursor=db.rawQuery("Select cvText from cvs where userId='"+userId+"';",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            return cursor.getString(0);
        }
        return null;
    }

    public void insertCV(String cvText, int userId){
        ContentValues contentValues=new ContentValues();
        contentValues.put("cvText",cvText);
        contentValues.put("userId",userId);
        db.insert("cvs",null,contentValues);
    }

    public void deleteAllCvsOfAUser(int userId){
        db.execSQL("DELETE FROM cvs WHERE userId"+"='"+userId+"';");
    }

}
