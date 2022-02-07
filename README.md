_Step 1: Defined your own `VendorTag` in `VendorTags.java`_

```java
public static final CaptureRequestKey<Integer> CONTROL_HDR_MODE = CaptureRequestKey.create(
        "com.google.control.hdr.mode", Integer.class);
```

_Step 2: Update its value in `CaptureRequest` or `CaptureRequest.Builder`_

```java
// turn off HDR for the CaptureRequest that to be submitted to CameraDevice 0
CONTROL_HDR_MODE.set(/* camera id */ 0, captureRequest, /* HDR_OFF */ 0);
```

_Just 2 steps, creating `VendorTag`s has never been so easy!_
