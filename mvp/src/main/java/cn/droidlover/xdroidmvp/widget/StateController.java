package cn.droidlover.xdroidmvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import cn.droidlover.xdroidmvp.R;

/**
 * Created by wanglei on 2016/1/21.
 */


public class StateController extends FrameLayout {

    View loadingView, errorView, emptyView, contentView;
    View currentView;

    public static final int STATE_LOADING = 0x1;
    public static final int STATE_ERROR = 0x2;
    public static final int STATE_EMPTY = 0x3;
    public static final int STATE_CONTENT = 0x4;
    int displayState = -1;

    int loadingLayoutId, errorLayoutId, emptyLayoutId, contentLayoutId;

    static final int RES_NONE = -1;



    public StateController(Context context) {
        this(context, null);
    }

    public StateController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupAttrs(context, attrs);
    }

    private void setupAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StateController);
        loadingLayoutId = typedArray.getResourceId(R.styleable.StateController_x_loadingLayoutId, RES_NONE);
        errorLayoutId = typedArray.getResourceId(R.styleable.StateController_x_errorLayoutId, RES_NONE);
        emptyLayoutId = typedArray.getResourceId(R.styleable.StateController_x_emptyLayoutId, RES_NONE);
        contentLayoutId = typedArray.getResourceId(R.styleable.StateController_x_contentLayoutId, RES_NONE);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount > 1) {
            throw new IllegalStateException("XStateController can only host 1 elements");
        } else {
            if (loadingLayoutId != RES_NONE) {
                loadingView = inflate(getContext(), loadingLayoutId, null);
                addView(loadingView);
            }
            if (errorLayoutId != RES_NONE) {
                errorView = inflate(getContext(), errorLayoutId, null);
                addView(errorView);
            }
            if (emptyLayoutId != RES_NONE) {
                emptyView = inflate(getContext(), emptyLayoutId, null);
                addView(emptyView);
            }
            if (contentLayoutId != RES_NONE) {
                contentView = inflate(getContext(), contentLayoutId, null);
                addView(contentView);
            }

            if (contentView == null) {
                if (childCount == 1) {
                    contentView = getChildAt(0);
                }
            }
            /*if (contentView == null) {
                throw new IllegalStateException("contentView can not be null");
            }*/

            for (int index = 0; index < getChildCount(); index++) {
                getChildAt(index).setVisibility(GONE);
            }

        }

    }


    public void setDisplayState(int newState) {
        int oldState = displayState;

        if (newState != oldState) {

            switch (newState) {
                case STATE_LOADING:
                    notifyStateChange(oldState, newState, loadingView);
                    break;
                case STATE_ERROR:
                    notifyStateChange(oldState, newState, errorView);
                    break;
                case STATE_EMPTY:
                    notifyStateChange(oldState, newState, emptyView);
                    break;
                case STATE_CONTENT:
                    notifyStateChange(oldState, newState, contentView);
                    break;
            }

        }
    }

    private View getDisplayView(int oldState) {
        if (oldState == STATE_LOADING) return loadingView;
        if (oldState == STATE_ERROR) return errorView;
        if (oldState == STATE_EMPTY) return emptyView;
        return contentView;
    }


    private void notifyStateChange(final int oldState, final int newState, final View enterView) {
        if (enterView != null) {
            if(currentView != null) {
                currentView.setVisibility(GONE);
            }
            displayState = newState;
            enterView.setVisibility(VISIBLE);
            currentView = enterView;
        }
    }


    public int getState() {
        return displayState;
    }

    public void showContent() {
        setDisplayState(STATE_CONTENT);
    }

    public void showEmpty() {
        setDisplayState(STATE_EMPTY);
    }

    public void showError() {
        setDisplayState(STATE_ERROR);
    }

    public void showLoading() {
        setDisplayState(STATE_LOADING);
    }

    public View getLoadingView() {
        return loadingView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public View getErrorView() {
        return errorView;
    }

    public View getContentView() {
        return contentView;
    }

    public StateController setLoadingView(View loadingView) {
        if (this.loadingView != null) {
            removeView(this.loadingView);
        }
        this.loadingView = loadingView;
        addView(this.loadingView);
        this.loadingView.setVisibility(GONE);
        return this;
    }

    public StateController setErrorView(View errorView) {
        if (this.errorView != null) {
            removeView(this.errorView);
        }
        this.errorView = errorView;
        addView(this.errorView);
        this.errorView.setVisibility(GONE);
        return this;
    }

    public StateController setEmptyView(View emptyView) {
        if (this.emptyView != null) {
            removeView(this.emptyView);
        }
        this.emptyView = emptyView;
        addView(this.emptyView);
        this.emptyView.setVisibility(GONE);
        return this;
    }

    public StateController setContentView(View contentView) {
        if (this.contentView != null) {
            removeView(this.contentView);
        }
        this.contentView = contentView;
        addView(this.contentView);
        this.contentView.setVisibility(GONE);
        return this;
    }

}
