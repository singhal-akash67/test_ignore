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
    Databaseh db;
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
        db=new Databaseh(this);
        Hawker currentHakwer=db.findHawker();
        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
        if(currentHakwer.typeOfVendor=="fruits") {
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
            if (initialfruits.contains("apple")) {
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
            if (initialfruits.contains("orange")) {
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
            if (initialfruits.contains("chikoo")) {
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
            if (initialfruits.contains("coconut")) {
                e.add("coconut");
                coconut.setBackgroundResource(R.drawable.background);
            }


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
            if (initialfruits.contains("banana")) {
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
            if (initialfruits.contains("greengrapes")) {
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
            if (initialfruits.contains("mango_neelam")) {
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
            if (initialfruits.contains("mangohafus")) {
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
            if (initialfruits.contains("mangolangra")) {
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
            if (initialfruits.contains("mangototapuri")) {
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
            if (initialfruits.contains("mangoda")) {
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
            if (initialfruits.contains("mangopa")) {
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
            if (initialfruits.contains("mangoke")) {
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
            if (initialfruits.contains("proe")) {
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
            if (initialfruits.contains("gauva")) {
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
            if (initialfruits.contains("watermelon")) {
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
            if (initialfruits.contains("muskmelon")) {
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
            if (initialfruits.contains("kiwi")) {
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
            if (initialfruits.contains("litchi")) {
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
            if (initialfruits.contains("mangoba")) {
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
            if (initialfruits.contains("peer")) {
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
            if (initialfruits.contains("plum")) {
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

            if (initialfruits.contains("dates")) {
                e.add("dates");
                dates.setBackgroundResource(R.drawable.background);
            }
            parent.addView(linearLayout6);
        }
        else {

            LinearLayout linearLayout1 = new LinearLayout(this);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setWeightSum(8.0f);
            linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ImageView beetroot = new ImageView(this);
            beetroot.setImageResource(R.drawable.beetroot);
            beetroot.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            beetroot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("beetroot");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });
            linearLayout1.addView(beetroot);
            if (initialfruits.contains("beetroot")) {
                e.add("beetroot");
                beetroot.setBackgroundResource(R.drawable.background);
            }
            ImageView bottleguard = new ImageView(this);
            bottleguard.setImageResource(R.drawable.bottleguard);
            bottleguard.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            bottleguard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("bottleguard");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout1.addView(bottleguard);
            if (initialfruits.contains("bottleguard")) {
                e.add("bottleguard");
                bottleguard.setBackgroundResource(R.drawable.background);
            }
            ImageView brinj = new ImageView(this);
            brinj.setImageResource(R.drawable.brinj);
            brinj.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            brinj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("brinj");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });

            linearLayout1.addView(brinj);
            if (initialfruits.contains("brinj")) {
                e.add("brinj");
                brinj.setBackgroundResource(R.drawable.background);
            }

            ImageView brocoulli = new ImageView(this);
            brocoulli.setImageResource(R.drawable.brocoulli);
            brocoulli.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            brocoulli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("brocoulli");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout1.addView(brocoulli);
            if (initialfruits.contains("brocoulli")) {
                e.add("brocoulli");
                brocoulli.setBackgroundResource(R.drawable.background);
            }


            parent.addView(linearLayout1);


            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setWeightSum(8.0f);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView cabbage = new ImageView(this);
            cabbage.setImageResource(R.drawable.cabbage);
            cabbage.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            cabbage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("cabbage");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(cabbage);
            if (initialfruits.contains("cabbage")) {
                e.add("cabbage");
                cabbage.setBackgroundResource(R.drawable.background);
            }
            ImageView carrot = new ImageView(this);
            carrot.setImageResource(R.drawable.carrot);
            carrot.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            carrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("carrot");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(carrot);
            if (initialfruits.contains("carrot")) {
                e.add("carrot");
                carrot.setBackgroundResource(R.drawable.background);
            }

            ImageView cauliflower = new ImageView(this);
            cauliflower.setImageResource(R.drawable.cauliflower);
            cauliflower.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            cauliflower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("cauliflower");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(cauliflower);
            if (initialfruits.contains("cauliflower")) {
                e.add("cauliflower");
                cauliflower.setBackgroundResource(R.drawable.background);
            }

            ImageView chavli = new ImageView(this);
            chavli.setImageResource(R.drawable.chavli);
            chavli.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            chavli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("chavli");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout2.addView(chavli);
            if (initialfruits.contains("chavli")) {
                e.add("chavli");
                chavli.setBackgroundResource(R.drawable.background);
            }
            parent.addView(linearLayout2);

            LinearLayout linearLayout3 = new LinearLayout(this);
            linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout3.setWeightSum(8.0f);
            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView drumstick = new ImageView(this);
            drumstick.setImageResource(R.drawable.drumstick);
            drumstick.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            drumstick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("drumstick");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }
                }
            });
            linearLayout3.addView(drumstick);
            if (initialfruits.contains("drumstick")) {
                e.add("drumstick");
                drumstick.setBackgroundResource(R.drawable.background);
            }
            ImageView garlic = new ImageView(this);
            garlic.setImageResource(R.drawable.garlic);
            garlic.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            garlic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("garlic");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout3.addView(garlic);
            if (initialfruits.contains("garlic")) {
                e.add("garlic");
                garlic.setBackgroundResource(R.drawable.background);
            }

            ImageView green_peas = new ImageView(this);
            green_peas.setImageResource(R.drawable.green_peas);
            green_peas.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            linearLayout3.addView(green_peas);
            green_peas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("green_peas");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            if (initialfruits.contains("green_peas")) {
                e.add("green_peas");
                green_peas.setBackgroundResource(R.drawable.background);
            }

            ImageView karela = new ImageView(this);
            karela.setImageResource(R.drawable.karela);
            karela.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            karela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("karela");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout3.addView(karela);
            if (initialfruits.contains("karela")) {
                e.add("karela");
                karela.setBackgroundResource(R.drawable.background);
            }
            parent.addView(linearLayout3);


            LinearLayout linearLayout4 = new LinearLayout(this);
            linearLayout4.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout4.setWeightSum(8.0f);
            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView mint = new ImageView(this);
            mint.setImageResource(R.drawable.mint);
            mint.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mint");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(mint);
            if (initialfruits.contains("mint")) {
                e.add("mint");
                mint.setBackgroundResource(R.drawable.background);
            }

            ImageView onion = new ImageView(this);
            onion.setImageResource(R.drawable.onion);
            onion.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            onion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("onion");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(onion);
            if (initialfruits.contains("onion")) {
                e.add("onion");
                onion.setBackgroundResource(R.drawable.background);
            }

            ImageView corn = new ImageView(this);
            corn.setImageResource(R.drawable.corn);
            corn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            corn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("corn");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(corn);
            if (initialfruits.contains("corn")) {
                e.add("corn");
                corn.setBackgroundResource(R.drawable.background);
            }

            ImageView ladyfinger = new ImageView(this);
            ladyfinger.setImageResource(R.drawable.ladyfinger);
            ladyfinger.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            ladyfinger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("ladyfinger");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout4.addView(ladyfinger);
            if (initialfruits.contains("ladyfinger")) {
                e.add("ladyfinger");
                ladyfinger.setBackgroundResource(R.drawable.background);
            }


            parent.addView(linearLayout4);




            LinearLayout linearLayout5 = new LinearLayout(this);
            linearLayout5.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout5.setWeightSum(8.0f);
            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView cucumber = new ImageView(this);
            cucumber.setImageResource(R.drawable.cucumber);
            cucumber.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            cucumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("cucumber");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(cucumber);
            if (initialfruits.contains("cucumber")) {
                e.add("cucumber");
                cucumber.setBackgroundResource(R.drawable.background);
            }

            ImageView gilki = new ImageView(this);
            gilki.setImageResource(R.drawable.gilki);
            gilki.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            gilki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("gilki");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(gilki);
            if (initialfruits.contains("gilki")) {
                e.add("gilki");
                gilki.setBackgroundResource(R.drawable.background);
            }

            ImageView masala = new ImageView(this);
            masala.setImageResource(R.drawable.masala);
            masala.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            masala.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("masala");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(masala);
            if (initialfruits.contains("masala")) {
                e.add("masala");
                masala.setBackgroundResource(R.drawable.background);
            }

            ImageView moli = new ImageView(this);
            moli.setImageResource(R.drawable.moli);
            moli.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            moli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("moli");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout5.addView(moli);
            if (initialfruits.contains("moli")) {
                e.add("moli");
                moli.setBackgroundResource(R.drawable.background);
            }


            parent.addView(linearLayout5);


            LinearLayout linearLayout6 = new LinearLayout(this);
            linearLayout6.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout6.setWeightSum(8.0f);
            linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView mushroom = new ImageView(this);
            mushroom.setImageResource(R.drawable.mushroom);
            mushroom.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            mushroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("mushroom");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(mushroom);
            if (initialfruits.contains("mushroom")) {
                e.add("mushroom");
                mushroom.setBackgroundResource(R.drawable.background);
            }

            ImageView potato = new ImageView(this);
            potato.setImageResource(R.drawable.potato);
            potato.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            potato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("potato");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(potato);
            if (initialfruits.contains("potato")) {
                e.add("potato");
                potato.setBackgroundResource(R.drawable.background);
            }

            ImageView pumpkin = new ImageView(this);
            pumpkin.setImageResource(R.drawable.pumpkin);
            pumpkin.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            pumpkin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("pumpkin");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(pumpkin);
            if (initialfruits.contains("pumpkin")) {
                e.add("pumpkin");
                pumpkin.setBackgroundResource(R.drawable.background);
            }

            ImageView simlamirchi = new ImageView(this);
            simlamirchi.setImageResource(R.drawable.simlamirchi);
            simlamirchi.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            simlamirchi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("simlamirchi");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout6.addView(simlamirchi);
            if (initialfruits.contains("simlamirchi")) {
                e.add("simlamirchi");
                simlamirchi.setBackgroundResource(R.drawable.background);
            }

            parent.addView(linearLayout6);



            LinearLayout linearLayout7 = new LinearLayout(this);
            linearLayout7.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout7.setWeightSum(8.0f);
            linearLayout7.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView tomato = new ImageView(this);
            tomato.setImageResource(R.drawable.tomato);
            tomato.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            tomato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("tomato");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout7.addView(tomato);
            if (initialfruits.contains("tomato")) {
                e.add("tomato");
                tomato.setBackgroundResource(R.drawable.background);
            }

            ImageView turai = new ImageView(this);
            turai.setImageResource(R.drawable.turai);
            turai.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f));
            turai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = addordeletefruits("turai");
                    if (i == 0) {
                        view.setBackgroundColor(Color.TRANSPARENT);

                    } else {
                        view.setBackgroundResource(R.drawable.background);
                    }

                }
            });
            linearLayout7.addView(turai);
            if (initialfruits.contains("turai")) {
                e.add("turai");
                potato.setBackgroundResource(R.drawable.background);
            }




            parent.addView(linearLayout7);

        }

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
       else{
           submitfruits.setEnabled(true);
           submitfruits.setAlpha(1);
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
