package itzik.com.membersimulator.view.bordView;

import android.content.Context;
import android.util.AttributeSet;

public class SpaceTouchMemberView extends SpaceMemberView {


    public static final String TAG = SpaceTouchMemberView.class.getSimpleName();


    public SpaceTouchMemberView(Context context) {
        super(context);
    }

    public SpaceTouchMemberView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpaceTouchMemberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected SquaresGenerateView createSquaresView() {
        return new SquaresTouchMemberView(getContext());
    }

}
