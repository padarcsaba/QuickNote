package hu.padar.app.quicknote;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

import hu.padar.app.quicknote.helper.StorageHelper;

/**
 * Created by Pádi on 2016. 01. 12..
 */
public class LoginFragment extends Fragment {

    EditText etUsername, etPassword;
    Button buttonLogin, buttonRegister;
    StorageHelper sh;
    TextView tvUser, tvPass;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        etUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        etPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        buttonLogin = (Button) rootView.findViewById(R.id.buttonLogin);
        buttonRegister = (Button) rootView.findViewById(R.id.buttonRegister);
        sh = new StorageHelper(getActivity());
        tvUser = (TextView)rootView.findViewById(R.id.textView);
        tvPass = (TextView)rootView.findViewById(R.id.textView3);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().length() < 5 || etPassword.getText().toString().length() < 5) {
                    Toast.makeText(getActivity(), "Username or password is too short! (min 5 char.)", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    ParseUser user = new ParseUser();
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "Succesfully registration!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                                Log.d("parse registration", e.toString());
                            }
                        }
                    });
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                ParseUser.logInInBackground(etUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null) {
                            sh.setCurrentUser(user.getUsername());
                            Toast.makeText(getActivity(), "Login succes!", Toast.LENGTH_SHORT).show();
                            buttonLogin.setVisibility(View.GONE);
                            buttonRegister.setVisibility(View.GONE);
                            etPassword.setVisibility(View.GONE);
                            etUsername.setVisibility(View.GONE);
                            tvUser.setVisibility(View.GONE);
                            tvPass.setVisibility(View.GONE);

                        }   else {
                            Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        });

        return rootView;
    }
}