package com.example.nutritionappseminar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Mother_details extends AppCompatActivity implements View.OnClickListener{
    EditText M_name, M_age, C_age;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_details);

        M_name = (EditText) findViewById(R.id.Mname);
        M_age = (EditText) findViewById(R.id.Mage);
        C_age =  (EditText) findViewById(R.id.Cage);

        submit = (Button) findViewById(R.id.MSubmit);
        submit.setOnClickListener((View.OnClickListener) this);

    }

    private void addUserToSheet(){
        final ProgressDialog loading = ProgressDialog.show(this,"Adding User","Please wait...");
        final String motherName = M_name.getText().toString().trim();
        final String mother_age = M_age.getText().toString().trim();
        final String child_age = C_age.getText().toString().trim();

        StringRequest StrReq =  new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/AKfycbyp_hC8RIz-aranDr11m5lsOpcopQBCdREBv7CHkrMbelZaoF3_/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(Mother_details.this,
                                response,
                                Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(getApplicationContext(), Mother_details.class);
                        startActivity(intent1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

            protected Map<String, String> getPrams(){
                Map<String, String> params = new HashMap<>();
                params.put("action", "addUserToSheet");
                params.put("motherName",motherName);
                params.put("m_Age", String.valueOf(mother_age));
                params.put("c_Age", String.valueOf(child_age));

                return params;
            }
        };

    }


    @Override
    public void onClick(View v) {
        if(v==submit){
            addUserToSheet();
        }
    }
}

