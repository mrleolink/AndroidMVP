package net.leolink.android.androidmvp.recyclerview;

import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.leolink.android.androidmvp.Item;
import net.leolink.android.androidmvp.mvp.android.recyclerview.BaseAdapter;
import net.leolink.android.androidmvp.mvp.android.recyclerview.BaseViewHolder;
import net.leolink.android.androidmvp.R;
import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * @author Leo
 */

public class MainFragmentAdapter extends BaseAdapter<Parcelable> {
    private static final int VIEWTYPE_1 = 0;
    private static final int VIEWTYPE_2 = 1;
    private static final int VIEWTYPE_3 = 2;

    public MainFragmentAdapter() {
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        // 0, 1 = same layout, different color
        // 2 = different layout
        if (mItems.get(position) instanceof Item) {
            return position % 2 == 0 ? VIEWTYPE_1 : VIEWTYPE_2;
        } else {
            return VIEWTYPE_3;
        }
    }

    @Override
    public BaseViewHolder<Parcelable, ? extends BaseView, ? extends ViewHolderPresenter<Parcelable, ? extends BaseView>>
            onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEWTYPE_3:
                return createComplexViewHolder(parent);
            default:
            case VIEWTYPE_1:
            case VIEWTYPE_2:
                return createSimpleColorfulViewHolder(parent, viewType);
        }
    }

    private SimpleColorfulViewHolder createSimpleColorfulViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        SimpleColorfulViewHolder res = new SimpleColorfulViewHolder(view);
        res.setPresenter(new SimpleColorfulPresenter(res, parent.getContext(), viewType == VIEWTYPE_1 ? Color.RED : Color.GREEN));
        return res;
    }

    private ComplexViewHolder createComplexViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_complex, parent, false);
        ComplexViewHolder res = new ComplexViewHolder(view);
        res.setPresenter(new ComplexPresenter(res, parent.getContext()));
        return res;
    }
}
