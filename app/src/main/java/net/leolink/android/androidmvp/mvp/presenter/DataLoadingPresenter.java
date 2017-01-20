package net.leolink.android.androidmvp.mvp.presenter;

/**
 * Interface definition for presenter that has responsibility of loading data.
 *
 * @author Leo
 */
public interface DataLoadingPresenter {
    void startLoadingData();
    void stopLoadingData();
}
