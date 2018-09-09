package com.davidgarrido.myweather.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.davidgarrido.myweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.imageButtonSearch)
    ImageButton imageButtonSearch;
    @BindView(R.id.editTextName)
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().length() == 0){
                    Toast.makeText(SearchActivity.this, "Please complete the text field", Toast.LENGTH_LONG).show();
                }else{
                    MainActivity.code = 2;
                    Intent intent2 = new Intent(SearchActivity.this, MainActivity.class);
                    intent2.putExtra("cityName", editTextName.getText().toString());
                    startActivity(intent2);
                }
            }
        });
    }
}
