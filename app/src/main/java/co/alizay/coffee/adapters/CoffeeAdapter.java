package co.alizay.coffee.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Map;

import co.alizay.coffee.R;

public class CoffeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private Map<String, Integer> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CoffeeAdapter(Map<String, Integer> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = null;
//        Log.d("View type", viewType + "");
//        if (viewType == TYPE_HEADER) {
//            v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.header_coffee, parent, false);
//            HeaderViewHolder vh = new HeaderViewHolder(v);
//            return vh;
//        }

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_coffee, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Log.d("mDataset", mDataset.size() + " position: " + position);
        if (viewHolder instanceof MyViewHolder) {
            final MyViewHolder holder = (MyViewHolder) viewHolder;
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) mDataset.entrySet().toArray()[position];
            Log.d("Entry", entry.getKey() + " : " + entry.getValue());

            final int count = entry.getValue();
            holder.tvCoffeeCount.setText( count + "");
            holder.tvCoffeeName.setText(entry.getKey());

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Count", holder.tvCoffeeCount.getText().toString());
                    int count = Integer.parseInt(holder.tvCoffeeCount.getText().toString());
                    holder.tvCoffeeCount.setText((count + 1) + "");
                    mDataset.put(holder.tvCoffeeName.getText().toString(), count + 1);
                }
            });
            holder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(holder.tvCoffeeCount.getText().toString());
                    if(count > 1) {
                        holder.tvCoffeeCount.setText((count - 1) + "");
                    } else {
                        mDataset.remove(holder.tvCoffeeName.getText().toString());
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        if(mDataset == null || mDataset.size() == 0) {
//            return 0;
//        }
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TYPE_HEADER;
//        }

        return super.getItemViewType(position);
    }

    public void setData(Map<String, Integer> data) {
        mDataset = data;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    private class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCoffeeName;
        public TextView tvCoffeeCount;
        public ImageButton btnAdd;
        public ImageButton btnRemove;
        public MyViewHolder(View v) {
            super(v);
            tvCoffeeName = v.findViewById(R.id.tvCoffeeName);
            tvCoffeeCount = v.findViewById(R.id.tvCoffeeCount);
            btnAdd = v.findViewById(R.id.btnAdd);
            btnRemove = v.findViewById(R.id.btnRemove);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCoffeeName;
        public TextView tvCoffeeCount;
        public HeaderViewHolder(View v) {
            super(v);
            tvCoffeeName = v.findViewById(R.id.tvTitle);
            tvCoffeeCount = v.findViewById(R.id.tvCount);

        }
    }
}
