package net.leolink.android.androidmvp.recyclerview;

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

    public SimpleColorfulPresenter(@NonNull SimpleColorfulView view, int bgColor) {
        super(view);
        this.mBgColor = bgColor;
    }

    @Override
    public void present(Parcelable data, int pos) {
        Item item = (Item) data;
        view.setTextView(item.text);
        view.setButtonText(item.button);
        view.setBackgroundColor(mBgColor);
    }

    public interface SimpleColorfulView extends BaseView {
        void setTextView(String text);
        void setButtonText(String text);
        void setBackgroundColor(int color);
    }
}