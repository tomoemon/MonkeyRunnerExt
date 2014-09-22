package com.android.monkeyrunner;

import java.io.IOException;

public class MonkeyRunnerExt {

    public static void touchMove(MonkeyDevice device, int x, int y) throws IOException {
        device.getImpl().getManager().touchMove(x, y);
    }
}
