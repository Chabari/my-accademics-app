package com.e.academics;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

public class nav_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String University,mClass,uuser_id;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu);

        auth = FirebaseAuth.getInstance();
        uuser_id = auth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        firebaseFirestore.collection("UniversityRegistration").document(uuser_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        University = task.getResult().getString("university");
                        setTitle(University);
                        Academics academics = new Academics();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main,academics).commit();
                    }else {
                        Toast.makeText(nav_menu.this, "Please register for a University", Toast.LENGTH_SHORT).show();
                        setTitle("Registration");
                        AcademicRegstration academicRegstration = new AcademicRegstration();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main,academicRegstration).commit();
                    }
                }

            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_academics) {
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registration) {

            setTitle("Registration");
            AcademicRegstration academicRegstration = new AcademicRegstration();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main,academicRegstration).commit();
            // Handle the camera action
        } else if (id == R.id.nav_cposts) {

            setTitle("My Class");
            ClassPosts classPosts = new ClassPosts();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main,classPosts).commit();

        } else if (id == R.id.nav_gposts) {

            firebaseFirestore.collection("UniversityRegistration").document(uuser_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        if (task.getResult().exists()){
                            University = task.getResult().getString("university");
                            setTitle(University);
                            Academics academics = new Academics();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main,academics).commit();
                        }else {
                            Toast.makeText(nav_menu.this, "Please register for a University", Toast.LENGTH_SHORT).show();
                            setTitle("Registration");
                            AcademicRegstration academicRegstration = new AcademicRegstration();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main,academicRegstration).commit();
                        }
                    }

                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
