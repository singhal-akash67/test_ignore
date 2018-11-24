package com.example.sweekar.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class Notificationatnight extends AppCompatActivity {
Databaseh db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new Databaseh(this);
        setContentView(R.layout.activity_notificationatnight);
        TextView totalsalesinaday=(TextView)findViewById(R.id.totalsalesinaday);
        TextView totaltransactionsinday=(TextView)findViewById(R.id.totaltransactionsinaday);
        TextView totalsalestillnow=(TextView)findViewById(R.id.totalsalestillnow);
        TextView newpehchaancustomersinaday=(TextView)findViewById(R.id.newpehchaancustomersinaday);
        TextView salesofnewpehchaancustomersinaday=(TextView)findViewById(R.id.salesofnewpehchaancustomersinaday);
        TextView totalpehchaancustomers=(TextView)findViewById(R.id.totalpehchaancustomers);
        TextView repeatcustomersinaday=(TextView)findViewById(R.id.repeatcustomersinaday);
        TextView salesfromrepeatcustomersinaday=(TextView)findViewById(R.id.salesfromrepeatcustomersinaday);
        String currentdate = DateFormat.getDateInstance().format(new Date());
        totalsalesinaday.setText(String.valueOf(db.totalsalesinaday(currentdate)));
        totaltransactionsinday.setText(String.valueOf(db.totaltransactionsinaday(currentdate)));
        totalsalestillnow.setText(String.valueOf(db.totalsalesunderpehchaan()));
        newpehchaancustomersinaday.setText(String.valueOf(db.newpehchaancustomerinaday(currentdate)));
        salesofnewpehchaancustomersinaday.setText(String.valueOf(db.salesofnewpehchaancustomerinaday(currentdate)));
        totalpehchaancustomers.setText(String.valueOf(db.totalpehchaancustomers()));
        repeatcustomersinaday.setText(String.valueOf(db.repeatcustomersinaday(currentdate)));
        salesfromrepeatcustomersinaday.setText(String.valueOf(db.salesfromrepeatcustomersinaday(currentdate)));
    }
}
