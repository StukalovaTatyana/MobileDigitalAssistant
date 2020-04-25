package ru.asu.mobiledigitalassistant.pojo.db;

import android.provider.BaseColumns;


public final class DbContract{
    public static final String SQL_CREATE_MESSAGE =
            "CREATE TABLE " + Message.TABLE_NAME + " (" +
                    Message._ID + " INTEGER PRIMARY KEY, " +
                    Message.COLUMN_NAME_SENDER + " TEXT, " +
                    Message.COLUMN_NAME_TEXT + " TEXT, " +
                    Message.COLUMN_NAME_TIME + " INTEGER)";
    public static final String SQL_DELETE_MESSAGE = "DROP TABLE IF EXISTS " + Message.TABLE_NAME;

    private DbContract(){}

    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "message";
        public static final String COLUMN_NAME_SENDER = "sender";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_TIME = "time";

    }
}
