/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Caro
 */
public class Figure extends Thread{
    
    //atributos
    private double originX;
    private double originY;
    private double finalX;
    private double finalY;
    private Color color;
    private int i;
    private int aux=0;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    public Figure(int i) {
        this.i = i;
        this.originX = new Random().nextInt(800 - 55);
        this.originY = new Random().nextInt(600 - 55);
        this.finalX =new Random().nextInt(800 - 55);
        this.finalY = new Random().nextInt(600 -55);
        this.color = Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));//se crea un color random por si se decide poner aleatorios los colores de las figuras
    } // constructor

    public void myDraw(GraphicsContext gc) {
        gc.setFill(this.color);
        Random rand = new Random();
        if (this.i % 2 == 0) {//controla que dibuje un cuadrado o un circulo
             gc.setFill(Color.AQUA);
             gc.fillRect(originX,originY, 100, 100);
                
             
        } else {
               gc.setFill(Color.ROSYBROWN);
               gc.fillOval(originX,originY, 100, 100);
        }
    } // draw

    @Override
    public void run() {
        while (true) {
            
            try {
                Thread.sleep(10);
                route();
            } // while
            catch (InterruptedException ex) {
                Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } // run
    
    private void route() {//crea la ruta del punto (originX,originY) al punto(finalX,finalY)

        double pendiente = Math.sqrt(Math.pow(this.finalX - this.originX, 2) + Math.pow(this.finalY - this.originY, 2));
        this.originX += (this.finalX - this.originX) / pendiente;
        this.originY += (this.finalY - this.originY) / pendiente;
        
        if(originY>finalY){
            aux=1;
        }
        if (aux==1 && this.originY <= this.finalY) {
            this.finalX = new Random().nextInt(800 - 55);
            this.finalY = new Random().nextInt(600 - 55);
            aux=0;
        }else if(aux==0 &&this.originY >= this.finalY){
            this.finalX = new Random().nextInt(800 -55);
            this.finalY = new Random().nextInt(600 -55);
            aux=0;
        }
    } // route
}
