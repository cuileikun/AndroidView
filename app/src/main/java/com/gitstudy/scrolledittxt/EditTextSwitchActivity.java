package com.gitstudy.scrolledittxt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gitstudy.R;

public class EditTextSwitchActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "EditTextSwitchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_switch);
        mEditText = (EditText) findViewById(R.id.edt_telephone_number);
        mTextView = (TextView) findViewById(R.id.tv_telephone_number);
        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "onLongClick: " + "textView被长按了");
                mEditText.setVisibility(View.VISIBLE);
                mTextView.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    String content = mEditText.getText().toString().trim();
                    mTextView.setVisibility(View.VISIBLE);
                    mTextView.setText(content);
                    mEditText.setVisibility(View.GONE);

                    return true;
                }
                return false;
            }
        });
    }

    public void hideKeyboard() {//隐藏软键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
