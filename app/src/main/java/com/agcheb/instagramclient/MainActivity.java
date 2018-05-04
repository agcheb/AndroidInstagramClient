package com.agcheb.instagramclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


import com.agcheb.instagramclient.Pojo.TokenResponse;
import com.agcheb.instagramclient.Services.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements  AuthenticationListener{


    AuthenticationDialog dialog;
    EditText mText;
    String mAuthToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new AuthenticationDialog(MainActivity.this, this);
        dialog.show();
    }

    @Override
    public void onCodeReceived(String code) {
        //mText = (EditText)findViewById(R.id.editText);

        if(code!= null){
            dialog.dismiss();

        }

        final Call<TokenResponse> accessToken =  ServiceGenerator.createTokenService().getAccessToken(Constants.CLIENT_ID,Constants.CLIENT_SECRET,Constants.REDIRECT_URI,Constants.AUTORISATION_CODE,code);
        accessToken.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                if(response.isSuccessful()){
                    mAuthToken = response.body().getAccess_token();
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("AUTH_TOKEN", mAuthToken);
                    startActivity(intent);

                }else{
                    try {
                        mText.setText(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                mText.setText("failure");
            }
        });


    }
}
