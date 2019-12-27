package io.deki.autochrome.api.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class BrowserMouseListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(String.format("%s, %s", e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
