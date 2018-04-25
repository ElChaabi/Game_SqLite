package com.vogella.lenovo.game_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by Lenovo on 4/15/2018.
 */

public class Game_Database_Handler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Game";
    // Table name
    private static final String TABLE_GAME_i = "Game_Infos";
    // Table Columns names
    private String a="id";
    private String b="id_application";
    private String c="id_apprenant";
    private String d="id_accompagnant";
    private String e="id_exercice";
    private String f="id_niveau";
    private String g="date_actuelle";
    private String h="heure_debut";
    private String i="heure_fin";
    private String j="Nombre_operation_reuss";
    private String k="Nombre_operation_echou";
    private String l="minimum_temps_operation_sec";
    private String m="moyen_temps_operation_sec";
    private String n="longitude";
    private String o="latitude";
    private String p="device";
    private String q="flag";

    public Game_Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_GAME_i + "("
                + a + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + b + " VARCHAR(100),"
                + c + " VARCHAR(100),"
                + d + " VARCHAR(100),"
                + e + " VARCHAR(100),"
                + f + " VARCHAR(100),"
                + g + " DATE,"
                + h + " TIME,"
                + i + " TIME,"
                + j + " INTEGER,"
                + k + " INTEGER,"
                + l + " INTEGER,"
                + m + " INTEGER,"
                + n + " DOUBLE,"
                + o + " DOUBLE,"
                + p + " VARCHAR(100),"
                + q + " BOOLEAN,"
                +")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_i);
        // Creating tables again
        onCreate(db);
    }

    public void addGame(Game Game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(b, Game.getId_application());
        values.put(c, Game.getId_apprenant());
        values.put(d, Game.getId_accompagnant());
        values.put(e, Game.getId_exercice());
        values.put(f, Game.getId_niveau());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdfm = new SimpleDateFormat("HH:mm:ss");
        String da = df.format(Game.getDate_actuelle());
        String hd = sdfm.format(Game.getHeure_debut());
        String hf = sdfm.format(Game.getHeure_fin());
        values.put(g, da);
        values.put(h, hd);
        values.put(i, hf);
        values.put(j, Game.getNombre_operation_reuss());
        values.put(k, Game.getNombre_operation_echou());
        values.put(l, Game.getMinimum_temps_operation_sec());
        values.put(m, Game.getMoyen_temps_operation_sec());
        values.put(n, Game.getLongitude());
        values.put(o, Game.getLatitude());
        values.put(p, Game.getDevice());
        values.put(q, Game.isFlag());

        // Inserting Row
        db.insert(TABLE_GAME_i, null, values);
        db.close(); // Closing database connection
    }
}
