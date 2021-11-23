package com.mygdx.drop.Entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Drop extends Rectangle {

    private int id;

    public Drop(int score){
        if(score < 10){
            id = 1;
        }else if(score < 50){
            id = MathUtils.random(0,5);
        }else{
            id = MathUtils.random(0,3);
        }

    }

    public int getId(){
        return id;
    }
}
