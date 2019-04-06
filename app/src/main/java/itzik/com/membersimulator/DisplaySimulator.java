package itzik.com.membersimulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itzik.com.membersimulator.app.ParamsApp;
import itzik.com.membersimulator.utils.UtilSplitString;

public class DisplaySimulator extends AppCompatActivity implements TextWatcher,View.OnClickListener {


    public static final String TAG = DisplaySimulator.class.getSimpleName();

    private Unbinder instance;

    @BindView(R.id.display_matrix)
       EditText matrix_size_view;

    @BindView(R.id.randomize_button)
       Button randomize_button;

    @BindView(R.id.bonus_button)
     Button bonus_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_simulator);
        instance = ButterKnife.bind(this);
        matrix_size_view.addTextChangedListener(this);
        randomize_button.setOnClickListener(this);
        bonus_button.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance.unbind();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.randomize_button){
            final String contentSize = matrix_size_view.getText().toString();
            Log.d(TAG, "onClick: content size? "+contentSize);
            int [] size_matrix = splitMatrixSize(contentSize);
            if(size_matrix!=null) {
                launchToScreen(size_matrix,DisplayRandomizeScreen.class);
                Log.d(TAG, "launch to randomize member screen activity");
            }
        }else if(v.getId() == R.id.bonus_button){
            final String contentSize = matrix_size_view.getText().toString();
            int [] size_matrix = splitMatrixSize(contentSize);
            if(size_matrix!=null) {
                launchToScreen(size_matrix,DisplayUserDrawScreen.class);
                Log.d(TAG, "launch to user draw member screen activity");
            }
        }
    }


    private void launchToScreen(int [] size_matrix,Class cl){
        Intent randomizeActivity = new Intent(this,cl);
        randomizeActivity = randomizeActivity.putExtra(ParamsApp.KEY_SIZE_MATRIX,size_matrix);
        startActivity(randomizeActivity);
        finish();
    }


    private int [] splitMatrixSize(String val){
        if(val!=null && !val.isEmpty()){
           int [] sizeMatrix =  UtilSplitString.splitValue(val,",");
           return sizeMatrix;
        }

        return null;
    }



}
