package com.senon.contactbar;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.List;

/**
 * 适配器
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;

    private List<PersonData> list;

    public ContactAdapter(Context context, List<PersonData> list) {
        this.context = context;
        this.list = list;
    }


    /**
     * 传入新的数据 刷新UI的方法
     */
    public void updateListView(List<PersonData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list == null)
            return null;

        if (position >= list.size())
            return null;

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final PersonData mContent = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.type_tv);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.item_igv);
            viewHolder.tvUserDes = (TextView) convertView.findViewById(R.id.item_des);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            String letterFirst = mContent.getLetters();
            if (!TextUtils.isEmpty(letterFirst)) {
                if (!isLetterDigitOrChinese(letterFirst)) {
                    letterFirst = "#";
                }else {
                    letterFirst = String.valueOf(letterFirst.toUpperCase().charAt(0));
                }
            }
            viewHolder.tvLetter.setText(letterFirst);
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        viewHolder.tvTitle.setText(this.list.get(position).getName());

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getLetters();
            char firstChar = sortStr.charAt(0);
            if (Character.toUpperCase(firstChar) == Character.toUpperCase(sectionIndex)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getLetters().charAt(0);
    }


    final static class ViewHolder {
        /**
         * 首字母
         */
        TextView tvLetter;
        /**
         * 昵称
         */
        TextView tvTitle;
        /**
         * 头像
         */
        ImageView mImageView;
        /**
         * des
         */
        TextView tvUserDes;
    }

    private boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }
}
