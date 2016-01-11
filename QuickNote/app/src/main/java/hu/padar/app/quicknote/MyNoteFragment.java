package hu.padar.app.quicknote;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pádi on 2016. 01. 11..
 */
public class MyNoteFragment extends Fragment {

    public MyNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mynote, container, false);

        return rootView;
    }

}