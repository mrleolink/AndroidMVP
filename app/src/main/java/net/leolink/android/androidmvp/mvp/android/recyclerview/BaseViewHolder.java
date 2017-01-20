package net.leolink.android.androidmvp.mvp.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

import butterknife.ButterKnife;

/**
 * Base implementation of {@link RecyclerView.ViewHolder} which is used in MVP pattern.
 *
 * @author Leo
 */

public class BaseViewHolder<M, V extends BaseView, P extends ViewHolderPresenter<M, V>>
        extends RecyclerView.ViewHolder {

    public P presenter;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public final void setPresenter(P presenter) {
        if (presenter == null) throw new IllegalArgumentException("presenter may not be null");
        this.presenter = presenter;
    }
}
