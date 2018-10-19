package com.springboot.crm.sys.kaptcha;

import com.google.code.kaptcha.impl.DefaultNoise;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class MyDefaultNoise extends DefaultNoise {

    public MyDefaultNoise() {
    }

    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
        Color color = this.getConfig().getNoiseColor();
        int width = image.getWidth();
        int height = image.getHeight();
        Point2D[] pts = null;
        Random rand = new Random();
        CubicCurve2D cc = new CubicCurve2D.Float((float) width * factorOne, (float) height * rand.nextFloat(), (float) width * factorTwo, (float) height * rand.nextFloat(), (float) width * factorThree, (float) height * rand.nextFloat(), (float) width * factorFour, (float) height * rand.nextFloat());
        PathIterator pi = cc.getPathIterator((AffineTransform) null, 2.0D);
        Point2D[] tmp = new Point2D[200];
        int i = 0;

        while (!pi.isDone()) {
            float[] coords = new float[6];
            switch (pi.currentSegment(coords)) {
                case 0:
                case 1:
                    tmp[i] = new java.awt.geom.Point2D.Float(coords[0], coords[1]);
                default:
                    ++i;
                    pi.next();
            }
        }

        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);
        Graphics2D graph = (Graphics2D) image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graph.setColor(color);

        for (i = 0; i < pts.length - 1; ++i) {
            if (i < 3) {
                graph.setStroke(new BasicStroke(0.9F * (float) (4 - i)));
            }

            graph.drawLine((int) pts[i].getX(), (int) pts[i].getY(), (int) pts[i + 1].getX(), (int) pts[i + 1].getY());
        }

        graph.dispose();
    }
}
