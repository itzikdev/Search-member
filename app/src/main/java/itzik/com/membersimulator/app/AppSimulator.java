package itzik.com.membersimulator.app;

import android.app.Application;
import android.util.Log;

public class AppSimulator extends Application {

    public static final String TAG = AppSimulator.class.getSimpleName();

    private static  int [][] matrixBord;
    private static  Object lock;

    @Override
    public void onCreate() {
        super.onCreate();
        lock = new Object();
        Log.d(TAG, "onCreate:");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate:");
    }


    public int [][] getMatrixBord(){
        return matrixBord;
    }

    public void createMatrixBord(int row,int col){
        synchronized (lock) {
           matrixBord = new int[row][col];
            Log.d(TAG, "createMatrixBord:");
        }
    }

    public void destroyMatrixBord(){
        synchronized (lock){
            matrixBord = null;
            Log.d(TAG, "destroyMatrixBord:");
        }
    }
}
