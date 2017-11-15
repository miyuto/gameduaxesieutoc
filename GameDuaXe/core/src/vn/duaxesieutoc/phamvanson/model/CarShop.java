package vn.duaxesieutoc.phamvanson.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by MIYUTO on 13/11/2017.
 */

public class CarShop {
    private TextureRegion carUnLock;
    private  TextureRegion carLock;
    private int x1,y1,width1,height1;
    private int x2,y2,width2,height2;
    public CarShop(TextureRegion carUnLock, TextureRegion carLock) {
        this.carUnLock = carUnLock;
        this.carLock = carLock;
    }
    public void setPositionUnLock(){
        x1 = 75;
        y1 = 470;
        width1 = 321;
        height1 = 142;
    }
    public void setPositionLock(){
        x2 = 75;
        y2 = 470;
        width2 = 321;
        height2 = 190;
    }

    public TextureRegion getCarUnLock() {
        return carUnLock;
    }

    public TextureRegion getCarLock() {
        return carLock;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getWidth1() {
        return width1;
    }

    public int getHeight1() {
        return height1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getWidth2() {
        return width2;
    }

    public int getHeight2() {
        return height2;
    }

    public void setCarUnLock(TextureRegion carUnLock) {
        this.carUnLock = carUnLock;
    }
}
