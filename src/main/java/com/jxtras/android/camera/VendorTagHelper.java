package com.jxtras.android.camera;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VendorTagHelper {

    private final static HashMap<String, ConcurrentHashMap<VendorTag<?>, Boolean>> sLookupTable = new HashMap<>();

    private static ConcurrentHashMap<VendorTag<?>, Boolean> getLookupTable(String cameraId) {
        ConcurrentHashMap<VendorTag<?>, Boolean> map = sLookupTable.get(cameraId);
        if (map == null) {
            synchronized (sLookupTable) {
                map = sLookupTable.get(cameraId);
                if (map == null) {
                    sLookupTable.put(cameraId, new ConcurrentHashMap<>());
                }
            }
        }
        return sLookupTable.get(cameraId);
    }

    public final static class CaptureRequestKey<T> extends VendorTag<CaptureRequest.Key<T>> {

        private final String name;
        private final Class<T> type;

        public static <T> CaptureRequestKey<T> create(final String name, final Class<T> type) {
            return new CaptureRequestKey<>(name, type);
        }

        public T getValue(String cameraId, CaptureRequest request) {
            Map<VendorTag<?>, Boolean> map = getLookupTable(cameraId);
            if (map != null && !map.getOrDefault(this, Boolean.TRUE)) {
                return null;
            }
            try {
                return request.get(getKey());
            } catch (IllegalArgumentException unused) {
                if (map != null) {
                    map.put(this, Boolean.FALSE);
                }
                return null;
            }
        }

        public T getValue(String cameraId, CaptureRequest.Builder builder) {
            Map<VendorTag<?>, Boolean> map = getLookupTable(cameraId);
            if (map != null && !map.getOrDefault(this, Boolean.TRUE)) {
                return null;
            }
            try {
                return builder.get(getKey());
            } catch (IllegalArgumentException unused) {
                if (map != null) {
                    map.put(this, Boolean.FALSE);
                }
                return null;
            }
        }

        public void setValue(String cameraId, CaptureRequest.Builder builder, T value) {
            Map<VendorTag<?>, Boolean> map = getLookupTable(cameraId);
            if (map != null && !map.getOrDefault(this, Boolean.TRUE)) {
                return;
            }
            try {
                builder.set(getKey(), value);
            } catch (IllegalArgumentException unused) {
                if (map != null) {
                    map.put(this, Boolean.FALSE);
                }
            }
        }

        private CaptureRequestKey(String name, Class<T> type) {
            super();
            this.name = name;
            this.type = type;
        }

        @Override
        public CaptureRequest.Key<T> createKey() {
            return new CaptureRequest.Key<>(name, type);
        }

        @NonNull
        @Override
        public String toString() {
            return String.format("CaptureRequest.Key(%s)", getName());
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }
    }

    public final static class CaptureResultKey<T> extends VendorTag<CaptureResult.Key<T>> {

        private final String name;
        private final Class<T> type;

        public static <T> CaptureResultKey<T> create(final String name, final Class<T> type) {
            return new CaptureResultKey<>(name, type);
        }

        public T getValue(String cameraId, CaptureResult result) {
            Map<VendorTag<?>, Boolean> map = getLookupTable(cameraId);
            if (map != null && !map.getOrDefault(this, Boolean.TRUE)) {
                return null;
            }
            try {
                return result.get(getKey());
            } catch (IllegalArgumentException unused) {
                if (map != null) {
                    map.put(this, Boolean.FALSE);
                }
                return null;
            }
        }

        private CaptureResultKey(String name, Class<T> type) {
            super();
            this.name = name;
            this.type = type;
        }

        @Override
        public CaptureResult.Key<T> createKey() {
            return new CaptureResult.Key<>(name, type);
        }

        @NonNull
        @Override
        public String toString() {
            return String.format("CaptureResult.Key(%s)", getName());
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }
    }

    public final static class CameraCharacteristicsKey<T> extends VendorTag<CameraCharacteristics.Key<T>> {

        private final String name;
        private final Class<T> type;

        public static <T> CameraCharacteristicsKey<T> create(final String name, final Class<T> type) {
            return new CameraCharacteristicsKey<>(name, type);
        }

        public T getValue(String cameraId, CameraCharacteristics characteristics) {
            Map<VendorTag<?>, Boolean> map = getLookupTable(cameraId);
            if (map != null && !map.getOrDefault(this, Boolean.TRUE)) {
                return null;
            }
            try {
                return characteristics.get(getKey());
            } catch (IllegalArgumentException unused) {
                if (map != null) {
                    map.put(this, Boolean.FALSE);
                }
                return null;
            }
        }

        private CameraCharacteristicsKey(String name, Class<T> type) {
            super();
            this.name = name;
            this.type = type;
        }

        @Override
        public CameraCharacteristics.Key<T> createKey() {
            return new CameraCharacteristics.Key<>(name, type);
        }

        @NonNull
        @Override
        public String toString() {
            return String.format("CameraCharacteristics.Key(%s)", getName());
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }
    }
}
