package nl.spijkerman.ivo.contactcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity parent = (MainActivity) getActivity();

        if (parent != null) {
            RecyclerView contactList = parent.findViewById(R.id.fl_recyclerViewPeopleList);
            PersonListAdapter adapter = new PersonListAdapter(parent);
            contactList.setAdapter(adapter);
            contactList.setLayoutManager(new LinearLayoutManager(parent));

            // is this parent or this?
            PersonViewModel personViewModel = new ViewModelProvider(parent).get(PersonViewModel.class);

            personViewModel.getAllPeople().observe(parent, adapter::setPeople);
        }
    }

    public interface OnContactClickedLister extends View.OnClickListener {
        @Override
        default void onClick(View view) {
            RecyclerView recyclerView = getActivity().findViewById(R.id.fl_recyclerViewPeopleList);
            int id = recyclerView.getChildLayoutPosition(view);
            Person person = PersonLoader.people.get(id);
            handle(person);
        }

        Activity getActivity();

        void handle(Person person);
    }
}
