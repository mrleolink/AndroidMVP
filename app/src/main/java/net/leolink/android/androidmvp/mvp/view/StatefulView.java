package net.leolink.android.androidmvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Interface definition for MVP view of Android views that don't have life cycle callbacks yet have UI state
 * (think RecyclerView's ViewHolder and such). So in order to those Android views' UI state, they should implement this
 * interface and use methods of this interface as lifecycle callbacks to save their UI state.<br/>
 *
 * Methods of this interface then should be called by either presenters or other components in appropriate timing to
 * trigger saving/restoring UI state.
 *
 * @author Leo
 */
public interface StatefulView {
    void onSaveViewState(@NonNull Bundle outState);
    void onRestoreViewState(@NonNull Bundle savedInstanceState);
}
