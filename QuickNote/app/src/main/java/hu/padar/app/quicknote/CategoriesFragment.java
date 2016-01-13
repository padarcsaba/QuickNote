package hu.padar.app.quicknote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.larswerkman.lobsterpicker.LobsterPicker;
import com.larswerkman.lobsterpicker.OnColorListener;
import com.parse.ParseObject;
import com.parse.ParseUser;


/**
 * Created by Pádi on 2016. 01. 11..
 */
public class CategoriesFragment extends Fragment {

    EditText etCat;

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        final LobsterPicker lobsterPicker = (LobsterPicker) rootView.findViewById(R.id.lobsterpicker);
        etCat = (EditText) rootView.findViewById(R.id.editTextCat);
        final Button buttonCreate = (Button) rootView.findViewById(R.id.buttonAdd);
        final Button buttonColor = (Button) rootView.findViewById(R.id.btnColor);
        buttonColor.setBackgroundColor(lobsterPicker.getColor());

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCat.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Give an category name!", Toast.LENGTH_SHORT).show();
                }
                 else {
                    ParseObject categoryObject = new ParseObject("Category");
                    categoryObject.put("name", etCat.getText().toString());
                    categoryObject.put("color", lobsterPicker.getColor());
                    categoryObject.put("username", ParseUser.getCurrentUser().getUsername());
                    categoryObject.saveEventually();
                    Toast.makeText(getActivity(), "Category added!", Toast.LENGTH_SHORT).show();
                    Log.d("CATEGORY: ", "added");
                }
            }
        });

        lobsterPicker.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {

            }

            @Override
            public void onColorSelected(@ColorInt int color) {
                buttonColor.setBackgroundColor(color);
            }
        });

        return rootView;

    }

}