package net.leolink.android.androidmvp;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Leo
 */
public class ComplexItem implements Parcelable {
    private boolean mLoaded;
    private Bundle mRecyclerViewState;

    public void setRecyclerViewState(Bundle recyclerViewState) {
        this.mRecyclerViewState = recyclerViewState;
    }

    public Bundle getRecyclerViewState() {
        return mRecyclerViewState;
    }

    public boolean isLoaded() {
        return mLoaded;
    }

    public void setLoaded(boolean loaded) {
        mLoaded = loaded;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mLoaded ? (byte) 1 : (byte) 0);
        dest.writeBundle(this.mRecyclerViewState);
    }

    public ComplexItem() {}

    protected ComplexItem(Parcel in) {
        this.mLoaded = in.readByte() != 0;
        this.mRecyclerViewState = in.readBundle();
    }

    public static final Creator<ComplexItem> CREATOR = new Creator<ComplexItem>() {
        @Override
        public ComplexItem createFromParcel(Parcel source) {return new ComplexItem(source);}

        @Override
        public ComplexItem[] newArray(int size) {return new ComplexItem[size];}
    };
}
