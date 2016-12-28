package net.leolink.android.androidmvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Interface definition for views that supports saving/restoring state.
 *
 * @author Leo
 */
public interface StateView {
    void onSaveViewState(@NonNull Bundle outState);
    void onRestoreViewState(@NonNull Bundle savedInstanceState);
}
