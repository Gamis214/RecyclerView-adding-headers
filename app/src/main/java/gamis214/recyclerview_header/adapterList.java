package gamis214.recyclerview_header;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pro on 20/07/16.
 */
public class adapterList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] lstData;
    private static final int HEADER = 0;
    private static final int ITEM = 1;

    public adapterList(String[] lstData){
        this.lstData = lstData;
    }

    public static class itemViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public itemViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.txt_content);
        }
    }

    public static class headerViewHolder extends RecyclerView.ViewHolder{

        public headerViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = null;

        if(getItemViewType(position) == HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false);
            return new headerViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
            return new itemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == HEADER){
            headerViewHolder itemHolder = (headerViewHolder) holder;
        }else{
            itemViewHolder itemHolder = (itemViewHolder) holder;
            itemHolder.textView.setText(lstData[position - 1]);
        }

    }

    @Override
    public int getItemCount() {
        return lstData.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : ITEM;
    }
}
