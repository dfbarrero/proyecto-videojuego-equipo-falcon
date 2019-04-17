/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Elementos.Bocadillo;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author lucas
 */
public class WanderTipo1 extends WanderTipoT {

    float coordenadaX = 500, coordenadaY = 450;
    Image[] movementUp;
    Image[] movementDown;
    Image[] movementLeft;
    Image[] movementRight;
    Animation actual, sup, sdown, left, right, sright, sleft;
    int[] duration = {200, 200};
    int[] duration2 = {100, 100, 100, 100, 100, 100, 100, 100};
    Rectangle h1;
    Bocadillo habla = new Bocadillo();
    int movement = 0;

    public WanderTipo1(String nombre) {
        try {

            h1 = new Rectangle(coordenadaX + 17, coordenadaY + 10, 30, 50);

            Image[] movementLeft = {new Image("ImagenesSprite\\izq1.png"), new Image("ImagenesSprite\\izq2.png"), new Image("ImagenesSprite\\izq3.png"), new Image("ImagenesSprite\\izq4.png"), new Image("ImagenesSprite\\izq5.png"), new Image("ImagenesSprite\\izq6.png"), new Image("ImagenesSprite\\izq7.png"), new Image("ImagenesSprite\\izq8.png")};
            Image[] movementRight = {new Image("ImagenesSprite\\der1.png"), new Image("ImagenesSprite\\der2.png"), new Image("ImagenesSprite\\der3.png"), new Image("ImagenesSprite\\der4.png"), new Image("ImagenesSprite\\der5.png"), new Image("ImagenesSprite\\der6.png"), new Image("ImagenesSprite\\der7.png"), new Image("ImagenesSprite\\der8.png")};

            Image[] stanceDown = {new Image("ImagenesSprite\\down0.png"), new Image("ImagenesSprite\\down0.png")};
            Image[] stanceUp = {new Image("ImagenesSprite\\up0.png"), new Image("ImagenesSprite\\up0.png")};
            Image[] stanceLeft = {new Image("ImagenesSprite\\izq0.png"), new Image("ImagenesSprite\\izq0.png")};
            Image[] stanceRight = {new Image("ImagenesSprite\\der0.png"), new Image("ImagenesSprite\\der0.png")};

            left = new Animation(movementLeft, duration2, false);
            right = new Animation(movementRight, duration2, false);
            sup = new Animation(stanceUp, duration, false);
            sdown = new Animation(stanceDown, duration, false);
            sleft = new Animation(stanceLeft, duration, false);
            sright = new Animation(stanceRight, duration, false);

            actual = right;
        } catch (SlickException e) {
        }
    }

    public void setDir(String string) {

        switch (string) {
            case "up":
                actual = sup;
                break;
            case "down":
                actual = sdown;
                break;
            case "right":
                actual = right;
                break;
            case "left":
                actual = left;
                break;
            case "sright":
                actual = sright;
                break;
            case "sleft":
                actual = sleft;
                break;

        }
    }

    public Animation getDir() {
        return actual;
    }

    public float getCoordenadaX() {
        return this.coordenadaX;
    }

    public float getCoordenadaY() {
        return this.coordenadaY;
    }

    public void move() {
        if (movement < 200) {
            coordenadaX = coordenadaX + (float) (0.5);
            movement++;
            setDir("right");
        } else if (movement >= 200 && movement < 1200) {
            movement++;
            setDir("up");
        } else if (movement >= 1200 && movement < 1500) {
            movement++;
            coordenadaX = coordenadaX - (float) (0.5);
            setDir("left");
        } else if (movement >= 1500 && movement < 2500) {
            movement++;
            setDir("up");
        } else {
            movement = 0;
        }
        h1.setBounds(coordenadaX + 17, coordenadaY + 10, 30, 50);
    }

    public void setCoordenadaX(float coordenadaX) {
        this.coordenadaX = coordenadaX;
        h1.setBounds(coordenadaX + 17, coordenadaY + 10, 30, 50);
    }

    public void setCoordenadaY(float coordenadaY) {

        this.coordenadaY = coordenadaY;
        h1.setBounds(coordenadaX + 17, coordenadaY + 10, 30, 50);

    }

    public Rectangle getH1() {
        return h1;
    }

    public void talk() {
        habla.dentro((int) coordenadaX+20, (int) coordenadaY - 20);
    }

    public Bocadillo getTalk(){
        return habla;
    }

    public void noTalk() {
        habla.fuera();
    }

}
