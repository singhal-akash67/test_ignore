package com.example.akash.customerside;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class eatthisfruit extends AppCompatActivity implements nutrient.OnFragmentInteractionListener
{
    private PendingIntent alarmIntent;
    private AlarmManager alarmMgr;
    private String bodyvalue;
    TextView extrainfo;
    private String extrainfovalue;
    TextView fruitname;
    private String fruitnamevalue;
    private String titlevalue;


    private void init()
    {
        this.fruitname = ((TextView)findViewById(R.id.fruitname));
        this.extrainfo = ((TextView)findViewById(R.id.extrainfo));
    }

    public void delay(View paramView)
    {
        this.alarmMgr = ((AlarmManager)getSystemService(ALARM_SERVICE));
        Intent localIntent = new Intent(this, alarmreceiver.class);
        localIntent.putExtra("fruitname", this.fruitnamevalue);
        localIntent.putExtra("extrainfo", this.extrainfovalue);
        localIntent.putExtra("title", this.titlevalue);
        localIntent.putExtra("body", this.bodyvalue);
        this.alarmIntent = PendingIntent.getBroadcast(this, 0, localIntent, 0);
        this.alarmMgr.set(2, 60000L + SystemClock.elapsedRealtime(), this.alarmIntent);
        finish();
        onBackPressed();
    }
    public void nutrientpage(View temp)
    {
        Object localObject=null;
        localObject=nutrient.newInstance(fruitnamevalue);

        FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
        localFragmentTransaction.replace(R.id.nutrients, (android.support.v4.app.Fragment)localObject);
        localFragmentTransaction.commit();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_eatthisfruit);
        init();
        this.fruitname.setText(getIntent().getExtras().getString("fruitname"));
        this.fruitnamevalue = getIntent().getExtras().getString("fruitname");
        this.extrainfo.setText(getIntent().getExtras().getString("extrainfo"));
        this.extrainfovalue = getIntent().getExtras().getString("extrainfo");
        this.titlevalue = getIntent().getExtras().getString("title");
        this.bodyvalue = getIntent().getExtras().getString("body");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
