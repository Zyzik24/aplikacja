package com.example.zuzia.oszczedzanie2.Model.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zuzia.oszczedzanie2.Model.Database.AccountancyDbHelper;
import com.example.zuzia.oszczedzanie2.Model.Entities.Entity;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;

public abstract class Table {
    protected SQLiteOpenHelper dbHelper;
    protected final String TABLE_NAME;

    public Table(Context context, String table_name) {
        this.dbHelper = new AccountancyDbHelper(context);
        this.TABLE_NAME = table_name;
    }

    public SQLiteOpenHelper getDbHelper() {
        return dbHelper;
    }

    protected abstract Entity parseCursor(Cursor cursor);

    protected abstract ContentValues getEntityContentValues(Entity entity);

    protected void parseCursor(Cursor cursor, List<Entity> result) {
        while (cursor.moveToNext()) {
            Entity entity = parseCursor(cursor);
            result.add(entity);
        }
    }


    /**
     * Pobieranie z bazy danych
     * Metoda tworzy obiekt SQLDatabase każdy find i get się do niej odwoluje
     * zwraca Cursor z wynikiem zapytania
     *
     * @param distinct
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return
     */
    protected Cursor find(boolean distinct,
                          String[] columns,
                          String selection,
                          String[] selectionArgs,
                          String groupBy,
                          String having,
                          String orderBy,
                          String limit) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(
                distinct,
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy,
                limit
        );
    }

    protected Cursor find(long id) {
        return find(
                true,
                null,
                _ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );
    }

    protected Cursor find() {
        return find(false, null, null, null, null, null, null, null);
    }

    protected Cursor find(String selection, String[] selectionArgs) {
        return find(false, null, selection, selectionArgs, null, null, null, null);
    }

    public Entity get(long id) {
        Cursor cursor = find(id);
        if (cursor.moveToNext()) {
            return parseCursor(cursor);
        }
        cursor.close();
        return null;
    }

    public List<Entity> get() {
        return get(null, null);
    }

    public List<Entity> get(String selection, String[] selectionArgs) {
        Cursor cursor = find(selection, selectionArgs);
        List<Entity> result = new ArrayList<>();

        parseCursor(cursor, result);

        cursor.close();
        return result;
    }

    /**
     * Dodawanie do bazy danych
     */
    public long add(ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, values);
        return id;
    }

    public void add(Entity entity) {
        entity.setId(add(getEntityContentValues(entity)));
    }

    /**
     * Usuwanie z bazy danych
     */
    public void delete(String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public void delete(Entity entity) {
        delete(_ID + " = ?", new String[]{String.valueOf(entity.getId())});
    }

    public void delete(long id) {
        delete(_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Edytowanie bazy danych
     */
    public void edit(ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void edit(ContentValues values, long id) {
        edit(values, _ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void edit(Entity entity) {
        edit(getEntityContentValues(entity), entity.getId());
    }

    @Override
    protected void finalize() throws Throwable {
        dbHelper.close();
        super.finalize();
    }
}
