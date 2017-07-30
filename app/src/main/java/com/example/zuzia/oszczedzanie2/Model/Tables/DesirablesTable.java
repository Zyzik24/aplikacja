package com.example.zuzia.oszczedzanie2.Model.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.zuzia.oszczedzanie2.Model.Entities.DesirablesEntity;
import com.example.zuzia.oszczedzanie2.Model.Entities.Entity;

import static z.zyzik.Model.Database.AccountancyContract.DesirablesEntry._COLUMN_AMOUNT;
import static z.zyzik.Model.Database.AccountancyContract.DesirablesEntry._COLUMN_CREATED_AT;
import static z.zyzik.Model.Database.AccountancyContract.DesirablesEntry._COLUMN_TYPE;
import static z.zyzik.Model.Database.AccountancyContract.DesirablesEntry._ID;
import static z.zyzik.Model.Database.AccountancyContract.DesirablesEntry._TABLE_NAME;

public class DesirablesTable extends Table {

    public DesirablesTable(Context context) {
        super(context, _TABLE_NAME);
    }

    @Override
    protected Entity parseCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(_ID));
        int amount = cursor.getInt(cursor.getColumnIndex(_COLUMN_AMOUNT));
        String type = cursor.getString(cursor.getColumnIndex(_COLUMN_TYPE));
        String created_at = cursor.getString(cursor.getColumnIndex(_COLUMN_CREATED_AT));
        return new DesirablesEntity(id, amount, type, created_at);
    }

    @Override
    protected ContentValues getEntityContentValues(Entity entity) {
        DesirablesEntity desirablesEntity = (DesirablesEntity) entity;
        ContentValues values = new ContentValues();
        values.put(_COLUMN_AMOUNT, desirablesEntity.getAmount().floatValue());
        values.put(_COLUMN_TYPE, desirablesEntity.getType());
        return values;
    }
}
