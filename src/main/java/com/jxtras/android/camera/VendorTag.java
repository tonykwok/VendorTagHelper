package com.jxtras.android.camera;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class VendorTag<K> {
    private final static AtomicInteger sIdGenerator = new AtomicInteger(0);

    private final AtomicReference<K> mCachedKey = new AtomicReference<>(null);
    private final int id;

    public VendorTag() {
        id = sIdGenerator.getAndIncrement();
    }

    public final K getKey() {
        K key = mCachedKey.get();
        if (key == null) {
            synchronized (mCachedKey) {
                key = mCachedKey.get();
                if (key == null) {
                    mCachedKey.set(createKey());
                }
            }
        }
        return mCachedKey.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(Object o) {
        return o instanceof VendorTag && ((VendorTag<K>)o).id == id;
    }

    @Override
    public final int hashCode() {
        return id;
    }

    protected abstract K createKey();
}
