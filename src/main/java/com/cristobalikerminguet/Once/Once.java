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

    public static void inicializarCero(int puntosCpu,int puntosPlayer){
        puntosCpu = 0;
        puntosPlayer = 0;
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
}
