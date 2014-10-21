package com.android.monkeyrunner;

import java.awt.image.DataBufferByte;
import java.io.IOException;

public class MonkeyRunnerExt {

    /**
     * device に対して、指を押し下げたまま画面上を動かすイベントを発行する。
     * MonkeyDevice でもともと用意されている <code>drag</code> では、開始地点から終了地点まで動かした時点で
     * すぐに指を離してしまうため、指を離さない動作をシミュレートするため別に作成。
     * @param device
     * @param x
     * @param y
     * @throws IOException
     */
    public static void touchMove(MonkeyDevice device, int x, int y) throws IOException {
        device.getImpl().getManager().touchMove(x, y);
    }

    /**
     * MonkeyDevice を使って int の配列としてデバイスのスクリーンショットを取得する
     * MonkeyImage に対して <code>getRawPixel</code> を使って1点ずつ参照すると重いため、
     * 高速にピクセル参照したい場合はこちらの方が速い。
     *
     * 1座標の点に付き [R, G, B, A] の4要素を持っている。
     * 座標 (x, y) = (0, 0) が最初にあり (1, 0), (2, 0), ..., ({SCREEN_WIDTH} - 1, 0), (0, 1), ... の画素情報が順に並んでおり、
     * 配列上では [R, G, B, A, R, G, B, A, ...] という値が並んでいる。
     * <code>(0 <= R, G, B, A <= 255)</code>
     * @param device
     * @return
     */
    public static int[] takeSnapshotArray(MonkeyDevice device) {
        return convertImageToArray(device.takeSnapshot());
    }

    /**
     * MonkeyImage を int の一次元配列に変換して返す
     * @param image
     * @return
     */
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
