package com.example.sweekar.sample;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.*;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.support.v4.*;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.LinearLayout.LayoutParams;
import static com.example.sweekar.sample.R.layout.activity_send;
import static com.example.sweekar.sample.R.menu.activity_main;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements ReferralFragment.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(String customerid)
    {
        AutoCompleteTextView customeridview=(AutoCompleteTextView)findViewById(R.id.customerid);
        customeridview.setText(customerid);
    }
    private FirebaseAuth mAuth;
    Button submit;
    List<Product> cart=new ArrayList<Product>();
    Databaseh db;
    Firebase mfirbase;
    AlertDialog alertDialog,alert;
    private EditText customerid;
    SharedPreferences sharedPreferences;
    Notification notification;
    ObjectAnimator animforfruits ;
    DrawerLayout mDrawerLayout;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        final LinearLayout combinedbag=(LinearLayout)findViewById(R.id.combinedbag);
        final LinearLayout combinedprice=(LinearLayout)findViewById(R.id.combinedrupee);
        final ImageView combinedbagimage=(ImageView)findViewById(R.id.combinedbagimage);
        final ImageView combinedrupeeimage=(ImageView)findViewById(R.id.combinedbagrupee);
        final EditText edittext=(EditText)findViewById(R.id.totalprice);
        final AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.customerid);
        final ObjectAnimator anim = ObjectAnimator.ofInt(combinedbag, "backgroundColor", Color.WHITE, Color.parseColor("#f4910f"));
        anim.setDuration(1300);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        final ObjectAnimator animfortotalprice = ObjectAnimator.ofInt(combinedprice, "backgroundColor", Color.WHITE, Color.parseColor("#f4910f"));
        animfortotalprice.setDuration(1300);
        animfortotalprice.setEvaluator(new ArgbEvaluator());
        animfortotalprice.setRepeatMode(Animation.REVERSE);
        animfortotalprice.setRepeatCount(Animation.INFINITE);
        final LinearLayout fruits=(LinearLayout)findViewById(R.id.fruits);
        animforfruits = ObjectAnimator.ofInt(fruits, "backgroundColor", Color.parseColor("#eeeeee"), Color.parseColor("#f4910f"));
        animforfruits.setDuration(1300);
        animforfruits.setEvaluator(new ArgbEvaluator());
        animforfruits.setRepeatMode(Animation.REVERSE);
        animforfruits.setRepeatCount(Animation.INFINITE);
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                else {
                    animfortotalprice.cancel();
                    edittext.setBackgroundColor(Color.parseColor("#ffffff"));
                    combinedrupeeimage.setBackgroundColor(Color.parseColor("#ffffff"));
                    combinedprice.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if(hasfocus)
                {
                    if(anim.isRunning())
                    {
                        anim.cancel();
                    }
                    autoCompleteTextView.setBackgroundColor(Color.parseColor("#ffffff"));
                    combinedbagimage.setBackgroundColor(Color.parseColor("#ffffff"));
                    combinedbag.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
            }
        });
        Intent reporterIntent = new Intent(this,alarmreceiver.class);
        reporterIntent.setAction(Context.ALARM_SERVICE);
        PendingIntent startPIntent = PendingIntent.getBroadcast(this, 0, reporterIntent, 0);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,22 );
        calendar.set(Calendar.MINUTE,00);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,startPIntent);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView customerid = (AutoCompleteTextView) findViewById(R.id.customerid);
                EditText totalprice = (EditText) findViewById(R.id.totalprice);
                if (customerid.getText().toString().equalsIgnoreCase("")) {
                    anim.start();
                    autoCompleteTextView.setBackgroundColor(Color.TRANSPARENT);
                    combinedbagimage.setBackgroundColor(Color.TRANSPARENT);
                } else if (totalprice.getText().toString().equalsIgnoreCase("")) {
                    /*Toast.makeText(MainActivity.this, "Enter the total price", Toast.LENGTH_SHORT).show();*/
                    animfortotalprice.start();
                    edittext.setBackgroundColor(Color.TRANSPARENT);
                    combinedrupeeimage.setBackgroundColor(Color.TRANSPARENT);
                }
                else if (cart.isEmpty())
                {
                    animforfruits.start();
                }
                else {
                    Date objDate = new Date(); // Current System Date and time is assigned to objDate
                    String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; //Date format is Specified
                    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
                    String time=objSDF.format(objDate).toLowerCase(); //Date formatting is applied to the current date
                    String hawkerid = db.gethawkerid();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    Set<String> tempi=sharedPreferences.getStringSet("customeridlist",null);
                    if(tempi!=null) {
                        tempi.add(customerid.getText().toString());
                        editor.putStringSet("customeridlist",tempi );
                        editor.commit();
                        Log.e("notnull", String.valueOf(tempi.size()));
                    }
                    else
                    {
                        Set<String> newlist=new HashSet<String>();
                        newlist.add(customerid.getText().toString());
                        editor.putStringSet("customeridlist",newlist);
                        editor.commit();
                        Log.e("null", String.valueOf(newlist.size()));
                    }
                    db.addTransaction(new Transactionbackup(time, Integer.parseInt(customerid.getText().toString()), Float.parseFloat(totalprice.getText().toString()), hawkerid));
                    int obtainedtransactionid = db.findTransaction(time);
                    for (Product temp : cart) {
                        db.addProductintoProductTable(new TransactionProducts(obtainedtransactionid,hawkerid, temp));
                    }
                    new aftersubmitting().execute();

                }
            }
        });
       Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        SharedPreferences fruitselected=getSharedPreferences("currentfruits",MODE_PRIVATE);
        Set<String> a=fruitselected.getStringSet("currentfruits",null);
       if (isFirstRun) {
           a=new HashSet<String>();
           a.add("garbage");
           SharedPreferences.Editor editor=fruitselected.edit();
           editor.putStringSet("currentfruits",a);
           editor.commit();
            finish();
            startActivity(new Intent(MainActivity.this, Register.class));
            return;
        }
        else if(a.size()==1)
        {

            finish();
            startActivity(new Intent(MainActivity.this, FruitSelectionPage.class));
            return;
        }
        else
        {
          LinearLayout fruitscontainer = (LinearLayout) findViewById(R.id.fruits);
          Iterator iter = a.iterator();
          while (iter.hasNext()) {
              LinearLayout fruitscolumn = new LinearLayout(this);
              fruitscolumn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
              fruitscolumn.setOrientation(LinearLayout.HORIZONTAL);
              fruitscolumn.setWeightSum(8);
              for (int i = 0; i < 4; i++) {
                  String fruitsname = (String) iter.next();
                  if (fruitsname.equalsIgnoreCase("garbage")) {
                      i--;
                      if (iter.hasNext() == false
                              ) {
                          break;
                      } else {
                          continue;
                      }

                  }
                  ImageView imageView = new ImageView(this);
                  LayoutParams params = new LayoutParams(
                          0,
                          LayoutParams.WRAP_CONTENT, 2.0f
                  );
                  params.setMargins(0, 0, 8, 0);
                  imageView.setLayoutParams(params);
                  imageView.setTag(fruitsname);
                  if (fruitsname.equalsIgnoreCase("apple")) {
                      imageView.setImageResource(R.drawable.apple);

                  } else if (fruitsname.equalsIgnoreCase("orange")) {
                      imageView.setImageResource(R.drawable.orange);

                  } else if (fruitsname.equalsIgnoreCase("chikoo")) {
                      imageView.setImageResource(R.drawable.chikoo);

                  } else if (fruitsname.equalsIgnoreCase("coconut")) {
                      imageView.setImageResource(R.drawable.coconut);

                  } else if (fruitsname.equalsIgnoreCase("banana")) {
                      imageView.setImageResource(R.drawable.banana);

                  } else if (fruitsname.equalsIgnoreCase("greengrapes")) {
                      imageView.setImageResource(R.drawable.greengrapes);

                  } else if (fruitsname.equalsIgnoreCase("mango_neelam")) {
                      imageView.setImageResource(R.drawable.mango_neelam);

                  } else if (fruitsname.equalsIgnoreCase("mangohafus")) {
                      imageView.setImageResource(R.drawable.mangohafus);

                  } else if (fruitsname.equalsIgnoreCase("mangolangra")) {
                      imageView.setImageResource(R.drawable.mangolangra);

                  } else if (fruitsname.equalsIgnoreCase("mangototapuri")) {
                      imageView.setImageResource(R.drawable.mangototapuri);

                  } else if (fruitsname.equalsIgnoreCase("mangoda")) {
                      imageView.setImageResource(R.drawable.mangoda);

                  } else if (fruitsname.equalsIgnoreCase("mangopa")) {
                      imageView.setImageResource(R.drawable.mangopa);

                  } else if (fruitsname.equalsIgnoreCase("mangoke")) {
                      imageView.setImageResource(R.drawable.mangoke);

                  } else if (fruitsname.equalsIgnoreCase("proe")) {
                      imageView.setImageResource(R.drawable.proe);

                  } else if (fruitsname.equalsIgnoreCase("gauva")) {
                      imageView.setImageResource(R.drawable.gauva);

                  } else if (fruitsname.equalsIgnoreCase("watermelon")) {
                      imageView.setImageResource(R.drawable.watermelon);

                  } else if (fruitsname.equalsIgnoreCase("muskmelon")) {
                      imageView.setImageResource(R.drawable.muskmelon);

                  } else if (fruitsname.equalsIgnoreCase("kiwi")) {
                      imageView.setImageResource(R.drawable.kiwi);

                  } else if (fruitsname.equalsIgnoreCase("litchi")) {
                      imageView.setImageResource(R.drawable.litchi);

                  } else if (fruitsname.equalsIgnoreCase("mangoba")) {
                      imageView.setImageResource(R.drawable.mangoba);

                  } else if (fruitsname.equalsIgnoreCase("peer")) {
                      imageView.setImageResource(R.drawable.peer);

                  } else if (fruitsname.equalsIgnoreCase("plum")) {
                      imageView.setImageResource(R.drawable.plum);

                  } else if (fruitsname.equalsIgnoreCase("dates")) {
                      imageView.setImageResource(R.drawable.dates);

                  } else if(fruitsname.equalsIgnoreCase("karela")) {
                      imageView.setImageResource(R.drawable.karela);

                  }else if(fruitsname.equalsIgnoreCase("brinj")) {
                      imageView.setImageResource(R.drawable.brinj);

                  } else if(fruitsname.equalsIgnoreCase("beetroot")) {
                      imageView.setImageResource(R.drawable.beetroot);

                  } else if(fruitsname.equalsIgnoreCase("brocoulli")) {
                      imageView.setImageResource(R.drawable.brocoulli);

                  } else if(fruitsname.equalsIgnoreCase("cauliflower")) {
                      imageView.setImageResource(R.drawable.cauliflower);

                  }  else if(fruitsname.equalsIgnoreCase("chavli")) {
                      imageView.setImageResource(R.drawable.chavli);

                  } else if(fruitsname.equalsIgnoreCase("cabbage")) {
                      imageView.setImageResource(R.drawable.cabbage);

                  }else if(fruitsname.equalsIgnoreCase("carrot")) {
                      imageView.setImageResource(R.drawable.carrot);

                  }else if(fruitsname.equalsIgnoreCase("bottleguard")) {
                      imageView.setImageResource(R.drawable.bottleguard);

                  }else if(fruitsname.equalsIgnoreCase("drumstick")) {
                      imageView.setImageResource(R.drawable.drumstick);

                  }
                  else if(fruitsname.equalsIgnoreCase("garlic")) {
                      imageView.setImageResource(R.drawable.garlic);

                  }
                  else if(fruitsname.equalsIgnoreCase("green_peas")) {
                      imageView.setImageResource(R.drawable.green_peas);

                  }
                  else if(fruitsname.equalsIgnoreCase("onion")) {
                      imageView.setImageResource(R.drawable.onion);

                  }
                  else if(fruitsname.equalsIgnoreCase("mint")) {
                      imageView.setImageResource(R.drawable.mint);

                  }else if(fruitsname.equalsIgnoreCase("cucumber")) {
                      imageView.setImageResource(R.drawable.cucumber);

                  }
                  imageView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          manipulatingcart(view);
                      }
                  });
                  fruitscolumn.addView(imageView);
                  if (iter.hasNext() == false) {
                      break;
                  }
              }
              fruitscontainer.addView(fruitscolumn);
      }
        }
        sharedPreferences = getSharedPreferences("mypreferences", MODE_PRIVATE);
        db = new Databaseh(this);
        Set<String> customeridlist = sharedPreferences.getStringSet("customeridlist", null);
        String[] listcustomerid ;
        if (customeridlist !=null) {
            listcustomerid= new String[customeridlist.size()];
            AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.customerid);
            int i=0;
            for (String yo:customeridlist) {
                listcustomerid[i]=yo;
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listcustomerid);
            textView.setAdapter(adapter);
        }
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(animfortotalprice.isRunning())
                {
                    animfortotalprice.cancel();
                }
                edittext.setBackgroundColor(Color.parseColor("#ffffff"));
                combinedrupeeimage.setBackgroundColor(Color.parseColor("#ffffff"));
                combinedprice.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        customerid=(EditText)findViewById(R.id.customerid);
        customerid.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                TextView textView=(TextView)findViewById(R.id.totalsalesoutput);
                TextView anothertextview=(TextView)findViewById(R.id.salepertransaction);
                if(customerid.getText().toString().equalsIgnoreCase("")||customerid.getText().toString()==null)
                {
                    textView.setText("0.0");
                    anothertextview.setText("0.0");
                    textView.setEnabled(false);
                    anothertextview.setEnabled(false);
                    return;
                }
                float totalearningsvalue = db.totalearnings(Integer.parseInt(customerid.getText().toString()));
                int frequency=db.findfrequencyofvisit(Integer.parseInt(customerid.getText().toString()));
                textView.setEnabled(true);
                anothertextview.setEnabled(true);
                if(totalearningsvalue!=0||frequency!=0) {
                    textView.setText(String.valueOf(totalearningsvalue));
                    int a=(int)totalearningsvalue/frequency;
                    anothertextview.setText(String.valueOf(a));
                }
                else
                {
                    textView.setText("0.0");
                    anothertextview.setText("0.0");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(anim.isRunning()) {

                    anim.cancel();
                }
                autoCompleteTextView.setBackgroundColor(Color.parseColor("#ffffff"));
                combinedbagimage.setBackgroundColor(Color.parseColor("#ffffff"));
                combinedbag.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        db=new Databaseh(this);
        Firebase.setAndroidContext(this);
        mfirbase=new Firebase("https://pehchaan-f3f07.firebaseio.com/");
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar  actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.nav);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        TextView headername=(TextView) header.findViewById(R.id.navheadertitle);
        ImageView headerimage=(ImageView)header.findViewById(R.id.profilepicture);
        headername.setText(db.findHawker().hawkername);
        if(getSharedPreferences("profilepicture",MODE_PRIVATE).getString("path",null)==null)
        {
            headerimage.setImageResource(R.drawable.profilepicture);
        }
        else
        {
            Bitmap bitmap=null;
            File file=new File(getSharedPreferences("profilepicture",MODE_PRIVATE).getString("path",null));

            if(file.exists()) {
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null,null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    headerimage.setImageBitmap(EditableHawkerProfile.modifyOrientation(bitmap,getSharedPreferences("profilepicture",MODE_PRIVATE).getString("path",null)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                getSharedPreferences("profilepicture",MODE_PRIVATE).edit().putString("path",null).commit();
                headerimage.setImageResource(R.drawable.profilepicture);
            }
        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        String itemtitle = menuItem.getTitle().toString();
                        if (itemtitle.equalsIgnoreCase("Home"))
                        {
                            //Do NOthing
                        }
                        else if(itemtitle.equalsIgnoreCase("Profile"))
                        {
                            mDrawerLayout.closeDrawers();
                            startActivity(new Intent(MainActivity.this,EditableHawkerProfile.class));
                        }
                        else if(itemtitle.equalsIgnoreCase("Selection"))
                        {
                            mDrawerLayout.closeDrawers();
                            Intent fruitselection=new Intent(MainActivity.this,FruitSelectionPage.class);
                            fruitselection.putExtra("frommainactivity",0);
                            finish();
                            startActivity(fruitselection);
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionBarLogo:
                setBox();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                Toast.makeText(MainActivity.this," Hello Vendor",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void referralfragment(View v)
    {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmenttransaction=fragmentManager.beginTransaction();
        ReferralFragment Referral=new ReferralFragment();
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
        frameLayout.setBackgroundColor(Color.parseColor("#000000"));
        frameLayout.setAlpha((float)0.4);
        fragmenttransaction.add(R.id.referralframe,Referral);
        fragmenttransaction.addToBackStack("added referral");
        fragmenttransaction.commit();
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                            } else {
                                // If sign in fails, display a message to the user.
                            }

                            // ...
                        }
                    });

        }
    }
    @Override
    public void onBackPressed()
    {
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
        frameLayout.setBackgroundColor(Color.TRANSPARENT);
        RadioButton x=(RadioButton)findViewById(R.id.pehchaanreferralmainscreen);
        x.setChecked(true);
        x=(RadioButton)findViewById(R.id.pehchaanreferralscreen);
        x.setChecked(false);
        AutoCompleteTextView temp=(AutoCompleteTextView)findViewById(R.id.customerid);
        temp.setEnabled(false);
        super.onBackPressed();
    }
    public void setBox() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
      if(!isConnected)
      {
          new internetnotconnected().execute();
          return;
      }
        List<com.example.sweekar.sample.Transaction> transactions=db.getTransactions();
        for(com.example.sweekar.sample.Transaction temp:transactions) {
            String k=temp.hawker_id+String.valueOf(temp.transaction_id);
            mfirbase.child("transactions").child(String.valueOf(k)).setValue(temp);
        }
        Hawker hawker=db.findHawker();


            mfirbase.child("hawker").child(String.valueOf(hawker.hawkerid)).setValue(hawker);
        mfirbase.child("hawker").child(String.valueOf(hawker.hawkerid)).child("fcmtoken").setValue(FirebaseInstanceId.getInstance().getToken());
        List<com.example.sweekar.sample.TransactionProducts> transactionProducts=db.getTransactionProducts();
        for(com.example.sweekar.sample.TransactionProducts temp:transactionProducts) {
            String k=temp.hawkerid+String.valueOf(temp.transactionid)+temp.productname;
            Log.e("productname",k);
            mfirbase.child("producttransaction").child(k).child("hawkerid").setValue(temp.hawkerid);
            mfirbase.child("producttransaction").child(k).child("transactionid").setValue(temp.transactionid);
            mfirbase.child("producttransaction").child(k).child("productname").setValue(temp.productname);
        }
        List<Referral> tempi=db.returnreferrals();
        for(com.example.sweekar.sample.Referral referral:tempi)
        {
            String k=referral.hawkerid+String.valueOf(referral.referredby)+String.valueOf(referral.referredto);
            mfirbase.child("referrals").child(k).setValue(referral);
        }

        FirebaseStorage storage=  FirebaseStorage.getInstance("gs://pehchaan-f3f07.appspot.com");

        StorageReference storageRef = storage.getReference();

        StorageReference mountainsRef = storageRef.child("images").child(db.gethawkerid());
        String path=getSharedPreferences("profilepicture",MODE_PRIVATE).getString("path",null);

        Bitmap bitmap=null;
        File a=null;
        if(path!=null) {
             a = new File(path);
        }

        if(a!=null&&a.exists()) {
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(a), null,null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                 bitmap=EditableHawkerProfile.modifyOrientation(bitmap,path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                }
            });
        }
        else
        {
            getSharedPreferences("profilepicture",MODE_PRIVATE).edit().putString("path",null).commit();
        }
        new ondatasent().execute();
    }
    public void manipulatingcart(View v)
    {
        if(animforfruits.isRunning())
        {
            animforfruits.cancel();
        }
        LinearLayout fruits=(LinearLayout)findViewById(R.id.fruits);
        fruits.setBackgroundColor(Color.parseColor("#eeeeee"));
        ImageView temp=(ImageView) v;
        String tag=String.valueOf(temp.getTag());
        for(Product t:cart)
        {
            if(t.name.equalsIgnoreCase(tag))
            {
                cart.remove(t);
                temp.setBackgroundColor(Color.TRANSPARENT);
                return;
            }
        }
        cart.add(new Product(tag));
        temp.setBackgroundResource(R.drawable.background);
        View parentView=(View)findViewById(R.id.parentwindow);
        hideKeyboard(parentView);
        return;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
        //If a layout container, iterate over children and seed recursion.
  /*  public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
              return true;
    }*/
    private class aftersubmitting extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            finish();
            startActivity(getIntent());
        }
        @Override
        protected void onPreExecute() {
            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
            frameLayout.setBackgroundColor(Color.parseColor("#000000"));
            frameLayout.setAlpha((float)0.4);
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_for_completing_transaction, (ViewGroup) findViewById(R.id.llCustom));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(toastLayout);
            toast.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    private class internetnotconnected extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
            frameLayout.setBackgroundColor(Color.TRANSPARENT);
        }
        @Override
        protected void onPreExecute() {
            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
            frameLayout.setBackgroundColor(Color.parseColor("#000000"));
            frameLayout.setAlpha((float)0.4);
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_for_internet_not_connected, (ViewGroup) findViewById(R.id.llCustom));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(toastLayout);
            toast.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    private class ondatasent extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
            frameLayout.setBackgroundColor(Color.TRANSPARENT);
        }
        @Override
        protected void onPreExecute() {
            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.referralframehelp);
            frameLayout.setBackgroundColor(Color.parseColor("#000000"));
            frameLayout.setAlpha((float)0.4);
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_for_pushing_data, (ViewGroup) findViewById(R.id.llCustom));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(toastLayout);
            toast.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
