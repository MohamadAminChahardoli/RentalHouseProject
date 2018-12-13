package mohamadamin.soft.com.rentalhouseproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Adapters.OwnerHousesRecyclerViewAdapter;
import mohamadamin.soft.com.rentalhouseproject.Models.OwnerRegisteredHouse;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorDrawablePreLollipopHelper;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorView;

public class MainHouseFragment extends Fragment
{
    private Button btnAddHouse;
    private RecyclerView recOwnerHouseList;

    public MainHouseFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_house, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initViewsEvents();
    }

    private void initViews(View view)
    {
        btnAddHouse = view.findViewById(R.id.btn_add_house);
        recOwnerHouseList = view.findViewById(R.id.rec_OwnerHouseList);
        VectorDrawablePreLollipopHelper.SetVectors(getResources(), new VectorView(R.drawable.ic_add_house, btnAddHouse, VectorDrawablePreLollipopHelper.MyDirType.end));
        LoadUpOwnerHouseList();

    }

    private void LoadUpOwnerHouseList()
    {
        OwnerHousesRecyclerViewAdapter OwnerHousesAdapter = new OwnerHousesRecyclerViewAdapter(getContext());
        LinearLayoutManager OwnerHousesLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recOwnerHouseList.getContext(), OwnerHousesLayoutManager.getOrientation());
        recOwnerHouseList.setLayoutManager(OwnerHousesLayoutManager);
        recOwnerHouseList.addItemDecoration(dividerItemDecoration);
        recOwnerHouseList.setAdapter(OwnerHousesAdapter);
        ArrayList<OwnerRegisteredHouse> OwnerRegisteredHouseList = new ArrayList<>();
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerRegisteredHouseList.add(new OwnerRegisteredHouse());
        OwnerHousesAdapter.AddToOwnerRegisteredHouseList(OwnerRegisteredHouseList);
    }


    private void initViewsEvents()
    {
        btnAddHouse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.owner_management_placeholder, new RegisterHouseFragment())
                        .addToBackStack(RegisterHouseFragment.class.getName())
                        .commit();
            }
        });
    }

}
