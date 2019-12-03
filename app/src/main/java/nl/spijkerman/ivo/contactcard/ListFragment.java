package nl.spijkerman.ivo.contactcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import nl.spijkerman.ivo.contactcard.room.PersonListAdapter;
import nl.spijkerman.ivo.contactcard.room.PersonLoader;

public class ListFragment extends Fragment {

    private MainActivity parent;
    private PersonViewModel personViewModel;


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

        parent = (MainActivity) getActivity();

        RecyclerView contactList = parent.findViewById(R.id.fl_recyclerViewPeopleList);
        PersonListAdapter adapter = new PersonListAdapter(parent);
        contactList.setAdapter(adapter);
        contactList.setLayoutManager(new LinearLayoutManager(parent));

        // is this parent or this?
        personViewModel = new ViewModelProvider(parent).get(PersonViewModel.class);

        personViewModel.getAllPeople().observe(parent, adapter::setPeople);
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(parent);
            name = itemView.findViewById(R.id.ec_textViewName);
            image = itemView.findViewById(R.id.ec_imageViewImage);
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
