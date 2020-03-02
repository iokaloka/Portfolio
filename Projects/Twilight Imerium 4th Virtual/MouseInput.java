/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti4v;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ti4v.TI4V.State;

/**
 *
 * @author jonde
 */
public class MouseInput implements MouseListener {

    private TI4V game;

    public MouseInput(TI4V game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (this.game.getState() == TI4V.State.MAINMENU) {
            mousePressedMenu(me);
        } else if (this.game.getState() == TI4V.State.SETUP) {
            mousePressedSetup(me);
        } else if (this.game.getState() == TI4V.State.FACTIONSELECTION) {
            mousePressedFactionSelect();
        } else if (this.game.getState() == TI4V.State.UNPLACED){
            mousePressedUnplaced(me);
        } else if (this.game.getState() == TI4V.State.PLAYERHUD){
            mousePressedPlayerHud(me);
        }
    }

    public void mousePressedMenu(MouseEvent me) {
        if (this.game.getState() == State.MAINMENU) {
            if (this.game.getM().mouseOverStart()) {
                this.game.setH(new Handler(game));
                this.game.setState(State.SETUP);
                this.game.getH().initialize(this.game.getPlayers());
            } else if (this.game.getM().MouseOverLess() && this.game.getDownTime() == 0 && this.game.getPlayers() > 3) {
                this.game.setPlayers(this.game.getPlayers() - 1);
                this.game.setDownTime(10);
            } else if (this.game.getM().mouseOverMore() && this.game.getDownTime() == 0 && this.game.getPlayers() < 6) {
                this.game.setPlayers(this.game.getPlayers() + 1);
                this.game.setDownTime(10);
            }
        }
    }

    public void mousePressedSetup(MouseEvent me) {
        if(this.game.getH().mouseOverUnplaced()){
            this.game.setState(State.UNPLACED);
        }
        if(!this.game.getH().mouseOverUnplaced() && this.game.getH().mouseOverPlayer()){
            this.game.setState(State.PLAYERHUD);
        }
    }

    public void mousePressedFactionSelect() {
        this.game.getFactionSelection().clickedFaction(this.game.getFactionSelection().mouseOverFaction());
        this.game.getFactionSelection().clickedColor(this.game.getFactionSelection().mouseOverColor());
        if(this.game.getFactionSelection().mouseOverConfirm()){
            this.game.getFactionSelection().confirm(new Faction(this.game.getFactionSelection().getActiveFaction()), this.game.getFactionSelection().getActiveColor());
            System.out.println(this.game.getFactionSelection().getActiveFaction());
        }
    }
    
    public void mousePressedUnplaced(MouseEvent me){
        if(me.getButton() == 3){
            this.game.setState(State.SETUP);
        }
    }
    
    public void mousePressedPlayerHud(MouseEvent me){
        if(me.getButton() == 3){
            this.game.setState(State.SETUP);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
