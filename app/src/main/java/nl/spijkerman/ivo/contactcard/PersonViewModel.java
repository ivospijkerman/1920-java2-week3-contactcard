package nl.spijkerman.ivo.contactcard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

class PersonViewModel extends AndroidViewModel {

    private final PersonRepository repository;

    private final LiveData<List<Person>> allPeople;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
        allPeople = repository.getAllPeople();
    }

    LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    public void insert(Person person) {
        repository.insert(person);
    }
}
