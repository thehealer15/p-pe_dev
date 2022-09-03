package com.ppe.ppedeveloper;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    View parent;
    EditText vName, vCountry, vBankName, vBankAccountNo;
    Button vDetailsSubmit;
    DatabaseReference root_ref;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent =  inflater.inflate(R.layout.fragment_home, container, false);
        init();

        return parent;
    }
    private void init(){
        vName = parent.findViewById(R.id.seller_name);
        vCountry = parent.findViewById(R.id.seller_country);
        vBankAccountNo = parent.findViewById(R.id.seller_account_number);
        vBankName = parent.findViewById(R.id.seller_bank_name);
        vDetailsSubmit = parent.findViewById(R.id.submit_details);
        root_ref = FirebaseDatabase.getInstance().getReference();

        vDetailsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* * * * * * * * * * * *
                 * Push it to database *
                 * * * * * * * * * * * */
//                after successfully pushing data to database I shall get API KEY
                // API Key will be UID of developer
                Map<String, Object> vendor = new HashMap<>();
                vendor.put("vName",vName.getText().toString());
                vendor.put("vCountry",vCountry.getText().toString());
                vendor.put("vBankAccountNo",vBankAccountNo.getText().toString());
                vendor.put("vBankName",vBankName.getText().toString());
                DatabaseReference vendor_detail = root_ref.child("vendor");
                try{
                    String userId = FirebaseAuth.getInstance().getUid();
                    vendor_detail.child(userId).updateChildren(vendor);
                    // now put the API key of user to db
                    DatabaseReference vendorAPIKey = root_ref.child("API_KEY");
                    vendorAPIKey.child(userId).setValue(userId);
                    // now they are at database, its time to put it to developer
                    EditText apiKeyET = parent.findViewById(R.id.api_key);
                    apiKeyET.setText(userId);
                    apiKeyET.setVisibility(View.VISIBLE);
                    // how to copy data to clipboard android
                    setClipboard(getContext(), userId);
                    Toast.makeText(getActivity(), "copied to clipboard!", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getActivity(), "Something went wrong! try again later", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}