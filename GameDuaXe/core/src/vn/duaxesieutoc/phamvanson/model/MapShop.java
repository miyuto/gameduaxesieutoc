package vn.duaxesieutoc.phamvanson.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MIYUTO on 12/11/2017.
 */

public class MapShop {
    private TextureRegion mapUnLock;
    private  TextureRegion mapLock;
    private Vector2 pos ;
    private int width,height;

    public MapShop(TextureRegion mapUnLock, TextureRegion mapLock) {
        this.mapUnLock = mapUnLock;
        this.mapLock = mapLock;
        pos = new Vector2(108,160);
        width = 269;
        height = 447;
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getMapLock() {
        return mapLock;
    }

    public TextureRegion getMapUnLock() {
        return mapUnLock;
    }
}
