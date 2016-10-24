package com.android.thinkive.invest_sd.beans;

import java.io.Serializable;

/**
 * Created by xiangfan on 2015/7/13.
 */
public class FristPageCicrleItemBean implements Serializable {
    private int jump_type;
    private int flag;
    private int sort;
    private String image_name ;
    private String jump_url;
    private String image_url;
    private int is_open;
    private int is_default;
    private int is_readonly;
    private int tctm_id;
    private String tctm_name;
    private String group_no;
    public FristPageCicrleItemBean(){

    }
    public FristPageCicrleItemBean(int jump_type){
        this.jump_type = jump_type;
    }
    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public int getTctm_id() {
        return tctm_id;
    }

    public void setTctm_id(int tctm_id) {
        this.tctm_id = tctm_id;
    }

    public String getTctm_name() {
        return tctm_name;
    }

    public void setTctm_name(String tctm_name) {
        this.tctm_name = tctm_name;
    }

    public int getIs_readonly() {
        return is_readonly;
    }

    public void setIs_readonly(int is_readonly) {
        this.is_readonly = is_readonly;
    }

    public int getJump_type() {
        return jump_type;
    }

    public void setJump_type(int jump_type) {
        this.jump_type = jump_type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public boolean mathcingnewvalue(FristPageCicrleItemBean newbean){
        try {
            if (this.getJump_url() != newbean.getJump_url()) {
                return false;
            } else if (this.getJump_type() != newbean.getJump_type()) {
                return false;
            } else if (this.getFlag() != newbean.getFlag()) {
                return false;
            } else if (this.getImage_name() != newbean.getImage_name()) {
                return false;
            } else if (this.getImage_url() != newbean.getImage_url()) {
                return false;
            } else if (this.getSort() != newbean.getIs_open()) {
                return false;
            } else if (this.getSort() != newbean.getSort()) {
                return false;
            } else return true;
        }catch (Exception e){
            return true;
        }
    }
}
