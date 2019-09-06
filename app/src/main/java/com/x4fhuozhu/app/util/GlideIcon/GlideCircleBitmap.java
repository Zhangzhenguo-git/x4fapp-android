package com.x4fhuozhu.app.util.GlideIcon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * @author Zhangzhenguo
 * @create 2019/8/24
 * @Email 18311371235@163.com
 * @Describe
 */
public class GlideCircleBitmap extends BitmapTransformation {
    public GlideCircleBitmap(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool,toTransform);
    }


    /**
     * 修改圆角
     * @param pool
     * @param source
     * @return
     */
    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        // 边长取长宽最小值
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);// ARGB_4444——代表4x4位ARGB位图,ARGB_8888——代表4x8位ARGB位图
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        // setShader 对图像进行渲染
        // 子类之一 BitmapShader设置Bitmap的变换  TileMode 有CLAMP （取bitmap边缘的最后一个像素进行扩展），REPEAT（水平地重复整张bitmap）
        //MIRROR(和REPEAT类似，但是每次重复的时候，将bitmap进行翻转)
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);// 抗锯齿
        // 半径取 size的一半
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
