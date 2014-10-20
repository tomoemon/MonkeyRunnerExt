package com.android.monkeyrunner;

import java.awt.image.DataBufferByte;
import java.io.IOException;

public class MonkeyRunnerExt {

    public static void touchMove(MonkeyDevice device, int x, int y) throws IOException {
        device.getImpl().getManager().touchMove(x, y);
    }

    public static int[] takeSnapshotArray(MonkeyDevice device) {
        return convertImageToArray(device.takeSnapshot());
    }

    public static int[] convertImageToArray(MonkeyImage image) {
        // link: http://stackoverflow.com/questions/10247123/java-convert-bufferedimage-to-byte-without-writing-to-disk
        return convertByteArrayToIntArray(((DataBufferByte)image.getImpl().getBufferedImage().getRaster().getDataBuffer()).getData());
    }

    private static int[] convertByteArrayToIntArray(byte[] array) {
        int[] result = new int[array.length];
        for(int i=0; i<array.length; i++) {
            result[i] = (int)(array[i] & 0xFF);
        }
        return result;
    }
}
