package net.leolink.android.androidmvp.recyclerview;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.leolink.android.androidmvp.Item;
import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.BaseView;

/**
 * @author Leo
 */
public class SimpleColorfulPresenter extends ViewHolderPresenter<Parcelable, SimpleColorfulPresenter.SimpleColorfulView> {
    private int mBgColor;

    public SimpleColorfulPresenter(@NonNull SimpleColorfulView view, Context context, int bgColor) {
        super(view, context);
        this.mBgColor = bgColor;
    }

    @Override
    public void present(Parcelable data, int pos, int viewType) {
        Item item = (Item) data;
        mView.setTextView(item.text);
        mView.setButtonText(item.button);
        mView.setBackgroundColor(mBgColor);
    }

    public interface SimpleColorfulView extends BaseView {
        void setTextView(String text);
        void setButtonText(String text);
        void setBackgroundColor(int color);
    }
}