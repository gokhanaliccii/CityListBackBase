package com.gokhanaliccii.citylist.data.datasource.loader;

import java.util.List;

public interface DataLoadListener {

    interface Single<T> {
        void onDataLoaded(T t);
    }

    interface Multi<T> {
        void onDataLoaded(List<T> list);
    }
}
