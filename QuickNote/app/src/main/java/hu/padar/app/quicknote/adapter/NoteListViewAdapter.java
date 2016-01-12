package hu.padar.app.quicknote.adapter;

/**
 * Created by Pádi on 2016. 01. 12..
 */
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hu.padar.app.quicknote.R;
import hu.padar.app.quicknote.object.Category;


public class NoteListViewAdapter extends ArrayAdapter<Category> {
    static class ViewHolder {
        public TextView noteTitle;
        public TextView noteCategory;
        public TextView noteDate;
        public Button noteColor;
    }

    public NoteListViewAdapter(Activity context, List<Category> values) {
        super(context, R.layout.list_item_note, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = View.inflate(getContext(), R.layout.list_item_note, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.noteTitle = (TextView) rowView.findViewById(R.id.tvTitle);
            viewHolder.noteCategory = (TextView) rowView.findViewById(R.id.tvCategory);
            viewHolder.noteDate = (TextView) rowView.findViewById(R.id.tvDate);
            viewHolder.noteColor = (Button) rowView.findViewById(R.id.buttonColor);
            rowView.setTag(viewHolder);
        }

        Category note = getItem(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.noteTitle.setText(note.getTitle());
        holder.noteCategory.setText("Category: " + note.getCategory());
        holder.noteDate.setText(note.getDate());
        holder.noteColor.setBackgroundColor(note.getColor());
        return rowView;
    }
}
