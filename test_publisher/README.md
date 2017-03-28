# Android app for iMover

This package provides android code for the tele-operation application.

## Prerequisits:

- You need to have ROS installed on your system.
- Need the ROSJAVA packages:
```
apt-get install ros-kinetic-rosjava
``` 
- Need the [android software development kit](https://developer.android.com/studio/index.html#downloads).


## How to build:

1. Got to the root folder.
1. Run `catkin_make`
1. Wait for the build to finish.

## Where to find the binaries:

The compiled apk is located in
```
src/android/teleop/build/outputs/apk/teleop-debug.apk
```

## How to install de app

### Using ADB:
Plug your android device and run the following command:
```
adb install src/android/teleop/build/outputs/apk/teleop-debug.apk
```

### Drag & drop:

- Connect the android device and copy the apk file into the device's filesystem.
- Locate the file using the android file explorer and click to install it.
