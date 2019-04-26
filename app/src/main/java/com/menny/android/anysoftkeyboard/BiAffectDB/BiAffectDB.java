package com.menny.android.anysoftkeyboard.BiAffectDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomDAO.Touch_DAO;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.KeyData;
import com.menny.android.anysoftkeyboard.BiAffectDB.BiAffectDB_roomModel.TouchData;

@Database(entities = {TouchData.class, KeyData.class}, version = 1,exportSchema = false)
public abstract class BiAffectDB extends RoomDatabase {

    public abstract Touch_DAO TouchDao();

    private static volatile BiAffectDB INSTANCE;

    public static BiAffectDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BiAffectDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BiAffectDB.class, "BiAffect_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
