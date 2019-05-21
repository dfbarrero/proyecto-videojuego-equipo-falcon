/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Elementos.Bocadillo;
import Personajes.PersonajeAcosador;
import Personajes.PersonajeCirculoRapido;
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
public class CompraInicial extends BasicGameState {

    @Override
    public int getID() {
        return 23; //To change body of generated methods, choose Tools | Templates.
    }

    private static TiledMap mapa;
    boolean choqueArriba = false, choqueAbajo = false, choqueIzquierda = false, choqueDerecha = false;
    PersonajePrincipal personaje;

    private float borde1[] = new float[]{384, 735, 64, 736, 63, 767, 31, 769, 31, 799, 0, 799, 0, 201, 842, 201, 840, 287, 1182, 287, 1183, 158, 1244, 159, 1245, 165, 1403, 165, 1407, 254, 1249, 257, 1247, 350, 1121, 351, 1121, 449, 1246, 450, 1247, 544, 1404, 546, 1408, 639, 1248, 640, 1247, 767, 551, 767, 550, 737, 480, 737, 480, 798, 384, 798, 384, 737};
    private float borde2[] = new float[]{95, 397, 192, 397, 192, 266, 95, 266};
    private float borde3[] = new float[]{95, 591, 191, 591, 191, 460, 95, 460};
    private float borde4[] = new float[]{255, 266, 255, 400, 352, 400, 352, 266};
    private float borde5[] = new float[]{255, 459, 255, 590, 352, 590, 352, 459};
    private float borde6[] = new float[]{510, 267, 510, 399, 607, 399, 607, 267};
    private float borde7[] = new float[]{510, 459, 510, 592, 608, 592, 608, 459};
    private float borde8[] = new float[]{671, 266, 671, 397, 767, 397, 767, 266};
    private float borde9[] = new float[]{671, 460, 671, 590, 768, 590, 768, 460};
    private ArrayList<Polygon> colisiones_bordes;

    private float salida1[] = new float[]{390, 800, 474, 800, 474, 798, 390, 798};
    private float salida2[] = new float[]{0, 204, 0, 729, 2, 792, 2, 204};
    private ArrayList<Polygon> colisiones_salidas;

    private ArrayList<PersonajeGeneral> NPCs = new ArrayList<>();
    private ArrayList<Rectangle> colisionNPCs;

    PersonajeEstatico tendero1 = new PersonajeEstatico("right", 380,180,"bocadilloMarinero");
    PersonajeEstatico tendero2 = new PersonajeEstatico("left", 420,180,"bocadilloMarinero");
    
    PersonajeEstatico adulto1 = new PersonajeEstatico("left", 750,280,"bocadilloMarinero");
    PersonajeEstatico adulto2 = new PersonajeEstatico("left", 750,320,"bocadilloMarinero");
    PersonajeEstatico adulto3 = new PersonajeEstatico("right", 50, 470,"bocadilloMarinero");
    PersonajeEstatico adulto5 = new PersonajeEstatico("right",50, 510,"bocadilloMarinero");
    PersonajeEstatico adulto6 = new PersonajeEstatico("right",625, 500,"bocadilloMarinero");
    
    PersonajeAcosador bully1 = new PersonajeAcosador("down", 1000, 550,"bocadilloMarinero");
    PersonajeAcosador bully2 = new PersonajeAcosador("up", 1000, 610,"bocadilloMarinero");
    PersonajeAcosador bully3 = new PersonajeAcosador("right", 960, 580,"bocadilloMarinero");
    PersonajeAcosador bully4 = new PersonajeAcosador("left", 1040, 580,"bocadilloMarinero");

    boolean historia = false;
    int contadorTemporal1 = -1500, contadorTemporal2 = 0;
    Bocadillo b0 = new Bocadillo("Historia10");
    Bocadillo b1 = new Bocadillo("Historia11");
    Bocadillo b2 = new Bocadillo("Historia12");
    Bocadillo b3 = new Bocadillo("Historia13");
    Bocadillo b4 = new Bocadillo("Historia15");

