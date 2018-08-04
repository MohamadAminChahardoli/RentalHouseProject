package mohamadamin.soft.com.rentalhouseproject.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import mohamadamin.soft.com.rentalhouseproject.Adapters.CostAdapter;
import mohamadamin.soft.com.rentalhouseproject.Models.BedCost;
import mohamadamin.soft.com.rentalhouseproject.R;


public class CostsViewerDialog extends DialogFragment implements View.OnClickListener
{
    private ListView ListViewCosts;
    private FloatingActionButton FabBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.costs_detail_layout, null);
        builder.setView(view);
        initializeComponents(view);
        setupCostListView();
        FabBack.setOnClickListener(this);

        return  builder.create();
    }

    private void initializeComponents(View view)
    {
        ListViewCosts = view.findViewById(R.id.list_view_costs);
        FabBack = view.findViewById(R.id.fab_back);
    }

    private void setupCostListView()
    {
        CostAdapter costAdapter = new CostAdapter(getActivity(), BedCost.createList());
        ListViewCosts.setAdapter(costAdapter);
    }

    @Override
    public void onClick(View view)
    {
        dismiss();
    }
}

