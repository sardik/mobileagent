package com.amtesting.mobileagentatex.Booking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link detailpaket.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link detailpaket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailpaket extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // inisiasi view
    EditText et_berat, et_panjang, et_lebar, et_tinggi, et_volume, et_total_berat, et_isi_paket;
    EditText et_nilai_barang, et_nilai_premi ;

    TextView tv_title_nilai_barang, tv_title_nilai_premi, tv_jenis_kirim, tv_kurs, tv_harga_pengiriman;
    CardView cv_jenis_kirim;

    SwitchCompat switch_asuransi;

    Button btn_next, btn_back;

    // inisiate Fragment
    FragmentManager fragmentManager;
    FragmentTransaction transaction;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public detailpaket() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailpaket.
     */
    // TODO: Rename and change types and number of parameters
    public static detailpaket newInstance(String param1, String param2) {
        detailpaket fragment = new detailpaket();
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

    // function for expanding nilai premi & nilai barang
    public void expandingNilai(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detailpaket, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Detail Paket");
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);

        setHasOptionsMenu(true);

        // deklarasi view detail paket
        et_berat = (EditText) view.findViewById(R.id.tf_beratkg);
        et_panjang = (EditText) view.findViewById(R.id.tf_panjang_paket);
        et_lebar = (EditText) view.findViewById(R.id.tf_lebar_paket);
        et_tinggi = (EditText) view.findViewById(R.id.tf_tinggi_paket);
        et_volume = (EditText) view.findViewById(R.id.tf_volumekg);
        et_total_berat = (EditText) view.findViewById(R.id.tf_totalberat);

        et_nilai_barang = (EditText) view.findViewById(R.id.tf_nilai_barang);
        et_nilai_premi = (EditText) view.findViewById(R.id.tf_nilai_premi);

        tv_title_nilai_barang = (TextView) view.findViewById(R.id.title_nilaibarang);
        tv_title_nilai_premi = (TextView) view.findViewById(R.id.title_nilaipremi);
        tv_jenis_kirim = (TextView) view.findViewById(R.id.title_jenis_pengiriman);
        tv_kurs = (TextView) view.findViewById(R.id.title_rp);
        tv_harga_pengiriman = (TextView) view.findViewById(R.id.title_biaya);

        switch_asuransi = (SwitchCompat) view.findViewById(R.id.sw_nilai);
        cv_jenis_kirim = (CardView) view.findViewById(R.id.cv_harga);

        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        return  view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // expanding nilai by switch
        switch_asuransi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {

                if(on){
                    tv_title_nilai_premi.setVisibility(View.VISIBLE);
                    tv_title_nilai_barang.setVisibility(View.VISIBLE);
                    et_nilai_premi.setVisibility(View.VISIBLE);
                    et_nilai_barang.setVisibility(View.VISIBLE);
                }
                else{
                    tv_title_nilai_premi.setVisibility(View.GONE);
                    tv_title_nilai_barang.setVisibility(View.GONE);
                    et_nilai_premi.setVisibility(View.GONE);
                    et_nilai_barang.setVisibility(View.GONE);

                    et_nilai_premi.setText("");
                    et_nilai_barang.setText("");
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                summarypengiriman summarypengiriman = new summarypengiriman();
                transaction.add(R.id.frame, summarypengiriman)
                        .addToBackStack(null)
                        //menyimpan fragment
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //transisi fragment
                        .commit();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Booking");
            }

        });
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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Booking");
    }

    //membuat button back
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
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
