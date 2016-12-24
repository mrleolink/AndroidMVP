package net.leolink.android.androidmvp.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Interface definition for presenter that support save/restore state of views.
 *
 * @author Leo
 */

public interface StatePresenter {
    void onSaveInstanceState(@NonNull Bundle outState);
    void onRestoreInstanceState(@NonNull Bundle savedInstanceState);
}
