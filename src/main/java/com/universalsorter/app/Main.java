package com.universalsorter.app;

import com.universalsorter.utils.ConsoleMenu;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        ConsoleMenu consoleMenu=new ConsoleMenu();
        consoleMenu.execute();
    }
}
