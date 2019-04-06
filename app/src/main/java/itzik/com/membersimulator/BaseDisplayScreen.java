package itzik.com.membersimulator;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import itzik.com.membersimulator.represent_search_member.SearchMember;
import itzik.com.membersimulator.represent_search_member.UtilsGenerator;
import itzik.com.membersimulator.represent_search_member.model.SpaceMember;

public  abstract class BaseDisplayScreen extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = BaseDisplayScreen.class.getSimpleName();

    private static final int FINISH_RANDOMIZE_TASK = 0x1;
    private static final int FINISH_SEARCH_MEMBER  = 0x2;


    private HandlerThread handlerThread;
    private HandlerMainThread handlerActivity;
    private Handler runnableTask;
    private Runnable task;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandlerThread();
        if(isNeedRandomTask()){
            startRandomizeMatrixTask();
        }
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
        finishHandlerThread();
        super.onDestroy();
    }




    protected  abstract boolean isNeedRandomTask();
    protected abstract int [][] getMatrixBord();
    protected abstract void finishRandomTask();
    protected abstract boolean isNeedeHideSolveButton();
    protected abstract void finishSearchMemberTask(ArrayList<SpaceMember> list);






    protected void startSearchMemberTask(){
        finishTaskIfNeeded();
        task = new SearchMemberTask(getMatrixBord());
        runnableTask.post(task);
        Log.d(TAG, "startSearchMemberTask:");
    }



    private  void sendNotifyMainHandler(Message message){
        if(handlerActivity!=null && message!=null){
            handlerActivity.sendMessage(message);
            Log.d(TAG, "sendNotifyMainHandler:");
        }
    }



    private void finishTaskIfNeeded(){
        if(runnableTask!=null && task!=null){
            runnableTask.removeCallbacks(task);
            Log.d(TAG, "finishTaskIfNeeded:");
        }
    }


    private void finishHandlerThread(){
        finishTaskIfNeeded();
        final boolean destroy =  handlerThread.quitSafely();
        Log.d(TAG, "finishHandlerThread: destroy handler thread? "+destroy);
    }

    private void initHandlerThread(){
        handlerThread = new HandlerThread("ActivityHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handlerActivity = new HandlerMainThread(Looper.getMainLooper());
        runnableTask = new Handler(looper);
        Log.d(TAG, "initHandlerThread:");
    }



    private void startRandomizeMatrixTask(){
        finishTaskIfNeeded();
        task = new GeneratorRandomTask(getMatrixBord());
        runnableTask.post(task);
        Log.d(TAG, "startRandomizeMatrixTask:");
    }


    protected class SearchMemberTask implements Runnable{

        private int [][] matrix;

        public SearchMemberTask(int [][] matrix){
            this.matrix = matrix;
        }

        @Override
        public void run() {
            startSearchMember();
        }

        private void startSearchMember(){
            ArrayList<SpaceMember> spaceMemberList =  SearchMember.startSearchMatrixMembers(matrix);
            final Message message = Message.obtain();
            message.what = FINISH_SEARCH_MEMBER;
            message.obj = spaceMemberList;
            sendNotifyMainHandler(message);
        }
    }



    class GeneratorRandomTask implements Runnable{
        private int [][] matrix;

        public GeneratorRandomTask(int  [][] matrix){
            this.matrix = matrix;
        }

        @Override
        public void run() {
            startRandomize();
        }

        private void startRandomize(){
            UtilsGenerator.generatorRandomMemberMatrix(matrix);
            final Message message = Message.obtain();
            message.what = FINISH_RANDOMIZE_TASK;
            sendNotifyMainHandler(message);
        }
    }

    class HandlerMainThread extends Handler {
        HandlerMainThread(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FINISH_RANDOMIZE_TASK:
                    Log.d(TAG, "handleMessage: finish randomize task");
                    finishRandomTask();
                    break;

                case FINISH_SEARCH_MEMBER:
                    Log.d(TAG, "handleMessage: finish search member");
                    finishSearchMemberTask((ArrayList<SpaceMember>)msg.obj);
                    //launchToDrawSolveMemberScreen((ArrayList<SpaceMember>)msg.obj);
                    //Bord b = new Bord((ArrayList<SpaceMember>)msg.obj,getMatrixBord());
                    //redrawSpaceMemberView(b);
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
