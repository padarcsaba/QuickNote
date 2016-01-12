package hu.padar.app.quicknote;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import hu.padar.app.quicknote.object.Category;

/**
 * Created by Pádi on 2016. 01. 12..
 */
public class AddFragment extends Fragment {

    Spinner spinnerCategory;
    Button BtnAddNote;
    EditText etToDo;

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        spinnerCategory = (Spinner) rootView.findViewById(R.id.spinner);
        final ArrayList<String> spinnerArray = new ArrayList<>();
        BtnAddNote = (Button) rootView.findViewById(R.id.buttonAddNewNote);
        etToDo = (EditText) rootView.findViewById(R.id.etToDo);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject category : objects) {
                        spinnerArray.add(category.getString("name"));
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategory.setAdapter(spinnerArrayAdapter);
                } else {
                    Log.d("parse query exeption", e.toString());
                }
            }
        });


        BtnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etToDo.getText().toString().isEmpty()) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
                    query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                    query.whereEqualTo("name", spinnerCategory.getSelectedItem().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (object == null) {
                                Log.d("parse query", "no result");
                            } else {
                                ParseObject objectto = new ParseObject("Note");
                                objectto.put("categoryID", spinnerCategory.getSelectedItem().toString());
                                objectto.put("title", etToDo.getText().toString());
                                objectto.put("username", ParseUser.getCurrentUser().getUsername());
                                objectto.put("color", object.getNumber("color"));
                                objectto.saveInBackground();
                            }
                        }
                    });
                }
            }
        });


        return rootView;
    }

}
