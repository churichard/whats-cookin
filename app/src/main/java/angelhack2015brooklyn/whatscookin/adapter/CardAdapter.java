package angelhack2015brooklyn.whatscookin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.data.model.Recipe;


public class CardAdapter extends ArrayAdapter<Recipe> {

    private static final String TAG = CardAdapter.class.getSimpleName();
    private Context context;
    private int resource;

    public CardAdapter(Context context, int resource, List<Recipe> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }

        Recipe recipe = getItem(position);

        if (recipe != null) {
            TextView title = (TextView) convertView.findViewById(R.id.recipe_title);
            ImageView image = (ImageView) convertView.findViewById(R.id.recipe_img);

            if (title != null) {
                title.setText(recipe.getTitle());
            }
            Glide.with(context)
                    .load(recipe.getImgUrl())
                    .centerCrop()
                    .into(image);
        }

        return convertView;
    }
}