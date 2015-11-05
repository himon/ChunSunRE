package com.chunsun.redenvelope.ui.activity.scan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScanChunsunCodeResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.chunsun.redenvelope.scanlibrary.R.layout.activity_scan_chunsun_code_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.chunsun.redenvelope.scanlibrary.R.menu.menu_scan_chunsun_code_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.chunsun.redenvelope.scanlibrary.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
