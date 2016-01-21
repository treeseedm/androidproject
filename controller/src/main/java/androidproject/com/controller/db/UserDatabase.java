package androidproject.com.controller.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import androidproject.com.controller.ApContext;

public class UserDatabase {
    private final String PRODUCT_NAME = "name";
    private static UserDatabase instance;
    private final Context context;
    private DbSQLiteOpenHelper helper;
    private String dbName;

    private UserDatabase(Context context) {
        this.context = context;

        long loginUid = 0;
        SharedPreferences preferences = context.getSharedPreferences(ApContext.sharedPreferences_name, 0);
        if (preferences.getLong("uid", 0) != 0) {
            loginUid = preferences.getLong("uid", 0);
        }

        init(String.valueOf(loginUid));
    }

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new UserDatabase(context.getApplicationContext());
        }
        return instance;
    }

    public void init(String uid) {
        String newDbName = PRODUCT_NAME + ((uid == null) ? "android" : uid);
        if (!TextUtils.equals(newDbName, dbName)) {
            dbName = newDbName;
            if (helper != null) {
                try {
                    helper.close();
                } catch (Exception e) {

                }

            }
            helper = new DbSQLiteOpenHelper(context, dbName);
        }
    }

    /**
     * @return user related database. Do not keep this database, cause it will be replace when user changed.
     */
    public SQLiteDatabase getDatabase() {
        return helper.getWritableDatabase();
    }
}
