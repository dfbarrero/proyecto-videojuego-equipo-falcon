/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author lucas
 */
public class Alerta {

    float coordenadaX = -1000, coordenadaY = -1000;
    Image foto;

    public Alerta() {
        try {
            foto = new Image("\\Elementos Aparte\\alerta.png");
        } catch (SlickException ex) {
        }
    }

    public void dentro(int cordX, int cordY) {
        coordenadaX = cordX;
        coordenadaY = cordY;
    }

    public void fuera() {
        coordenadaX = -1000;
        coordenadaY = -1000;
    }

    public float getCoordenadaX(){
        return coordenadaX;
    }
    public float getCoordenadaY(){
        return coordenadaY;
    }
    public Image getImagen() {
        return foto;
    }
}