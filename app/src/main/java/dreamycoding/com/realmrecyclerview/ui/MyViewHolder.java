package dreamycoding.com.realmrecyclerview.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dreamycoding.com.realmrecyclerview.R;

/**
 * Created by Juyel on 10/18/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView txtName, txtEmail, txtPhone, txtAge;

    public MyViewHolder(View itemView) {
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        txtAge = (TextView) itemView.findViewById(R.id.txtAge);
    }
}
