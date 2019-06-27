# ContactBar
右边联系人滑动字母索引
-------

1.默认使用方法：   
```
<com.senon.contactbar.SideBar  
  app:mTextSize="10dp"//索引字体大小 
  app:mTextColor="#858c94"//索引字体颜色 
  app:mRadius="5dp"//展示选中字体背景圆角 
  app:mBgTextColor="#ffffff"//展示选中字体颜色  
  app:mBgTextSize="40dp"//展示选中字体大小 
  app:mBgColor="#aa000000"//展示选中字体背景颜色  
  android:id="@+id/sideBar" 
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  android:layout_marginTop="100dp"  
  android:layout_marginBottom="100dp"  
  />
```
 //监听到滑动的索引
```
sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {  
  @Override  
  public void onTouchingLetterChanged(String s) {  
    Log.e("onTouchingLetterChanged", "  s=   " + s);  
  }  
}); 
```
2.自定义选中字体展示Textview方法
  定义一个textview来展示被选中的字体，除上面两个步骤外，还需要多以下两个步骤即可。
```
<TextView
  android:background="@drawable/bg_toast"
  android:id="@+id/tv_dialog"
  android:layout_width="80dp"
  android:layout_height="80dp"
  android:layout_gravity="center"
  android:gravity="center"
  android:textColor="#ffffffff"
  android:textSize="35dp"
  android:visibility="gone" />
```
```
sideBar.setTextView(tv_dialog);
```
这样当索引被选中时，默认展示的是tv_dialog。</br>
#### 注意：设置了sideBar.setTextView()之后，不会展示SideBar的默认选中字体，需要自己定制展示Textview。
     
