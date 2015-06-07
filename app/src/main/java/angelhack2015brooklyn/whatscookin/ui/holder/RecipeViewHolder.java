package angelhack2015brooklyn.whatscookin.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import angelhack2015brooklyn.whatscookin.R;


public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title;
    public ImageView image;
    public RecipeViewHolderClick mListener;

    public RecipeViewHolder(View v, RecipeViewHolderClick listener) {
        super(v);

        title = (TextView) v.findViewById(R.id.recyclerview_title);
        image = (ImageView) v.findViewById(R.id.recyclerview_image);
        mListener = listener;

        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, getLayoutPosition());
    }

    public interface RecipeViewHolderClick {
        void onClick(View v, int position);
    }
}