package com.gitstudy.listview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gitstudy.R;

public class Mydilog extends Dialog implements
		View.OnClickListener {

	private TextView leftTextView;
	private TextView rightTextView;
	private Context context;
	private IDialogOnclickInterface dialogOnclickInterface;

	public Mydilog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dialog);

		leftTextView = (TextView) findViewById(R.id.textview_one);
		rightTextView = (TextView) findViewById(R.id.textview_two);

		leftTextView.setOnClickListener(this);
		rightTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		dialogOnclickInterface = (IDialogOnclickInterface) context;
		switch (v.getId()) {
		case R.id.textview_one:
			dialogOnclickInterface.leftOnclick();
			break;
		case R.id.textview_two:
			dialogOnclickInterface.rightOnclick();
			break;
		default:
			break;
		}
	}

	public interface IDialogOnclickInterface {
		void leftOnclick();

		void rightOnclick();
	}

}
