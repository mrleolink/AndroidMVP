package net.leolink.android.androidmvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import net.leolink.android.androidmvp.mvp.presenter.BasePresenter;
import net.leolink.android.androidmvp.mvp.presenter.StatefulPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;
import net.leolink.android.androidmvp.mvp.view.LoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 */

public class MainFragmentPresenter extends BasePresenter<MainFragmentPresenter.MainFragmentView>
        implements StatefulPresenter {
    private Handler mHandler;
    private ArrayList<Parcelable> mItems;

    public MainFragmentPresenter(@NonNull MainFragmentView view) {
        super(view);
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void loadItems() {
        mView.setLoadingIndicatorVisible(true);
        if (mItems != null) {
            Log.e("linhln", "Cache hit!");
            mView.showItems(mItems);
            mView.setLoadingIndicatorVisible(false);
        } else {
            loadFromNetwork();
        }
    }

    private void loadFromNetwork() { // We should have a network service to load data here so we can mock it.
        Log.e("linhln", "Load from network!");
        mHandler.postDelayed(() -> {
            mItems = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                mItems.add(i % 20 != 0 ? new Item("text " + i, "button " + i) : new ComplexItem());
            }
            mView.showItems(mItems);
            mView.setLoadingIndicatorVisible(false);
        }, 2000);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("items", mItems);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        mItems = savedInstanceState.getParcelableArrayList("items");
        mView.showItems(mItems);
    }


    public interface MainFragmentView extends BaseView, LoadingIndicatorView {
        void showItems(List<Parcelable> items);
    }
}
