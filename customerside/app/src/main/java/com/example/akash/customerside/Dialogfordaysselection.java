package com.example.akash.customerside;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;

public class Dialogfordaysselection
        extends DialogFragment
{
    int itemsselected = 0;
    ArrayList<Integer> mSelectedItems;
    NoticeDialogListener temp;

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
        try
        {
            this.temp = ((NoticeDialogListener)paramActivity);
            return;
        }
        catch (ClassCastException localClassCastException)
        {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramActivity.toString());
            localStringBuilder.append(" must implement NoticeDialogListener");
            throw new ClassCastException(localStringBuilder.toString());
        }
    }

    public Dialog onCreateDialog(Bundle paramBundle)
    {
        boolean[] arrayOfBoolean = null;
        this.mSelectedItems = getArguments().getIntegerArrayList("daysselected");
        if (this.mSelectedItems != null)
        {
            arrayOfBoolean = new boolean[5];
            Iterator localIterator = this.mSelectedItems.iterator();
            while (localIterator.hasNext())
            {
                arrayOfBoolean[((Integer)localIterator.next()).intValue()] = true;
                this.itemsselected = (1 + this.itemsselected);
            }
        }
        this.mSelectedItems = new ArrayList();
        AlertDialog.Builder localBuilder;
        localBuilder = new AlertDialog.Builder(getActivity());
        localBuilder.setTitle("select days").setPositiveButton(R.string.donefordialog, new OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                if (Dialogfordaysselection.this.itemsselected < 3)
                {
                    Toast.makeText(getContext(), "sdf", Toast.LENGTH_SHORT).show();
                    Dialogfordaysselection.this.temp.onDialogNegativeClick();
                }
                Dialogfordaysselection.this.temp.onDialogPositiveClick(Dialogfordaysselection.this.mSelectedItems);
            }
        }).setNegativeButton(R.string.cancelfordialog, new OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                Dialogfordaysselection.this.itemsselected = 0;
                Dialogfordaysselection.this.temp.onDialogNegativeClick();
            }
        }).setMultiChoiceItems(getResources().getStringArray(R.array.daysselected), arrayOfBoolean, new DialogInterface.OnMultiChoiceClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, boolean paramAnonymousBoolean)
            {
                if (paramAnonymousBoolean)
                {
                    Dialogfordaysselection localDialogfordaysselection2 = Dialogfordaysselection.this;
                    localDialogfordaysselection2.itemsselected = (1 + localDialogfordaysselection2.itemsselected);
                    Dialogfordaysselection.this.mSelectedItems.add(Integer.valueOf(paramAnonymousInt));
                    return;
                }
                if (Dialogfordaysselection.this.mSelectedItems.contains(Integer.valueOf(paramAnonymousInt)))
                {
                    Dialogfordaysselection localDialogfordaysselection1 = Dialogfordaysselection.this;
                    localDialogfordaysselection1.itemsselected = (-1 + localDialogfordaysselection1.itemsselected);
                    Dialogfordaysselection.this.mSelectedItems.remove(Integer.valueOf(paramAnonymousInt));
                }
            }
        });
        return localBuilder.create();
    }

    public static abstract interface NoticeDialogListener
    {
        public abstract void onDialogNegativeClick();

        public abstract void onDialogPositiveClick(ArrayList paramArrayList);
    }
}