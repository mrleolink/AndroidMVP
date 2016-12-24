package net.leolink.android.androidmvp.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * Presenter that supports lifecycle of {@link RecyclerView.ViewHolder}.
 *
 * @author Leo
 */

public abstract class ViewHolderPresenter<T, V extends BaseView> extends BasePresenter<V> {
    public ViewHolderPresenter(@NonNull V view) {
        super(view);
    }

    /**
     * Delegate method of {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}
     */
    public void onViewRecycled() {}

    /**
     * Delegate method of {@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}
     */
    public void onViewAttachedToWindow() {}

    /**
     * Delegate method of {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     */
    public abstract void onBindViewHolder(T item, int position);
}
