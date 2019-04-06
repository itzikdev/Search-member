package itzik.com.membersimulator.view.bordView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import itzik.com.membersimulator.represent_search_member.model.SpaceMember;
import itzik.com.membersimulator.view.bordView.bord_model.Bord;

public class SquaresGenerateView extends View {


    public static final String TAG = SquaresGenerateView.class.getSimpleName();

    protected Bord bord;
    private ArrayMap<Integer,Paint> paintsMap;
    protected int  cellWidth,cellHeight;

    public SquaresGenerateView(Context context) {
        super(context);
    }

    public SquaresGenerateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaresGenerateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(Bord b){
        this.bord = b;
        initPaintsMap();
        //calculateDimensions();
        Log.d(TAG, "initView:");
    }

    public void setNewBordWithDraw(Bord b){
        this.bord = b;
        initPaintsMap();
        calculateDimensions();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged:");
        calculateDimensions();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawsSquareTableView(canvas);
    }



    protected void calculateDimensions() {
        if(bord!=null) {
            if (bord.getRow() < 1 || bord.getCol() < 1) {
                return;
            }

            cellWidth = getWidth() / bord.getCol();
            cellHeight = getHeight() / bord.getRow();
            Log.d(TAG, "calculateDimensions: cell w? "+cellWidth+" , cell h? "+cellHeight);
            invalidate();
        }
    }



    private void drawsSquareTableView(Canvas canvas){
        if(bord!=null) {
            //canvas.drawCircle(60,60,10,paintsMap.get(1));
            int[][] matrix = bord.getMatrix();
            int width = getWidth();
            int height = getHeight();
            Log.d(TAG, "drawsSquareTableView: w? "+width);
            Log.d(TAG, "drawsSquareTableView: h? "+height);
            for(int i = 0 ; i<matrix.length ; i++){
                for(int j = 0 ; j<matrix[0].length ; j++){
                    //System.out.print("["+matrix[i][j]+"]");
                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            paintsMap.get(matrix[i][j]));
                }
                //System.out.println();
            }

            for (int i = 1; i <matrix[0].length; i++) {
                canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, paintsMap.get(0));
            }

            for (int i = 1; i < matrix.length; i++) {
                canvas.drawLine(0, i * cellHeight, width, i * cellHeight, paintsMap.get(0));
            }
        }
    }

    private void initPaintsMap(){
        paintsMap = new ArrayMap<Integer, Paint>();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paintsMap.put(0,paint);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        paintsMap.put(1,paint);
        if(bord!=null){
            ArrayList<SpaceMember> spaceList = bord.getSpaceMembers();
            if(spaceList!=null) {
                final Random random = new Random();
                for (int i = 0; i < spaceList.size(); i++) {
                    final SpaceMember spaceMember = spaceList.get(i);
                    final int keyColor = spaceMember.getColorMember();
                    Paint paintMember = new Paint();
                    paintMember.setStyle(Paint.Style.FILL_AND_STROKE);
                    Log.d(TAG, "key color? "+keyColor);
                    int a = random.nextInt(255);
                    int r = random.nextInt(255);
                    int g = random.nextInt(255);
                    int b = random.nextInt(255);
                    //Log.d(TAG, "initPaintsMap: a? "+a+"\tr? "+r+"\tg? "+g+"\tb? "+b);
                    paintMember.setARGB(255,r,g,b);
                    paintsMap.put(keyColor,paintMember);
                }
            }
        }
        Log.d(TAG, "initPaintsMap:");
    }


}
