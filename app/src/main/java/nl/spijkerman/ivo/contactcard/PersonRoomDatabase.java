package nl.spijkerman.ivo.contactcard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonRoomDatabase extends RoomDatabase {

    private static Callback initialLoad = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            execute(() -> {
                PersonDao dao = INSTANCE.personDao();

                Person p = new Person("Harry", "Gaastra");
                dao.insert(p);
            });
        }
    };

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
                            .addCallback(initialLoad)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
