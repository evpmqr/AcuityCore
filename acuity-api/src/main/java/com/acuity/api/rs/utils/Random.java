package com.acuity.api.rs.utils;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Random {

    private static final java.util.Random GEN = new java.util.Random();

    private Random() {
        throw new IllegalAccessError();
    }

    public static int nextInt() {
        return GEN.nextInt();
    }

    public static int nextInt(int max) {
        return GEN.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        if (min > max) {
            min ^= max;
            max = min ^ max;
            min ^= max;
        }
        return min + nextInt(max - min);
    }

    public static double nextDouble() {
        return GEN.nextDouble();
    }

    public static double nextDouble(double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        if (min == max) {
            return min;
        }
        return min + nextDouble() * (max - min);
    }

    public static boolean nextBoolean() {
        return GEN.nextBoolean();
    }

    public static <T> T nextElement(T[] elements) {
        if (elements == null || elements.length == 0){
            return null;
        }

        if (elements.length == 1){
            return elements[0];
        }
        return elements[nextInt(elements.length - 1)];
    }

    @SuppressWarnings("unchecked")
    public static <T> T nextElement(List<T> elements) {
        if (elements == null || elements.size() == 0){
            return null;
        }

        if (elements.size() == 1){
            return elements.get(0);
        }
        return elements.get(nextInt(elements.size() - 1));
    }

    public static double nextGaussian() {
        return GEN.nextGaussian();
    }

    public static double mid(double min, double max) {
        double r = max - min;
        double m = r / 2;
        double s = Random.nextDouble(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + (sign * (m - Math.sqrt(m * m - s * s)));
    }

    public static int mid(int min, int max) {
        int r = max - min;
        int m = r / 2;
        int s = Random.nextInt(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return (int) (min + m + (sign * (m - Math.sqrt(m * m - s * s))));
    }

    public static int polar(int min, int max) {
        int r = max - min;
        int m = r / 2;
        int s = Random.nextInt(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + (int) (sign * Math.sqrt(m * m - s * s));
    }

    public static double polar(double min, double max) {
        double r = max - min;
        double m = r / 2;
        double s = Random.nextDouble(0, m);
        int sign = Random.nextBoolean() ? -1 : 1;
        return m + sign * Math.sqrt(m * m - s * s);
    }

    public static int high(int min, int max) {
        int r = max - min;
        int s = Random.nextInt(0, r);
        return (int) (min + Math.sqrt(r * r - s * s));
    }

    public static double high(double min, double max) {
        double r = max - min;
        double s = Random.nextDouble(0, r);
        return (min + Math.sqrt(r * r - s * s));
    }

    public static int low(int min, int max) {
        int r = max - min;
        int s = Random.nextInt(0, r);
        return (int) (max - Math.sqrt(r * r - s * s));
    }

    public static double low(double min, double max) {
        double r = max - min;
        double s = Random.nextDouble(0, r);
        return (max - Math.sqrt(r * r - s * s));
    }

    public static Point nextPoint(Rectangle r) {
        int rx = r.x + nextInt(0, r.width);
        int ry = r.y + nextInt(0, r.height);
        return new Point(rx, ry);
    }

    public static <T extends java.util.List> T shuffle(T t){
        Collections.shuffle(t);
        return t;
    }

    public static Point mid(Rectangle r) {
        return mid(r.x, r.y, r.width, r.height);
    }

    public static Point mid(int x, int y, int w, int h) {
        int rx = x + mid(0, w);
        int ry = y + mid(0, h);
        return new Point(rx, ry);
    }

    private static int idProgress = 0;
    public synchronized static String nextProgressiveID(){
        if (idProgress > 99999){
            idProgress = 0;
        }
        return System.currentTimeMillis() + "." + Random.nextInt(100, 999) + "." + idProgress++;
    }

}
