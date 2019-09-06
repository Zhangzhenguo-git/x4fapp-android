
package com.x4fhuozhu.app.model;


import com.x4fhuozhu.app.base.BaseFragment;
import com.x4fhuozhu.app.base.IBasePresenter;

/**
 * @author cginechen
 * @date 2016-10-21
 */

public class QDItemDescription {
    private Class<? extends BaseFragment<IBasePresenter>> mKitDemoClass;
    private String mKitName;
    private int mIconRes;
    private String mDocUrl;

    public QDItemDescription(Class<? extends BaseFragment<IBasePresenter>> kitDemoClass, String kitName){
        this(kitDemoClass, kitName, 0, "");
    }


    public QDItemDescription(Class<? extends BaseFragment<IBasePresenter>> kitDemoClass, String kitName, int iconRes, String docUrl) {
        mKitDemoClass = kitDemoClass;
        mKitName = kitName;
        mIconRes = iconRes;
        mDocUrl = docUrl;
    }

    public Class<? extends BaseFragment<IBasePresenter>> getDemoClass() {
        return mKitDemoClass;
    }

    public String getName() {
        return mKitName;
    }

    public int getIconRes() {
        return mIconRes;
    }

    public String getDocUrl() {
        return mDocUrl;
    }
}
