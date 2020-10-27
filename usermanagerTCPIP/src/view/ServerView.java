/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ServerControl;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public final class ServerView {

    public ServerView() {
        ServerControl serverControl = new ServerControl();
        showMessage("TCP server is running...");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
