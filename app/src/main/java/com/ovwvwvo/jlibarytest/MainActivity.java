package com.ovwvwvo.jlibarytest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.funny.addworddemo.view.AddWordFrame;
import com.funny.addworddemo.view.AddWordOutsideLinearLayout;
import com.ovwvwvo.jlibrary.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    AddWordOutsideLinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    int i = 0;

    @Override
    protected void onStart() {
        super.onStart();
        AddWordFrame addWordFrame = new AddWordFrame(this);
        layout = addWordFrame.getLayout();
        layout.setText("asdsadfsda\n jiayaguang\n加压刮刮khhhjy撒旦飞洒发送的是的发生的方式分 是否对萨达");

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i(layout + "  11111");
                if (i % 2 == 0)
                    layout.setTextViewOrientation(LinearLayout.VERTICAL);
                else
                    layout.setTextViewOrientation(LinearLayout.HORIZONTAL);
                i++;
                LogUtil.i(layout + "  22222");
            }
        });
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

}
