package hu.padar.app.quicknote;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import hu.padar.app.quicknote.helper.StorageHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    StorageHelper sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        sh = new StorageHelper(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Fragment fragment = null;
        if(sh.getCurrentUser().equals("NO_USER_LOGGED_IN_CURRENTLY")) {
            fragment = new LoginFragment();
        } else {
            fragment = new MyNoteFragment();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_place, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new LoginFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(!sh.getCurrentUser().equals("NO_USER_LOGGED_IN_CURRENTLY")) {
            switch (id) {
                case R.id.nav_myNotes:
                    fragment = new MyNoteFragment();
                    Log.d("MYLOG Selected menu: ", "Notes");
                    break;
                case R.id.nav_settings:
                    fragment = new SettingsFragment();
                    Log.d("MYLOG Selected menu: ", "Settings");
                    break;
                case R.id.nav_categories:
                    fragment = new CategoriesFragment();
                    Log.d("MYLOG Selected menu: ", "Categories");
                    break;
                case R.id.nav_add_note:
                    fragment = new AddFragment();
                    Log.d("MYLOG Selected menu: ", "Add");
                    break;
                case R.id.nav_logout:
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new LoginFragment();
                    sh.setCurrentUser("NO_USER_LOGGED_IN_CURRENTLY");
                    Log.d("MYLOG User", "logout");
                default:
                    break;
            }
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_place, fragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
