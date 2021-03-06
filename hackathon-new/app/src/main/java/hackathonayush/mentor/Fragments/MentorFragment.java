package hackathonayush.mentor.Fragments;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import butterknife.ButterKnife;
import hackathonayush.mentor.Model.MentorLightTextView;
import hackathonayush.mentor.R;

/**
 * Created by user on 19-Jun-17.
 */

abstract public class MentorFragment extends Fragment {

    protected Toolbar toolbar;
    protected MentorLightTextView a2doodhLightTextView;



    public MentorFragment() {

    }

    @LayoutRes
    abstract protected int getLayoutResId();

    abstract protected String setToolbarTitle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }


    protected void replaceFragment(Fragment launchingFragment, int containerId, Fragment currentFragmentObject) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragmentObject);
        transaction.add(containerId, launchingFragment);
        transaction.commit();
    }

    protected void setToolBarTitleOnFragment() {
        Toolbar toolbar = (Toolbar) getView().getRootView().findViewById(R.id.toolbar);
        View view;
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            view = toolbar.getChildAt(i);
            if (view instanceof MentorLightTextView) {
                ((MentorLightTextView) view).setText(setToolbarTitle());
                break;
            }
        }
    }

    protected void setToolBarTitleOnFragment(String title) {
        Toolbar toolbar = (Toolbar) getView().getRootView().findViewById(R.id.toolbar);
        View view;
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            view = toolbar.getChildAt(i);
            if (view instanceof MentorLightTextView) {
                ((MentorLightTextView) view).setText(title);
                break;
            }
        }
    }


    protected int getFragmentContainer() {
        int containerID = ((ViewGroup) getView().getParent()).getId();
        return containerID;
    }


    protected void hideKeyboard() {
        if (getActivity() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getView() != null) {
            imm.hideSoftInputFromWindow(getView().getApplicationWindowToken(), 0);
        }
    }

    protected void showKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                getView().getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    public void playSound(){
        MediaPlayer ring= MediaPlayer.create(getContext(),R.raw.click);
        ring.start();
    }

    protected void goToFragment(Fragment fragment, boolean addToBackStack, int container) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        if (fragmentManager.findFragmentById(R.id.container) == null) {
            transaction.add(container, fragment).commit();
        }
    }

}
