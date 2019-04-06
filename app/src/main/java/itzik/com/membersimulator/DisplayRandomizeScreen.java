package itzik.com.membersimulator;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


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

public class DisplayRandomizeScreen extends BaseDisplayScreen/*extends AppCompatActivity implements View.OnClickListener*/ {

    public static final String TAG = DisplayRandomizeScreen.class.getSimpleName();



    //private int [][] matrixBord;


    private Unbinder instance;

    private SpaceMemberView spaceMemberView;

    @BindView(R.id.container_bord)
    RelativeLayout cb;

    @BindView(R.id.solve)
    Button solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_display_randomize_screen);
        setBordMatrix();
        instance = ButterKnife.bind(this);
        updateSolveButton();
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:");
        //createSpaceMemberView();
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
        if(v.getId() == R.id.solve){
            startSearchMemberTask();
            Log.d(TAG, "onClick: solve button");
        }
    }



    private void setBordMatrix(){
        int [] size = getSizeMatrix();
        if(size!=null) {
            ((AppSimulator)getApplication()).createMatrixBord(size[0],size[1]);
            Log.d(TAG, "setBordMatrix:");
        }
    }

    private int [] getSizeMatrix(){
         final Intent result = getIntent();
         if(result!=null){
            int [] size_matrix =  result.getIntArrayExtra(ParamsApp.KEY_SIZE_MATRIX);
             Log.d(TAG, "getSizeMatrix: size row? "+size_matrix[0]+" , col? "+size_matrix[1]);
            return size_matrix;
         }

         return null;
    }


     @Override
    protected int [][] getMatrixBord(){
        return ((AppSimulator)getApplication()).getMatrixBord();
    }


    @Override
    protected void finishRandomTask() {
        Log.d(TAG, "finishRandomTask:");
        createSpaceMemberView();
        visibleButtonSolve();
    }

    @Override
    protected boolean isNeedeHideSolveButton() {
        return true;
    }

    @Override
    protected void finishSearchMemberTask(ArrayList<SpaceMember> memberList) {
        launchToDrawSolveMemberScreen(memberList);
        Log.d(TAG, "finishSearchMemberTask:");
    }

    @Override
    protected boolean isNeedRandomTask() {
        return true;
    }


    private void updateSolveButton(){
        solve.setOnClickListener(this);
        if(isNeedeHideSolveButton())
            hideButtonSolve();

    }

    private void hideButtonSolve(){
        solve.setVisibility(View.INVISIBLE);
        Log.d(TAG, "hideButtonSolve:");
    }

    private void visibleButtonSolve(){
        solve.setVisibility(View.VISIBLE);
        Log.d(TAG, "visibleButtonSolve:");
    }


    private void launchToDrawSolveMemberScreen(ArrayList<SpaceMember> list){
        Intent solveMemberActivity = new Intent(this,DisplaySolveMemberScreen.class);
        solveMemberActivity = solveMemberActivity.putParcelableArrayListExtra(ParamsApp.KEY_LIST_SPACE_MEMBERS,list);
        startActivity(solveMemberActivity);
        finish();
        Log.d(TAG, "launchToDrawSolveMemberScreen:");
    }

    private void createSpaceMemberView(){
            Point screen = UtilView.getScreenSize(getApplicationContext());
            Bord bord = new Bord(null,getMatrixBord());
            spaceMemberView = new SpaceMemberView(this);
            spaceMemberView.initView(bord);
            spaceMemberView.setId(UtilView.generateViewId());
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams((int)(screen.x*0.95f),(int)(screen.y*0.75f));
            param.addRule(RelativeLayout.CENTER_HORIZONTAL);
            cb.addView(spaceMemberView,param);
            Log.d(TAG, "createSpaceMemberVie:");
    }



//    private void redrawSpaceMemberView(Bord bord){
//        if(spaceMemberView!=null){
//            spaceMemberView.reDrawNewBord(bord);
//            Log.d(TAG, "redrawSpaceMemberView:");
//        }
//    }






}
