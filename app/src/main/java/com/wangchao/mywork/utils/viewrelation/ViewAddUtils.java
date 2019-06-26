package com.wangchao.mywork.utils.viewrelation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewAddUtils {
    private String className = "ViewAddUtils";
    public DisplayMetrics getScreenDisPlayMetrics(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    public View setImagesMatchParent(int resId, Context context, int width, int height) {
        ImageView imageView = new ImageView(context);
        Bitmap bitmap = getMyBitmap(context,resId,width,height);
        imageView.setImageBitmap(bitmap);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    private Bitmap getMyBitmap(Context context, int resId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(),resId,options);
        options.inSampleSize = 8;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(),resId,options);
    }

    private int caculateSampleSize(BitmapFactory.Options options, int width, int height) {
        int sample =1,tempWidth = options.outWidth,tempHeight = options.outHeight;
        while(tempWidth>width||tempHeight>height){
            sample*=2;
        }
        return sample;
    }
}
