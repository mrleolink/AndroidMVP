package net.leolink.android.androidmvp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.leolink.android.androidmvp.mvp.BaseFragment;
import net.leolink.android.androidmvp.recyclerview.MainFragmentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Leo
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentPresenter.MainFragmentView {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    View mProgressView;

    private MainFragmentAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @NonNull
    @Override
    protected MainFragmentPresenter createPresenter() {
        return new MainFragmentPresenter(this);
    }

    @Override
    public void setupView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new MainFragmentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @OnClick(R.id.load)
    public void load() {
        mPresenter.loadItems();
    }

    @Override
    public void showItems(List<Parcelable> items) {
        mAdapter.addData(items);
    }

    @Override
    public void setLoadingIndicatorVisible(boolean visible) {
        if (visible) {
            mProgressView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mProgressView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
