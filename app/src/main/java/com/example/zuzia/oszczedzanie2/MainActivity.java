package com.example.zuzia.oszczedzanie2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    boolean visibilityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lista);
        visibilityList=false;
        listView.setVisibility(View.INVISIBLE);


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, getClassList(position));
                startActivity(intent);

            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        visibilityList=false;
        listView.setVisibility(View.INVISIBLE);
    }

    public Class getClassList(int position)
    {
        switch (position)
        {
            case 0:
                return Cele.class;

            case 1:
                return MojeUstawienia.class;

            case 2:
                return Statystyki.class;
        }

        return  null;
    }

    public void settingsClick(View view) {
        if(visibilityList)
        {
            visibilityList=false;
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            visibilityList=true;
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
            listView.setVisibility(View.INVISIBLE);
            visibilityList=false;
        return true;
    }
}
