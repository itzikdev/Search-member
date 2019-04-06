package itzik.com.membersimulator.view.bordView;

import android.content.Context;
import android.util.AttributeSet;

public class SpaceMemberView extends BaseBordView {




    public SpaceMemberView(Context context) {
        super(context);
    }

    public SpaceMemberView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpaceMemberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected SquaresGenerateView createSquaresView() {
        return new SquaresGenerateView(getContext());
    }
}
