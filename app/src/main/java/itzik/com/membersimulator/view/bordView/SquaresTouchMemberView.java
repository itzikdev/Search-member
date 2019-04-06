package itzik.com.membersimulator.view.bordView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class SquaresTouchMemberView extends SquaresGenerateView {

    public static final String TAG = SquaresTouchMemberView.class.getSimpleName();


    public SquaresTouchMemberView(Context context) {
        super(context);
    }

    public SquaresTouchMemberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaresTouchMemberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent: x? "+event.getX()+", y? "+event.getY());
            Log.d(TAG, "onTouchEvent: cell w? "+cellWidth+", h? "+cellHeight);
            int column = /*(int)(getWidth()/event.getX());*/  (int) (event.getY() / cellHeight);
            int row = /*(int)(getHeight()/event.getY());*/   (int) (event.getX() /  cellWidth);
            Log.d(TAG, "onTouchEvent: row? "+row+"\ncol? "+column);
            changeCellInBordMatrix(row,column);
        }

        return true;
    }

    private void changeCellInBordMatrix(final int row,final int col){
        int [][] matrix = bord.getMatrix();
        if((row>=0 && row<matrix.length) && (col>=0 && col<matrix[0].length)){
            matrix[row][col] = 1;
            invalidate();
            //calculateDimensions();
            Log.d(TAG, "changeCellInBordMatrix:");
        }

    }
}
