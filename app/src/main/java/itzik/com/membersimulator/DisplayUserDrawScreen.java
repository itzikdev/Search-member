package itzik.com.membersimulator;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import itzik.com.membersimulator.utils.UtilView;
import itzik.com.membersimulator.view.bordView.SpaceMemberView;
import itzik.com.membersimulator.view.bordView.SpaceTouchMemberView;
import itzik.com.membersimulator.view.bordView.bord_model.Bord;

public class DisplayUserDrawScreen extends DisplayRandomizeScreen {


    public static final String TAG = DisplayUserDrawScreen.class.getSimpleName();

    private SpaceMemberView spaceMemberView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSpaceMemberView();
        Log.d(TAG, "onCreate: ");
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
    }


    @Override
    protected boolean isNeedRandomTask() {
        return false;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    protected boolean isNeedeHideSolveButton() {
        return false;
    }

    private void createSpaceMemberView(){
        Point screen = UtilView.getScreenSize(getApplicationContext());
        Bord bord = new Bord(null,getMatrixBord());
        spaceMemberView = new SpaceTouchMemberView(this);
        spaceMemberView.initView(bord);
        spaceMemberView.setId(UtilView.generateViewId());
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams((int)(screen.x*0.95f),(int)(screen.y*0.75f));
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);
        cb.addView(spaceMemberView,param);
        Log.d(TAG, "createSpaceMemberVie:");
    }
}
