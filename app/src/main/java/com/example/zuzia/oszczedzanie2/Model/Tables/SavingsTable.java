package com.example.zuzia.oszczedzanie2.Model.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.zuzia.oszczedzanie2.Model.Entities.Entity;
import com.example.zuzia.oszczedzanie2.Model.Entities.SavingsEntity;

import static z.zyzik.Model.Database.AccountancyContract.SavingsEntry._COLUMN_AMOUNT;
import static z.zyzik.Model.Database.AccountancyContract.SavingsEntry._COLUMN_CREATED_AT;
import static z.zyzik.Model.Database.AccountancyContract.SavingsEntry._COLUMN_DESIRABLE_ID;
import static z.zyzik.Model.Database.AccountancyContract.SavingsEntry._ID;
import static z.zyzik.Model.Database.AccountancyContract.SavingsEntry._TABLE_NAME;

public class SavingsTable extends Table {
    public SavingsTable(Context context) {
        super(context, _TABLE_NAME);
    }

    @Override
    public Entity parseCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(_ID));
        int amount = cursor.getInt(cursor.getColumnIndex(_COLUMN_AMOUNT));
        long desirable_id = cursor.getLong(cursor.getColumnIndex(_COLUMN_DESIRABLE_ID));
        String created_at = cursor.getString(cursor.getColumnIndex(_COLUMN_CREATED_AT));
        return new SavingsEntity(id, desirable_id, amount, created_at);
    }

    @Override
    protected ContentValues getEntityContentValues(Entity entity) {
        SavingsEntity savingsEntity = (SavingsEntity) entity;
        ContentValues values = new ContentValues();
        values.put(_COLUMN_AMOUNT, savingsEntity.getAmount().floatValue());
        values.put(_COLUMN_DESIRABLE_ID, savingsEntity.getDesirableId());
        return values;
    }

}
