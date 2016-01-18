package cf.bcmbx.exam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cf.bcmbx.exam.R;
import cf.bcmbx.exam.activity.DeatailedViewActivity;
import cf.bcmbx.exam.model.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.WordsViewHolder> {
    private List<Person> mPersonsList;
    private Context mContext;
    public static final String PERSON_ID = null;

    public PersonAdapter (List person, Context mContext) {
        this.mContext = mContext;
        mPersonsList = person;
    }

    public class WordsViewHolder extends RecyclerView.ViewHolder{
        private TextView firstName;
        private TextView lastName;
        private ImageView imageUrl;

        public WordsViewHolder(View v) {
            super(v);
            this.firstName = (TextView) v.findViewById(R.id.first_name);
            this.lastName = (TextView) v.findViewById(R.id.last_name);
            this.imageUrl = (ImageView) v.findViewById(R.id.user_image);
        }
    }

    @Override
    public WordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new WordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordsViewHolder holder, final int position) {
        holder.firstName.setText(mPersonsList.get(position).getFirstName());
        holder.lastName.setText(mPersonsList.get(position).getLastName());
        Picasso.with(mContext).load(mPersonsList.get(position).getImageUrl()).into(holder.imageUrl);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DeatailedViewActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(PERSON_ID, mPersonsList.get(position).getUsername());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPersonsList.size();
    }
}
