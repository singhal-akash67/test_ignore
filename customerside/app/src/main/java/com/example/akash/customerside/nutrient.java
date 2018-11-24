package com.example.akash.customerside;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link nutrient.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nutrient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nutrient extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public nutrient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment nutrient.
     */
    // TODO: Rename and change types and number of parameters
    public static nutrient newInstance(String param1) {
        nutrient fragment = new nutrient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View temp= inflater.inflate(R.layout.fragment_nutrient, container, false);
        init(temp);
        return temp;
    }
    private void init(View temp)
    {
        TextView carbohydrates=(TextView) temp.findViewById(R.id.carbohydrates);
        TextView vitamin=(TextView)temp.findViewById(R.id.vitamin);
        TextView calories=(TextView) temp.findViewById(R.id.calories);
        TextView fibre=(TextView)temp.findViewById(R.id.fibre);
        String fruitname=mParam1.toLowerCase();
        switch(fruitname)
        {
            case "apple":
                carbohydrates.setText("28 mg");
                vitamin.setText("9.2 mg");
                calories.setText("108 Kcal");
                fibre.setText("4.8 g");
                break;
            case "banana":
                carbohydrates.setText("23 mg");
                vitamin.setText("9 mg");
                calories.setText("89 Kcal");
                fibre.setText("3 g");
                break;

            case "peers":
                carbohydrates.setText("23 mg");
                vitamin.setText("7 mg");
                calories.setText("86 Kcal");
                fibre.setText("5 g");
                break;
            case "plum":
                carbohydrates.setText("6 mg");
                vitamin.setText("5 mg");
                calories.setText("21 Kcal");
                fibre.setText("1 g");
                break;
            case "kiwi":
                carbohydrates.setText("15 mg");
                vitamin.setText("91 mg");
                calories.setText("61 Kcal");
                fibre.setText("3 g");
                break;
            case "guava":
                carbohydrates.setText("20 mg");
                vitamin.setText("320 mg");
                calories.setText("96 Kcal");
                fibre.setText("7 g");
                break;


        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
