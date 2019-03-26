package com.sunnsoft.goodsdetaildemo;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.request.RequestOptions;
import com.sunnsoft.goodsdetaildemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends BaseActivity {

    private IdeaViewPager viewPager;
    private IdeaScrollView ideaScrollView;
    private LinearLayout header;
    private RadioGroup radioGroup;
    private ImageView iv_back, iv_collection, iv_share;
    private WebView web_view;
    private LinearLayout headerParent;
    private float currentPercentage = 0;
    private float limit_percentage = 0.9f;
    private View statusBar;

    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    ideaScrollView.setPosition(i);
                }
            }
        }
    };
    private boolean isNeedScrollTo = true;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        initStatusBar();
        header = (LinearLayout) findViewById(R.id.header);
        headerParent = (LinearLayout) findViewById(R.id.headerParent);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ideaScrollView = (IdeaScrollView) findViewById(R.id.idea_scrollview);
        viewPager = (IdeaViewPager) findViewById(R.id.view_pager);
        web_view = findViewById(R.id.web_view);
        iv_back = findViewById(R.id.iv_back);
        iv_share = findViewById(R.id.iv_share);
        iv_collection = findViewById(R.id.iv_collection);

        ViewGroup.LayoutParams para = viewPager.getLayoutParams();
        para.height = Global.sScreenWidth;
        para.width = Global.sScreenWidth;
        viewPager.setLayoutParams(para);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setUseWideViewPort(true);
        web_view.getSettings().setLoadWithOverviewMode(true);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        web_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        String d = "<div>有生之年狭路相逢，终不能幸免</div><div><img style=\"width: 1041px;\" src=\"http://p3.so.qhimgs1.com/sdr/200_200_/t01aa29b0a32f7826c3.jpg\"></div><div>懂事之前</div><div>情动以后</div><div>长不过一天~~</div><div><img style=\"width: 1041px;\" src=\"http://i1.umei.cc/uploads/tu/201701/798/s11u0nvggfr.jpg\"><br></div><div><br></div><div><br></div>";
        web_view.loadDataWithBaseURL(null, d, "text/html", "utf-8", null);
        //web_view.loadUrl("https://www.baidu.com/");
        List<String> mData = new ArrayList<>();
        mData.add("http://i1.umei.cc/uploads/tu/201701/798/hnqz4tavams.jpg");
        mData.add("http://i1.umei.cc/uploads/tu/201608/80/aptbl35fuwm.jpg");
        mData.add("http://i1.umei.cc/uploads/tu/201701/798/crxieha1urr.jpg");
        initImages(mData);
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        ideaScrollView.setViewPager(viewPager, getMeasureHeight(headerParent) - rectangle.top);

        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());

        View one = findViewById(R.id.one);
        View two = findViewById(R.id.two);
        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(one) - getMeasureHeight(headerParent)-rectangle.top);
        araryDistance.add(getMeasureHeight(one) + getMeasureHeight(two) - getMeasureHeight(headerParent)-rectangle.top);

        ideaScrollView.setArrayDistance(araryDistance);

        ideaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {

                int color = getAlphaColor(percentage > 0.9f ? 1.0f : percentage);
                header.setBackgroundDrawable(new ColorDrawable(color));
                if (statusBar != null) {
                    statusBar.setBackgroundDrawable(new ColorDrawable(color));
                }
                radioGroup.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);

                setRadioButtonTextColor(percentage);

            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        ideaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                radioGroup.check(radioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        radioGroup.setOnCheckedChangeListener(radioGroupListener);
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v, int id) {

    }


    private void initImages(final List<String> imgsList) {


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgsList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(Main2Activity.this);
                RequestOptions options = new RequestOptions();
                options.centerCrop()
                        .error(R.color.c_007AFF);
                Global.displayImage(imgsList.get(position), view, options);
                container.addView(view);
                return view;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);

    }

    public void setRadioButtonTextColor(float percentage) {
        radioGroup.setAlpha((percentage > limit_percentage ? 1.0f : percentage) * 255);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            radioButton.getBackground().setAlpha((int) ((percentage > limit_percentage ? 1.0f : percentage) * 255));
            radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor((percentage > limit_percentage ? 1.0f : percentage)) : getRadioAlphaColor((percentage > limit_percentage ? 1.0f : percentage)));
            if (percentage > 0.1f) {
                radioButton.setEnabled(true);
            } else {
                radioButton.setEnabled(false);
            }
        }

        iv_back.getBackground().setAlpha((int) ((1.0f - (percentage > limit_percentage ? 1.0f : percentage)) * 255));
        iv_share.getBackground().setAlpha((int) ((1.0f - (percentage > limit_percentage ? 1.0f : percentage)) * 255));
        iv_collection.getBackground().setAlpha((int) ((1.0f - (percentage > limit_percentage ? 1.0f : percentage)) * 255));
        if (percentage > limit_percentage) {
            iv_share.setImageDrawable(Global.getDrawable(R.drawable.icon_detail_share_gray));
            iv_back.setImageDrawable(Global.getDrawable(R.drawable.arrow_back));

            iv_collection.setImageDrawable(Global.getDrawable(R.drawable.icon_collection_n_gray));
        } else {
            iv_share.setImageDrawable(Global.getDrawable(R.drawable.icon_detail_share));
            iv_back.setImageDrawable(Global.getDrawable(R.drawable.arrow_back_white));
            iv_collection.setImageDrawable(Global.getDrawable(R.drawable.icon_collection_n_white));
        }


        this.currentPercentage = percentage;
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBar = findViewById(R.id.view_status_bar);
            statusBar.setVisibility(View.VISIBLE);
            int statusBarHeight = Global.getStatusBarHeight(this);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = statusBarHeight;
            statusBar.setLayoutParams(layoutParams);
        }
    }


    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f) {
        return Color.argb((int) (f * 255), 255, 255, 255);
    }

    public int getLayerAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x09, 0xc1, 0xf4);
    }

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 240, 135, 25);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 153, 153, 153);
    }
}
