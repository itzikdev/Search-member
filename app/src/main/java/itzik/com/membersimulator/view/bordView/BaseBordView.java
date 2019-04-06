package itzik.com.membersimulator.view.bordView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import itzik.com.membersimulator.utils.UtilView;
import itzik.com.membersimulator.view.bordView.bord_model.Bord;

public abstract class BaseBordView extends RelativeLayout {

    public static final String TAG = BaseBordView.class.getSimpleName();

     private SquaresGenerateView squaresGenerateVie;

     protected abstract SquaresGenerateView createSquaresView();

    public BaseBordView(Context context) {
        super(context);
        squaresGenerateVie = createSquaresView();
        squaresGenerateVie.setId(UtilView.generateViewId());
    }

    public BaseBordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initView(Bord bord){
        if(bord!=null){
            squaresGenerateVie.initView(bord);
            addView(squaresGenerateVie);
            Log.d(TAG, "initView:");
        }
    }

    public void reDrawNewBord(Bord bord){
      if(bord!=null){
          squaresGenerateVie.setNewBordWithDraw(bord);
          Log.d(TAG, "reDrawNewBord:");
      }
    }



}
