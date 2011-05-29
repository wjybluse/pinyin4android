package com.pinyin4android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView textView = (TextView) findViewById(R.id.demotext);
        TextView textView1 = (TextView) findViewById(R.id.demotext1);
        textView.setText(PinyinUtil.toPinyin(this, "这是一个测试"));
        textView1.setText(String.valueOf(PinyinUtil.toPinyin(this, '我')));
    }
}
