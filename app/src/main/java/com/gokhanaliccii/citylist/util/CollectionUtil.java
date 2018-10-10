package com.gokhanaliccii.citylist.util;

import java.util.List;

public class CollectionUtil {

    private CollectionUtil() {
    }

    public static <T> int getListItemCount(List<T> items) {
        return items == null
                ? 0
                : items.size();
    }
}
