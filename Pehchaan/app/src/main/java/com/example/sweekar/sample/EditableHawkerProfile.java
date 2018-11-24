package com.example.sweekar.sample;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class EditableHawkerProfile extends AppCompatActivity implements AutomaticMessageRead.callbacktoinsertotp, profilepicturedialog.galleryorcamera {
    Databaseh db;
    Button submit;
    Boolean isverified=true;
    String otpvalue;
    BroadcastReceiver br;
    Hawker details;
    String mCurrentPhotoPath,profilepath;


    boolean[] arraylistener = new boolean[5];
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_Gallery_CAPTURE = 2;


    private File createImageFile() throws IOException {
        String imageFileName = "myprofilepicture";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",storageDir/* suffix */
                      /* directory */
        );
        mCurrentPhotoPath=image.getAbsolutePath();
        return image;
    }
    public void galleryorcamerafunction(int i)
    {
        if(i==0)
        {
            photofromgallery();
        }

        else if(i==1)
        {
            dispatchTakePictureIntent();
        }

    }
    private void photofromgallery() {
        SharedPreferences profile=getSharedPreferences("profilepicture",MODE_PRIVATE);
        profilepath=profile.getString("path",null);
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String picturepath=pictureDirectory.getPath();
        Uri uri=Uri.parse(picturepath);
        takePictureIntent.setDataAndType(uri,"image/*");
        startActivityForResult(takePictureIntent,REQUEST_Gallery_CAPTURE);

    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            SharedPreferences profile=getSharedPreferences("profilepicture",MODE_PRIVATE);
             profilepath=profile.getString("path",null);
            File photoFile=null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName()+".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if(profilepath!=null)
            {
                File a=new File(profilepath);
                if(a.exists())
                {
                    a.delete();
                }
            }
            SharedPreferences profile=getSharedPreferences("profilepicture",MODE_PRIVATE);
            profile.edit().putString("path",mCurrentPhotoPath).commit();
            ImageView profilepicture=(ImageView)findViewById(R.id.profilepicture);
            profilepicture.setImageURI(Uri.fromFile(new File(mCurrentPhotoPath)));
        }
        if (requestCode == REQUEST_Gallery_CAPTURE && resultCode == RESULT_OK) {
            if(profilepath!=null)
            {
                File a=new File(profilepath);
                if(a.exists())
                {
                    a.delete();
                }
            }
            Uri temp=data.getData();
            InputStream inputStream=null;
            try {
                inputStream=getContentResolver().openInputStream(temp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            SharedPreferences profile=getSharedPreferences("profilepicture",MODE_PRIVATE);
            mCurrentPhotoPath=getRealPathFromURI(this,temp);
            profile.edit().putString("path",mCurrentPhotoPath).commit();
            ImageView profilepicture=(ImageView)findViewById(R.id.profilepicture);
            try {
               Bitmap image= modifyOrientation(BitmapFactory.decodeStream(inputStream),mCurrentPhotoPath);
               profilepicture.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);
            default:
                return bitmap;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }


    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void takeprofilepicture(View v)
    {
        DialogFragment temp=new profilepicturedialog();

        temp.show(getFragmentManager(),"imagepicker");
    }

    public void sendotp(View v)
    {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(!isConnected)
        {
            Toast.makeText(this, "Switch on mobile data for otp verfication", Toast.LENGTH_SHORT).show();
            return;
        }
        int a=new Random().nextInt(89999)+10000;

        otpvalue=String.valueOf(a);

        new EditableHawkerProfile.CallAPI(details.phonenumber,otpvalue).execute();
        final Button sendotp=(Button)findViewById(R.id.sendotp);
        sendotp.setEnabled(false);
        sendotp.setAlpha((float) 0.2);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendotp.setEnabled(true);
                sendotp.setAlpha(1);
            }
        }, 120000);
        Toast.makeText(this, "Enter otp upon receiving", Toast.LENGTH_SHORT).show();
        br = new AutomaticMessageRead(this);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(br, intentFilter);
    }
    @Override
    public void callbackregister(String message) {
        EditText otp=(EditText)findViewById(R.id.otp);
        otp.setText(message);
        forotpscreen(new View(this));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_hawker_profile);
        Spinner spinner=(Spinner)findViewById(R.id.area);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.areastring));
        spinner.setAdapter(adapter);
        db=new Databaseh(this);
         details=db.findHawker();
         ImageView profilepicture=(ImageView)findViewById(R.id.profilepicture);
         String path=getSharedPreferences("profilepicture",MODE_PRIVATE).getString("path",null);
         if(path==null)
         {
             profilepicture.setImageResource(R.drawable.profilepicture);
         }
         else
         {
             Bitmap bitmap=null;
             File a=new File(path);

             if(a.exists()) {
                 try {
                      bitmap = BitmapFactory.decodeStream(new FileInputStream(a), null,null);
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
                 try {
                     profilepicture.setImageBitmap(modifyOrientation(bitmap,path));
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             else
             {
                 getSharedPreferences("profilepicture",MODE_PRIVATE).edit().putString("path",null).commit();
                 profilepicture.setImageResource(R.drawable.profilepicture);
             }
         }
        EditText name=(EditText)findViewById(R.id.name);
        Spinner experience=(Spinner)findViewById(R.id.experience);
        RadioGroup gender=(RadioGroup) findViewById(R.id.gender);
        EditText phonenumber=(EditText)findViewById(R.id.phonenumber);
        name.setText(details.hawkername);
        ArrayAdapter<String> experienceadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.experiencestring));
        experience.setAdapter(experienceadapter);
        int spinnerPosition = experienceadapter.getPosition(details.experiece);
        experience.setSelection(spinnerPosition);
        gender.clearCheck();
        if(details.gender.equalsIgnoreCase("Male"))
        {
            gender.check(R.id.male);
        }
        else
        {
            gender.check(R.id.female);
        }
        phonenumber.setText(details.phonenumber);
        String area=details.area;
        int positionofarea=adapter.getPosition(area);
        spinner.setSelection(positionofarea);
        submit=(Button)findViewById(R.id.submit);
        submit.setAlpha((float)0.2);
        submit.setEnabled(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals(details.area)==false)
                {

                    changesubmit();
                    arraylistener[0]=true;
                }
                else
                {
                    arraylistener[0]=false;

                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase(details.hawkername)==false) {
                    changesubmit();
                    arraylistener[1]=true;
                }
                else
                {
                    arraylistener[1]=false;


                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals(details.experiece)==false)
                {

                    changesubmit();
                    arraylistener[0]=true;
                }
                else
                {
                    arraylistener[0]=false;

                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
 gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
     @Override
     public void onCheckedChanged(RadioGroup radioGroup, int id) {
         RadioButton genderbutton=(RadioButton)findViewById(id);
         if(!genderbutton.getText().toString().equalsIgnoreCase(details.gender))
         {
          changesubmit();
             arraylistener[3]=true;

         }
         else
         {
             arraylistener[3]=false;

             for(Boolean arrayi:arraylistener)
             {
                 if(arrayi==true)
                 {
                     return;

                 }
             }

             submit.setAlpha((float)0.2);
             submit.setEnabled(false);
         }

     }
 });
        phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().equalsIgnoreCase(details.phonenumber)==false) {
                    changesubmit();
                    isverified=false;
                    arraylistener[4]=true;

                }
                else
                {
                    arraylistener[4]=false;

                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;
                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                    isverified=true;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void changesubmit()
    {
        submit.setAlpha(1);
        submit.setEnabled(true);
    }
    public void submit(View v)
    {
        EditText phonenumber=(EditText)findViewById(R.id.phonenumber);
        EditText name=(EditText)findViewById(R.id.name);
        RadioGroup gender=(RadioGroup) findViewById(R.id.gender);
        Spinner experience=(Spinner)findViewById(R.id.experience);
        if(name.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter correct name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(experience.getSelectedItem().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter correct name", Toast.LENGTH_SHORT).show();
            return;

        }

        else if(phonenumber.getText().toString().equalsIgnoreCase("")||phonenumber.getText().toString().length()!=10)
        {
            Toast.makeText(this, "Wrong Phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        Spinner spinner=(Spinner)findViewById(R.id.area);
        details.hawkername=name.getText().toString();
        int id=gender.getCheckedRadioButtonId();
        RadioButton genderbutton=(RadioButton)findViewById(id);
        details.gender=genderbutton.getText().toString();
        details.experiece=experience.getSelectedItem().toString();
        details.area=spinner.getSelectedItem().toString();
        if(!isverified)
        {
            details.phonenumber=phonenumber.getText().toString();
            setContentView(R.layout.registerationpart2);
            sendotp(new View(this));
            return;
        }

        db.updateHawker(details);
        onBackPressed();
    }



    public void forotpscreen(View v)
    {

        EditText a=(EditText)findViewById(R.id.otp);
        if(!otpvalue.equalsIgnoreCase(a.getText().toString())||otpvalue.equalsIgnoreCase(a.getText().toString()))
        {
            Toast.makeText(this, "Wrong otp", Toast.LENGTH_SHORT).show();
            return;
        }
        isverified=true;
        setContentView(R.layout.activity_editable_hawker_profile);
        Spinner spinner=(Spinner)findViewById(R.id.area);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.areastring));
        spinner.setAdapter(adapter);
        EditText name=(EditText)findViewById(R.id.name);
        Spinner experience=(Spinner)findViewById(R.id.experience);
        ArrayAdapter<String> experienceadapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.experiencestring));
        experience.setAdapter(experienceadapter);
        int spinnerPosition = experienceadapter.getPosition(details.experiece);
        experience.setSelection(spinnerPosition);
        RadioGroup gender = (RadioGroup) findViewById(R.id.gender);
        EditText phonenumber=(EditText)findViewById(R.id.phonenumber);
        name.setText(details.hawkername);
        gender.clearCheck();
        if(details.gender.equalsIgnoreCase("Male"))
        {
            gender.check(R.id.male);
        }
        else
        {
            gender.check(R.id.female);
        }
        phonenumber.setText(details.phonenumber);
        String area=details.area;
        int positionofarea=adapter.getPosition(area);
        spinner.setSelection(positionofarea);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals(details.area)==false)
                {

                    changesubmit();
                    arraylistener[0]=true;
                }
                else
                {
                    arraylistener[0]=false;
                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;
                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase(details.hawkername)==false) {
                    changesubmit();
                    arraylistener[1]=true;
                }
                else
                {
                    arraylistener[1]=false;
                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals(details.experiece)==false)
                {

                    changesubmit();
                    arraylistener[0]=true;
                }
                else
                {
                    arraylistener[0]=false;

                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton genderbutton=(RadioButton)findViewById(id);
                if(!genderbutton.getText().toString().equalsIgnoreCase(details.gender))
                {
                    changesubmit();
                    arraylistener[3]=true;
                }
                else
                {
                    arraylistener[3]=false;
                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }

                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                }

            }
        });

        phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().equalsIgnoreCase(details.phonenumber)==false) {
                    changesubmit();
                    isverified=false;
                    arraylistener[4]=true;

                }
                else
                {
                    arraylistener[4]=false;

                    for(Boolean arrayi:arraylistener)
                    {
                        if(arrayi==true)
                        {
                            return;

                        }
                    }
                    submit.setAlpha((float)0.2);
                    submit.setEnabled(false);
                    isverified=true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public class CallAPI extends AsyncTask< Void,Void,Void> {

        String mobile_number;
        String otpvalue;
        CallAPI(String mobile_number,String otpvalue)
        {
            this.mobile_number=mobile_number;
            this.otpvalue=otpvalue;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String urlString = "https://2factor.in/API/V1/f477903a-88fc-11e8-a895-0200cd936042/SMS/" +mobile_number+ "/" +otpvalue;

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, urlString, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonobject);
            return null;
        }
    }
}
