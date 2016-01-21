package androidproject.com.controller.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 49;

    public DbSQLiteOpenHelper(Context context, String name) {
        super(context, name, null, VERSION);
    }


    private static void createTriggers(SQLiteDatabase db) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.WORK_REPORT + "(" +
                Contracts.WorkReport._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contracts.WorkReport.ID + " TEXT UNIQUE ON CONFLICT REPLACE," +
                Contracts.WorkReport.UID + " INTEGER," +
                Contracts.WorkReport.WORK + " TEXT," +
                Contracts.WorkReport.TYPE + " INTEGER," +
                Contracts.WorkReport.NEXT_WORK + " TEXT," +
                Contracts.WorkReport.QUESTION + " TEXT," +
                Contracts.WorkReport.DATE + " INTEGER," +
                Contracts.WorkReport.USER_NAME + " TEXT, " +
                Contracts.WorkReport.CTS + " INTEGER, " +
                Contracts.WorkReport.STATUS + " INTEGER DEFAULT 0," +
                Contracts.WorkReport.VERSION + " TEXT," +
                Contracts.WorkReport.TEXT1 + " TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteOld(db);
        onCreate(db);
    }

    private void deleteOld(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.WORK_REPORT);
    }

    interface Tables {
        String WORK_REPORT = "work_report";
    }
}
