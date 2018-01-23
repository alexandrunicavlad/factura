package com.alexandrunica.factura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nica on 1/23/2018.
 */

public class DatabaseServices extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "facturi.db";
    private SQLiteDatabase mDb;
    private Context context;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseFacturaModel.FacturaEntry.TABLE_NAME + " (" +
                    DatabaseFacturaModel.FacturaEntry.COLUMN_FURNIZOR + " TEXT," +
                    DatabaseFacturaModel.FacturaEntry.COLUMN_START + " TEXT," +
                    DatabaseFacturaModel.FacturaEntry.COLUMN_END + " TEXT," +
                    DatabaseFacturaModel.FacturaEntry.COLUMN_PRET + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseFacturaModel.FacturaEntry.TABLE_NAME;

    public DatabaseServices (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate (SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private SQLiteDatabase getDatabase () {
        if (mDb != null && mDb.isOpen())
            return mDb;
        mDb = getWritableDatabase();
        return mDb;
    }

    public void closeDatabase () {
        if (mDb == null)
            return;
        mDb.close();
    }

    public void writeFactura (FacturaModel facturaModel) {
        getDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseFacturaModel.FacturaEntry.COLUMN_FURNIZOR, facturaModel.getFurnizor());
            values.put(DatabaseFacturaModel.FacturaEntry.COLUMN_START, facturaModel.getStartDate());
            values.put(DatabaseFacturaModel.FacturaEntry.COLUMN_END, facturaModel.getEndDate());
            values.put(DatabaseFacturaModel.FacturaEntry.COLUMN_PRET, facturaModel.getPret());


            long newRowId = mDb.insert(DatabaseFacturaModel.FacturaEntry.TABLE_NAME, null, values);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public int countCart () {
        getDatabase();


        String Query = "Select * from " + DatabaseFacturaModel.FacturaEntry.TABLE_NAME;
        Cursor cursor = mDb.rawQuery(Query, null);
        if (cursor.getCount() >= 0) {
            cursor.close();
            return cursor.getCount();
        }
        cursor.close();
        return 0;
    }

    public List<FacturaModel> getFacturi () {
        getDatabase();

        String createString = "SELECT * from " + DatabaseFacturaModel.FacturaEntry.TABLE_NAME;
        List<FacturaModel> list = new ArrayList<>();
        try {
            Cursor cursor = mDb.rawQuery(createString, null);
            while (cursor.moveToNext()) {
                try {
                    String furnizor = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseFacturaModel.FacturaEntry.COLUMN_FURNIZOR));
                    String start = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseFacturaModel.FacturaEntry.COLUMN_START));
                    String end = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseFacturaModel.FacturaEntry.COLUMN_END));
                    String price = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseFacturaModel.FacturaEntry.COLUMN_PRET));
                    FacturaModel facturaModel = new FacturaModel(furnizor, start, end, price);
                    list.add(facturaModel);

                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        } catch (
                Exception e)

        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    return list;
    }
}
