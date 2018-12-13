package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.OwnerRegisteredHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.R;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private Context ContextInstance;
    private List<SecondaryHouse> Houses;
    private final int DORM = 1;
    private final int HOUSE = 2;

    public SearchResultAdapter(Context contextInstance) {
        ContextInstance = contextInstance;
        Houses = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(LayoutInflater.from(ContextInstance).inflate(R.layout.search_result_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, final int position) {
        String imageUrl = ContextInstance.getString(R.string.houseImageFolder) + Houses.get(position).getPhoto();
        Glide.with(ContextInstance).load(imageUrl).into(holder.ImgHousePhoto);
        holder.TxtHouseTitle.setText(Houses.get(position).getTitle());
        holder.TxtHouseDateAndZone.setText(String.format("%s در %s", Houses.get(position).getDateOfRegister(), Houses.get(position).getZone()));
        holder.TxtHouseMortgage.setText(Houses.get(position).getMortgage());
        holder.TxtHouseMonthlyRent.setText(Houses.get(position).getMonthlyRent());
        if (Houses.get(position).getType() == DORM)
            holder.TxtHouseType.setText(R.string.dormText);
        else if (Houses.get(position).getType() == HOUSE)
            holder.TxtHouseType.setText(R.string.houseText);
        holder.TxtHouseVisitedCount.setText(Houses.get(position).getVisitedCount() + " بازدید");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContextInstance, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Houses.size();
    }

    public void insertNewHousesToList(List<SecondaryHouse> newHouses) {
        Houses.addAll(newHouses);
        this.notifyDataSetChanged();
    }

    public void clearList()
    {
        Houses.clear();
        this.notifyDataSetChanged();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private ImageView ImgHousePhoto;
        private TextView TxtHouseTitle;
        private TextView TxtHouseDateAndZone;
        private TextView TxtHouseMortgage;
        private TextView TxtHouseMonthlyRent;
        private TextView TxtHouseType;
        private TextView TxtHouseVisitedCount;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            ImgHousePhoto = itemView.findViewById(R.id.img_house_main_photo_search);
            TxtHouseTitle = itemView.findViewById(R.id.txt_house_title_search);
            TxtHouseDateAndZone = itemView.findViewById(R.id.txt_house_zone_and_registered_time_search);
            TxtHouseMortgage = itemView.findViewById(R.id.txt_house_mortgage_search);
            TxtHouseMonthlyRent = itemView.findViewById(R.id.txt_house_monthly_rent_search);
            TxtHouseType = itemView.findViewById(R.id.txt_house_type_search);
            TxtHouseVisitedCount = itemView.findViewById(R.id.txt_house_visited_count_item_search);
        }
    }
}
