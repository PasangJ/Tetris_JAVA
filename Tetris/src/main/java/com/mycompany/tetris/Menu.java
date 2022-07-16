/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pasangsherpa
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Menu extends JPanel {
    JButton play=new JButton("Play");
    JButton highscore=new JButton("Leaderboard");
    JButton quit=new JButton("Quit");
    JTextArea table=new JTextArea();


    Menu(TetrisDisplay tetrisDisplay){
        setLayout (new GridBagLayout());
        GridBagConstraints g=new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx=0;
        g.gridy=0;
        add(play);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx=1;
        g.gridy=0;
        add(highscore);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx=2;
        g.gridy=0;
        add(quit);


        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisDisplay.startGame();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        highscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.fill = GridBagConstraints.HORIZONTAL;
                g.gridx=3;
                g.gridy=0;
                add(table);
                ArrayList<Integer> scores=new ArrayList<>();
                try {
                    File myObj = new File("scores.txt");
                    Scanner myReader = new Scanner(myObj);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        scores.add(Integer.valueOf(data));
                    }
                    myReader.close();
                    Collections.sort(scores,Collections.reverseOrder());
                    for(int i=0;i<scores.size();i++){
                        table.append("\n"+(i+1)+". "+scores.get(i));
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("An error occurred.");
                    ex.printStackTrace();
                }

            }
        });
    }
}
