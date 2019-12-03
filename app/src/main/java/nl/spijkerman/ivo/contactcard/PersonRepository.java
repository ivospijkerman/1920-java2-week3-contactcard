package nl.spijkerman.ivo.contactcard;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import nl.spijkerman.ivo.contactcard.room.PersonDao;
import nl.spijkerman.ivo.contactcard.room.PersonRoomDatabase;

import static nl.spijkerman.ivo.contactcard.room.PersonRoomDatabase.*;


public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> allPeople;

    public PersonRepository(Application application) {
        PersonRoomDatabase db = getDatabase(application);
        personDao = db.personDao();
        allPeople = personDao.getAllPeople();
    }

    public LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    void insert(Person person) {
        execute(() -> personDao.insert(person));
    }
}
