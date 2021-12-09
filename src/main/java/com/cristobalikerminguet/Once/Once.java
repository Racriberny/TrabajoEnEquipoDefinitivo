package com.cristobalikerminguet.Once;

import com.cristobalikerminguet.Lib.Lib;

public class Once {
    public void execute() {
        int opcion = 0;
        do {
            opcion = menuPrincipal();
            switch (opcion){
                case 1:
                    juegoPrincipal();
                    break;
            }
        }while (opcion !=0);
    }

    public static void juegoPrincipal() {
        final int DELAY = 500;
        final int VALOR_MIN = 1;
        final int VALOR_MAX = 6;
        final int MAX_PUNT = 11;
        boolean plantado = false;
        int porrasCPU = 0;
        int porrasPlayer = 0;
        int tiradaPlayer;
        int puntosPlayer = 0;
        int tiradaCPU;
        int puntosCPU = 0;
        char plantarse;
        do {
            System.out.println("Jugador lanza el dado al aire ...");
            Lib.espera(DELAY);
            tiradaPlayer = Lib.numeroAleatorio(VALOR_MIN, VALOR_MAX);
            System.out.println("\n*****");
            System.out.println("* " + tiradaPlayer + " *");
            System.out.println("*****");
            puntosPlayer += tiradaPlayer;
            System.out.println("\nTu puntuación actual es " + puntosPlayer);
            if (puntosPlayer < MAX_PUNT) {
                System.out.print("¿Deseas plantarte con " + puntosPlayer + " puntos? (s/n): ");
                plantarse = Lib.leerLinea().charAt(0);
                plantado = plantarse == 's' || plantarse == 'S';
                if (plantado) {
                    plantado = false;
                    //Ahora la CPU intenta igualar o mejorar el resultado del jugador
                    while (puntosCPU < puntosPlayer) {
                        System.out.println("CPU lanza el dado al aire ...");
                        Lib.espera(DELAY);
                        tiradaCPU = Lib.numeroAleatorio(VALOR_MIN, VALOR_MAX);
                        System.out.println("\n?????");
                        System.out.println("? " + tiradaCPU + " ?");
                        System.out.println("?????\n");
                        puntosCPU += tiradaCPU;
                        System.out.println("La puntuación actual de la CPU es " + puntosCPU);
                    }
                    if (puntosCPU > MAX_PUNT) {
                        System.out.println("CPU se ha pasado. Porra para Player!!");
                        porrasPlayer = sumaUnaPorra(porrasPlayer);
                        puntosPlayer = inicializarPuntos();
                        puntosCPU = inicializarPuntos();
                    } else if (puntosCPU > puntosPlayer) { // Gana CPU
                        if (puntosCPU == MAX_PUNT) {
                            System.out.println("\n$$$$$$$$$$$$$$$$");
                            System.out.println("$$$$ ¡ONCE! $$$$");
                            System.out.println("$$$$$$$$$$$$$$$$");
                            System.out.println("\nDoble porra para CPU\n");
                            porrasCPU = sumaDosPorras(porrasCPU);
                        } else {
                            System.out.println("CPU ha mejorado tu puntuación. Porra para CPU!!");
                            porrasCPU = sumaUnaPorra(porrasCPU);
                        }
                        puntosPlayer = inicializarPuntos();
                        puntosCPU = inicializarPuntos();
                    } else { // Empate
                        System.out.println("Empate!! Porra para los dos");
                        puntosPlayer = inicializarPuntos();
                        puntosCPU = inicializarPuntos();
                        porrasCPU = sumaUnaPorra(porrasCPU);
                        porrasPlayer = sumaUnaPorra(porrasPlayer);
                    }
                    System.out.println("\n**********************");
                    System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " + porrasCPU + " *");
                    System.out.println("**********************");
                    Lib.intro();
                }
            } else if (puntosPlayer == MAX_PUNT) {
                //Al obtener 11 el jugador suma automáticamente 2 porras
                porrasPlayer = sumaDosPorras(porrasPlayer);
                puntosPlayer = inicializarPuntos();
                puntosCPU = inicializarPuntos();
                System.out.println("\n$$$$$$$$$$$$$$$$");
                System.out.println("$$$$ ¡ONCE! $$$$");
                System.out.println("$$$$$$$$$$$$$$$$");
                System.out.println("\n¡Bonificación! Doble porra");
                System.out.println("\n**********************");
                System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " + porrasCPU + " *");
                System.out.println("**********************");
                Lib.intro();
            } else {
                //El jugador se ha pasado
                System.out.println("Ohh!! Te has pasado");
                System.out.println("Porra para la CPU");
                porrasCPU = sumaUnaPorra(porrasCPU);
                puntosPlayer = inicializarPuntos();
                puntosCPU = inicializarPuntos();
                System.out.println("\n**********************");
                System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " + porrasCPU + " *");
                System.out.println("**********************");
                Lib.intro();
            }

        } while (porrasPlayer < 5 && porrasCPU < 5);

        if (comprobarPorras(porrasPlayer, porrasCPU)) {
            System.out.println("\nPlayer gana la partida!!! por " + porrasPlayer + " porras a " + porrasCPU + " de la CPU\n");
        } else {
            System.out.println("\nCPU gana la partida!!! por " + porrasCPU + " porras a " + porrasPlayer + " del player\n");
        }
    }

    public static int menuPrincipal(){
        int opcion;
        System.out.println("***************************");
        System.out.println("*** JUEGO DE DADOS ONCE ***");
        System.out.println("***************************");
        System.out.println("1. Nueva partida ...");
        System.out.println("0. Salir");
        System.out.print("\nElige una opción: ");
        opcion = Lib.leerInt();
        return opcion;
    }

    public static int inicializarPuntos(){
       return 0;
    }

    public static boolean comprobarPorras(int porrasPlayer, int porrasCPU) {
        boolean ganador = true;

        //Si el ganador es la CPU, devuelve FALSE, si es el jugador, devuelve TRUE
        if (porrasPlayer >= 5) {
            ganador = true;
        } else if(porrasCPU >= 5) {
            ganador = false;
        }

        return ganador;
    }

    public static int sumaDosPorras(int porras){
        porras += 2;

        return porras;
    }

    public static int sumaUnaPorra(int porras){
        porras++;

        return porras;
    }
}
