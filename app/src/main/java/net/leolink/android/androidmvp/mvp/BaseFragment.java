package net.leolink.android.androidmvp.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.leolink.android.androidmvp.mvp.presenter.BasePresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

import butterknife.ButterKnife;

/**
 * Base {@link Fragment} that supports MVP pattern.
 *
 * @author Leo
 */
public abstract class BaseFragment<P extends BasePresenter<? extends BaseView>> extends Fragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        mPresenter = createPresenter();
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * Create presenter for this fragment.
     */
    @NonNull
    protected abstract P createPresenter();
}
