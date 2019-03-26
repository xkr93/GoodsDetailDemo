package com.sunnsoft.goodsdetaildemo.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.sunnsoft.goodsdetaildemo.StatusBarCompat;

import java.lang.reflect.Field;

/**
 * Created by HeCh on 2017/3/13 0013.
 */

public abstract class BaseActivity extends AppCompatActivity implements IUIOperation {
    protected Bundle savedInstanceState;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //
            Window window = this.getWindow();
            ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //状态栏全透明时设置图标黑白色
                decorViewGroup.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //解决Android7.0后沉浸式状态栏灰色遮罩层
                try {
                    Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                    Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                    field.setAccessible(true);
                    field.setInt(decorViewGroup, Color.TRANSPARENT);  //改为透明
                } catch (Exception e) {
                }
            }
        }
        //设置状态图标深色（适配小米、魅族）
        StatusBarCompat.StatusBarLightMode(this, StatusBarCompat.StatusBarLightMode(this));
        setContentView(getLayoutRes());
        this.savedInstanceState = savedInstanceState;
        //拿到系统跟布局对象，可查找到activity所有子控件
        View root = findViewById(android.R.id.content);
        // 查找activity布局中所有的Button（ImageButton），并设置点击事件
//        Utils.findButtonAndSetOnClickListener(root, this);


        initView();
        initListener();
        initData();
    }


    /**
     * 查找空间的方法，可省略强转
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T> T findView(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    @Override
    public void onClick(View view) {
        onClick(view, view.getId());
    }


    private ProgressDialog mPDialog;

    /**
     * 显示加载提示框(不能在子线程调用)
     */
    public void showProgressDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPDialog = new ProgressDialog(BaseActivity.this);
                mPDialog.setMessage(message);
                // 点击外部时不销毁
                mPDialog.setCanceledOnTouchOutside(false);

                // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
                if (!isFinishing())
                    mPDialog.show();
            }
        });
    }

    /**
     * 销毁加载提示框
     */
    public void dismissProgressDialog() {
        if (mPDialog != null) {
            mPDialog.dismiss();
            mPDialog = null;
        }
    }

    public void showMyDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("确定", null);
        alertDialog.show();
    }

}
