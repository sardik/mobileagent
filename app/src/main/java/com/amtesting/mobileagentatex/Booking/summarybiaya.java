package com.amtesting.mobileagentatex.Booking;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.R;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link summarybiaya.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link summarybiaya#newInstance} factory method to
 * create an instance of this fragment.
 */
public class summarybiaya extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText et_no_awb;
    Button btn_proses, btn_scan;

    // inisiate Fragment
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public summarybiaya() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment summarybiaya.
     */
    // TODO: Rename and change types and number of parameters
    public static summarybiaya newInstance(String param1, String param2) {
        summarybiaya fragment = new summarybiaya();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_summarybiaya, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Summary Biaya");
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);

        et_no_awb = (EditText) view.findViewById(R.id.tf_no_booking);
        btn_proses = (Button) view.findViewById(R.id.btn_proses);
        btn_scan = (Button) view.findViewById(R.id.btn_scan);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_proses.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                statuspengiriman statuspengiriman = new statuspengiriman();
                transaction.add(R.id.frame, statuspengiriman)
                        .addToBackStack(null)
                        //menyimpan fragment
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //transisi fragment
                        .commit();
            }

        });
        btn_scan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // create new intent for call CaptureActivity bawaan ZXing
                Intent captureIntent = new Intent(getActivity(), CaptureActivity.class);
                // set promp on scanning
                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");
                // set startActivityForResult, for the keep QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                et_no_awb.setText(value);
            } else if (resultCode == Activity.RESULT_CANCELED) {
               // et_no_awb.setText("Scanning Gagal, mohon coba lagi.");
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Message");
                builder.setMessage("Scanning Gagal, mohon coba lagi");
                builder.setCancelable(false);
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

                    // @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Summary Pengiriman");
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
