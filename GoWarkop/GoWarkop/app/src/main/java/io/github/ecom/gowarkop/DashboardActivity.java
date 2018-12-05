package io.github.ecom.gowarkop;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.github.ecom.gowarkop.adapter.SlidingImageAdapter;

public class DashboardActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    CardView  cvDaftarMenu, cvContact, cvProfil;

//    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initSlider();
        initializedObject();
        actionClicked();
        sessionCheck();
    }


    private void initSlider() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(DashboardActivity.this,ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    public void initializedObject(){
        cvDaftarMenu = findViewById(R.id.cv_daftar_menu_menu);
//        slipGaji = findViewById(R.id.slip_gaji);
//        Cuti = findViewById(R.id.cuti);
//        infoPerusahaan = findViewById(R.id.informasi_perusahaan);
//        presensiHarian = findViewById(R.id.presensi_harian);
    }

    public void actionClicked() {
        cvDaftarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, ListMenuActivity.class);
                startActivity(i);
            }
        });
    }

    public void sessionCheck(){
//        session = new SessionManager(getApplicationContext());
//        session.checkLogin();
//        String token = session.getAccesToken();
    }

    public void logoutUser(){
//        session = new SessionManager(getApplicationContext());
//        session.logoutUser();
//        String token = session.getAccesToken();
//        Toast.makeText(DashboardActivity.this, token, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                logoutUser();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}

