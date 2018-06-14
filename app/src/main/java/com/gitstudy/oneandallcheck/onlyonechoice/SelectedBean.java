package com.gitstudy.oneandallcheck.onlyonechoice;

import java.io.Serializable;

/**
 * Created by mbcloud-cuilk on 2018/6/12.
 * 介绍：是否勾选中的Bean
 */
public class SelectedBean implements Serializable{
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
