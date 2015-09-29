package com.yds.scrollstop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yds.scrollstop.MyScrollView.OnScrollBack;

public class MainActivity extends Activity implements OnScrollBack,
		OnClickListener {

	private TextView tv_1;
	private TextView tv_2;
	private ListView lv;
	private List<String> list = new ArrayList<String>();
	private MyScrollView myScrollView;
	private View view_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myScrollView = (MyScrollView) findViewById(R.id.myScrollView);
		tv_1 = (TextView) findViewById(R.id.tv_1);
		tv_2 = (TextView) findViewById(R.id.tv_2);
		lv = (ListView) findViewById(R.id.lv);
		view_2 = findViewById(R.id.view_2);

		for (int i = 0; i < 30; i++) {
			list.add("" + i);
		}

		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, list));

		myScrollView.setOnScrollBack(this);
		myScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						onScrollChanged(myScrollView.getScrollY());
					}
				});

		tv_1.setOnClickListener(this);
		tv_2.setOnClickListener(this);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(MainActivity.this, "position-->" + arg2,
						Toast.LENGTH_SHORT).show();
			}
		});

		myScrollView.smoothScrollBy(0, 0);
	}

	@Override
	public void onScrollChanged(int scrollY) {
		int h = view_2.getHeight();
		tv_2.setTranslationY(Math.max(h, scrollY));
		if (scrollY >= h) {
			tv_2.setVisibility(View.VISIBLE);
			tv_1.setVisibility(View.INVISIBLE);
		} else {
			tv_2.setVisibility(View.GONE);
			tv_1.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (tv_1.getId()) {
		case R.id.tv_1:
			Toast.makeText(this, "First~", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tv_2:
			Toast.makeText(this, "Second~", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
