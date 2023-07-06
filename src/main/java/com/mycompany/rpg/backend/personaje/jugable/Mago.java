package com.mycompany.rpg.backend.personaje.jugable;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Mago extends Jugable {

    public Mago(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        super(nombre, vida, danio, movimineto, esVolador);
    }

    @Override
    public String obtenerTipoAtaque() {
        return """
               dispara un hechizo hacia el final de una linea que
                                   impacta en el primer enemigo/obstaculo que se
                                   encuentre""";
    }

    @Override
    public void revivir() {
        this.setVida(100);
    }

    @Override
    public void ataque(Tablero tab, int direccion) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getPlayerPosicion().getX();
        int y = tab.getPlayerPosicion().getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            //ataque-izquierda
            if (direccion == 65) {
                for (int i = x - 1; i >= 0; i--) {
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
                    } else if (cell[y][i] instanceof CasillaEnemigo) {
                        CasillaEnemigo tmp = (CasillaEnemigo) cell[y][i];

                        if (tmp.estaSobreArbol()) {
                            CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                            arbol.setVida(arbol.getVida() - this.getDanio());
                            if (arbol.estaArbolVivo()) {
                                tmp.setSobreCasilla(arbol);
                            } else {
                                tmp.setSobreCasilla(new CasillaPlanicie());
                                modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba un enemigo");
                            }
                            break;
                        } else {
                            tmp.getEnemigo().setVida(tmp.getEnemigo().getVida() - this.getDanio());
                            if (tmp.getEnemigo().estaVivo()) {
                                cell[y][i] = tmp;
                            } else {
                                modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getEnemigo().getNombre());
                                cell[y][i] = new CasillaPlanicie();
                            }
                        }
                        break;
                    }
                }
            }

            //ataque-derecha
            if (direccion == 68) {
                for (int i = x + 1; i < cell[0].length; i++) {
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
                    } else if (cell[y][i] instanceof CasillaEnemigo) {
                        CasillaEnemigo tmp = (CasillaEnemigo) cell[y][i];

                        if (tmp.estaSobreArbol()) {
                            CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                            arbol.setVida(arbol.getVida() - this.getDanio());
                            if (arbol.estaArbolVivo()) {
                                tmp.setSobreCasilla(arbol);
                            } else {
                                tmp.setSobreCasilla(new CasillaPlanicie());
                                modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba un enemigo");
                            }
                            break;
                        } else {
                            tmp.getEnemigo().setVida(tmp.getEnemigo().getVida() - this.getDanio());
                            if (tmp.getEnemigo().estaVivo()) {
                                cell[y][i] = tmp;
                            } else {
                                modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getEnemigo().getNombre());
                                cell[y][i] = new CasillaPlanicie();
                            }
                        }
                        break;
                    }
                }
            }

            //ataque->abajo
            if (direccion == 83) {
                for (int i = y + 1; i < cell.length; i++) {
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
                    } else if (cell[i][x] instanceof CasillaEnemigo) {
                        CasillaEnemigo tmp = (CasillaEnemigo) cell[i][x];

                        if (tmp.estaSobreArbol()) {
                            CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                            arbol.setVida(arbol.getVida() - this.getDanio());
                            if (arbol.estaArbolVivo()) {
                                tmp.setSobreCasilla(arbol);
                            } else {
                                tmp.setSobreCasilla(new CasillaPlanicie());
                                modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba un enemigo");
                            }
                            break;
                        } else {
                            tmp.getEnemigo().setVida(tmp.getEnemigo().getVida() - this.getDanio());
                            if (tmp.getEnemigo().estaVivo()) {
                                cell[i][x] = tmp;
                            } else {
                                modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getEnemigo().getNombre());
                                cell[i][x] = new CasillaPlanicie();
                            }
                        }
                        break;
                    }
                }
            }

            //ataque->arriba
            if (direccion == 87) {
                for (int i = y - 1; i >= 0; i--) {
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
                    } else if (cell[i][x] instanceof CasillaEnemigo) {
                        CasillaEnemigo tmp = (CasillaEnemigo) cell[i][x];

                        if (tmp.estaSobreArbol()) {
                            CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                            arbol.setVida(arbol.getVida() - this.getDanio());
                            if (arbol.estaArbolVivo()) {
                                tmp.setSobreCasilla(arbol);
                            } else {
                                tmp.setSobreCasilla(new CasillaPlanicie());
                                modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba un enemigo");
                            }
                            break;
                        } else {
                            tmp.getEnemigo().setVida(tmp.getEnemigo().getVida() - this.getDanio());
                            if (tmp.getEnemigo().estaVivo()) {
                                cell[i][x] = tmp;
                            } else {
                                modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getEnemigo().getNombre());
                                cell[i][x] = new CasillaPlanicie();
                            }
                        }
                        break;
                    }
                }
            }

            tab.setCasillas(cell);
        } else {
            modificarAccion(this.getNombre() + " no acerto el ataque");
        }
    }
}
