package cf.bcmbx.exam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bcmb on 1/16/2016.
 */
public class PersonDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "person.db";
    public static final String TABLE_NAME = "persons";
    public static final String FIRSTNAME_FIELD = "fname";
    public static final String LASTNAME_FIELD = "lname";
    public static final String IMAGEURL_FIELD = "imgurl";
    public static final String USERNAME_FIELD = "username";
    public static final String GENDER_FIELD = "gender";

    public PersonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FIRSTNAME_FIELD + " TEXT, " +
                        LASTNAME_FIELD + " TEXT, " +
                        IMAGEURL_FIELD + " TEXT, " +
                        USERNAME_FIELD + " TEXT, " +
                        GENDER_FIELD + " TEXT" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ");");
        onCreate(db);
    }
}
