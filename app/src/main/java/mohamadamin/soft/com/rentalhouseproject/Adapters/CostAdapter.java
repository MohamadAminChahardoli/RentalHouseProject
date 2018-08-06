package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.BedCost;
import mohamadamin.soft.com.rentalhouseproject.R;

public class CostAdapter extends BaseAdapter
{

    private Context ContextInstance;
    private ArrayList<BedCost> CostsList;
    private CostViewHolder CostHolder;

    public CostAdapter(Context contextInstance, ArrayList<BedCost> costsList)
    {
        ContextInstance = contextInstance;
        CostsList = costsList;
    }

    @Override
    public int getCount()
    {
        return CostsList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        View row = view;
        if (row==null){
            row = LayoutInflater.from(ContextInstance).inflate(R.layout.cost_item_layout,
                    viewGroup, false);
            CostHolder = new CostViewHolder();
        }
        initializeComponents(row);
        bindViewsContent(position);
        return row;
    }

    private void initializeComponents(View view)
    {
        CostHolder.TxtBedType = view.findViewById(R.id.txt_bed_type);
        CostHolder.TxtBedCost = view.findViewById(R.id.txt_bed_cost);
    }

    private void bindViewsContent(int position)
    {
        CostHolder.TxtBedType.setText(CostsList.get(position).getBedType());
        CostHolder.TxtBedCost.setText(CostsList.get(position).getCost());
    }

    class CostViewHolder
    {
        private TextView TxtBedType;
        private TextView TxtBedCost;
    }

}
