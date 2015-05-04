package com.example.diane.testingcolorselectorlibrary;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.rosalinesrandoms.colourSelector.ColorSelectorDialog;
import com.rosalinesrandoms.colourSelector.ColourSelector;
import com.rosalinesrandoms.colourSelector.SearchSelector;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.rosalinesrandoms.colorSelector.R;


public class TestActivity extends ActionBarActivity {

    @InjectView(R.id.get_current_color)
    public Button getColor;
    @InjectView(R.id.current_color_text)
    public TextView colorText;
    @InjectView(R.id.color_selector)
    public ColourSelector colorSelector;
    @InjectView(R.id.search_selector)
    public SearchSelector searchSelector;
    @InjectView(R.id.copy_selector)
    public ColourSelector copySelector;
    @InjectView(R.id.button)
    public ButtonRectangle buttonRectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
        Log.i("color", "creating activity");
        colorSelector.setFragmentManager(getSupportFragmentManager());

        colorSelector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("color", "click");
                DialogFragment dialog = new ColorSelectorDialog();
                Bundle args = new Bundle();
                args.putInt("color", 0xFFFFFFFF);
                dialog.setArguments(args);

                dialog.show(getSupportFragmentManager(), "tag");

            }
        });
    }

    @OnClick(R.id.get_current_color)
    public void setText(){
        copySelector.setColor(colorSelector.getColor());
        Log.i("color", "colorSelector is "+colorSelector.getColor());
        colorText.setTextColor(copySelector.getHue());
        buttonRectangle.setBackgroundColor(colorSelector.getColor());

        Log.i("color", copySelector.getHue() + " " + copySelector.getHuePosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
