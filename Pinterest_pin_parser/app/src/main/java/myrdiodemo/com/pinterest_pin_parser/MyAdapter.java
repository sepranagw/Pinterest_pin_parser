package myrdiodemo.com.pinterest_pin_parser;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> images;

    public MyAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            //gridView = new View(context);

            gridView = inflater.inflate(R.layout.imagelayout, null);



        } else {
            gridView = (View) convertView;
        }
        ImageView iv = (ImageView) gridView .findViewById(R.id.pin_image);

        //String mobile = images.get(position);

        Picasso.with(context).load(images.get(position))
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.camera_delete_icon)
                        //.resizeDimen(80, 80)
                        //.centerInside()
                .into(iv);

        return gridView;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}