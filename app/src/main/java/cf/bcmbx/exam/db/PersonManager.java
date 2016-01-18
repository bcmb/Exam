package cf.bcmbx.exam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cf.bcmbx.exam.model.Person;

public class PersonManager {
    private SQLiteDatabase mDatabase;
    private String[] mAllColumns = {
            PersonDBHelper.FIRSTNAME_FIELD,
            PersonDBHelper.LASTNAME_FIELD,
            PersonDBHelper.IMAGEURL_FIELD,
            PersonDBHelper.USERNAME_FIELD,
            PersonDBHelper.GENDER_FIELD};

    public PersonManager (Context c) {
        mDatabase = new cf.bcmbx.exam.db.PersonDBHelper(c).getWritableDatabase();
    }

    public boolean insertUser(String firstName, String lastName, String pictureUrl, String gender, String userName) {
        ContentValues values = new ContentValues();
        values.put(PersonDBHelper.FIRSTNAME_FIELD, firstName);
        values.put(PersonDBHelper.LASTNAME_FIELD, lastName);
        values.put(PersonDBHelper.IMAGEURL_FIELD, pictureUrl);
        values.put(PersonDBHelper.USERNAME_FIELD, userName);
        values.put(PersonDBHelper.GENDER_FIELD, gender);
        return mDatabase.insert(PersonDBHelper.TABLE_NAME, null, values) != -1;
    }

    public List<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor cursor = mDatabase.query(PersonDBHelper.TABLE_NAME,
                mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Person person = cursorToWord(cursor);
            persons.add(person);
            cursor.moveToNext();
        }
        cursor.close();

        return persons;
    }

    private Person cursorToWord(Cursor cursor) {
        Person person = new Person();
        person.setFirstName(cursor.getString(cursor.getColumnIndex(PersonDBHelper.FIRSTNAME_FIELD)));
        person.setLastName(cursor.getString(cursor.getColumnIndex(PersonDBHelper.LASTNAME_FIELD)));
        person.setImageUrl(cursor.getString(cursor.getColumnIndex(PersonDBHelper.IMAGEURL_FIELD)));
        person.setUsername(cursor.getString(cursor.getColumnIndex(PersonDBHelper.USERNAME_FIELD)));
        person.setGender(cursor.getString(cursor.getColumnIndex(PersonDBHelper.GENDER_FIELD)));

        return person;
    }

    public List<Person> getUser(String id) {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor cursor = mDatabase.query(PersonDBHelper.TABLE_NAME,
                mAllColumns, PersonDBHelper.USERNAME_FIELD + "=?", new String[] {id}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Person person = cursorToWord(cursor);
            persons.add(person);
            cursor.moveToNext();
        }
        cursor.close();

        return persons;
    }
}
