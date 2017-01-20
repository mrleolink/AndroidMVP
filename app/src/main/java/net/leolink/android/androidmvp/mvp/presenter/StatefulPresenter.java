package net.leolink.android.androidmvp.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Interface definition for presenters that supports saving/restoring their instance state.
 *
 * @author Leo
 */
public interface StatefulPresenter {
    void onSaveInstanceState(@NonNull Bundle outState);
    void onRestoreInstanceState(@Nullable Bundle savedInstanceState);
}
