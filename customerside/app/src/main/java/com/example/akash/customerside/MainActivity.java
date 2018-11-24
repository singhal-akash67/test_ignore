package com.example.akash.customerside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Homefragment.OnFragmentInteractionListener
{
    public void onBackPressed()
    {
        DrawerLayout localDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (localDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            localDrawerLayout.closeDrawer(Gravity.START);
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        int i = getSharedPreferences("register", 0).getInt("registervalue", 0);
        if ((i != 0) && (i != 1))
        {
            Bundle localBundle = getIntent().getExtras();
            if ((localBundle != null) && (localBundle.getString("notificationtype") != null) && (localBundle.getString("notificationtype").equalsIgnoreCase("box")))
            {
                Intent localIntent = new Intent(this, eatthisfruit.class);
                if (localBundle.getString("fruitname") != null)
                {
                    localIntent.putExtra("fruitname", localBundle.getString("fruitname"));
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append("it is time to have ");
                    localStringBuilder.append(getIntent().getExtras().getString("fruitname"));
                    localIntent.putExtra("body", localStringBuilder.toString());
                }
                else
                {
                    localIntent.putExtra("body", "it is time to have default");
                }
                if (localBundle.getString("extrainfo") != null) {
                    localIntent.putExtra("extrainfo", localBundle.getString("extrainfo"));
                } else {
                    localIntent.putExtra("extrainfo", "Fruits make you healthy");
                }
                localIntent.putExtra("title", "Reminder");
                finish();
                startActivity(localIntent);
            }
            setContentView(R.layout.activity_main);
            Toolbar localToolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(localToolbar);

            DrawerLayout localDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, localDrawerLayout, localToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            localDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            NavigationView localNavigationView = (NavigationView)findViewById(R.id.nav_view);
            localNavigationView.setNavigationItemSelectedListener(this);
            onNavigationItemSelected(localNavigationView.getMenu().getItem(0));
            return;
        }
        finish();
        startActivity(new Intent(this, Login.class));
    }


    public boolean onNavigationItemSelected(MenuItem paramMenuItem)
    {
        int i = paramMenuItem.getItemId();
        Object localObject=null;
       if(i==R.id.home)
        {
            localObject=new Homefragment();
        }

        if (localObject != null)
        {
            FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.content_frame, (Fragment)localObject);
            localFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            localFragmentTransaction.commit();
        }
        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawer(Gravity.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String temp) {
        if(temp.equalsIgnoreCase(getString(R.string.order)))
        {
            startActivity(new Intent(this,Orderformation.class));
        } else if(temp.equalsIgnoreCase(getString(R.string.callus)))
        {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "918208182210"));
            startActivity(intent);
        }
        else if(temp.equalsIgnoreCase(getString(R.string.aboutus)))
        {
            FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment localfragment=new aboutus();
            localFragmentTransaction.replace(R.id.content_frame,localfragment);
            localFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            localFragmentTransaction.addToBackStack(null);
            localFragmentTransaction.commit();
        }
        else if(temp.equalsIgnoreCase(getString(R.string.feedback)))
        {
            startActivity(new Intent(this,feedback.class));
        }
        else if(temp.equalsIgnoreCase(getString(R.string.manageorders)))
        {
            startActivity(new Intent(this,manageorders.class));
        }


    }
}
