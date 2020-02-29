package com.welch.fargoeventspage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayeventsActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayevents);
        Bundle b = getIntent().getExtras();
        String token = "";
        if (b != null) {
            token = b.getString("token");
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://challenge.myriadapps.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        EventsApi eventsApi = retrofit.create(EventsApi.class);

        Call<List<Event>> call = eventsApi.getEvents(token);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()) {
                    TextView status = findViewById(R.id.event_title);
                    status.setText("Code" +response.code());
                    return;
                }
                List<Event> events = response.body();

                recyclerView = findViewById(R.id.my_recycler_view);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                data = new ArrayList<DataModel>();
                for(Event event : events) {
                    data.add(new DataModel(event.getTitle(), event.getLocation(), event.getImage_url()));
                }

                adapter = new EventAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                TextView status = findViewById(R.id.event_title);
                status.setText(t.getMessage());
            }
        });
        }
    }
