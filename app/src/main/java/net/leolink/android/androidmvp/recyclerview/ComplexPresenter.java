package net.leolink.android.androidmvp.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import net.leolink.android.androidmvp.ComplexItem;
import net.leolink.android.androidmvp.MainFragmentPresenter;
import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;
import net.leolink.android.androidmvp.mvp.view.LoadingIndicatorView;
import net.leolink.android.androidmvp.mvp.view.StatefulView;

/**
 * @author Leo
 */

public class ComplexPresenter extends ViewHolderPresenter<Parcelable, ComplexPresenter.ComplexView> {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ComplexItem mItem;

    public ComplexPresenter(@NonNull ComplexView view, Context context) {
        super(view, context);
    }

    @Override
    public void present(Parcelable item, int pos, int viewType) {
        mItem = (ComplexItem) item;
        loadData();
    }

    private void loadData() {
        mView.setLoadingIndicatorVisible(true);
        if (mItem.isLoaded()) {
            mView.showItems(null); // fake data
            if (mItem.getRecyclerViewState() != null) {
                mView.onRestoreViewState(mItem.getRecyclerViewState());
            }
            mView.setLoadingIndicatorVisible(false);
        } else {
            mHandler.postDelayed(() -> {
                mItem.setLoaded(true);
                mView.showItems(null); // fake data
                mView.setLoadingIndicatorVisible(false);
                Log.e("linhln", "loaded: " + mItem);
            }, 1500);
        }
    }

    @Override
    public void onViewRecycled() {
        Bundle bundle = new Bundle();
        mView.onSaveViewState(bundle);
        mItem.setRecyclerViewState(bundle);
    }

    public void saveScrollPosition() {
        onViewRecycled();
    }

    public interface ComplexView extends BaseView, LoadingIndicatorView, MainFragmentPresenter.MainFragmentView, StatefulView {
    }
}
