package com.jxtras.android.camera;

import com.jxtras.android.camera.VendorTagHelper.CaptureRequestKey;
import com.jxtras.android.camera.VendorTagHelper.CaptureResultKey;
import com.jxtras.android.camera.VendorTagHelper.CameraCharacteristicsKey;

public class VendorTags {
    public final static class CaptureRequestKeys {
        public static final CaptureRequestKey<Integer> CONTROL_HDR_MODE = CaptureRequestKey.create(
                "com.google.control.hdr.mode", Integer.class);
    }

    public final static class CaptureResultKeys {
        public static final CaptureResultKey<Integer> HDR_MODE = CaptureResultKey.create(
                "com.google.hdr.mode", Integer.class);
    }

    public final static class CameraCharacteristicsKeys {
        public static final CameraCharacteristicsKey<int[]> AVAILABLE_HDR_MODES = CameraCharacteristicsKey.create(
                "com.google.hdr.availableModes", int[].class);
    }
}
