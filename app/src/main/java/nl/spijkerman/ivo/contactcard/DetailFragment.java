package nl.spijkerman.ivo.contactcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class DetailFragment extends Fragment {

    private final Person person;


    public DetailFragment() {
        this(null);
    }

    DetailFragment(Person person) {
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
        Activity activity = getActivity();

        if (person != null && activity != null) {
            TextView firstName = activity.findViewById(R.id.fd_textViewFirstName);
            TextView lastName = activity.findViewById(R.id.fd_textViewLastName);

            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
        }
    }

    void handle(Person person) {
        Activity activity = getActivity();
        if (activity != null) {
            TextView firstName = getActivity().findViewById(R.id.fd_textViewFirstName);
            TextView lastName = getActivity().findViewById(R.id.fd_textViewLastName);

            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
        }
    }
}
