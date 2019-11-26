package nl.spijkerman.ivo.contactcard;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    @Query("DELETE FROM people")
    void deleteAll();

    @NonNull
    @Query("SELECT * FROM people ORDER BY firstName")
    LiveData<List<Person>> getAllPeople();

}
