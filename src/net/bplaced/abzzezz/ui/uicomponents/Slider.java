/*
 * Copyright (c) 2020. Roman P.
 * All code belongs to its owners!
 * Last modified: 24.06.20, 16:00
 * APIS used:
 * LWJGL (https://www.lwjgl.org/)
 * Slick (http://slick.ninjacave.com/slick-util/)
 * Abzzezz Util (https://github.com/Abzzezz/AbzzezzUtil)
 */

package net.bplaced.abzzezz.ui.uicomponents;
/*
 * Copyright (c) 2020. Roman P.
 * All code belongs to its owners!
 * Last modified: 24.06.20, 15:29
 * APIS used:
 * LWJGL (https://www.lwjgl.org/)
 * Slick (http://slick.ninjacave.com/slick-util/)
 * Abzzezz Util (https://github.com/Abzzezz/AbzzezzUtil)
 */

import net.bplaced.abzzezz.utils.MouseUtil;
import net.bplaced.abzzezz.utils.RenderUtil;
import net.bplaced.abzzezz.utils.Util;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class Slider implements UIComponent {

    private float min, max, current, xPos, yPos, step;
    private int width, height;
    private SliderListener sliderListener;
    private String text;

    public Slider(String text, float xPos, float yPos, int width, int height, float min, float max, float current) {
        this.text = text;
        this.min = min;
        this.max = max;
        this.current = current;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public Slider(String text, float xPos, float yPos, int width, int height, float min, float max) {
        this.text = text;
        this.min = min;
        this.max = max;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    @Override
    public void initComponent() {
        this.step = width / max;
    }

    //TODO: More design
    @Override
    public void drawComponent() {
        RenderUtil.drawQuad(xPos, yPos - height / 4, width, height, Color.GRAY);
        RenderUtil.drawQuad(xPos, yPos, current * step, height / 2, Util.mainColor);
        textFont.drawString(text + ":" + Math.round(current), xPos, yPos - height, textColor);
    }

    /**
     * TODO: Move to abzzezz util
     *
     * @param val
     * @param min
     * @param max
     * @return
     */
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public void keyListener(int keyCode, char keyTyped) {
        float newValue = 0;
        if (keyCode == Keyboard.KEY_LEFT) {
            newValue = current - step;
        } else if (keyCode == Keyboard.KEY_RIGHT) {
            newValue = current + step;
        }
        valueChanged(newValue);
    }

    @Override
    public void mouseListener(int mouseButton) {
        if (MouseUtil.mouseHovered(xPos, yPos, width, height)) {
            float newValue = min + ((Mouse.getX() - xPos) / width) * (max - min);
            valueChanged(newValue);
        }
    }

    /**
     * Checks if value has changed and calls listener if initialised
     * @param newValue
     */
    public void valueChanged(float newValue) {
        if (newValue > current && sliderListener != null) {
            sliderListener.onSliderValueIncreased(newValue);
        } else if (newValue < current && sliderListener != null) {
            sliderListener.onSliderValueDecreased(newValue);
        }
        this.current = clamp(newValue, min, max);
        if (sliderListener != null) sliderListener.onSliderValueChanged(current);
    }

    public void setSliderListener(SliderListener sliderListener) {
        this.sliderListener = sliderListener;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /*
            Not used
             */
    @Override
    public void drawShader() {

    }

    public interface SliderListener {
        void onSliderValueChanged(float value);
        void onSliderValueDecreased(float value);
        void onSliderValueIncreased(float value);
    }
}
