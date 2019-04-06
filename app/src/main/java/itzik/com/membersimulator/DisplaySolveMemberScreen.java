package itzik.com.membersimulator;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itzik.com.membersimulator.app.AppSimulator;
import itzik.com.membersimulator.app.ParamsApp;
import itzik.com.membersimulator.represent_search_member.model.SpaceMember;
import itzik.com.membersimulator.utils.UtilView;
import itzik.com.membersimulator.view.bordView.SpaceMemberView;
import itzik.com.membersimulator.view.bordView.bord_model.Bord;

public class DisplaySolveMemberScreen extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = DisplaySolveMemberScreen.class.getSimpleName();


    private Unbinder instance;
    private SpaceMemberView spaceMemberView;

    @BindView(R.id.container_bord)
    RelativeLayout cb;

    @BindView(R.id.title_sum_member)
    TextView sum_view;

    @BindView(R.id.restart)
    Button restart;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_solve_member_screen);
        instance = ButterKnife.bind(this);
        restart.setOnClickListener(this);
        createSpaceMemberView();
        setSumMembersFound(getMemberList().size());
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
    public void onClick(View v) {
        if(v.getId() == R.id.restart){
            launchToDisplaySimulatorScreen();
        }
    }

    private void launchToDisplaySimulatorScreen(){
        Intent displaySimulator = new Intent(this,DisplaySimulator.class);
        startActivity(displaySimulator);
        finish();
        Log.d(TAG, "launchToDisplaySimulatorScreen:");
    }

    private int [][] getMatrixBord(){
        return ((AppSimulator)getApplication()).getMatrixBord();
    }

    private void setSumMembersFound(final int size){
        sum_view.setText(String.format(getString(R.string.title_sum),size));
    }

    private ArrayList<SpaceMember> getMemberList(){
        final Intent result = getIntent();
        if(result!=null) {
            ArrayList<SpaceMember> list = result.getParcelableArrayListExtra(ParamsApp.KEY_LIST_SPACE_MEMBERS);
            Log.d(TAG, "getMemberList: size? "+list.size());
            return list;
        }
        return null;
    }

    private void createSpaceMemberView(){
        Point screen = UtilView.getScreenSize(getApplicationContext());
        Bord bord = new Bord(getMemberList(),getMatrixBord());
        spaceMemberView = new SpaceMemberView(this);
        spaceMemberView.initView(bord);
        spaceMemberView.setId(UtilView.generateViewId());
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams((int)(screen.x*0.95f),(int)(screen.y*0.75f));
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);
        cb.addView(spaceMemberView,param);
        Log.d(TAG, "createSpaceMemberVie:");
    }
}
