package net.leolink.android.androidmvp.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import net.leolink.android.androidmvp.MainFragmentPresenter;
import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.StateView;
import net.leolink.android.androidmvp.ComplexItem;
import net.leolink.android.androidmvp.mvp.view.BaseView;
import net.leolink.android.androidmvp.mvp.view.LoadingIndicatorView;

/**
 * @author Leo
 */

public class ComplexPresenter extends ViewHolderPresenter<Parcelable, ComplexPresenter.ComplexView> {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ComplexItem mItem;

    public ComplexPresenter(@NonNull ComplexView view) {
        super(view);
    }

    @Override
    public void onBindViewHolder(Parcelable item, int pos) {
        mItem = (ComplexItem) item;
        loadData();
    }

    private void loadData() {
        view.setLoadingIndicatorVisible(true);
        if (mItem.isLoaded()) {
            view.showItems(null); // fake data
            if (mItem.getRecyclerViewState() != null) {
                view.onRestoreViewState(mItem.getRecyclerViewState());
            }
            view.setLoadingIndicatorVisible(false);
        } else {
            mHandler.postDelayed(() -> {
                mItem.setLoaded(true);
                view.showItems(null); // fake data
                view.setLoadingIndicatorVisible(false);
                Log.e("linhln", "loaded: " + mItem);
            }, 1500);
        }
    }

    @Override
    public void onViewRecycled() {
        Bundle bundle = new Bundle();
        view.onSaveViewState(bundle);
        mItem.setRecyclerViewState(bundle);
    }

    public void saveScrollPosition() {
        onViewRecycled();
    }

    public interface ComplexView extends BaseView, LoadingIndicatorView, MainFragmentPresenter.MainFragmentView, StateView {
    }
}
