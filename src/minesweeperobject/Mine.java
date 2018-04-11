/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperobject;

/**
 *
 * @author alois
 */
public class Mine {
    
    private boolean isMine;
    private boolean open;
    private String nbMine;
    
    Mine() {
        isMine = false;
        open = false;
    }
    
    Mine(boolean mine) {
        isMine = mine;
        open = false;
    }
    
    public String display() {
        if (open) {
            if (isMine) {
                return "*";
            } else {
                return nbMine;
            }
        } else {
            return "";
        }
    }
    
    public boolean isMine() {
        return isMine;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen() {
        this.open = true;
    }
    
    public void setNbMine(String nb) {
        this.nbMine = nb;
    }
    
    
}
