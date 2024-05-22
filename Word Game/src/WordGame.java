//Sabo did the file parsing :3
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JTextField;
import javax.swing.SwingUtilities;
public class WordGame 
{
    private static String unga = "";
    private static String chosenWord = "";
    private static int wordKey=-1;
    private static int life = 5;
    private static boolean loseHP = true;
    private static JFrame frame = new JFrame("Word Game");
    private static JTextField t = new JTextField("", 20);
    private static JLabel label = new JLabel("Text");
    private static JLabel remain = new JLabel("Text");
    private static ImageIcon gojo = new ImageIcon("lib/baldman.jpg");
    private static JLabel jogo = new JLabel(gojo);
    private static ImageIcon jin = new ImageIcon("lib/lose.jpg");
    private static JLabel jinwoo = new JLabel(jin);
    private static long time;
    public static void main(String args[])
    {
        frame.getContentPane().setBackground(Color.GRAY);
        Scanner sb = new Scanner(System.in);
        System.out.println("Choose the word length (s)mall, (m)edium, or (l)ong");
        char b = sb.nextLine().charAt(0);
        if(b != 'l' && b != 'm' && b != 's')
        {
            System.out.println("Try again: ");
            b = sb.nextLine().charAt(0);
        }
        WordGame.selWord(b);
        System.out.println(chosenWord);
        char[] word = chosenWord.toCharArray();
        char[] placeholder = new char[chosenWord.length()];
        Arrays.fill(placeholder, 0, placeholder.length, '-');
        System.out.println(String.valueOf(placeholder));
        WordGame.initializeFrame(placeholder);
        sb.close();
        System.out.println("You have " + life + " tries remaining.");
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String w = t.getText();
                System.out.println(w);
                t.setText("");
                unga = w;
                System.out.println(unga);
                loseHP = true;
                for(int i = 0; i < word.length; i++)
                {
                    char guess = unga.charAt(0);
                    if(guess == word[i])
                    {
                        placeholder[i] = word[i];
                        loseHP = false;
                    }
                }
                updateLife();
                updateFrame(placeholder);
                System.out.println("You have " + life + " tries remaining.");
                System.out.println(String.valueOf(placeholder).substring(0,chosenWord.length()));
                if(life == 0)
                {
                    jinwoo.setHorizontalAlignment(JLabel.LEFT);
                    frame.add(jinwoo);
                    System.out.println("The word was " + String.valueOf(word));
                    return;
                }
                if((Arrays.equals(word,placeholder)))
                {
                    jogo.setHorizontalAlignment(JLabel.RIGHT);
                    frame.add(jogo);
                    SwingUtilities.updateComponentTreeUI(frame);
                    System.out.println("Correct!");
                    time = System.currentTimeMillis();
                }
              }});

    }
    private static void updateLife()
    {
        if(loseHP == true)
        {
            life--;
        }
        

    }
    private static void updateFrame(char[] d){
        label.setText(String.valueOf(d));
        System.out.println(String.valueOf(d));
        remain.setText("Tries left: " + life);
        SwingUtilities.updateComponentTreeUI(frame);
    }
    private static void initializeFrame(char[] d){
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.setDefaultCloseOperation(3);
        remain.setFont(new Font("Courier", Font.PLAIN,75));
        remain.setText("Tries left: " + life);
        label.setText(String.valueOf(d));
        label.setFont(new Font("Courier", Font.PLAIN,75));
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout());
        t.setBounds(960 - 300, 540, 280, 50);
        frame.add(t);
        frame.add(remain, BorderLayout.SOUTH);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private static void selWord(char key){
        try{
        File f=new File("lwrd.txt");
        System.out.println(f.getAbsolutePath());
        BufferedReader brLon = new BufferedReader(new FileReader("lib/lwrd.txt"));
        BufferedReader brMed = new BufferedReader(new FileReader("lib/mwrd.txt"));
        BufferedReader brSho = new BufferedReader(new FileReader("lib/swrd.txt"));
        Random r = new Random(); 
        switch(key){
            case 'l': wordKey = r.nextInt(2240)+1; break;
            case 'm': wordKey = r.nextInt(5458)+1; break;
            case 's': wordKey = r.nextInt(2183)+1; break;
        }for(int i = 1; i < wordKey; i++){
            if(key=='l'){
                chosenWord = brLon.readLine();
            }else if(key=='m'){
                chosenWord = brMed.readLine();
            }else if(key == 's'){
                chosenWord = brSho.readLine();
            }
        }
        brLon.close();
        brMed.close();
        brSho.close();
    }catch(IOException e){
        System.out.println(e);
    }}
}