package com.example.prem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


import com.example.prem.model.MessageModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.prem.database.DatabaseManager.MessageDataSQlite.TABLE_NAME;

/**
 * Created by prem on 9/3/18.
 */

public class DatabaseManager {
    private MessageRecorder dbHelper;
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new MessageRecorder(context);
    }

    public void close() {
        dbHelper.close();
    }

    public synchronized void insertValues(String mesage, int type) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onOpen(db);
        MessageDataSQlite.insertValues(db, mesage, type);
        db.close();
    }

    public ArrayList<MessageModel> getMessages(Context context) {
        ArrayList<MessageModel> messageModels = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " order by " + _ID + " DESC";
        cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            String Id = cursor.getString(0);
            String message = cursor.getString(1);
            int type = Integer.parseInt(cursor.getString(2));
            messageModels.add(new MessageModel(Id, message, type));
        }

        return messageModels;
    }


    public static abstract class MessageDataSQlite implements BaseColumns {
        public static final String TABLE_NAME = "message_table";
        public static final String MESSAGE = " message";
        public static final String MESG_TYPE = " msg_type";


        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ( " +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MESSAGE + " text , " + MESG_TYPE + " text  )";

        private static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        private static void insertValues(SQLiteDatabase db, String message, int type) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MESSAGE, message);
            contentValues.put(MESG_TYPE, type);
            db.insert(TABLE_NAME, null, contentValues);
        }
    }

    private class MessageRecorder extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "message.db";

        public MessageRecorder(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MessageDataSQlite.CREATE_TABLE);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                db.execSQL(MessageDataSQlite.DELETE_TABLE);
                onCreate(db);
            }
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
