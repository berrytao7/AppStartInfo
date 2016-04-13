package com.berry.appstartinfo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
 List<String> list = null;
 List<ResolveInfo> mApps = null;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btn = (Button) findViewById(R.id.btn);  
    ListView listView = (ListView) findViewById(R.id.listView);  
    list = new ArrayList<String>();  
    getList();  
    ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(  
            MainActivity.this, android.R.layout.simple_list_item_1, list);  
    listView.setAdapter(mAdapter);  
    btn.setOnClickListener(new OnClickListener() {  

        @Override  
        public void onClick(View v) {  
            Intent intent = new Intent();  
            ComponentName comp = new ComponentName(  
                    "com.android.mmreader362",  
                    "com.android.mmreader362.mmreader");  
            intent.setComponent(comp);  
            intent.setAction("android.intent.action.MAIN");  
            startActivity(intent);  
        }  
    });  
  }
  private void getList() {  
    list.clear();  
    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);  
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
    mApps = getPackageManager().queryIntentActivities(mainIntent, 0); 
    ResolveInfo info = null;
    for (int i = 0; i < mApps.size(); i++) {  
        info = mApps.get(i);  
        String appLabel = info.loadLabel(getPackageManager()).toString();  
        String packagename = info.activityInfo.packageName;  
        String appname = info.activityInfo.name;  
        list.add(appLabel + " : package:" + packagename  
                + " || activity:" + appname);  
    }  
}  
}
