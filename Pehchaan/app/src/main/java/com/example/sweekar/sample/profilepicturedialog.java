package com.example.sweekar.sample;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class profilepicturedialog extends DialogFragment {
    private galleryorcamera temp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("From where do you want to upload a profile picture")
                .setItems(R.array.imagestring, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0)
                        {
                            temp.galleryorcamerafunction(0);

                        }
                        else if(i==1)
                        {
                            temp.galleryorcamerafunction(1);
                        }

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            temp = (galleryorcamera) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface galleryorcamera
    {
        public void galleryorcamerafunction(int num);

    }
}