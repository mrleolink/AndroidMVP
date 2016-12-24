package net.leolink.android.androidmvp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Leo
 */

public class Item implements Parcelable {
    public String text;
    public String button;

    public Item(String text, String button) {
        this.text = text;
        this.button = button;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.button);
    }

    protected Item(Parcel in) {
        this.text = in.readString();
        this.button = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {return new Item(source);}

        @Override
        public Item[] newArray(int size) {return new Item[size];}
    };
}
