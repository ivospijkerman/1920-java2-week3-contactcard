package nl.spijkerman.ivo.contactcard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    @Nullable
    private final Person person;


    public DetailFragment() {
        this(null);
    }

    public DetailFragment(Person person) {
        this.person = person;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (person != null) {
            TextView firstName = getActivity().findViewById(R.id.fd_textViewFirstName);
            TextView lastName = getActivity().findViewById(R.id.fd_textViewLastName);

            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
        }
    }

    public void handle(Person person) {
        TextView firstName = getActivity().findViewById(R.id.fd_textViewFirstName);
        TextView lastName = getActivity().findViewById(R.id.fd_textViewLastName);

        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());

    }
}
