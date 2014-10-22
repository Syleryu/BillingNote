package com.axybn.billingnote;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            Resources resources = getResources();
            Drawable drawable = resources
                    .getDrawable(R.drawable.actionbar_background);
            actionBar.setBackgroundDrawable(drawable);
        }

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        
//        int uiOption = getWindow().getDecorView().getSystemUiVisibility();
//        uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE;
////          | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
////          | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        getWindow().getDecorView().setSystemUiVisibility(uiOption);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        case 0:
            Toast.makeText(this, "action menu", Toast.LENGTH_SHORT).show();
            return true;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        createMenu(menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void createMenu(Menu menu){
        MenuItem menuItem = menu.add(0,0,0,"item1");
        menuItem.setIcon(R.drawable.ic_launcher);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

}
