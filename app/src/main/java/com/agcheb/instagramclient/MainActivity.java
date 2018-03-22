package com.agcheb.instagramclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.agcheb.instagramclient.dummy.DummyContent;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FavoritesFragment.OnListFragmentInteractionListener{

    private final int CAMERA_RESULT = 0;
    private final int TYPE_PHOTO = 1;

    final String TAG = "MyLog!!!";

    File directory;


    private CustomFragmentPA customFragmentPA;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymainnavview);
        createDirectory();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainActivityFragment fragment1 = MainActivityFragment.newInstance(null);
        FavoritesFragment fragment2 = FavoritesFragment.newInstance(null);

        customFragmentPA = new CustomFragmentPA(getSupportFragmentManager());
        customFragmentPA.addFragment(fragment1,"General");
        customFragmentPA.addFragment(fragment2,"Favored");

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(customFragmentPA);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
                startActivityForResult(cameraIntent, CAMERA_RESULT);





            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_RESULT){
            if (resultCode == RESULT_OK){
                if(data == null){
                    Log.d(TAG, "intent is null");
                }else {
                    Bundle bnd1 = data.getExtras();
                    if (bnd1 != null){
                        Object obj = data.getExtras().get("data");
                        if (obj instanceof Bitmap){
                            Bitmap bitmap = (Bitmap) obj;
                        }
                    }
                }
            }else if(resultCode == RESULT_CANCELED){
                Log.d(TAG,"canceled");
            }
         }
//        Snackbar.make(, "Фото добавлено", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home){
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if(drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else {
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int optionID = R.layout.fragment_main;


        if (id == R.id.mainscreen) {
            } else if (id == R.id.colorthemechooser) {
            Intent intent = new Intent(MainActivity.this, ChooseAppColorThemeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            optionID = R.layout.about_devs_layout;
        } else if (id == R.id.nav_send) {
            // потом для обратной связи
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// Не работает в версиях начиная с Nougatе
    //    private Uri generateFileUri(int type){
//        File file = null;
//        if(type == TYPE_PHOTO){
//            file = new File(directory.getPath() + "/" + "photo_"
//                    + System.currentTimeMillis() + ".jpg");
//        }
//        Uri uri= Uri.fromFile(file);
//
//        return uri;
//    }
    private void createDirectory(){
        directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"myfold");
        if (!directory.exists())directory.mkdirs();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
