package net.leolink.android.androidmvp.mvp.android;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.leolink.android.androidmvp.mvp.presenter.BasePresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * Base {@link AppCompatActivity Activity} that supports MVP pattern.
 *
 * @author Leo
 */
public abstract class BaseActivity<P extends BasePresenter<? extends BaseView>> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = createPresenter();
    }

    /**
     * Override this method to specify layout ID for this Activity.
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * Create presenter for this Activity
     */
    @NonNull
    protected abstract P createPresenter();
}
