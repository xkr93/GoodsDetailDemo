package com.sunnsoft.goodsdetaildemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;


/**
 * 全局的公共类，封装一些常用的方法
 * Created by HeCh on 2017/3/13 0013.
 */

public class Global {

    public static Context sContext;
    public static float sDensity;
    public static int sScreenWidth;
    public static int sScreenHeight;

    public static int sVersionCode;
    public static String sVersionName;

    private static int img_error_default = R.color.color_bg;
    private static int img_header_default = R.color.color_bg;
    private static int img_stores_logo_default = R.color.color_bg;

    public static int LOGIN_REQUEST_CODE = 0x000a;




    public static void init(Context context) {
        sContext = context;
        initScreenSize();
        getVersion();

    }



    /**
     * 获取屏幕属性，密度，宽度，高度
     */
    private static void initScreenSize() {
        DisplayMetrics dm = sContext.getResources().getDisplayMetrics();
        sDensity = dm.density;
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
    }

    /**
     * dp to px
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        return (int) (dp * sDensity);
    }

    /**
     * dp to px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return (int) (dp * sDensity);
    }

    public static View inflate(int layoutResID, ViewGroup parent) {
        return LayoutInflater.from(sContext).inflate(layoutResID, parent, false);
    }

    public static View inflate(int layoutResID) {
        return inflate(layoutResID, null);
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainHandler() {
        return mHandler;
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @return true表示当前是在主线程中运行
     */
    public static boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUIThread(Runnable run) {
        if (isUIThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }

    private static Toast mToast;

    /**
     * 可以在子线程中调用
     *
     * @param msg toast内容
     */
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(sContext, msg, Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    public static String getString(int stringId) {
        return sContext.getResources().getString(stringId);
    }

    public static float getDimen(int dimenId) {
        return sContext.getResources().getDimension(dimenId);
    }

    public static int getColor(int colorId) {
        return sContext.getResources().getColor(colorId);
    }

    public static Drawable getDrawable(int drawableId) {
        return sContext.getResources().getDrawable(drawableId);
    }

    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    /**
     * 判断APP是否运行在前台
     *
     * @param context
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }


    public static int getHeightDifference(Activity activity) {
        Rect r = new Rect();
        //获取当前界面可视部分
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight = activity.getWindow().getDecorView().getRootView()
                .getHeight();
        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
        int heightDifference = screenHeight - r.bottom;
        heightDifference = heightDifference - getSoftButtonsBarHeight(activity);

        return heightDifference;
    }

    /**
     * 底部虚拟按键栏的高度
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }


    public static void getVersion() {
        PackageManager packageManager = sContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(sContext.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            sVersionCode = packageInfo.versionCode;
            sVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * glide 加载图片
     */
    public static void displayImage(String imgUrl, ImageView target, int defaultValue) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(defaultValue);
        Glide.with(sContext).load(imgUrl).apply(options).into(target);
    }


    /**
     * glide 加载图片
     */
    public static void displayImage(String imgUrl, ImageView target) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(img_error_default);
        Glide.with(sContext).load(imgUrl).apply(options).into(target);
    }

    /**
     * glide 加载图片
     */
    public static void displayImage(String imgUrl, ImageView target, RequestOptions options) {
        Glide.with(sContext).load(imgUrl).apply(options).into(target);
    }


    /**
     * glide 加载图片
     */
    public static void displayImage(String imgUrl, ImageView target, float thumbnail) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(img_error_default);
        Glide.with(sContext).load(imgUrl).apply(options).thumbnail(thumbnail).into(target);
    }

    /**
     * glide 加载图片
     */
    public static void displayImageBg(String imgUrl, ImageView target) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(getColor(R.color.c_F6F9FA));
        Glide.with(sContext).load(imgUrl).apply(options).into(target);
    }

    /**
     * glide 加载图片
     */
    public static void displayDrawable(int imgUrl, final ImageView target) {

        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(img_error_default);
        Glide.with(sContext).load(imgUrl).apply(options).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                target.setImageDrawable(resource);
            }
        });
    }

    /**
     * glide 加载图片
     */
    public static void displayLocation(String imgUrl, final ImageView target) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(img_error_default);
        Glide.with(sContext).load(imgUrl).apply(options).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                target.setImageDrawable(resource);
            }
        });
    }


    /**
     * glide 加载
     */
    public static void displayImage(File file, ImageView target) {
        final RequestOptions options = new RequestOptions();
        options.centerCrop()
                .error(img_error_default);
        Glide.with(sContext).load(file).apply(options).into(target);
    }





}
