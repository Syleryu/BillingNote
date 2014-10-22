package com.axybn.billingnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BillingNoteDB extends SQLiteOpenHelper{
    
    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "User";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String SEX = "sex";
    
    

    public BillingNoteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    
//    public static class Flow {
//        public static final Date Date = new Da 
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME +" TEXT DEFAULT \"\","+SEX+" TEXT DEFAULT \"\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }

}
