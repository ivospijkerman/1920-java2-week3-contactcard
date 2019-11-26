package nl.spijkerman.ivo.contactcard;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonRoomDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static volatile PersonRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static void execute(Runnable action) {
        databaseWriteExecutor.execute(action);
    }


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
