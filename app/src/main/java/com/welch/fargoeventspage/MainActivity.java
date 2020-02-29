package com.welch.fargoeventspage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private TextView txtOutput;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UsernameView = (EditText) findViewById(R.id.editTextUsername);
                EditText PasswordView = (EditText) findViewById(R.id.editTextPassword);

                String username = UsernameView.getText().toString();
                String password = PasswordView.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://challenge.myriadapps.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LoginApi loginApi = retrofit.create(LoginApi.class);
                Call<Token> call = loginApi.getToken(username, password);
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (!response.isSuccessful()) {
                            Context context = getApplicationContext();
                            CharSequence text = "Login failed";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            Token token = response.body();
                            openDisplayeventsActivity(token.getToken());
                        }

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, t.getMessage(), duration);
                        toast.show();
                    }
                });
            }


            public void openDisplayeventsActivity(String token){
                Intent intent = new Intent(getApplicationContext(), DisplayeventsActivity.class);
                Bundle b = new Bundle();
                b.putString("token", token);
                intent.putExtras(b);
                startActivity(intent);
            }

        });
    }
}
