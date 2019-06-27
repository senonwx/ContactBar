package com.senon.contactbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "#"};
    private TextView tv_dialog;
    private SideBar sideBar;
    private ListView listView;
    private ContactAdapter adapter;
    private List<PersonData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_dialog = findViewById(R.id.tv_dialog);
        sideBar = findViewById(R.id.sideBar);
        listView = findViewById(R.id.listView);

        //外部textview显示选中字母
//        sideBar.setTextView(tv_dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listView.setSelection(position);
                }
                Log.e("onTouchingLetterChanged", "  s=   " + s);
            }
        });

        setListData();
        setListView();
    }

    public void change(View view) {
        sideBar.setTextList(a);
    }

    //模拟联系人数据
    private void setListData() {
        list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            char letter = (char) (Math.random() * 26 + 'a');

            int randomJ = new Random().nextInt(10) + 1;
            for (int j = 0; j < randomJ; j++) {
                PersonData personData = new PersonData();
                personData.setLetters(letter + "");
                personData.setName(letter + "");

                int randomK = new Random().nextInt(5) + 3;
                for (int k = 0; k < randomK; k++) {
                    personData.setName(personData.getName() + (char) (Math.random() * 26 + 'a'));
                }
                list.add(personData);
            }
        }

        //对联系人进行首字母排序
        Collections.sort(list, new PinyinComparator());
    }

    //设置适配器
    private void setListView() {
        adapter = new ContactAdapter(this, list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
