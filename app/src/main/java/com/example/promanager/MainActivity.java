package com.example.promanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    FragmentPagerAdapter adapterViewPager;
    public TabLayout tabLayout;
    public static String userId = MyDatabase.getCurrentUserId();

    public static Context getAppContext() {
        return MainActivity.context;
    }

    Query db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        tabLayout = (TabLayout) findViewById(R.id.pager_tablayout);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(MainActivity.this,
//                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        //------------- DATABASE -----------------

        //Create database
        db = new Query(this, "ProManager1.sqlite", null, 1);

        // Create table
        String createUserInfo = "CREATE TABLE IF NOT EXISTS UserInfo(username VARCHAR(100) PRIMARY KEY," +
                "pass VARCHAR(100)," +
                "fullname NVARCHAR(100)," +
                "overview NVARCHAR(1000)," +
                "totalTasks smallint," +
                "totalHours smallint," +
                "currentTasks smallint," +
                "currentFinished smallint)";
        db.queryData(createUserInfo);
//
//        db.queryData("DROP TABLE UserInfo");

        String createProject = "CREATE TABLE IF NOT EXISTS Project(projectID VARCHAR(10) PRIMARY KEY," +
                "projectName NVARCHAR(100)," +
                "projectOwner VARCHAR(100)," +
                "projectDeadline date," +
                "projectDescribe NVARCHAR(1000)," +
                "projectPrivacy SMALLINT)";
        db.queryData(createProject);
//
//        String createActivity = "CREATE TABLE IF NOT EXISTS Activity(activityID varchar(10) PRIMARY KEY," +
//                "activityName nvarchar(100)," +
//                "activityDescribe nvarchar(1000)," +
//                "activityDeadline date," +
//                "activityHost varchar(10)," +
//                "activityFile varchar(1000)," +
//                "activityStatus varchar(1000)," +
//                "activityAgreement nvarchar(100))";
//        db.queryData(createActivity);
//
//        String createUserConnection = "CREATE TABLE IF NOT EXISTS UserConnection(" +
//                "usernameA varchar(100)," +
//                "usernameB varchar(100)," +
//                "PRIMARY KEY (usernameA, usernameB))";
//        db.queryData(createUserConnection);
//
//        String createActivityInProject = "CREATE TABLE IF NOT EXISTS ActivityInProject(" +
//                "projectID varchar(10)," +
//                "activityID varchar(10)," +
//                "PRIMARY KEY (projectID, activityID))";
//        db.queryData(createActivityInProject);
//
//        String createUserResponActivity = "CREATE TABLE IF NOT EXISTS UserResponActivity(" +
//                "activityID varchar(10)," +
//                "username varchar(100)," +
//                "PRIMARY KEY (activityID, username))";
//        db.queryData(createUserResponActivity);
//
//        String createUserResponProject = "CREATE TABLE IF NOT EXISTS UserResponProject(" +
//                "username varchar(100)," +
//                "projectID varchar(10)," +
//                "PRIMARY KEY (username, projectID))";
//        db.queryData(createUserResponProject);
//
//        String createAchieveActivity = "CREATE TABLE IF NOT EXISTS AchieveActivity(" +
//                "username varchar(100)," +
//                "activityID varchar(10)," +
//                "PRIMARY KEY (username, activityID))";
//        db.queryData(createAchieveActivity);
//
//        // ADD DATA
//
//        // UserInfo TABLE
//        db.queryData("INSERT INTO UserInfo VALUES ('username1', '123456', 'US1', 'ABC', 10, 30, 3, 7)");
//        db.queryData("INSERT INTO UserInfo VALUES ('username2', '123456', 'US2', 'DEF', 5, 24, 1, 4)");
//        db.queryData("INSERT INTO UserInfo VALUES ('username3', '123456', 'US3', 'CCC', 8, 20, 5, 3)");
//
//        //Project TABLE
//        db.queryData("INSERT INTO Project VALUES ('project1', 'PA1', 'username1', '2022-11-30', NULL, NULL)");
//        db.queryData("INSERT INTO Project VALUES ('project2', 'PA2', 'username2', '2023-05-05', NULL, NULL)");
//
//        //Activity TABLE
//        db.queryData("INSERT INTO Activity VALUES ('activity1', 'CNPM_Task1', 'dosth', '2023-10-01', username1, NULL, NULL, NULL)");
//        db.queryData("INSERT INTO Activity VALUES ('activity2', 'CNPM_Task2', 'dosth2', '2022-12-12', username3, NULL, NULL, NULL)");
//        db.queryData("INSERT INTO Activity VALUES ('activity3', 'WEB_Task1', 'dosth3', '2022-12-15', username3, NULL, NULL, NULL)");
//
//        //UserConnection TABLE
//        db.queryData("INSERT INTO UserConnection VALUES ('username1', 'username2')");
//        db.queryData("INSERT INTO UserConnection VALUES ('username1', 'username3')");
//        db.queryData("INSERT INTO UserConnection VALUES ('username2', 'username3')");
//
//        //ActivityInProject TABLE
//        db.queryData("INSERT INTO ActivityInProject VALUES ('project1', 'activity2')");
//        db.queryData("INSERT INTO ActivityInProject VALUES ('project1', 'activity3')");
//
//        //UserResponActivity TABLE
//        db.queryData("INSERT INTO UserResponActivity VALUES ('activity1', 'username1')");
//        db.queryData("INSERT INTO UserResponActivity VALUES ('activity2', 'username1')");
//        db.queryData("INSERT INTO UserResponActivity VALUES ('activity2', 'username3')");
//
//        //UserResponProject TABLE
//        db.queryData("INSERT INTO UserResponProject VALUES ('username3', 'project1')");
//        db.queryData("INSERT INTO UserResponProject VALUES ('username3', 'project2')");
//
//        //AchieveActivity TABLE
//        db.queryData("INSERT INTO AchieveActivity VALUES ('username1', 'activity3')");
//        db.queryData("INSERT INTO AchieveActivity VALUES ('username3', 'activity3')");
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return MyFragment.newInstance(position, userId);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String[] titles = {"Activity", "Infor", "Own", "Seek"};
            return titles[position];
        }
    }

}
