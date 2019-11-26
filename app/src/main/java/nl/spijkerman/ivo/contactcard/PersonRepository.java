package nl.spijkerman.ivo.contactcard;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class PersonRepository {

    private final PersonDao personDao;
    private final LiveData<List<Person>> allPeople;

    PersonRepository(Application application) {
        PersonRoomDatabase db = PersonRoomDatabase.getDatabase(application);
        personDao = db.personDao();
        allPeople = personDao.getAllPeople();
    }

    LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    void insert(Person person) {
        PersonRoomDatabase.execute(() -> personDao.insert(person));
    }
}
