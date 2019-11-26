package nl.spijkerman.ivo.contactcard;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> allPeople;

    PersonRepository(Application application) {
        PersonRoomDatabase db = PersonRoomDatabase.getDatabase(application);
        personDao = db.personDao();
        allPeople = personDao.getAllPeople();
    }

    public LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    void insert(Person person) {
        PersonRoomDatabase.execute(() -> personDao.insert(person));
    }
}
