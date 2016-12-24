package net.leolink.android.androidmvp.recyclerview;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import net.leolink.android.androidmvp.R;
import net.leolink.android.androidmvp.mvp.view.recyclerview.BaseViewHolder;

import butterknife.BindView;

/**
 * @author Leo
 */

public class SimpleColorfulViewHolder extends BaseViewHolder<Parcelable, SimpleColorfulPresenter.SimpleColorfulView, SimpleColorfulPresenter>
        implements SimpleColorfulPresenter.SimpleColorfulView {
    @BindView(R.id.textview)
    TextView mTextView;

    @BindView(R.id.button)
    TextView mButton;

    public SimpleColorfulViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setTextView(String text) {
        mTextView.setText(text);
    }

    @Override
    public void setButtonText(String text) {
        mButton.setText(text);
    }

    @Override
    public void setBackgroundColor(int color) {
        itemView.setBackgroundColor(color);
    }

    @Override
    public void setupView() {
    }
}
