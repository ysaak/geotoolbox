package ysaak.geotoolbox.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ysaak.geotoolbox.R;

public class ToolsMenuAdapter extends RecyclerView.Adapter<ToolsMenuAdapter.MenuViewHolder> {
    private List<ToolMenuItem> toolList;

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;

        private MenuViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.menu_title);
            description = view.findViewById(R.id.menu_description);
        }
    }


    public ToolsMenuAdapter(List<ToolMenuItem> toolList) {
        this.toolList = toolList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_menu_row, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        ToolMenuItem item = toolList.get(position);
        holder.title.setText(item.title);
        holder.description.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }
}
