package net.leolink.android.androidmvp.mvp.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * Wrapper class of a {@link Parcelable parcelable} class {@link M} with an extra field for saving its UI state.<br/>
 *
 * A typical use case of this class would be to wrap data of views that are reused frequently (think
 * {@link RecyclerView}, {@link android.widget.ListView} and such). In those cases, you can't couple the UI state to
 * the mView like you usually do with Activity or Fragment, because if the mView is reused to present other model data
 * object, it should also present that object's UI state. So instead of coupling UI state to the View object itself,
 * you should couple the UI state to the model by wrapping the model object in this class.
 *
 * @param <M> M has to extend {@link Parcelable} for the sake of passing/saving data around in Android.
 *
 * @author Leo
 */
public class StatefulModel<M extends Parcelable> implements Parcelable {
    @Nullable
    private M mModel;
    @Nullable
    private Bundle mUiState;

    @Nullable
    public Bundle getUiState() {
        return mUiState;
    }

    public void setUiState(@Nullable Bundle uiState) {
        mUiState = uiState;
    }

    @Nullable
    public M getModel() {
        return mModel;
    }

    public void setModel(@Nullable M model) {
        mModel = model;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mModel, flags);
        dest.writeBundle(this.mUiState);
    }

    public StatefulModel() {
    }

    public StatefulModel(@Nullable M model) {
        mModel = model;
    }

    public StatefulModel(@Nullable M model, @Nullable Bundle uiState) {
        mModel = model;
        mUiState = uiState;
    }

    protected StatefulModel(Parcel in) {
        ClassLoader classLoader = getClass().getClassLoader();
        this.mModel = in.readParcelable(classLoader);
        this.mUiState = in.readBundle(classLoader);
    }

    public static final Creator<StatefulModel> CREATOR = new Creator<StatefulModel>() {
        @Override
        public StatefulModel createFromParcel(Parcel source) {return new StatefulModel(source);}

        @Override
        public StatefulModel[] newArray(int size) {return new StatefulModel[size];}
    };
}

