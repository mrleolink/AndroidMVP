package net.leolink.android.androidmvp.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Interface definition for presenters that support saving/restoring state.
 *
 * @author Leo
 */

public interface StatePresenter {
    void onSaveInstanceState(@NonNull Bundle outState);
    void onRestoreInstanceState(@NonNull Bundle savedInstanceState);
}
