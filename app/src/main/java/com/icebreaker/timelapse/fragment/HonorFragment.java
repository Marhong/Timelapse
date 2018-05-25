package com.icebreaker.timelapse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icebreaker.timelapse.R;
import com.icebreaker.timelapse.person.PersonActivity;


/**
 * 展示荣誉信息界面
 * @author Marhong
 * @time 2018/5/25 19:35
 */
public class HonorFragment extends BaseFragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.honor_page, container, false);
        initview(view);

        return view;
    }

    private void initview(View view) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        PersonActivity.curFragmentTag = getString(R.string.str_honor);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

        }
    }
}
