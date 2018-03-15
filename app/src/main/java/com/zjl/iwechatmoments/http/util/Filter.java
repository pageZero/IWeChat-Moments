package com.zjl.iwechatmoments.http.util;

import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public interface Filter<T> {
    List<T> filter(List<T> items);
}
