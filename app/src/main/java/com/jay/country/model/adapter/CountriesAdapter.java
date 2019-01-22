package com.jay.country.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.jay.country.R;

import java.util.HashMap;
import java.util.List;




public class CountriesAdapter extends BaseExpandableListAdapter {

    private List<String> titles;
    private HashMap<String, List<String>> childrenTitles;
    private LayoutInflater inflater;


    public CountriesAdapter(List<String> titles, HashMap<String, List<String>> childrenTitles, Context context) {

        this.titles = titles;
        this.childrenTitles = childrenTitles;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getGroupCount() {
        return titles.size();
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenTitles.get(titles.get(groupPosition)).size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return titles.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenTitles.get(titles.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_countries, null);
        }

        TextView title = convertView.findViewById(R.id.text_view_title);
        title.setText(headerTitle);

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_cities, null);

        }

        String childText = (String) getChild(groupPosition, childPosition);

        TextView childTitle = convertView.findViewById(R.id.text_view_city);
        childTitle.setText(childText);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public String getCity(int groupPosition, int childPosition){

        return  childrenTitles.get(titles.get(groupPosition)).get(childPosition);
    }
}
