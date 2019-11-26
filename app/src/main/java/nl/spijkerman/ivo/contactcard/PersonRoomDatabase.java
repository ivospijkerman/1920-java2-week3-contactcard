package nl.spijkerman.ivo.contactcard;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonRoomDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static volatile PersonRoomDatabase INSTANCE;

    static PersonRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PersonRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PersonRoomDatabase.class,
                            "contactCard")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
