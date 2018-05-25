package com.icebreaker.timelapse.fragment;

/**
 * Created by Marhong on 2018/5/25.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.icebreaker.timelapse.R;
import com.icebreaker.timelapse.internet.HttpGetData;
import com.icebreaker.timelapse.person.PersonActivity;
import com.icebreaker.timelapse.person.Record;
import com.icebreaker.timelapse.person.RecordAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 展示战绩信息的Fragment
 * @author Marhong
 * @time 2018/5/25 15:58
 */
public class RecordFragment extends BaseFragment implements View.OnClickListener{
    private ListView mList;
    private RecordAdapter mAdapter;
    private String userName;
    private static final int GET_USER_RECORDS = 1;
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_USER_RECORDS:
                    showRecords(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.record_page, container, false);
        initView(view);
        getCurUserRecords();
        return view;

    }

    /**
     * 初始化RecordFragment界面所有控件
     * @author Marhong
     * @time 2018/5/25 17:48
     */
    private void initView(View view) {
        // TODO Auto-generated method stub
        mList = (ListView)view.findViewById(R.id.list);
    }

    /**
     * 给控件设置监听器
     * @author Marhong
     * @time 2018/5/25 17:48
     */
    private void setListener(){

    }
    
    /**
     * 获取当前用户的所有对战记录
     * @author Marhong
     * @time 2018/5/25 23:30
     */
    private void getCurUserRecords(){
        SharedPreferences user = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
         userName = user.getString("userName",null);
        final ArrayList<NameValuePair> arrayValues = new ArrayList<NameValuePair>();
        arrayValues.add(new BasicNameValuePair("userName", userName));
        new Thread(new Runnable() { // 开启线程上传文件
            @Override
            public void run() {
                String loginResult = "";
                loginResult = HttpGetData.GetData("http://192.168.1.112:8080/Timelapse/getRecordsServlet", arrayValues);
                Message msg = new Message();
                msg.obj = loginResult;
                msg.what = GET_USER_RECORDS;
                uiHandler.sendMessage(msg);
            }
        }).start();
    }
    
    /**
     * 将获取的json数组格式的字符串以ListView的方式展示出来
     * @author Marhong
     * @time 2018/5/25 23:41
     */
    private void showRecords(String jsonArrayString){
        Log.e("JSONArray",jsonArrayString);
        ArrayList<Record> records = new ArrayList<Record>();
        try{
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Long id = jsonObject.getLong("id");
                String initiator = jsonObject.getString("initiator");
                String receiver = jsonObject.getString("receiver");
                String time = jsonObject.getString("time");
                int initiatorResult = jsonObject.getInt("initiatorResult");
                int receiverResult = jsonObject.getInt("receiverResult");
                int iniUnfinishedNum = jsonObject.getInt("iniUnfinishedNum");
                int recUnfinishedNum = jsonObject.getInt("recUnfinishedNum");
                Record record = new Record(id,initiator,receiver,time,initiatorResult,receiverResult,iniUnfinishedNum,recUnfinishedNum);
                Log.e("Record",record.toString());
                records.add(record);
            }

            if(records.size()>0){
                mAdapter = new RecordAdapter(records,getContext(),userName);
                Log.e("记录个数",String.valueOf(mAdapter.getCount()));
                mList.setAdapter(mAdapter);
                mList.invalidate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        PersonActivity.curFragmentTag = getString(R.string.str_record);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

        }
    }
}
