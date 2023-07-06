package com.mycompany.rpg.backend.personaje.enemigo;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

/**
 * Clase que contiene la informacion de un personaje enemigo del tipo Flor
 * Carnivora
 *
 * @author giovanic
 */
public class FlorCarnivora extends Enemigo {

    public FlorCarnivora(String nombre, double vida, int danio, int movimineto, boolean esVolador, int puntaje) {
        super(nombre, vida, danio, movimineto, esVolador, puntaje);
    }

    @Override
    public void ataque(Tablero tab, int direccion, int numPersonaje) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getEnemiPos()[numPersonaje].getX();
        int y = tab.getEnemiPos()[numPersonaje].getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            if (cercaDeJugador(tab, numPersonaje)) {
                tab.getPlayerCell().getJugable().setVida(0);
                modificarAccion(getNombre() + " se ha comido al jugador del usuario.");
            } else {
                //ataque->izquierda
                if (direccion == 1) {
                    for (int i = x - 1; i > x - 4; i--) {
                        if (i > -1) {
                            cell[y][i].modificarImage(true);
                            if (cell[y][i] instanceof CasillaArbol) {
                                CasillaArbol tmp = (CasillaArbol) cell[y][i];
                                tmp.setVida(tmp.getVida() - this.getDanio());
                                if (tmp.estaArbolVivo()) {
                                    cell[y][i] = tmp;
                                } else {
                                    cell[y][i] = new CasillaPlanicie();
                                    modificarAccion(getNombre() + " ha derivado un arbol");
                                }
                                break;
                            } else if (cell[y][i] instanceof CasillaJugable) {
                                CasillaJugable tmp = (CasillaJugable) cell[y][i];

                                if (tmp.estaSobreArbol()) {
                                    CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                                    arbol.setVida(arbol.getVida() - this.getDanio());
                                    if (arbol.estaArbolVivo()) {
                                        tmp.setSobreCasilla(arbol);
                                    } else {
                                        tmp.setSobreCasilla(new CasillaPlanicie());
                                        modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba el usuario");
                                    }
                                    break;
                                } else {
                                    tmp.getJugable().setVida(tmp.getJugable().getVida() - this.getDanio());
                                    if (tmp.getJugable().estaVivo()) {
                                        cell[y][i] = tmp;
                                    } else {
                                        cell[y][i] = new CasillaPlanicie();
                                        modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getJugable().getNombre());
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                //ataque->derecha
                if (direccion == 2) {
                    for (int i = x + 1; i < x + 4; i++) {
                        if (i < cell[0].length) {
                            cell[y][i].modificarImage(true);
                            if (cell[y][i] instanceof CasillaArbol) {
                                CasillaArbol tmp = (CasillaArbol) cell[y][i];
                                tmp.setVida(tmp.getVida() - this.getDanio());
                                if (tmp.estaArbolVivo()) {
                                    cell[y][i] = tmp;
                                } else {
                                    cell[y][i] = new CasillaPlanicie();
                                    modificarAccion(getNombre() + " ha derivado un arbol");
                                }
                                break;
                            } else if (cell[y][i] instanceof CasillaJugable) {
                                CasillaJugable tmp = (CasillaJugable) cell[y][i];

                                if (tmp.estaSobreArbol()) {
                                    CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                                    arbol.setVida(arbol.getVida() - this.getDanio());
                                    if (arbol.estaArbolVivo()) {
                                        tmp.setSobreCasilla(arbol);
                                    } else {
                                        tmp.setSobreCasilla(new CasillaPlanicie());
                                        modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba el usuario");
                                    }
                                    break;
                                } else {
                                    tmp.getJugable().setVida(tmp.getJugable().getVida() - this.getDanio());
                                    if (tmp.getJugable().estaVivo()) {
                                        cell[y][i] = tmp;
                                    } else {
                                        modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getJugable().getNombre());
                                        cell[y][i] = new CasillaPlanicie();
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                //ataque->abajo
                if (direccion == 3) {
                    for (int i = y + 1; i < y + 4; i++) {
                        if (i < cell.length) {
                            cell[i][x].modificarImage(true);
                            if (cell[i][x] instanceof CasillaArbol) {
                                CasillaArbol tmp = (CasillaArbol) cell[i][x];
                                tmp.setVida(tmp.getVida() - this.getDanio());
                                if (tmp.estaArbolVivo()) {
                                    cell[i][x] = tmp;
                                } else {
                                    modificarAccion(getNombre() + " ha derivado un arbol");
                                    cell[i][x] = new CasillaPlanicie();
                                }
                                break;
                            } else if (cell[i][x] instanceof CasillaJugable) {
                                CasillaJugable tmp = (CasillaJugable) cell[i][x];

                                if (tmp.estaSobreArbol()) {
                                    CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                                    arbol.setVida(arbol.getVida() - this.getDanio());
                                    if (arbol.estaArbolVivo()) {
                                        tmp.setSobreCasilla(arbol);
                                    } else {
                                        tmp.setSobreCasilla(new CasillaPlanicie());
                                        modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba el usuario");
                                    }
                                    break;
                                } else {
                                    tmp.getJugable().setVida(tmp.getJugable().getVida() - this.getDanio());
                                    if (tmp.getJugable().estaVivo()) {
                                        cell[i][x] = tmp;
                                    } else {
                                        modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getJugable().getNombre());
                                        cell[i][x] = new CasillaPlanicie();
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                //ataque->arriba
                if (direccion == 4) {
                    for (int i = y - 1; i > y - 4; i--) {
                        if (i > -1) {
                            cell[i][x].modificarImage(true);
                            if (cell[i][x] instanceof CasillaArbol) {
                                CasillaArbol tmp = (CasillaArbol) cell[i][x];
                                tmp.setVida(tmp.getVida() - this.getDanio());
                                if (tmp.estaArbolVivo()) {
                                    cell[i][x] = tmp;
                                } else {
                                    cell[i][x] = new CasillaPlanicie();
                                    modificarAccion(getNombre() + " ha derivado un arbol");
                                }
                                break;
                            } else if (cell[i][x] instanceof CasillaJugable) {
                                CasillaJugable tmp = (CasillaJugable) cell[i][x];

                                if (tmp.estaSobreArbol()) {
                                    CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                                    arbol.setVida(arbol.getVida() - this.getDanio());
                                    if (arbol.estaArbolVivo()) {
                                        tmp.setSobreCasilla(arbol);
                                    } else {
                                        tmp.setSobreCasilla(new CasillaPlanicie());
                                        modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba el usuario");
                                    }
                                    break;
                                } else {
                                    tmp.getJugable().setVida(tmp.getJugable().getVida() - this.getDanio());
                                    if (tmp.getJugable().estaVivo()) {
                                        cell[i][x] = tmp;
                                    } else {
                                        modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getJugable().getNombre());
                                        cell[i][x] = new CasillaPlanicie();
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                tab.setCasillas(cell);
                String lado = switch (direccion) {
                    case 1:
                        yield "izquierda";
                    case 2:
                        yield "derecha";
                    case 3:
                        yield "abajo";
                    case 4:
                        yield "arriba";
                    default:
                        throw new IllegalStateException("Invalid day: " + direccion);
                };
                modificarAccion(getNombre() + " ha realizdo un ataque en direccion " + lado);
            }
        } else {
            System.out.println("No acertaste");
        }
    }
}
