package nl.spijkerman.ivo.contactcard;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements ListFragment.OnContactClickedLister {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void handle(Person person) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.am_fragmentDetail);
            detailFragment.handle(person);
        } else {
            DetailFragment detailFragment = new DetailFragment(person);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.am_fragmentList, detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
