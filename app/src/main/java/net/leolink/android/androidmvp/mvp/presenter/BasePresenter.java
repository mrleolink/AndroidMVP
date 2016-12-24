package net.leolink.android.androidmvp.mvp.presenter;

import android.support.annotation.NonNull;

import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * Base presenter which is used in MVP pattern.
 *
 * @author Leo
 */
public abstract class BasePresenter<V extends BaseView> {
    protected final V view;

    public BasePresenter(@NonNull V view) {
        if (view == null) {
            throw new IllegalArgumentException("View can't be null");
        }
        this.view = view;
        this.view.setupView();
    }
}
