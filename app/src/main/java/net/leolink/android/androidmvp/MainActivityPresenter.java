package net.leolink.android.androidmvp;

import android.support.annotation.NonNull;

import net.leolink.android.androidmvp.mvp.presenter.BasePresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * @author Leo
 */

public class MainActivityPresenter extends BasePresenter<MainActivityPresenter.MainActivityView> {
    public MainActivityPresenter(@NonNull MainActivityView view) {
        super(view);
    }

    public interface MainActivityView extends BaseView {
        // not much to do
    }
}

