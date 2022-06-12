package com.example.a11stapp;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText requestText;
    TextView responseText;

    static RequestQueue requestQueue;

    RecyclerView recyclerView;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestText = findViewById(R.id.edit_text);
        //responseText = findViewById(R.id.textView);


        Button requestBtn = findViewById(R.id.request_btn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        recyclerView=findViewById(R.id.recycleerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(
                this,LinearLayoutManager.VERTICAL,false);



        recyclerView.setLayoutManager(layoutManager);

        adapter=new  MovieAdapter();
        recyclerView.setAdapter(adapter);

        adapter.addItem(new Movie("마녀2","3561248명","1위","총 판매금액:  3500000원"));
        adapter.addItem(new Movie("범죄도시2","4526156명","2위","총 판매금액:  2300000원"));
        adapter.addItem(new Movie("스파이더맨","4511256명","3위","총 판매금액:  2100000원"));
        adapter.addItem(new Movie("나 홀로집에","4325167명","4위","총 판매금액:  2000000원"));
        adapter.addItem(new Movie("미이라3","4123654명","5위","총 판매금액:  1950000원"));
        adapter.addItem(new Movie("신과함께2","4021567명","6위","총 판매금액:  1930000원"));



    }

    public void makeRequest() {
        String url = requestText.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄");
    }
    public void println(String data){
       // responseText.append(data + "\n");
        Log.d("MainActivity ",data);


    }
    public void processResponse(String response)
    {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response,MovieList.class);
        println("영화 정보의 수 : " + movieList.boxOfficeResult.dailyBoxOfficeList.size());

        for(int i=0;i<movieList.boxOfficeResult.dailyBoxOfficeList.size();i++){
            Movie movie=movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
            adapter.addItem(movie);

    }
        adapter.notifyDataSetChanged();

    }


}