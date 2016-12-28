package net.leolink.android.androidmvp.recyclerview;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.leolink.android.androidmvp.R;
import net.leolink.android.androidmvp.mvp.presenter.ViewHolderPresenter;
import net.leolink.android.androidmvp.mvp.view.recyclerview.BaseAdapter;
import net.leolink.android.androidmvp.mvp.view.recyclerview.BaseViewHolder;
import net.leolink.android.androidmvp.mvp.view.BaseView;

import java.util.List;

import butterknife.BindView;

/**
 * @author Leo
 */

public class ComplexViewHolder extends BaseViewHolder<Parcelable, ComplexPresenter.ComplexView, ComplexPresenter>
        implements ComplexPresenter.ComplexView {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    View mProgressView;

    private LinearLayoutManager mLayoutManager;
    private ComplexViewAdapter mAdapter;

    public ComplexViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void showItems(List<Parcelable> items) {
        mAdapter.setNum(100);
    }

    @Override
    public void setLoadingIndicatorVisible(boolean visible) {
        mProgressView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setupView() {
        mLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ComplexViewAdapter(0);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // this is to fix the case where this horizontal view is on screen, user scrolls a little bit, then
                // rotate screen => scrolling position is not saved because #onSaveViewState hasn't been called called
                // after user scrolls.
                // After screen rotation, because data has been already loaded, presenter#present will be called
                // which causes old scroll position being restored.
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    presenter.saveScrollPosition();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveViewState(@NonNull Bundle outState) {
        Log.e("linhln", "save... " + getAdapterPosition());
        outState.putParcelable("state", mLayoutManager.onSaveInstanceState());
    }

    @Override
    public void onRestoreViewState(@NonNull Bundle savedInstanceState) {
        Log.e("linhln", "restore... " + getAdapterPosition() + " - contain: " + savedInstanceState.containsKey("state"));
        LinearLayoutManager.SavedState state = new LinearLayoutManager.SavedState();
        if (savedInstanceState.containsKey("state")) {
            state = savedInstanceState.getParcelable("state");
        }
        mLayoutManager.onRestoreInstanceState(state);
    }

    //==============================================================================================

    private static class ComplexViewAdapter extends BaseAdapter<Void> {
        private int num;

        public ComplexViewAdapter(int num) {
            this.num = num;
        }

        public void setNum(int num) {
            this.num = num;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return num;
        }

        @Override
        public ComplexItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_complex_item, parent, false);
            ComplexItemViewHolder res = new ComplexItemViewHolder(view);
            res.setPresenter(new ViewHolderPresenter<Void, ComplexItemView>(res) {
                @Override
                public void present(Void item, int pos) {
                    view.setText("pos " + pos);
                }
            });
            return res;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder<Void, ? extends BaseView, ? extends ViewHolderPresenter> holder, int position) {
            holder.presenter.present(null, position);
        }
    }

    public static class ComplexItemViewHolder extends BaseViewHolder<Void, ComplexItemView, ViewHolderPresenter<Void, ComplexItemView>>
            implements ComplexItemView {
        @BindView(R.id.button)
        Button button;

        public ComplexItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setText(String text) {
            button.setText(text);
        }

        @Override
        public void setupView() {

        }
    }

    private interface ComplexItemView extends BaseView {
        void setText(String text);
    }
}
