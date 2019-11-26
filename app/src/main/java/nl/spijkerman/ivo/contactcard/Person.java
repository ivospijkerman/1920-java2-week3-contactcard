package nl.spijkerman.ivo.contactcard;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@Entity(tableName = "people")
public class Person {

    // TODO make this work with auto increment ID

    @NonNull
    @ColumnInfo(name = "firstName")
    private final String firstName;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lastName")
    private final String lastName;

    public Person(@NonNull String firstName,
                  @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    @NonNull
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
