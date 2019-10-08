package com.amtesting.mobileagentatex.Navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.amtesting.mobileagentatex.Helper.SessionManager;
import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // edittext login
    EditText et_id_user, et_password ;
    // checkbox
    CheckBox cb_password;
    // button
    Button btn_login;

    public TelephonyManager telephonymanager;
    String Imei;
    Context mContext;

//    String url, success, response_code;
    SessionManager session;
    public String Koneksi,login_user;

    // progress dialog
    ProgressDialog pdialog;
    // response dari json
    String responsecode, responsemessage;
    String SessionName, SessionIdAgen , SessionEmail, SessionPhone;
    String id_Agent, Password, imei;

    public login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
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
        telephonymanager = (TelephonyManager) getActivity().getSystemService(mContext.TELEPHONY_SERVICE);
        Imei = telephonymanager.getDeviceId().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Login");
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);
        setHasOptionsMenu(true);

        et_id_user = (EditText) view.findViewById(R.id.tf_id);
        et_password = (EditText) view.findViewById(R.id.tf_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        cb_password = (CheckBox) view.findViewById(R.id.cbShowPwd);
        //agar button back work
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mContext = getContext();
        // action for show password
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        // action click button login
        btn_login.setOnClickListener(new View.OnClickListener() {
            ///METHOD POST///
            @Override
            public void onClick(View v) {

                id_Agent = et_id_user.getText().toString();
                Password = et_password.getText().toString();
                imei = Imei.toString();

                Log.i("Debug", "IMEI " + imei);

                Log.d("Debug", "Info Login = " + Password + " " + id_Agent + " " + imei);

                if (TextUtils.isEmpty(id_Agent)) {
                    et_id_user.setError("Id tidak boleh kosong");
                    return;
                } else if (TextUtils.isEmpty(Password)) {
                    et_password.setError("Password tidak boleh kosong");
                    return;
                } else {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // execute login async
                            new loginAsync().execute();
                        }
                    }, 500);
                }
            }
        });

    }
    // login async
    public class loginAsync extends AsyncTask<String, String, String>
    {
            protected void onPreExecute(){
            super.onPreExecute();

//            pDialog = new ProgressDialog(LoginFragment.this);
            pdialog = new ProgressDialog(getActivity());
            pdialog.setMessage("Loading...");
            pdialog.setIndeterminate(false);
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            HttpURLConnection connection;
            OutputStreamWriter request = null;
            URL url = null;
            String URI = null;
            String response = null;
            String parameters = "user="+id_Agent+"&password="+Password+"&imei="+imei;
            try
            {
                //url = new URL("http://10.0.2.2/mobile_agen/login");
                //url = new URL("http://192.168.6.197/mobile_agen/login");
                //url = new URL("http://192.168.6.12/mobile_agen/login");
//                url = new URL(login_user);
                url = new URL("http://192.168.6.194/mobile_agen/login");
                Log.d("Debug","Test URL Login " + url);

                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(parameters);
                request.flush();
                request.close();
                String line = "";

                //Get data from server
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                    sb.append("response_code");
                    sb.append("response_message");
                    Log.d("Debug","TraceLine = " + line);
                }


                JSONObject jsonObject = new JSONObject(sb.toString());
                responsecode = jsonObject.getString("response_code");
                responsemessage = jsonObject.getString("response_message");

                JSONObject json = new JSONObject(sb.toString()).getJSONObject("data");
                SessionName = json.getString("nama");
                SessionEmail = json.getString("email");
                SessionPhone = json.getString("phone");
                SessionIdAgen = json.getString("kode_agen");
                Log.i("Debug","Cek Hasil "
                        + SessionName + " - "
                        + SessionEmail + " - "
                        + SessionPhone + " - "
                        + SessionIdAgen);
                session = new SessionManager(getActivity().getApplicationContext());
                session.createLoginSession(SessionName,SessionEmail,SessionIdAgen,SessionPhone);
                isr.close();
                reader.close();
            }
            catch(IOException e)
            {
                // Error

                Log.d("Debug","Trace = ERROR ");
                Log.d("Debug","response_code  " + responsecode);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pdialog.dismiss();

            if (!responsecode.equals("1")) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Id atau password salah", Snackbar.LENGTH_LONG)
                        .setAction("Tutup", null).show();
            }
            else {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Login Berhasil", Snackbar.LENGTH_LONG)
                        .setAction("Tutup", null).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finishAffinity();
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
