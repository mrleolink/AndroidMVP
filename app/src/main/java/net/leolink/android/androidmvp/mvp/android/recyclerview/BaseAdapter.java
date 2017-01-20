package net.leolink.android.androidmvp.mvp.android.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Base {@link RecyclerView.Adapter} for {@link RecyclerView} which is used in MVP pattern.
 *
 * @author Leo
 */
public abstract class BaseAdapter<M> extends
        RecyclerView.Adapter<BaseViewHolder<M, ? extends BaseView, ? extends ViewHolderPresenter>> {

    protected List<M> mItems;

    public BaseAdapter() {
        mItems = new ArrayList<>();
    }

    public BaseAdapter(@Nullable List<M> data) {
        setData(data);
    }

    /**
     * Replace data of this adapter with new data.
     * @param newData new data
     */
    public void setData(@Nullable List<M> newData) {
        this.mItems = newData == null ? new ArrayList<>() : newData;
        notifyDataSetChanged();
    }

    /**
     * Add new data to the end of the list of data of this adapter.<br/>
     * {@link RecyclerView.ItemAnimator} will not be applied when {@code newData} is added to {@link RecyclerView},
     * if {@link RecyclerView.ItemAnimator} is needed, use {@link #addData(List, boolean)} instead.
     *
     * @param newData new data
     */
    public void addData(@Nullable List<M> newData) {
        addData(newData, false);
    }

    /**
     * Add new data to the end of the list of data of this adapter.
     * @param newData new data
     * @param withItemAnimator whether {@link RecyclerView.ItemAnimator} should be applied when {@code newData} is added
     *                         to {@link RecyclerView}.
     */
    public void addData(@Nullable List<M> newData, boolean withItemAnimator) {
        if (newData == null) return;
        int oldSize = mItems.size();
        this.mItems.addAll(newData);
        if (withItemAnimator) {
            notifyItemRangeInserted(oldSize, newData.size());
        } else {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<M, ? extends BaseView, ? extends ViewHolderPresenter> holder,
                                 int position) {
        if (holder.presenter != null) holder.presenter.present(mItems.get(position), position, getItemViewType(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<M, ? extends BaseView, ? extends ViewHolderPresenter> holder) {
        if (holder.presenter != null) holder.presenter.onViewRecycled();
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder<M, ? extends BaseView, ? extends ViewHolderPresenter> holder) {
        if (holder.presenter != null) holder.presenter.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder<M, ? extends BaseView, ? extends ViewHolderPresenter> holder) {
        if (holder.presenter != null) holder.presenter.onViewDetachedFromWindow();
    }
}