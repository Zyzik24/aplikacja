package com.example.zuzia.oszczedzanie2.Model.Database;

import android.provider.BaseColumns;

public final class AccountancyContract {

    /**
     * Zapytanie do tworzenia bazy danych
     * Tabela desirables:
     * id - id rekordu, primary key
     * amount - ile chce zaoszczedzic w danym przedziale czasowym
     * type - ile czasu na zaoszczedzenie np. tygodniowy to tydzien dopuszczalne typy to:
     * tygodniowy,
     * miesieczny,
     * roczny
     * created at - data utworzenia rekordu domyslnie timestamp
     * <p>
     * Tabela Savings:
     * id - id rekordu, primary key
     * desirable_id - id rekordu z tabeli desirable do ktorego odnosi sie amount
     * amount - ile zaoszczędzono (wartosc ktora nalezy odjac od amount z tabeli desirables)
     * created at - data utworzenia rekordu domyslnie timestamp
     */
    static final String _SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DesirablesEntry._TABLE_NAME + "(" +
                    DesirablesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DesirablesEntry._COLUMN_AMOUNT + " INTEGER NOT NULL," +
                    DesirablesEntry._COLUMN_TYPE + " TEXT NOT NULL," +
                    DesirablesEntry._COLUMN_CREATED_AT + " INTEGER DEFAULT CURRENT_TIMESTAMP" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS " + SavingsEntry._TABLE_NAME + "(" +
                    SavingsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SavingsEntry._COLUMN_DESIRABLE_ID + " INTEGER NOT NULL REFERENCES Desirables (" +
                    DesirablesEntry._ID + ") ON DELETE CASCADE ON UPDATE CASCADE," +
                    SavingsEntry._COLUMN_AMOUNT + " INTEGER NOT NULL," +
                    SavingsEntry._COLUMN_CREATED_AT + " INTEGER DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    static final String _SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SavingsEntry._TABLE_NAME + "; DROP TABLE IF EXISTS " + DesirablesEntry._TABLE_NAME + ";";

    static final String _SQL_ENABLE_FOREIGN_KEY_SUPPORT = "PRAGMA foreign_keys = ON;";
    static final String _SQL_DISABLE_FOREIGN_KEY_SUPPORT = "PRAGMA foreign_keys = OFF;";

    private AccountancyContract() {
    }

    public static class SavingsEntry implements BaseColumns {
        public static final String _TABLE_NAME = "savings";
        public static final String _COLUMN_DESIRABLE_ID = "desirable_id";
        public static final String _COLUMN_AMOUNT = "amount";
        public static final String _COLUMN_CREATED_AT = "created_at";

    }

    public static class DesirablesEntry implements BaseColumns {
        public static final String _TABLE_NAME = "desirables";
        public static final String _COLUMN_AMOUNT = "amount";
        public static final String _COLUMN_TYPE = "type";
        public static final String _COLUMN_CREATED_AT = "created_at";

    }
}
