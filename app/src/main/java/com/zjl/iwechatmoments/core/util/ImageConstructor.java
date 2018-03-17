package com.zjl.iwechatmoments.core.util;

import com.zjl.iwechatmoments.R;

/**
 * Created by zjl on 2018/3/16.
 * Default image constructor.
 */

public class ImageConstructor {
    private static int[] imageUrl = {
            R.drawable.avatar,
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3
    };
    public static int mappingImageUrl(int i) {
        return imageUrl[i % imageUrl.length];
    }
}
