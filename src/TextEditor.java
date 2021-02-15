import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {


    JTextArea textArea ;
    JScrollPane scrollPane ;
    JSpinner spinner ;
    JLabel label  ;
    JButton button  ;
    JButton btn ;
    JComboBox comboBox  ;

    JMenuBar menuBar ;
    JMenu file  ;
    JMenuItem open ;
    JMenuItem save ;
    JMenuItem exit ;




    TextEditor(){

       //jfram :
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("text editor");
        this.setSize(500 , 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);


      // text area :
        textArea = new JTextArea() ;
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial" , Font.PLAIN , 20));


      // scrollPane:
        scrollPane = new JScrollPane(textArea) ;
        scrollPane.setPreferredSize(new Dimension(450  , 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


     //  spinner:
        spinner = new JSpinner() ;
        spinner.setPreferredSize(new Dimension(50  , 25));
        spinner.setValue(20);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily() ,  Font.PLAIN  , (int) spinner.getValue()));
            }
        });


        label = new JLabel("font : ") ;

        button = new JButton("color:");
        button.addActionListener(this);

        btn = new JButton("backgrand color") ;
        btn.addActionListener(this);

        //comboBox :
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()  ;
        comboBox = new JComboBox(fonts) ;
        comboBox.addActionListener(this);
        comboBox.setSelectedItem("Arial");


        //menubar  :
        menuBar = new JMenuBar()  ;
        file = new JMenu("File") ;
        open = new JMenuItem("open");
        save = new JMenuItem("save");
        exit = new JMenuItem("exit");

        file.add(open) ;
        file.add(save) ;
        file.add(exit) ;

        open.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        menuBar.add(file) ;

        //end menu ____





     //add in JFrame :
        this.setJMenuBar(menuBar);
        this.add(label) ;
        this.add(spinner) ;
        this.add(button) ;
        this.add(btn) ;
        this.add(comboBox) ;
        this.add(scrollPane) ;
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button){
            JColorChooser colorChooser  = new JColorChooser() ;
            Color c = colorChooser.showDialog(null , "choose a color " , Color.black) ;
            textArea.setForeground(c);
        }

        if (e.getSource() == comboBox){
            textArea.setFont(new Font( (String) comboBox.getSelectedItem() ,Font.PLAIN  , textArea.getFont().getSize() ));
        }

        if (e.getSource() == btn){
            JColorChooser colorChooser  = new JColorChooser() ;
            Color c = colorChooser.showDialog(null , "choose a color " , Color.black) ;
            textArea.setBackground(c);
        }

        if (e.getSource() == open){

            JFileChooser fileChooser = new JFileChooser() ;
            fileChooser.setCurrentDirectory(new File("."));

            FileNameExtensionFilter filter =new FileNameExtensionFilter("text file" , "txt") ;
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showSaveDialog(null) ;

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()) ;
                Scanner filein = null ;

                try {
                    filein = new Scanner(file) ;
                    while (filein.hasNext()){
                        String line = filein.nextLine()+"\n" ;
                        textArea.append(line);

                    }
                    if (file.isFile()){

                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                finally {
                    filein.close();
                }
            }

        }


        if (e.getSource() == save){

            JFileChooser fileChooser = new JFileChooser() ;
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null) ;

            if (response == JFileChooser.APPROVE_OPTION){

                File file ;
                PrintWriter fileout = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath())  ;

                try {
                    fileout = new PrintWriter(file) ;
                    fileout.println(textArea.getText());

                } catch (FileNotFoundException fileNotFoundException) {

                    fileNotFoundException.printStackTrace();
                }
                finally {
                    fileout.close();
                }
            }

        }


        if (e.getSource() == exit){
            System.exit(0);
        }
    }
}




























