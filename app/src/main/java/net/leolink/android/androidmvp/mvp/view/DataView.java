package net.leolink.android.androidmvp.mvp.view;

/**
 * Interface definition for views that supports presenting data or error while loading data.
 *
 * @author Leo
 */
public interface DataView<D, E extends Throwable> extends BaseView {
    void onDataLoaded(D data);
    void onLoadingDataFailed(E throwable);
}