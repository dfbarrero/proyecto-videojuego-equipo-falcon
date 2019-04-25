/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Elementos.Bocadillo;
import Personajes.PersonajeEstatico;
import Personajes.PersonajePrincipal;
import Personajes.PersonajeGeneral;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.*;

/**
 *
 * @author lucas
 */
public class ColegioInicial extends BasicGameState {

    @Override
    public int getID() {
        return 13; //To change body of generated methods, choose Tools | Templates.
    }
    
    private static TiledMap mapa;
    boolean choqueArriba = false, choqueAbajo = false, choqueIzquierda = false, choqueDerecha = false;
    PersonajePrincipal personaje;

    private float borde1[] = new float[]{477, 768, 477, 730, 417, 730, 417, 514, 405, 514, 405, 732, 287, 732, 287, 686, 193, 686, 193, 732, 126, 732, 126, 493, 40, 493, 40, 429, 153, 429, 153, 388, 102, 388, 102,
        353, 192, 353, 192, 363, 288, 363, 288, 353, 349, 353, 349, 373, 408, 373, 408, 448, 417, 448, 417, 352, 478, 352, 478, 321, 417, 321, 417, 225, 357, 225, 357, 288, 319, 288, 319, 225, 259, 225, 259, 288, 223, 288, 223, 226, 163, 226, 161, 288, 127,
        288, 127, 225, 69, 225, 69, 289, 40, 289, 40, 96, 70, 96, 70, 163, 100, 163, 100, 181, 127, 181, 127, 97, 165, 97, 165, 162, 197, 162, 197, 181, 224, 181, 224, 97, 260, 97, 260, 163, 292, 163, 292, 181, 319, 181, 319, 98, 357, 98, 357, 163, 389, 163, 389,
        181, 416, 181, 416, 97, 486, 97, 486, 171, 536, 171, 536, 132, 600, 132, 600, 318, 547, 318, 547, 350, 608, 350, 608, 256, 638, 256, 638, 225, 608, 225, 608, 98, 640, 98, 640, 125, 799, 125, 799, 97, 831, 97, 831, 117, 928, 117, 928, 97, 982, 97, 982,
        226, 706, 226, 706, 255, 992, 255, 992, 223, 1118, 223, 1118, 255, 1183, 255, 1183, 223, 1127, 223, 1127, 98, 1152, 98, 1152, 117, 1247, 117, 1247, 96, 1279, 96, 1279, 126, 1438, 126, 1438, 99, 1463, 99, 1463, 225, 1250, 225, 1250, 257, 1464, 257,
        1464, 449, 1090, 449, 1090, 480, 1146, 480, 1146, 739, 617, 739, 617, 697, 990, 697, 990, 508, 617, 508, 617, 474, 1020, 474, 1020, 449, 607, 449, 607, 733, 547, 733, 547, 768};
    private float borde2[] = new float[]{255,552,255,516,288,516,288,493,191,493,191,640,288,640,288,588,255,588};    
    private ArrayList<Polygon> colisiones_bordes;

    private float salida1[] = new float[]{477, 767, 545, 767, 445, 768, 577, 768};
    private ArrayList<Polygon> colisiones_salidas;

    private ArrayList<PersonajeGeneral> NPCs = new ArrayList<>();
    private ArrayList<Rectangle> colisionNPCs;
    PersonajeEstatico niño1  = new PersonajeEstatico("up", 970, 380, "bocadilloNiño1");
    PersonajeEstatico niño2  = new PersonajeEstatico("up", 930, 380, "bocadilloNiño2");
    PersonajeEstatico niño3  = new PersonajeEstatico("up", 890, 380, "bocadilloNiño3");
    PersonajeEstatico niño4  = new PersonajeEstatico("up", 850, 380, "bocadilloNiño4");
    PersonajeEstatico niño5  = new PersonajeEstatico("up", 810, 380, "bocadilloNiño5");
    PersonajeEstatico niño6  = new PersonajeEstatico("up", 770, 380, "bocadilloNiño6");
    PersonajeEstatico niño7  = new PersonajeEstatico("up", 730, 380, "bocadilloNiño7");
    PersonajeEstatico niño8  = new PersonajeEstatico("up", 690, 380, "bocadilloNiño8");
    PersonajeEstatico niño9  = new PersonajeEstatico("up", 80,680, "bocadilloNiño9");
    PersonajeEstatico niño10 = new PersonajeEstatico("up", 18,680, "bocadilloNiño10");
    PersonajeEstatico niño11 = new PersonajeEstatico("up", 80,580, "bocadilloNiño11");
    PersonajeEstatico niño12 = new PersonajeEstatico("up", 18,580, "bocadilloNiño12");
    PersonajeEstatico niño13 = new PersonajeEstatico("up", 80,480, "bocadilloNiño13");
    PersonajeEstatico niño14 = new PersonajeEstatico("up", 18,480, "bocadilloNiño14");

