package com.amtesting.mobileagentatex.Navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amtesting.mobileagentatex.Adapter.RecycleView_Account_Customer;
import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link accountcustomer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link accountcustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class accountcustomer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public accountcustomer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment accountcustomer.
     */
    // TODO: Rename and change types and number of parameters
    public static accountcustomer newInstance(String param1, String param2) {
        accountcustomer fragment = new accountcustomer();
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

        dataSet = new ArrayList<>();
        initDataset();

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */



    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvView = (RecyclerView) getActivity().findViewById(R.id.rv_main);

        layoutManager = new LinearLayoutManager(getContext());
        rvView.setLayoutManager(layoutManager);

        adapter = new RecycleView_Account_Customer(dataSet);
        rvView.setAdapter(adapter);

        rvView.setHasFixedSize(true);
    }
    private void initDataset(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");
        dataSet.add("Tanggal");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accountcustomer, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Account Customer");
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);

        return view;
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
