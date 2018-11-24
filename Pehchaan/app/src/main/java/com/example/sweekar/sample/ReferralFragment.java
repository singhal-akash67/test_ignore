package com.example.sweekar.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReferralFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReferralFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReferralFragment extends Fragment {
    Databaseh db;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public ReferralFragment() {
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReferralFragment.
     */
    public static ReferralFragment newInstance(String param1, String param2) {
        ReferralFragment fragment = new ReferralFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View temp= inflater.inflate(R.layout.fragment_referral, container, false);
        Button submitreferral=(Button)temp.findViewById(R.id.submitreferral);
        submitreferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText referredby=(EditText)temp.findViewById(R.id.referredbyeditbox);
                EditText referredto=(EditText)temp.findViewById(R.id.referredtoeditbox);
                db=new Databaseh(getContext());
                if(referredby.getText().toString()==NULL||referredby.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(), "Enter referredby Bag No.", Toast.LENGTH_SHORT).show();
                }
                else if(referredto.getText().toString()==NULL||referredto.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(), "Enter referredto Bag No.", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addreferral(new Referral(db.gethawkerid(), Integer.parseInt(referredby.getText().toString()), Integer.parseInt(referredto.getText().toString())));
                    mListener.onFragmentInteraction(referredto.getText().toString());
                    ((MainActivity) getActivity()).onBackPressed();
                    return;
                }
            }
        });
        return temp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String customerid);
    }
}
