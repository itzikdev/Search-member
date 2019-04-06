package itzik.com.membersimulator.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.concurrent.atomic.AtomicInteger;

public class UtilView {

    public static final String TAG = UtilView.class.getSimpleName();

    private static final AtomicInteger viewIdGenerator = new AtomicInteger(15000000);


    public static Point getScreenSize(Context context){
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(point);
        return point;
    }

    public static int generateViewId(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return generateUniqueViewId();
        } else {
            return View.generateViewId();
        }
    }



    private static int generateUniqueViewId() {
        final int result = viewIdGenerator.get();
        while (true) {
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (viewIdGenerator.compareAndSet(result, newValue)) {
                break;
            }
        }
        Log.d(TAG, "generateUniqueViewId:   "+result);
        return result;
    }
}
