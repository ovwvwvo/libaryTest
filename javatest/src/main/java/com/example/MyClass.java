package com.example;

public class MyClass {

    public static void main(String[] args) {
        heart(18, 0.8, "*");
    }

    private static void heart(int r, double size, String req) {
        size = 1 / (1.5 * r * size);
        StringBuilder sb = new StringBuilder();
        for (int y = r; y > -r; y--, sb.append("\n"))
            for (int x = -2 * r; x < 3 * r; x++) {
                char msg = (req + req).charAt((x - y) % req.length() + req.length());
                sb.append((inHeart(size, x, y) ? msg + "" : " "));
            }
        System.err.println(sb.toString());
    }

    private static boolean inHeart(double size, int x, int y) {
        return Math.pow(Math.pow(x * size, 2) + Math.pow(y * 2 * size, 2) - 1, 3) - Math.pow(x * size, 2) * Math.pow(y * 2 * size, 3) <= 0;
    }
}
