package pr.tongson.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import pr.tongson.library.R;
import pr.tongson.library.fragment.MyItemRecyclerViewAdapter;
import pr.tongson.library.fragment.dummy.DummyContent;

/**
 * <b>Create Date:</b> 2020-02-28<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class GuideGalleryView extends RelativeLayout {
    private ViewPager2 viewPager2;
    private CompositePageTransformer compositePageTransformer;
    private MyAdapter mMyAdapter;

    public GuideGalleryView(Context context) {
        super(context, null);
    }

    public GuideGalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideGalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);

    }

    private void initViews(Context context) {
        viewPager2 = new ViewPager2(context);
        viewPager2.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewPager2.setPageTransformer(compositePageTransformer = new CompositePageTransformer());
        mMyAdapter = new MyAdapter();
        ArrayList<String> dds=new ArrayList<>();
        dds.add("1234124214");
        dds.add("41242112");
        dds.add("21412412");
        dds.add("412412421");
        dds.add("12412412412");

        mMyAdapter.setItem(dds);
        viewPager2.setAdapter(mMyAdapter);
        addView(viewPager2);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mValues;

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position));
            holder.mContentView.setText(mValues.get(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }


        private class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.item_number);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }

        public void setItem(List<String> values) {
            mValues = values;
            notifyDataSetChanged();
        }

    }


}
