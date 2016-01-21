package androidproject.com.controller.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public interface Contracts {
    Uri AUTHORITY_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(DbProvider.authority).build();

    public interface WorkReport extends BaseColumns {
        Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, DbSQLiteOpenHelper.Tables.WORK_REPORT);
        String TYPE = "type";
        String UID = "uid";
        String WORK = "work";
        String NEXT_WORK = "next_work";
        String QUESTION = "question";
        String DATE = "date";
        String CTS = "cts";
        String ID = "id";
        String USER_NAME = "user_name";
        String STATUS = "status";
        String VERSION = "version";
        String TEXT1 = "text1";
    }


}
