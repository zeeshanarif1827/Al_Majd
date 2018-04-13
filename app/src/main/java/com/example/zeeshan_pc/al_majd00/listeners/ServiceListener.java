package com.example.zeeshan_pc.al_majd00.listeners;

import android.support.annotation.Nullable;

public interface ServiceListener<T> {
    void success(@Nullable T obj);
    void fail(@Nullable T obj);
}
