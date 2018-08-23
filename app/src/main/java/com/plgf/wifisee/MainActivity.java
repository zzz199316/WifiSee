package com.plgf.wifisee;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private WifiManage wifiManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManage = new WifiManage();
        try {
            Init();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Init() throws Exception {
        List<WifiInfo> wifiInfos = wifiManage.Read();
        ListView wifiInfosView=(ListView)findViewById(R.id.listview_wifi);
        WifiAdapter ad = new WifiAdapter(wifiInfos,MainActivity.this);
        wifiInfosView.setAdapter(ad);
    }

    public class WifiAdapter extends BaseAdapter {

        List<WifiInfo> wifiInfos =null;
        Context con;

        public WifiAdapter(List<WifiInfo> wifiInfos,Context con){
            this.wifiInfos =wifiInfos;
            this.con = con;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return wifiInfos.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return wifiInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            convertView = LayoutInflater.from(con).inflate(android.R.layout.simple_list_item_1, null);
            TextView tv = (TextView)convertView.findViewById(android.R.id.text1);
            tv.setText("Wifi:"+wifiInfos.get(position).Ssid+"\n密码:"+wifiInfos.get(position).Password);
            return convertView;
        }

    }

}
