package com.gitstudy.history;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";
    private EditText mEditText;
    private ArrayAdapter<String> mArrAdapter;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private RelativeLayout mSearchHistoryLl;
    private List<String> mHistoryKeywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        mPref = getSharedPreferences("test", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
        mHistoryKeywords = new ArrayList<String>();
        final ImageView clearKeywordIv = (ImageView) findViewById(R.id.clear_keyword_iv);
        clearKeywordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                v.setVisibility(View.GONE);
            }
        });
        mEditText = (EditText) findViewById(R.id.et_search);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mEditText.getText().length() > 0) {
                        save();
                    } else {
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    clearKeywordIv.setVisibility(View.GONE);
                    if (mHistoryKeywords.size() > 0) {
                        mSearchHistoryLl.setVisibility(View.VISIBLE);
                    } else {
                        mSearchHistoryLl.setVisibility(View.GONE);
                    }
                } else {
                    mSearchHistoryLl.setVisibility(View.GONE);
                    clearKeywordIv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEditText.requestFocus();
        initSearchHistory();
    }
    public void initSearchHistory() {
        mSearchHistoryLl = (RelativeLayout) findViewById(R.id.search_history_rl);
        ListView listView = (ListView) findViewById(R.id.search_history_lv);
        findViewById(R.id.clear_history_btn).setOnClickListener(this);
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            if (list.size() > 5) {
                for (int i = 0; i < 5; i++) {
                    mHistoryKeywords.add(i, list.get(i));
                }
            } else {
                mHistoryKeywords = list;
            }
//            mHistoryKeywords = list;
        }
        if (mHistoryKeywords.size() > 0) {
            mSearchHistoryLl.setVisibility(View.VISIBLE);
        } else {
            mSearchHistoryLl.setVisibility(View.GONE);
        }
        mArrAdapter = new ArrayAdapter<String>(this, R.layout.item_search_history, mHistoryKeywords);
        listView.setAdapter(mArrAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEditText.setText(mHistoryKeywords.get(i));
                mSearchHistoryLl.setVisibility(View.GONE);
            }
        });
        mArrAdapter.notifyDataSetChanged();
    }

    public void save() {
        String text = mEditText.getText().toString();
        String oldText = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        System.out.println("zlw=======" + oldText);
        if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
            if (mHistoryKeywords.size() > 4) {
//                Toast.makeText(this,"最多保存5条历史",Toast.LENGTH_SHORT).show();
                mHistoryKeywords.remove(4);
//                return;
            }
            mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            mEditor.commit();
            mHistoryKeywords.add(0, text);
        }
        mArrAdapter.notifyDataSetChanged();
    }

    public void cleanHistory() {
        mEditor.clear();
        mEditor.commit();
        mHistoryKeywords.clear();
        mArrAdapter.notifyDataSetChanged();
//        mSearchHistoryLl.setVisibility(View.GONE);
        Toast.makeText(this, "清除搜索历史记录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_history_btn:
                cleanHistory();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String keyword = intent.getStringExtra(EXTRA_KEY_KEYWORD);
        if (!TextUtils.isEmpty(keyword)) {
            mEditText.setText(keyword);
        }
    }
}
