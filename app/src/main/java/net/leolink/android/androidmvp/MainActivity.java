package net.leolink.android.androidmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.leolink.android.androidmvp.mvp.android.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityPresenter.MainActivityView {
    private MainFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    public void setupView() {
        ButterKnife.bind(this);
        mFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @OnClick(R.id.add_fragment)
    public void addFragment() {
        if (mFragment != null) return;
        mFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mFragment)
                .commit();
    }
}
