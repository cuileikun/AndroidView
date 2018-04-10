package com.gitstudy.tablayout.verticaltablayout;

/**
 * Created by mbcloud-cuilk on 2018/4/10.
 */

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gitstudy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    String[] array = new String[]{"Android 1", "Android 2", "Android 3",
            "Android 4", "Android 5", "Android 6", "Android 7", "Android 8",
            "Android 9", "Android 10", "Android 11", "Android 12", "Android 13",
            "Android 14", "Android 15", "Android 16"};

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tablayout, container, false);
        Log.d("Debug", "creating fragment " + getArguments().getInt(ARG_SECTION_NUMBER));
        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                rootView.setBackgroundColor(Color.WHITE);
                break;
        }
        final ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.tablayout_list_item, R.id.text1, array));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), "点击了第" + position + "个条目" + array[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}

