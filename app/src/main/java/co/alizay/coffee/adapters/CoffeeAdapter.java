package co.alizay.coffee.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import co.alizay.coffee.R;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.MyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private Map<String, Integer> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CoffeeAdapter(Map<String, Integer> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CoffeeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = null;
        if (viewType == TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_coffee, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_coffee, parent, false);
        }

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (position > 0) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) mDataset.entrySet().toArray()[position-1];
            holder.tvCoffeeCount.setText(entry.getValue());
            holder.tvCoffeeName.setText(entry.getKey());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size() + 1;
    }

    public void setData(Map<String, Integer> data) {
        mDataset = data;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCoffeeName;
        public TextView tvCoffeeCount;
        public MyViewHolder(View v) {
            super(v);
            tvCoffeeName = v.findViewById(R.id.tvCoffeeName);
            tvCoffeeCount = v.findViewById(R.id.tvCoffeeCount);
        }
    }
}
