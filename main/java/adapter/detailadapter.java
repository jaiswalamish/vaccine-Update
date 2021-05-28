package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccineupdate.MapsActivity;
import com.example.vaccineupdate.R;

import org.w3c.dom.Text;

import java.util.List;

import model.detail;

public class detailadapter extends RecyclerView.Adapter<detailadapter.holder> {

    private List<detail> list;
    private Context mContext;

    public detailadapter(List<detail> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override


    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.vaccine,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  detailadapter.holder holder, int position) {
            detail Detail=list.get(position);
            holder.storename.setText(Detail.getCentreName());
            holder.location.setText(Detail.getAddress());
            holder.availability.setText(Detail.getAvailability());
            holder.ageLimit.setText(Detail.getAgeLimit());
            holder.vaccinename.setText(Detail.getVaccineName());
            holder.feeType.setText(Detail.getFeeType());
            holder.slot.setText(Detail.getSlotTime());
            holder.mapsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext, MapsActivity.class);
                    String latitude= Detail.getLatitude();
                    String longitude=Detail.getLongitude();
                    intent.putExtra("lat",latitude);
                    intent.putExtra("name",Detail.getAddress());
                    intent.putExtra("long",longitude);
                    mContext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView storename;
        TextView location;
        TextView slot;
        TextView vaccinename;
        TextView feeType;
        TextView ageLimit;
        TextView availability;
        ImageView mapsearch;

        public holder(@NonNull  View itemView) {
            super(itemView);
            storename=itemView.findViewById(R.id.storename);
            location=itemView.findViewById(R.id.location);
            slot=itemView.findViewById(R.id.slot);
            vaccinename=itemView.findViewById(R.id.vaccinename);
            feeType=itemView.findViewById(R.id.feetype);
            ageLimit=itemView.findViewById(R.id.agelimit);
            availability=itemView.findViewById(R.id.availability);
            mapsearch=itemView.findViewById(R.id.mapsearch);
        }
    }
}
