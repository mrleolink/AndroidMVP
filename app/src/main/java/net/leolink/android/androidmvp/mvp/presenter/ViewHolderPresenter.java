package net.leolink.android.androidmvp.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * Base presenter for {@link RecyclerView.ViewHolder#itemView}.
 *
 * @author Leo
 */
public abstract class ViewHolderPresenter<M, V extends BaseView> extends BasePresenter<V> {
    protected final Context mContext;

    public ViewHolderPresenter(@NonNull V view, @NonNull Context context) {
        super(view);
        this.mContext = context;
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
     * Delegate method of {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}
     */
    public void onViewDetachedFromWindow() {}

    /**
     * Present {@code data} to the {@link #mView}.
     *
     * @param data data to present
     * @param position adapter position
     * @param viewType view type of ViewHolder that this presenter is bound to
     */
    public abstract void present(M data, int position, int viewType);
}