    boolean historia = false;
    int contadorTemporal1 = 0, contadorTemporal2 = 0;
    Bocadillo introduccionHistoria = new Bocadillo("Historia10");
    Bocadillo bocadilloSalida = new Bocadillo("Historia15");

    public ColegioInicial() {
        colisiones_bordes = new ArrayList<>();
        colisiones_bordes.add(new Polygon(borde1));

        colisiones_salidas = new ArrayList<>();
        colisiones_salidas.add(new Polygon(salida1));
        
        NPCs.add(niño1);
        NPCs.add(niño2);
        NPCs.add(niño3);
        NPCs.add(niño4);
        NPCs.add(niño5);
        NPCs.add(niño6);
        NPCs.add(niño7);
        NPCs.add(niño8);
        NPCs.add(niño9);
        NPCs.add(niño10);
        NPCs.add(niño11);
        NPCs.add(niño12);
        NPCs.add(niño13);
        NPCs.add(niño14);

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapa = new TiledMap("\\Mapas\\mapa7.tmx", "\\Construccion Mapas\\");
        personaje = new PersonajePrincipal();
        personaje.setCoordenadaX(482);
        personaje.setCoordenadaY(703);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            System.out.println(input.getMouseX()+","+input.getMouseY());
        }
        
        if (historia) {
            contadorTemporal2++;
            personaje.getDir().update(i);
        } else {
            
            if(contadorTemporal1<1500)
                contadorTemporal1++;
            
            int velocidad = 1;
            
            colisionNPCs = new ArrayList<>();
            for (int j = 0; j < NPCs.size(); j++) {
                colisionNPCs.add(NPCs.get(j).getHitbox());
            }

            if (input.isKeyDown(Input.KEY_W)) {
                personaje.setDir("up");
                personaje.getDir().update(i);
                for (int n = 0; n < colisiones_bordes.size(); n++) {
                    if (personaje.getH1().intersects(colisiones_bordes.get(n))) {
                        choqueArriba = true;
                        break;
                    } else {
                        choqueArriba = false;
                    }
                }
                for (int j = 0; j < NPCs.size(); j++) {
                    if (colisionNPCs.get(j).intersects(personaje.getH1())) {
                        if (NPCs.get(j).isSGB()) {
                            int state = NPCs.get(j).getSGB();
                            NPCs.get(j).notSGB();
                            if (state == 20) {
                                historia = true;
                            } else {
                                sbg.enterState(state);
                            }
                        }
                        choqueArriba = true;
                        break;
                    }
                }
                if (!choqueArriba || choqueAbajo) {
                    personaje.setCoordenadaY(personaje.getCoordenadaY() - i * 0.14f * velocidad);
                    for (int n = 0; n < colisiones_salidas.size(); n++) {
                        if (personaje.getH1().intersects(colisiones_salidas.get(n))) {
                            bocadilloSalida.dentro();
                        } else {
                            bocadilloSalida.fuera();
                        }
                    }
                    choqueAbajo = false;
                }

            } else if (input.isKeyDown(Input.KEY_S)) {
                personaje.setDir("down");
                personaje.getDir().update(i);
                for (int n = 0; n < colisiones_bordes.size(); n++) {
                    if (personaje.getH4().intersects(colisiones_bordes.get(n))) {
                        choqueAbajo = true;
                        break;
                    } else {
                        choqueAbajo = false;
                    }
                }
                for (int j = 0; j < NPCs.size(); j++) {
                    if (colisionNPCs.get(j).intersects(personaje.getH4())) {
                        if (NPCs.get(j).isSGB()) {
                            int state = NPCs.get(j).getSGB();
                            NPCs.get(j).notSGB();
                            if (state == 20) {
                                historia = true;
                            } else {
                                sbg.enterState(state);
                            }
                        }
                        choqueAbajo = true;
                        break;
                    }
                }
                if (!choqueAbajo || choqueArriba) {
                    personaje.setCoordenadaY(personaje.getCoordenadaY() + i * 0.14f * velocidad);
                    bocadilloSalida.fuera();
                    choqueArriba = false;
                }

            } else if (input.isKeyDown(Input.KEY_A)) {
                personaje.setDir("left");
                personaje.getDir().update(i);
                for (int n = 0; n < colisiones_bordes.size(); n++) {
                    if (personaje.getH2().intersects(colisiones_bordes.get(n))) {
                        choqueIzquierda = true;
                        break;
                    } else {
                        choqueIzquierda = false;
                    }
                }
                for (int j = 0; j < NPCs.size(); j++) {
                    if (colisionNPCs.get(j).intersects(personaje.getH2())) {
                        if (NPCs.get(j).isSGB()) {
                            int state = NPCs.get(j).getSGB();
                            NPCs.get(j).notSGB();
                            if (state == 20) {
                                historia = true;
                            } else {
                                sbg.enterState(state);
                            }
                        }
                        choqueIzquierda = true;
                        break;
                    }
                }
                if (!choqueIzquierda || choqueDerecha) {
                    personaje.setCoordenadaX(personaje.getCoordenadaX() - i * 0.16f * velocidad);
                    choqueDerecha = false;
                }
            } else if (input.isKeyDown(Input.KEY_D)) {
                personaje.setDir("right");
                personaje.getDir().update(i);
                for (int n = 0; n < colisiones_bordes.size(); n++) {
                    if (personaje.getH3().intersects(colisiones_bordes.get(n))) {
                        choqueDerecha = true;
                        break;
                    } else {
                        choqueDerecha = false;
                    }
                }
                for (int j = 0; j < NPCs.size(); j++) {
                    if (colisionNPCs.get(j).intersects(personaje.getH3())) {
                        if (NPCs.get(j).isSGB()) {
                            int state = NPCs.get(j).getSGB();
                            NPCs.get(j).notSGB();
                            if (state == 20) {
                                historia = true;
                            } else {
                                sbg.enterState(state);
                            }
                        }
                        choqueDerecha = true;
                        break;
                    }
                }
                if (!choqueDerecha || choqueIzquierda) {
                    personaje.setCoordenadaX(personaje.getCoordenadaX() + i * 0.16f * velocidad);
                    choqueIzquierda = false;
                }
            } else {
                personaje.setDir("stance");
                personaje.getDir().update(i);
            }

            for (int j = 0; j < NPCs.size(); j++) {
                if (colisionNPCs.get(j).intersects(personaje.getH4())) {
                    NPCs.get(j).setDir("sup");
                    NPCs.get(j).alerta();
                    ;
                    if (input.isKeyDown(Input.KEY_E)) {
                        NPCs.get(j).talk();
                    }
                } else if (colisionNPCs.get(j).intersects(personaje.getH3())) {
                    NPCs.get(j).setDir("sleft");
                    NPCs.get(j).alerta();
                    ;
                    if (input.isKeyDown(Input.KEY_E)) {
                        NPCs.get(j).talk();
                    }
                } else if (colisionNPCs.get(j).intersects(personaje.getH2())) {
                    NPCs.get(j).setDir("sright");
                    NPCs.get(j).alerta();
                    ;
                    if (input.isKeyDown(Input.KEY_E)) {
                        NPCs.get(j).talk();
                    }
                } else if (colisionNPCs.get(j).intersects(personaje.getH1())) {
                    NPCs.get(j).setDir("sdown");
                    NPCs.get(j).alerta();
                    ;
                    if (input.isKeyDown(Input.KEY_E)) {
                        NPCs.get(j).talk();
                    }
                } else {
                    NPCs.get(j).noAlerta();
                    NPCs.get(j).noTalk();
                    NPCs.get(j).move();
                    NPCs.get(j).getDir().update(i);
                }
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Input input = gc.getInput();
        
        mapa.render(0, 0, 0);
        mapa.render(0, 0, 1);
        
        for (int j = 0; j < NPCs.size(); j++) {
            NPCs.get(j).getDir().draw(NPCs.get(j).getCoordenadaX(), NPCs.get(j).getCoordenadaY());
            g.draw(NPCs.get(j).getHitbox());
        }
        
        personaje.getDir().draw((int) personaje.getCoordenadaX(), (int) personaje.getCoordenadaY());

        mapa.render(0, 0, 2);
        mapa.render(0, 0, 3);
        mapa.render(0, 0, 4);

        for (int i = 0; i < colisiones_bordes.size(); i++) {
            g.draw(colisiones_bordes.get(i));
        }

        for (int j = 0; j < NPCs.size(); j++) {
            NPCs.get(j).getTalk().getImagen().draw(NPCs.get(j).getTalk().getCoordenadaX(), NPCs.get(j).getTalk().getCoordenadaY());
            NPCs.get(j).getAlerta().getImagen().draw(NPCs.get(j).getAlerta().getCoordenadaX(), NPCs.get(j).getAlerta().getCoordenadaY());
        }
        
        if (input.isKeyDown(Input.KEY_T)) {
            g.drawImage(new Image("\\Elementos aparte\\mapa1.png"), 550, 200);
        }
        
        //HISTORIA
        if (contadorTemporal1 > 100 && contadorTemporal1 < 1500) {
            introduccionHistoria.dentro();
            introduccionHistoria.getImagen().draw(introduccionHistoria.getCoordenadaX(), introduccionHistoria.getCoordenadaY());
        }

        bocadilloSalida.getImagen().draw(bocadilloSalida.getCoordenadaX(), bocadilloSalida.getCoordenadaY());

        /*if (contadorTemporal2 > 0) {
            personaje.setDir("stance");
        if (contadorTemporal2 < 1600) {
            bocadilloProf1.dentro();
            bocadilloProf1.getImagen().draw(bocadilloProf1.getCoordenadaX(), bocadilloProf1.getCoordenadaY());
        } else if (contadorTemporal2 < 3200) {
            bocadilloAlex1.dentro();
            bocadilloAlex1.getImagen().draw(bocadilloAlex1.getCoordenadaX(), bocadilloAlex1.getCoordenadaY());
        } else if (contadorTemporal2 < 4400) {
            personaje.setDir("down");
            if (personaje.getCoordenadaY() < 120) {
                personaje.setCoordenadaY(personaje.getCoordenadaY() + 1);
            } else if (personaje.getCoordenadaX() < 1015) {
                personaje.setDir("right");
                personaje.setCoordenadaX(personaje.getCoordenadaX() + 1);
            } else if (personaje.getCoordenadaY() < 673) {
                personaje.setCoordenadaY(personaje.getCoordenadaY() + 1);
            } else {
                personaje.setDir("up");
                personaje.setDir("stance");
            }
        } else if (contadorTemporal2 < 7000) {
            bocadilloProf2.dentro();
            bocadilloProf2.getImagen().draw(bocadilloProf2.getCoordenadaX(), bocadilloProf2.getCoordenadaY());

        } else if (contadorTemporal2 < 7500) {
            if (profesor.getDir().getFrameCount() == 2) {
                profesor.lanza();
            } else {
                if (profesor.getDir().getFrame() != 5) {
                    profesor.getDir().update(contadorTemporal2 / 500);
                } else {
                    ball.setCoordenadas(950, 30);
                    ball.setDestinoN(personaje.getCoordenadaX(), personaje.getCoordenadaY());
                    ball.getDir().draw(ball.getCoordenadaX(), ball.getCoordenadaY());
                }
            }
        } else if (contadorTemporal2 < 7820) {
            profesor.noLanza();
            if (ball.getCoordenadaY() != personaje.getCoordenadaY()) {
                ball.move();
                ball.getDir().draw(ball.getCoordenadaX(), ball.getCoordenadaY());
            } 
        } else{
            sbg.enterState(11); // --> CINEMATICA1
        } */ 
    }
}
