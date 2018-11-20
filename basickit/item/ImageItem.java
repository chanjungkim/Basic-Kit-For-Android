package com.koreanlab.bestfood.item;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chanj on 25/02/2018.
 */

public class ImageItem {
    public int seq;
    @SerializedName("info_seq") public int infoSeq;
    @SerializedName("file_name") public String fileName;
    @SerializedName("image_memo") public String imageMemo;
    @SerializedName("reg_date") public String regDate;

    @Override
    public String toString() {
        return "ImageItem{" +
                "seq=" + seq +
                ", infoSeq=" + infoSeq +
                ", fileName='" + fileName + '\'' +
                ", imageMemo='" + imageMemo + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
