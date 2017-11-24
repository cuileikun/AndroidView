package com.gitstudy.xrcycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class MyMultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<String> datas = null;

    private enum ITEM_TYPE {
        ITEM_TYPE_ONE,
        ITEM_TYPE_TWO,
        ITEM_TYPE_TTHREE,
        ITEM_TYPE_FOUR,
        ITEM_TYPE_FIVE,
        ITEM_TYPE_SIX,
        ITEM_TYPE_SEVEN
    }

    public MyMultiTypeAdapter(ArrayList<String> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_ONE.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_one, viewGroup, false);
            return new ItemOneViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            return new ItemTwoViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TTHREE.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_three, viewGroup, false);
            return new ItemThreeViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_FOUR.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_four, viewGroup, false);
            return new ItemFourViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_FIVE.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_five, viewGroup, false);
            return new ItemFiveViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_SIX.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_six, viewGroup, false);
            return new ItemSixViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_SEVEN.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_seven, viewGroup, false);
            return new ItemSevenViewHolder(view);
        } else
            return null;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemOneViewHolder) {
//            ((ItemOneViewHolder) viewHolder).image.setImageResource(R.drawable.headimg);
        } else if (viewHolder instanceof ItemTwoViewHolder) {
//            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position));
            ((ItemTwoViewHolder) viewHolder).title.setText("222222222222222222");
        } else if (viewHolder instanceof ItemThreeViewHolder) {
            ((ItemThreeViewHolder) viewHolder).tv1.setText("333333333333333333");
            ((ItemThreeViewHolder) viewHolder).tv2.setText("333333333333333333");
            ((ItemThreeViewHolder) viewHolder).tv3.setText("333333333333333333");
        }else if (viewHolder instanceof ItemFourViewHolder) {
//            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position));
//            ((ItemFourViewHolder) viewHolder).tv4.setText("44444444444444444444444444");
        }else if (viewHolder instanceof ItemFiveViewHolder) {
//            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position));
//            ((ItemFiveViewHolder) viewHolder).tv5.setText("5555555555555555555555555555");
        }else if (viewHolder instanceof ItemSixViewHolder) {
//            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position));
//            ((ItemSixViewHolder) viewHolder).tv6.setText("6666666666666666666666666");
        }else if (viewHolder instanceof ItemSevenViewHolder) {
//            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position));
//            ((ItemSevenViewHolder) viewHolder).tv7.setText("7777777777777777777777777777");
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return position == 0 ? ITEM_TYPE.ITEM_TYPE_ONE.ordinal() : ITEM_TYPE.ITEM_TYPE_TWO.ordinal();

        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_ONE.ordinal();
        } else if (position == 1) {
            return ITEM_TYPE.ITEM_TYPE_TWO.ordinal();
        } else if (position == 2) {
            return ITEM_TYPE.ITEM_TYPE_TTHREE.ordinal();
        } else if (position == 3) {
            return ITEM_TYPE.ITEM_TYPE_FOUR.ordinal();
        } else if (position == 4) {
            return ITEM_TYPE.ITEM_TYPE_FIVE.ordinal();
        } else if (position == 5) {
            return ITEM_TYPE.ITEM_TYPE_SIX.ordinal();
        } else if (position == 6) {
            return ITEM_TYPE.ITEM_TYPE_SEVEN.ordinal();
        }

        return -1;

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return 7;
    }

    public class ItemOneViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;

        public ItemOneViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
        }
    }

    public class ItemTwoViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ItemTwoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public class ItemThreeViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;

        public ItemThreeViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            tv3 = (TextView) itemView.findViewById(R.id.tv3);

        }
    }


    public class ItemFourViewHolder extends RecyclerView.ViewHolder {
        TextView tv4;

        public ItemFourViewHolder(View itemView) {
            super(itemView);
            tv4 = (TextView) itemView.findViewById(R.id.tv4);
        }
    }

    public class ItemFiveViewHolder extends RecyclerView.ViewHolder {
        TextView tv5;

        public ItemFiveViewHolder(View itemView) {
            super(itemView);
            tv5 = (TextView) itemView.findViewById(R.id.tv5);
        }
    }

    public class ItemSixViewHolder extends RecyclerView.ViewHolder {
        TextView tv6;

        public ItemSixViewHolder(View itemView) {
            super(itemView);
            tv6 = (TextView) itemView.findViewById(R.id.tv6);
        }
    }

    public class ItemSevenViewHolder extends RecyclerView.ViewHolder {
        TextView tv7;

        public ItemSevenViewHolder(View itemView) {
            super(itemView);
            tv7 = (TextView) itemView.findViewById(R.id.tv7);
        }
    }
}