    public CompraInicial() {
        colisiones_bordes = new ArrayList<>();
        colisiones_bordes.add(new Polygon(borde1));
        colisiones_bordes.add(new Polygon(borde2));
        colisiones_bordes.add(new Polygon(borde3));
        colisiones_bordes.add(new Polygon(borde4));
        colisiones_bordes.add(new Polygon(borde5));
        colisiones_bordes.add(new Polygon(borde6));
        colisiones_bordes.add(new Polygon(borde7));
        colisiones_bordes.add(new Polygon(borde8));
        colisiones_bordes.add(new Polygon(borde9));

        colisiones_salidas = new ArrayList<>();
        colisiones_salidas.add(new Polygon(salida1));
        colisiones_salidas.add(new Polygon(salida2));

        NPCs.add(adulto1);
        NPCs.add(adulto2);
        NPCs.add(adulto3);
        NPCs.add(adulto5);
        NPCs.add(adulto6);
        NPCs.add(bully1);
        NPCs.add(bully2);
        NPCs.add(bully3);
        NPCs.add(bully4);
        NPCs.add(tendero1);
        NPCs.add(tendero2);

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapa = new TiledMap("\\Mapas\\mapa4.tmx", "\\Construccion Mapas\\");
        personaje = new PersonajePrincipal();
        personaje.setCoordenadaX(0);
        personaje.setCoordenadaY(570);
        personaje.setDir("right");

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    
            contadorTemporal1++;
            personaje.getDir().update(i);
    
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


        if (input.isKeyDown(Input.KEY_T)) {
            g.drawImage(new Image("\\Elementos aparte\\mapa1.png"), 550, 200);
        }

        //HISTORIA
        if (contadorTemporal1 > -1400 && contadorTemporal1 < 0) {
            b0.dentro();
            b0.getImagen().draw(b0.getCoordenadaX(), b0.getCoordenadaY());
            personaje.setDir("stance");
        } else if(contadorTemporal1 > 0 && contadorTemporal1 < 2500)
        {   
            personaje.setDir("right");
            personaje.setDir("stance");
            if(bully3.getCoordenadaX()-32>personaje.getCoordenadaX())
            {   
                bully1.setDir("left");
                bully2.setDir("left");
                bully3.setDir("left");
                bully4.setDir("left");
                bully1.setCoordenadas(bully1.getCoordenadaX()-(float)0.5, bully1.getCoordenadaY());
                bully2.setCoordenadas(bully2.getCoordenadaX()-(float)0.5, bully2.getCoordenadaY());
                bully3.setCoordenadas(bully3.getCoordenadaX()-(float)0.5, bully3.getCoordenadaY());
                bully4.setCoordenadas(bully4.getCoordenadaX()-(float)0.5, bully4.getCoordenadaY());
                
            }
            else
            {
                bully1.setDir("sleft");
                bully2.setDir("sleft");
                bully3.setDir("sleft");
                bully4.setDir("sleft");
            }
            bully1.getDir().update(contadorTemporal1%7);
            bully2.getDir().update(contadorTemporal1%7);
            bully3.getDir().update(contadorTemporal1%7);
            bully4.getDir().update(contadorTemporal1%7);
        }else if(contadorTemporal1 > 2500 && contadorTemporal1 < 4100)
        {   
            b1.dentro();
            b1.getImagen().draw(b1.getCoordenadaX(), b1.getCoordenadaY());
        }else if(contadorTemporal1 > 4100 && contadorTemporal1 < 5700)
        {   
            b2.dentro();
            b2.getImagen().draw(b2.getCoordenadaX(), b2.getCoordenadaY());
        }else if(contadorTemporal1 > 5700 && contadorTemporal1 < 7300)
        {   
            b3.dentro();
            b3.getImagen().draw(b3.getCoordenadaX(), b3.getCoordenadaY());
        }else if(contadorTemporal1 > 7300 && contadorTemporal1 < 8900)
        {   
            b4.dentro();
            b4.getImagen().draw(b4.getCoordenadaX(), b4.getCoordenadaY());
        }
        else if (contadorTemporal1 > 8900)
        {
            sbg.enterState(21);
        }
    }

}
