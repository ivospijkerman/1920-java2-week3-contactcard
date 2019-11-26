package nl.spijkerman.ivo.contactcard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    public PersonListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.picasso = Picasso.with(context);
    }

    private final Picasso picasso;
    private final LayoutInflater inflater;
    private List<Person> people;

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View personView = inflater.inflate(R.layout.element_contact, parent, false);
        return new PersonViewHolder(personView);
    }

    void setPeople(List<Person> people) {
        this.people = people;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        if (people == null) {
            holder.nameView.setText("No Person");
        } else {
            Person person = people.get(position);
            holder.nameView.setText(person.toString());
            Log.i("Henk", person.getThumbnail());
            picasso.load(person.getThumbnail())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (people == null) {
            return 0;
        }
        return people.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ImageView imageView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.ec_textViewName);
            imageView = itemView.findViewById(R.id.ec_imageViewImage);
        }
    }
}
