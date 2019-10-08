package com.amtesting.mobileagentatex.Booking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.R;
import com.amtesting.mobileagentatex.register;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link booking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link booking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class booking extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // pelanggan
    EditText et_email_pelanggan, et_nama_customer;
    CheckBox cb_idkurir, cb_kontakpelanggan;

    // pengirim
    TextView tv_tambahpengirim, tv_namapengirim, tv_teleponpengirim, tv_kotapengirim, tv_kecamatanpengirim;
    EditText et_namapengirim, et_teleponpengirim, et_kotapengirim, et_kecamatanpengirim;
    CheckBox cb_alamatpengirim;

    //penerima
    TextView tv_tambahpenerima, tv_namapenerima, tv_teleponpenerima, tv_kotapenerima, tv_kecamatanpenerima, tv_alamat_penerima;
    EditText et_namapenerima, et_teleponpenerima, et_kotapenerima, et_kecamatanpenerima, et_alamat_penerima;
    CheckBox cb_alamatpenerima;

    //button
    Button btn_next, btn_back;
    // inisiate Fragment
    FragmentManager fragmentManager;
    FragmentTransaction transaction;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public booking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment booking.
     */
    // TODO: Rename and change types and number of parameters
    public static booking newInstance(String param1, String param2) {
        booking fragment = new booking();
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
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Booking");
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);

        setHasOptionsMenu(true);

        // Deklarasi view untuk pelanggan
        //edittext
        et_email_pelanggan = (EditText) view.findViewById(R.id.tf_email_pelanggan) ;
        et_nama_customer = (EditText) view.findViewById(R.id.tf_nama_kurir);
        // checkbox
        cb_idkurir = (CheckBox) view.findViewById(R.id.checkbox_idkurir) ;
        cb_kontakpelanggan = (CheckBox) view.findViewById(R.id.checkbox_id_pelanggan) ;


        // Deklarasi view untuk pengirim
        // textview
        tv_tambahpengirim = (TextView) view.findViewById(R.id.expand_alamat_pengirim);
        tv_namapengirim = (TextView) view.findViewById(R.id.title_nama_pengirim);
        tv_teleponpengirim = (TextView) view.findViewById(R.id.title_handphone_pengirim);
        tv_kotapengirim = (TextView) view.findViewById(R.id.title_kota_pengirim);
        tv_kecamatanpengirim = (TextView) view.findViewById(R.id.title_kecamatan_pengirim) ;
        //edittext
        et_namapengirim = (EditText) view.findViewById(R.id.tf_nama_pengirim);
        et_teleponpengirim = (EditText) view.findViewById(R.id.tf_handphone_pengirim) ;
        et_kotapengirim = (EditText) view.findViewById(R.id.tf_kota_pengirim) ;
        et_kecamatanpengirim = (EditText) view.findViewById(R.id.tf_kecamatan_pengirim) ;
        // checkbox
        cb_alamatpengirim = (CheckBox) view.findViewById(R.id.checkbox_id_pengirim);

        // Deklarasi view untuk penerima
        // textview
        tv_tambahpenerima = (TextView) view.findViewById(R.id.expand_alamat_penerima);
        tv_namapenerima = (TextView) view.findViewById(R.id.title_nama_penerima);
        tv_teleponpenerima = (TextView) view.findViewById(R.id.title_handphone_penerima);
        tv_kotapenerima = (TextView) view.findViewById(R.id.title_kota_penerima);
        tv_kecamatanpenerima = (TextView) view.findViewById(R.id.title_kecamatan_penerima) ;
        tv_alamat_penerima = (TextView) view.findViewById(R.id.title_alamat_penerima);
        //edittext
        et_namapenerima = (EditText) view.findViewById(R.id.tf_nama_penerima);
        et_teleponpenerima = (EditText) view.findViewById(R.id.tf_handphone_penerima) ;
        et_kotapenerima = (EditText) view.findViewById(R.id.tf_kota_penerima) ;
        et_kecamatanpenerima = (EditText) view.findViewById(R.id.tf_kecamatan_penerima) ;
        et_alamat_penerima = (EditText) view.findViewById(R.id.tf_alamat_penerima);
        // checkbox
        cb_alamatpenerima = (CheckBox) view.findViewById(R.id.checkbox_id_penerima);

        // button next and back
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_back = (Button) view.findViewById(R.id.btn_back);

        //agar button back work
        return view;


    }

    // function for expanding alamat pengirim
    public void expandAlamatPengirim(){
        if (tv_namapengirim.getVisibility()==View.VISIBLE)
        {
            //text view
            tv_namapengirim.setVisibility(View.GONE);
            tv_teleponpengirim.setVisibility(View.GONE);
            tv_kotapengirim.setVisibility(View.GONE);
            tv_kecamatanpengirim.setVisibility(View.GONE);
            // edittext
            et_namapengirim.setVisibility(View.GONE);
            et_teleponpengirim.setVisibility(View.GONE);
            et_kotapengirim.setVisibility(View.GONE);
            et_kecamatanpengirim.setVisibility(View.GONE);
            cb_alamatpengirim.setVisibility(View.GONE);
            tv_tambahpengirim.setText("+ ALAMAT PENGIRIM");

            clearAlamatPengirim();
        }
        else{
            // text view
            tv_namapengirim.setVisibility(View.VISIBLE);
            tv_teleponpengirim.setVisibility(View.VISIBLE);
            tv_kotapengirim.setVisibility(View.VISIBLE);
            tv_kecamatanpengirim.setVisibility(View.VISIBLE);
            // eduttext
            et_namapengirim.setVisibility(View.VISIBLE);
            et_teleponpengirim.setVisibility(View.VISIBLE);
            et_kotapengirim.setVisibility(View.VISIBLE);
            et_kecamatanpengirim.setVisibility(View.VISIBLE);
            cb_alamatpengirim.setVisibility(View.VISIBLE);

            tv_tambahpengirim.setText("- TUTUP FORM");
        }
    }
    // function for expanding alamat penerima
    public void expandAlamatPenerima(){
        if (tv_namapenerima.getVisibility()==View.VISIBLE)
        {
            // tetxview
            tv_namapenerima.setVisibility(View.GONE);
            tv_teleponpenerima.setVisibility(View.GONE);
            tv_kotapenerima.setVisibility(View.GONE);
            tv_kecamatanpenerima.setVisibility(View.GONE);
            tv_alamat_penerima.setVisibility(View.GONE);
            // edit text
            et_namapenerima.setVisibility(View.GONE);
            et_teleponpenerima.setVisibility(View.GONE);
            et_kotapenerima.setVisibility(View.GONE);
            et_kecamatanpenerima.setVisibility(View.GONE);
            et_alamat_penerima.setVisibility(View.GONE);
            cb_alamatpenerima.setVisibility(View.GONE);
            tv_tambahpenerima.setText("+ ALAMAT PENERIMA");

            clearAlamatPenerima();
        }
        else{
            //textview
            tv_namapenerima.setVisibility(View.VISIBLE);
            tv_teleponpenerima.setVisibility(View.VISIBLE);
            tv_kotapenerima.setVisibility(View.VISIBLE);
            tv_kecamatanpenerima.setVisibility(View.VISIBLE);
            tv_alamat_penerima.setVisibility(View.VISIBLE);
            // edittext
            et_namapenerima.setVisibility(View.VISIBLE);
            et_teleponpenerima.setVisibility(View.VISIBLE);
            et_kotapenerima.setVisibility(View.VISIBLE);
            et_kecamatanpenerima.setVisibility(View.VISIBLE);
            et_alamat_penerima.setVisibility(View.VISIBLE);
            cb_alamatpenerima.setVisibility(View.VISIBLE);

            tv_tambahpenerima.setText("- TUTUP FORM");
        }
    }
    public void clearAlamatPengirim(){
        // edittext
        et_namapengirim.setText("");
        et_teleponpengirim.setText("");
        et_kotapengirim.setText("");
        et_kecamatanpengirim.setText("");
        // checkbox
        cb_alamatpengirim.setChecked(false);
    }
    public void clearAlamatPenerima(){
        // edittext
        et_namapenerima.setText("");
        et_teleponpenerima.setText("");
        et_kotapenerima.setText("");
        et_kecamatanpenerima.setText("");
        et_alamat_penerima.setText("");
        // checkbox
        cb_alamatpenerima.setChecked(false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // expand tambah pengirim
        tv_tambahpengirim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                expandAlamatPengirim();
            }
        });

        // expand tambah penerima
        tv_tambahpenerima.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                expandAlamatPenerima();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                detailpaket detailpaket = new detailpaket();
                transaction.add(R.id.frame, detailpaket)
                        .addToBackStack(null)
                        //menyimpan fragment
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //transisi fragment
                        .commit();
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        ((MainActivity) getActivity()).getSupportActionBar().setLogo(R.mipmap.atex_main_logo);
        ((MainActivity) getActivity()).SetBtmNavState(R.id.bottom_pickup);
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
