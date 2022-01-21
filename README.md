_You will NEVER worry about `VendorTagNotFoundException` anymore!_

_Create your own `VendorTag` using `VendorTagHelper` and that's all!_


# Get Started

Step 1: Defined your own `VendorTag` in `VendorTags.java`

```java
public static final CaptureRequestKey<Integer> CONTROL_HDR_MODE = CaptureRequestKey.create(
        "com.google.control.hdr.mode", Integer.class);
```

Step 2: Update its value in `CaptureRequest` or `CaptureRequest.Builder`

```java
// turn off HDR for the CaptureRequest that to be submitted to CameraDevice 0
CONTROL_HDR_MODE.set(/* camera id */ 0, captureRequest, /* HDR_OFF*/ 0);
```
