package com.example.vaccineupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.detailadapter;
import model.detail;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText pincode;
    EditText date;
    Button search;
    private List<detail> list;
    private detailadapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pincode=findViewById(R.id.pinCode);
        date=findViewById(R.id.date);
        search=findViewById(R.id.search);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new detailadapter(list,MainActivity.this);
        recyclerView.setAdapter(adapter);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePick();
            }
        });
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                    String selectedDate=date.getText().toString();
                    String pinCodeSelected=pincode.getText().toString();
                    if(pinCodeSelected.length()!=6){
                        Toast.makeText(MainActivity.this, "Enter valid PinCode", Toast.LENGTH_SHORT).show();
                    }
                   else if(date.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Enter date", Toast.LENGTH_SHORT).show();
                    }else {
                        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pinCodeSelected + "&date=" + selectedDate;
                        searching(url);
                    }
                }
            });
        }

    private void searching(String url) {
        search.setText("searching...");
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("res",response);
                try{
                    JSONObject obj=new JSONObject(response);
                    JSONArray arr=obj.getJSONArray("sessions");
                    if(arr.length()==0){
                        Toast.makeText(MainActivity.this, "No centre available", Toast.LENGTH_SHORT).show();
                    }
                    list.clear();
                    for(int i=0;i< arr.length();i++){
                        JSONObject object= (JSONObject) arr.get(i);
                         String centreName=object.getString("name");
                         String address=object.getString("address")+" , "+object.getString("district_name");
                         String slotTime=object.getString("from")+" to "+object.getString("to");;
                         String vaccineName=object.getString("vaccine");
                         String feeType= "fee: "+object.getString("fee_type");
                         String ageLimit="Age Limit: "+object.getString("min_age_limit")+"+";
                         String availability="Available: "+object.getString("available_capacity");
                         String latitude=object.getString("lat");

                         String longitude=object.getString("long");
                         System.out.println(latitude+longitude);
                        detail  Detail=new detail(centreName,address,slotTime,vaccineName,feeType,ageLimit,availability, latitude,longitude);
                        list.add(Detail);
                    }
                    search.setText("search");
                    adapter.notifyDataSetChanged();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void datePick() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,this,year,month,day);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String res=i2+"-"+Integer.toString((int)i1+1)+"-"+i;
        date.setText(res);
    }
}
