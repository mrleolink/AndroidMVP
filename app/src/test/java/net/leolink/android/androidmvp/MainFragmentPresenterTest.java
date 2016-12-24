package net.leolink.android.androidmvp;

import android.os.Bundle;
import android.os.Parcelable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;

/**
 * @author Leo
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 23, constants = BuildConfig.class)
public class MainFragmentPresenterTest {
    @Test
    public void loadItem() throws InterruptedException {
        MainFragmentPresenter.MainFragmentView mainFragmentView = Mockito.mock(MainFragmentPresenter.MainFragmentView.class);
        MainFragmentPresenter presenter = new MainFragmentPresenter(mainFragmentView);
        presenter.loadItems();
        Mockito.verify(mainFragmentView).setLoadingIndicatorVisible(true);
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        Mockito.verify(mainFragmentView).showItems(Mockito.any()); // we can also mock the loading service and test mock
                                                                   // data here instead of any()
        Mockito.verify(mainFragmentView).setLoadingIndicatorVisible(false);
    }

    @Test
    public void loadItemTwice() throws InterruptedException {
        MainFragmentPresenter.MainFragmentView mainFragmentView = Mockito.mock(MainFragmentPresenter.MainFragmentView.class);
        InOrder inOrder = Mockito.inOrder(mainFragmentView);
        MainFragmentPresenter presenter = new MainFragmentPresenter(mainFragmentView);
        presenter.loadItems();
        inOrder.verify(mainFragmentView).setLoadingIndicatorVisible(true);
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        inOrder.verify(mainFragmentView).showItems(Mockito.any()); // we can also mock the loading service and test mock
        // data here instead of any()
        inOrder.verify(mainFragmentView).setLoadingIndicatorVisible(false);
        presenter.loadItems();
        inOrder.verify(mainFragmentView).setLoadingIndicatorVisible(true);
        inOrder.verify(mainFragmentView).showItems(Mockito.any()); // we can also mock the loading service and test mock
        inOrder.verify(mainFragmentView).setLoadingIndicatorVisible(false);
    }

    @Test
    public void testOnSaveInstanceState() {
        MainFragmentPresenter.MainFragmentView mainFragmentView = Mockito.mock(MainFragmentPresenter.MainFragmentView.class);
        MainFragmentPresenter presenter = new MainFragmentPresenter(mainFragmentView);
        presenter.loadItems(); // we should have a mock loading service to mock data here
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        Bundle bundle = new Bundle();
        presenter.onSaveInstanceState(bundle);
        Assert.assertNotNull(bundle.getParcelableArrayList("items"));
        Assert.assertThat(bundle.getParcelableArrayList("items").size(), is(100));
    }

    @Test
    public void testSaveRestoreInstanceState() {
        MainFragmentPresenter.MainFragmentView mainFragmentView = Mockito.mock(MainFragmentPresenter.MainFragmentView.class);
        MainFragmentPresenter presenter = new MainFragmentPresenter(mainFragmentView);

        Bundle bundle = new Bundle();
        ArrayList<Parcelable> data = new ArrayList<>();
        data.add(new Item("abc", "def"));
        data.add(new ComplexItem());
        bundle.putParcelableArrayList("items", data);
        presenter.onRestoreInstanceState(bundle);
        Mockito.verify(mainFragmentView).showItems(data);
    }
}
