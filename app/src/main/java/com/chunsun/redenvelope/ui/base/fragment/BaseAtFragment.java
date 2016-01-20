package com.chunsun.redenvelope.ui.base.fragment;

import android.view.View;
import android.widget.EditText;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;

import butterknife.Bind;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/21 11:50
 * @des @功能父类
 */
public abstract class BaseAtFragment<V, T extends BasePresenter<V>> extends MBaseFragment {

    @Bind(R.id.et_comment)
    protected EditText mEtComment;

    /**
     * 回复@用户的id
     */
    protected String at = "0";
    /**
     * 头像点击事件
     */
    protected final View.OnClickListener mHeadPortraitOnClickListener;
    /**
     * 头像长按事件
     */
    protected final View.OnLongClickListener mHeadPortraitOnLongClickListener;

    public BaseAtFragment() {
        mHeadPortraitOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head_logo:
                        Object tag = v.getTag(R.id.tag_first);
                        toUserRewardActivity(tag.toString());
                        break;
                }
            }
        };
        mHeadPortraitOnLongClickListener = new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                at = v.getTag(R.id.tag_first).toString();
                String tag = v.getTag(R.id.tag_second).toString();
                mEtComment.setHint("@" + tag + "：");
                return true;
            }
        };
    }

    /**
     * 跳转用户奖励页面
     */
    protected abstract void toUserRewardActivity(String id);
}