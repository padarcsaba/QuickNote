package hu.padar.app.quicknote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import hu.padar.app.quicknote.adapter.NoteListViewAdapter;
import hu.padar.app.quicknote.object.Category;

/**
 * Created by Pádi on 2016. 01. 11..
 */
public class MyNoteFragment extends Fragment {

    private ArrayList<Category> adapterValues = new ArrayList<Category>();
    private ArrayAdapter<Category> adapter;
    private ListView listViewNotes;
    private FloatingActionButton floating;

    public MyNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mynote, container, false);
        listViewNotes = (ListView) rootView.findViewById(R.id.listViewNotes);
        adapter = new NoteListViewAdapter(getActivity(), adapterValues);
        listViewNotes.setAdapter(adapter);
        floating = (FloatingActionButton) rootView.findViewById(R.id.floating);

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "It is not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });

        final ArrayList<Category> notes = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Note");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject note : objects) {
                        Category currentNote = new Category(note.getString("title"), note.getCreatedAt().toString(), note.getString("categoryID"), (int) (note.getNumber("color")));
                        notes.add(currentNote);
                    }
                    adapterValues.addAll(notes);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("Parse query exeption", e.toString());
                }
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        return rootView;
    }

}