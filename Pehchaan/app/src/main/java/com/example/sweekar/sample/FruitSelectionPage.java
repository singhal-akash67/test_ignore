package com.example.sweekar.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FruitSelectionPage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Set<String> e;
    Button submitfruits;
    int comingfromfruitselectionpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_selection_page);
        Intent fruits=getIntent();
        int value=fruits.getIntExtra("frommainactivity",1);
        submitfruits=(Button)findViewById(R.id.submitfruits);
        submitfruits.setEnabled(false);
        submitfruits.setAlpha((float)0.5);
        sharedPreferences=getSharedPreferences("currentfruits",MODE_PRIVATE);
        e=new HashSet<String>();
        e.add("garbage");
        Set<String> initialfruits=sharedPreferences.getStringSet("currentfruits",null);
        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setWeightSum(8.0f);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ImageView apple = new ImageView(this);
        apple.setImageResource(R.drawable.apple);
        apple.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
        apple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("apple");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });
        linearLayout1.addView(apple);
            if(initialfruits.contains("apple"))
            {
                e.add("apple");
                apple.setBackgroundResource(R.drawable.background);
            }
            ImageView orange = new ImageView(this);
            orange.setImageResource(R.drawable.orange);
            orange.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            orange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("orange");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout1.addView(orange);
        if(initialfruits.contains("orange"))
        {
            e.add("orange");
            orange.setBackgroundResource(R.drawable.background);
        }
        ImageView chikoo = new ImageView(this);
        chikoo.setImageResource(R.drawable.chikoo);
        chikoo.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
        chikoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("chikoo");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });

            linearLayout1.addView(chikoo);
        if(initialfruits.contains("chikoo"))
        {
            e.add("chikoo");
            chikoo.setBackgroundResource(R.drawable.background);
        }

            ImageView coconut = new ImageView(this);
            coconut.setImageResource(R.drawable.coconut);
            coconut.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            coconut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("coconut");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout1.addView(coconut);
        if(initialfruits.contains("coconut"))
        {
            e.add("coconut");
            coconut.setBackgroundResource(R.drawable.background);
        }

            LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
            parent.addView(linearLayout1);


            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setWeightSum(8.0f);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView banana = new ImageView(this);
            banana.setImageResource(R.drawable.banana);
            banana.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            banana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("banana");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(banana);
        if(initialfruits.contains("banana"))
        {
            e.add("banana");
            banana.setBackgroundResource(R.drawable.background);
        }
            ImageView greengrapes = new ImageView(this);
            greengrapes.setImageResource(R.drawable.greengrapes);
            greengrapes.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            greengrapes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("greengrapes");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(greengrapes);
        if(initialfruits.contains("greengrapes"))
        {
            e.add("greengrapes");
            greengrapes.setBackgroundResource(R.drawable.background);
        }

            ImageView mangoneelam = new ImageView(this);
            mangoneelam.setImageResource(R.drawable.mango_neelam);
            mangoneelam.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangoneelam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mango_neelam");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(mangoneelam);
        if(initialfruits.contains("mango_neelam"))
        {
            e.add("mango_neelam");
            mangoneelam.setBackgroundResource(R.drawable.background);
        }

            ImageView mangohafus = new ImageView(this);
            mangohafus.setImageResource(R.drawable.mangohafus);
            mangohafus.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangohafus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangohafus");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(mangohafus);
        if(initialfruits.contains("mangohafus"))
        {
            e.add("mangohafus");
            mangohafus.setBackgroundResource(R.drawable.background);
        }
            parent.addView(linearLayout2);

            LinearLayout linearLayout3 = new LinearLayout(this);
            linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout3.setWeightSum(8.0f);
            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView mangolangra = new ImageView(this);
            mangolangra.setImageResource(R.drawable.mangolangra);
            mangolangra.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangolangra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangolangra");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });
            linearLayout3.addView(mangolangra);
        if(initialfruits.contains("mangolangra"))
        {
            e.add("mangolangra");
            mangolangra.setBackgroundResource(R.drawable.background);
        }
            ImageView mangototapuri = new ImageView(this);
            mangototapuri.setImageResource(R.drawable.mangototapuri);
            mangototapuri.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangototapuri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangototapuri");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout3.addView(mangototapuri);
        if(initialfruits.contains("mangototapuri"))
        {
            e.add("mangototapuri");
            mangototapuri.setBackgroundResource(R.drawable.background);
        }

            ImageView mangoda = new ImageView(this);
            mangoda.setImageResource(R.drawable.mangoda);
            mangoda.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            linearLayout3.addView(mangoda);
            mangoda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangoda");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
        if(initialfruits.contains("mangoda"))
        {
            e.add("mangoda");
            mangoda.setBackgroundResource(R.drawable.background);
        }

            ImageView mangopa = new ImageView(this);
            mangopa.setImageResource(R.drawable.mangopa);
            mangopa.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangopa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangopa");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout3.addView(mangopa);
        if(initialfruits.contains("mangopa"))
        {
            e.add("mangopa");
            mangopa.setBackgroundResource(R.drawable.background);
        }
            parent.addView(linearLayout3);


            LinearLayout linearLayout4 = new LinearLayout(this);
            linearLayout4.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout4.setWeightSum(8.0f);
            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView mangoke = new ImageView(this);
            mangoke.setImageResource(R.drawable.mangoke);
            mangoke.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangoke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangoke");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(mangoke);
        if(initialfruits.contains("mangoke"))
        {
            e.add("mangoke");
            mangoke.setBackgroundResource(R.drawable.background);
        }

            ImageView proe = new ImageView(this);
            proe.setImageResource(R.drawable.proe);
            proe.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            proe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("proe");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(proe);
        if(initialfruits.contains("proe"))
        {
            e.add("proe");
            proe.setBackgroundResource(R.drawable.background);
        }
            ImageView guava = new ImageView(this);
            guava.setImageResource(R.drawable.gauva);
            guava.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            guava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("gauva");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(guava);
        if(initialfruits.contains("gauva"))
        {
            e.add("gauva");
            guava.setBackgroundResource(R.drawable.background);
        }

            ImageView watermelon = new ImageView(this);
            watermelon.setImageResource(R.drawable.watermelon);
            watermelon.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            watermelon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("watermelon");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(watermelon);
        if(initialfruits.contains("watermelon"))
        {
            e.add("watermelon");
            watermelon.setBackgroundResource(R.drawable.background);
        }
            parent.addView(linearLayout4);

            LinearLayout linearLayout5 = new LinearLayout(this);
            linearLayout5.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout5.setWeightSum(8.0f);
            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView muskmellon = new ImageView(this);
            muskmellon.setImageResource(R.drawable.muskmelon);
            muskmellon.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            muskmellon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("muskmelon");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(muskmellon);
        if(initialfruits.contains("muskmelon"))
        {
            e.add("muskmelon");
            muskmellon.setBackgroundResource(R.drawable.background);
        }

            ImageView kiwi = new ImageView(this);
            kiwi.setImageResource(R.drawable.kiwi);
            kiwi.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            kiwi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("kiwi");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(kiwi);
        if(initialfruits.contains("kiwi"))
        {
            e.add("kiwi");
            kiwi.setBackgroundResource(R.drawable.background);
        }

            ImageView litchi = new ImageView(this);
            litchi.setImageResource(R.drawable.litchi);
            litchi.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            litchi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("litchi");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(litchi);
        if(initialfruits.contains("litchi"))
        {
            e.add("litchi");
            litchi.setBackgroundResource(R.drawable.background);
        }

            ImageView mangoba = new ImageView(this);
            mangoba.setImageResource(R.drawable.mangoba);
            mangoba.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mangoba.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mangoba");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(mangoba);
        if(initialfruits.contains("mangoba"))
        {
            e.add("mangoba");
            mangoba.setBackgroundResource(R.drawable.background);
        }
            parent.addView(linearLayout5);


            LinearLayout linearLayout6 = new LinearLayout(this);
            linearLayout6.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout6.setWeightSum(8.0f);
            linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView peer = new ImageView(this);
            peer.setImageResource(R.drawable.peer);
            peer.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            peer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("peer");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(peer);
        if(initialfruits.contains("peer"))
        {
            e.add("peer");
            peer.setBackgroundResource(R.drawable.background);
        }

            ImageView plum = new ImageView(this);
            plum.setImageResource(R.drawable.plum);
            plum.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            plum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("plum");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(plum);
        if(initialfruits.contains("plum"))
        {
            e.add("plum");
            plum.setBackgroundResource(R.drawable.background);
        }

            ImageView dates = new ImageView(this);
            dates.setImageResource(R.drawable.dates);
            dates.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            dates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("dates");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(dates);

        if(initialfruits.contains("dates"))
        {
            e.add("dates");
            dates.setBackgroundResource(R.drawable.background);
        }
            parent.addView(linearLayout6);

    }
    public int addordeletefruits(String a)
    {
     for(String t:e)
     {
      if(t.equalsIgnoreCase(a))
      {
       e.remove(t);
       if(e.size()==1)
       {
           submitfruits.setEnabled(false);
           submitfruits.setAlpha((float) 0.5);
       }
       return 0;
      }
     }
     e.add(a);
        submitfruits.setEnabled(true);
        submitfruits.setAlpha(1);
     return 1;
    }
    public void submitfruits(View v)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putStringSet("currentfruits",e);
        editor.commit();
        finish();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void onBackPressed()
    {
        if(comingfromfruitselectionpage==1) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }else
        {
            finish();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

        }
    }
}
