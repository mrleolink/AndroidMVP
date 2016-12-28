package net.leolink.android.androidmvp.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * Base presenter for {@link RecyclerView.ViewHolder#itemView}.
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
     * Present {@code data} to the {@link #view}.
     *
     * @param data data to present
     * @param position adapter position
     */
    public abstract void present(T data, int position);
}
