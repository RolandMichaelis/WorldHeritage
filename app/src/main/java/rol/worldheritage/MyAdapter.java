package rol.worldheritage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item> {

    ArrayList<Item> heritageList = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        heritageList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        TextView textView2 = (TextView) v.findViewById(R.id.textView2);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        ImageView imageStatus = (ImageView) v.findViewById(R.id.imageStatus);
        textView.setText(heritageList.get(position).getAnimalName());
        textView2.setText(heritageList.get(position).getTextContent());
        imageView.setImageResource(heritageList.get(position).getAnimalImage());
        imageStatus.setImageResource(heritageList.get(position).getStatusImage());
        return v;

    }

}
