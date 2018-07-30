package ysaak.geotoolbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ysaak.geotoolbox.menu.ToolMenuItem;
import ysaak.geotoolbox.menu.ToolsMenuAdapter;
import ysaak.geotoolbox.wordvalue.WordValueActivity;

public class MainActivity extends Activity {

    private final List<ToolMenuItem> toolMenuList = new ArrayList<>();
    private ToolsMenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView menuList = findViewById(R.id.menu_list);

        menuAdapter = new ToolsMenuAdapter(toolMenuList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        menuList.setLayoutManager(mLayoutManager);
        menuList.setItemAnimator(new DefaultItemAnimator());
        menuList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        menuList.setAdapter(menuAdapter);

        menuList.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), menuList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToolMenuItem item = toolMenuList.get(position);
                startSubActivity(item.activityClass);
            }

            @Override
            public void onLongClick(View view, int position) {/**/}
        }));

        prepareMenuItems();
    }

    private void prepareMenuItems() {
        toolMenuList.add(createItem(WordValueActivity.class, R.string.word_value_title, R.string.word_value_description));

        menuAdapter.notifyDataSetChanged();
    }

    private ToolMenuItem createItem(Class<?> activityClass, int titleId, int descriptionId) {
        return new ToolMenuItem(activityClass, getResources().getString(titleId), getResources().getString(descriptionId));
    }

    private void startSubActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
