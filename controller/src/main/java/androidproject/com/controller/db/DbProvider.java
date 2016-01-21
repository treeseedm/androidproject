package androidproject.com.controller.db;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

import androidproject.com.controller.db.DbSQLiteOpenHelper.Tables;


public class DbProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static String authority;
    private static final int WORK_REPORT = 10000;


    private final void init() {

        sUriMatcher.addURI(authority, DbSQLiteOpenHelper.Tables.WORK_REPORT, WORK_REPORT);

    }

    private static final HashMap<String, String> sRosterProjectionMap = new HashMap<String, String>();


    private UserDatabase userDatabase;
    private ContentResolver mContentResolver;

    @Override
    public boolean onCreate() {
        String packageName = getContext().getPackageName();
        authority = packageName.endsWith(".xmpp") ? packageName : (packageName + ".xmpp");
        userDatabase = UserDatabase.getInstance(getContext());
        mContentResolver = getContext().getContentResolver();
        init();
        return true;
    }

    private SQLiteDatabase getDatabase() {
        return userDatabase.getDatabase();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = getDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Cursor cursor = null;
        HashMap<String, String> projectionMap = new HashMap<String, String>();

        String lastPathSegment = uri.getLastPathSegment();
        switch (sUriMatcher.match(uri)) {
            case WORK_REPORT:
                qb.setTables(Tables.WORK_REPORT);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(mContentResolver, uri);
        }
        return cursor;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = getDatabase();
        db.beginTransaction();
        try {
            switch (sUriMatcher.match(uri)) {

                case WORK_REPORT:
                    for (ContentValues contentValues : values) {
                        db.insert(Tables.WORK_REPORT, null, contentValues);
                    }
                    mContentResolver.notifyChange(Contracts.WorkReport.CONTENT_URI, null);
                    break;

            }
            db.setTransactionSuccessful();
            return values.length;
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = getDatabase();
        long id = -1;
        switch (sUriMatcher.match(uri)) {
            case WORK_REPORT:
                id = db.insert(Tables.WORK_REPORT, null, values);
                mContentResolver.notifyChange(Contracts.WorkReport.CONTENT_URI, null);
                return ContentUris.withAppendedId(uri, id);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = getDatabase();
        int count = 0;
        int match = sUriMatcher.match(uri);
        String lastPathSegment = uri.getLastPathSegment();
        String[] affectedSessions;
        switch (match) {
            case WORK_REPORT:
                count = db.delete(DbSQLiteOpenHelper.Tables.WORK_REPORT, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            mContentResolver.notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = getDatabase();
        int count = 0;
        int match = sUriMatcher.match(uri);
        String lastPathSegment = uri.getLastPathSegment();
        String[] affectedSessions;
        switch (match) {
            case WORK_REPORT:
                count = db.update(Tables.WORK_REPORT, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            mContentResolver.notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        SQLiteDatabase db = getDatabase();
        db.beginTransaction();
        try {
            ContentProviderResult[] results = super.applyBatch(operations);
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }

    private String appendWhere(String source, String append) {
        if (TextUtils.isEmpty(source)) {
            return append;
        } else {
            return String.format("(%s) AND (%s)", append, source);
        }
    }
}
