package com.kazimasum.retro4demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
   EditText nametext, emailtext, passwordtext;
   TextView tv;
   Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nametext=findViewById(R.id.nametext);
        emailtext=findViewById(R.id.emailtext);
        passwordtext=findViewById(R.id.passwordtext);

        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processdata(nametext.getText().toString(), emailtext.getText().toString(), passwordtext.getText().toString());
            }
        });
    }

    public  void processdata(String name, String email, String password)
    {
        Call<responsemodel> call= apiController.getInstance()
                .getapi()
                .getregister(name,email,password);

        call.enqueue(new Callback<responsemodel>() {
            @Override
            public void onResponse(Call<responsemodel> call, Response<responsemodel> response) {
                responsemodel obj=response.body();
                tv.setText(obj.getMessage());
                nametext.setText("");
                emailtext.setText("");
                passwordtext.setText("");
            }

            @Override
            public void onFailure(Call<responsemodel> call, Throwable t) {
                tv.setText("something went wrong");
                nametext.setText("");
                emailtext.setText("");
                passwordtext.setText("");
            }
        });
    }
}