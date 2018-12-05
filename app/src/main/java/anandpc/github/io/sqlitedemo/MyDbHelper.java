package anandpc.github.io.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student";
    private static final String ROLL = "roll";
    private static final String NAME = "name";
    private static final String TABLE_CREATE = "create table student(roll text,name text)";
    private static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;

    Context context;

    public MyDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        Toast.makeText(context,"in MydbHelper Constructor",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Toast.makeText(context,"in MydbHelper OnCreate()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context,"in MydbHelper onUpgrade()",Toast.LENGTH_SHORT).show();
    }

    public boolean Save(String roll,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ROLL,roll);
        values.put(NAME,name);
        long res = db.insert(TABLE_NAME,null,values);

        if(res != 0 ){
            return true;
        }else {
            return false;
        }
    }

    public Cursor view(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);
        return cursor;
    }

    public int update(String roll, String name){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROLL,"99");
        values.put(NAME,"dummy");

        String where = "name=? AND roll=?";
        String[] whereArgs = new String[]{String.valueOf(name),String.valueOf(roll)};

        int isUpdate = db.update(TABLE_NAME,values,where,whereArgs);

        return isUpdate;
    }

    public int delete(String roll, String name){

        SQLiteDatabase db = getWritableDatabase();

        String where = "roll=? AND name=?";
        String[] whereArgs = new String[]{roll,name};

        int isDeleted = db.delete(TABLE_NAME,where,whereArgs);

        return isDeleted;
    }
}
