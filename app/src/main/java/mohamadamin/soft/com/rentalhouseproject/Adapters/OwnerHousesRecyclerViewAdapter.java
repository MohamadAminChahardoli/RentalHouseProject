package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.OwnerRegisteredHouse;
import mohamadamin.soft.com.rentalhouseproject.R;

public class OwnerHousesRecyclerViewAdapter extends RecyclerView.Adapter<OwnerHousesRecyclerViewAdapter.OwnerHousesRecyclerViewHolder>
{
    private ArrayList<OwnerRegisteredHouse> OwnerRegisteredHouseList;
    private Context context;
    private RequestOptions placeholderRequest;

    public OwnerHousesRecyclerViewAdapter(Context context)
    {
        OwnerRegisteredHouseList = new ArrayList<>();
        this.context = context;
        placeholderRequest = new RequestOptions();
        placeholderRequest.placeholder(R.drawable.preloading_img).error(R.drawable.no_image);
    }

    @NonNull
    @Override
    public OwnerHousesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new OwnerHousesRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.owner_rec_registered_house_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final OwnerHousesRecyclerViewHolder holder, int position)
    {
        if (OwnerRegisteredHouseList.size() <= 0)
        {
            return;
        }
        Glide.with(context).load(context.getResources().getString(R.string.houseImageFolder) + OwnerRegisteredHouseList.get(position).ImageName).apply(placeholderRequest).into(holder.imgOwnerHouse);
        holder.txtOwnerHouseTitle.setText(OwnerRegisteredHouseList.get(position).HouseTitle);
        holder.txtOwnerHouseZone.setText(OwnerRegisteredHouseList.get(position).Zone);
        //------------------------------
        holder.btnOwnerHouseEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });
        holder.btnOwnerHouseDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ShowDeleteHouseDialog(OwnerRegisteredHouseList.get(holder.getAdapterPosition()).HouseId);
            }
        });

    }

    private void ShowDeleteHouseDialog(final int houseId)
    {
        android.support.v7.app.AlertDialog.Builder DeleteHouseDialog = new android.support.v7.app.AlertDialog.Builder(context);
        DeleteHouseDialog.setCancelable(false);
        View AlertView = View.inflate(context, R.layout.general_dialog_layout, null);
        TextView txtDialogTitle = AlertView.findViewById(R.id.txt_dialog_title);
        TextView txtDialogMessage = AlertView.findViewById(R.id.txt_dialog_message);
        txtDialogTitle.setText("حذف آگهی");
        txtDialogMessage.setText("مالک گرامی آیا قصد حذف این آگهی را دارید؟");
        DeleteHouseDialog.setView(AlertView);
        DeleteHouseDialog.setPositiveButton("بله حذف بشه", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                DeleteHouse(houseId);
                Toast.makeText(context, "حذف آگهی با موفقیت انجام گرفت.", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        DeleteHouseDialog.setNegativeButton("خیر", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(context, "لغو شد.", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        DeleteHouseDialog.show();
    }

    private void DeleteHouse(int houseId)
    {

    }

    public void AddToOwnerRegisteredHouseList(ArrayList<OwnerRegisteredHouse> OwnerRegisteredHouseList)
    {
        this.OwnerRegisteredHouseList.addAll(OwnerRegisteredHouseList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return 10;
    }

    class OwnerHousesRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgOwnerHouse;
        private TextView txtOwnerHouseTitle;
        private TextView txtOwnerHouseZone;
        private Button btnOwnerHouseEdit;
        private Button btnOwnerHouseDelete;

        OwnerHousesRecyclerViewHolder(View itemView)
        {
            super(itemView);
            this.imgOwnerHouse = itemView.findViewById(R.id.img_owner_reg_house);
            this.txtOwnerHouseTitle = itemView.findViewById(R.id.txt_owner_reg_house_title);
            this.txtOwnerHouseZone = itemView.findViewById(R.id.txt_owner_reg_house_zone);
            this.btnOwnerHouseEdit = itemView.findViewById(R.id.btn_owner_reg_house_edit);
            this.btnOwnerHouseDelete = itemView.findViewById(R.id.btn_owner_reg_house_delete);
        }
    }
}
