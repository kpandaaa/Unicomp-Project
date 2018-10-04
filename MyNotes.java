import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.*;
import java.awt.Component;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
 
public class MyNotes extends JFrame {

public JFileChooser fc;
public JFontChooser jfonchoo;
public String currentfile="";
public UndoManager undo;
public JTextArea TextArea;
public JFrame frame;

    public MyNotes() {
        fc=new JFileChooser();
        jfonchoo = new JFontChooser();

        setTitle("MyNotes");
        setSize(600, 600);

        JTextArea TextArea = new JTextArea();
        TextArea.setSize(500,500);   
        TextArea.setBounds(35,10,500,500);

        JScrollPane scroll = new JScrollPane (TextArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        TextArea.setLineWrap(true);
        TextArea.setVisible(true);


        undo = new UndoManager();
            TextArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() 
                {
                    public void undoableEditHappened(UndoableEditEvent e)
                    {
                        undo.addEdit(e.getEdit());
                    }
                });
    
        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Add the menubar to the frame
        setJMenuBar(menuBar);
        add(TextArea);
        add(scroll);
        
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu aboutMenu = new JMenu("About");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(aboutMenu);
         
        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem saveAction = new JMenuItem("Save");
        JMenuItem saveasAction = new JMenuItem("Save As");
        JMenuItem exitAction = new JMenuItem("Exit");

        JMenuItem cutAction = new JMenuItem("Cut");
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");
        JMenuItem undoAction = new JMenuItem("Undo");
        JMenuItem findAction = new JMenuItem("Find");
        JMenuItem selectallAction = new JMenuItem("Select All");

        JMenuItem fontAction = new JMenuItem("Font");
        
        JMenuItem aboutAction = new JMenuItem("About");

        
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveasAction);

        fileMenu.addSeparator();

        fileMenu.add(exitAction);

        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        editMenu.add(undoAction);
        editMenu.add(findAction);
        editMenu.add(selectallAction);

        formatMenu.add(fontAction);

        aboutMenu.add(aboutAction);


    exitAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
                System.out.println("You have clicked on the exit menu item");
                System.exit(0);
        }
    
    });

    newAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
                TextArea.setText("");
        }
    
    });

    openAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0){

        //Handle open button action.
        if (arg0.getSource() == openAction) {
            int returnVal = fc.showOpenDialog(MyNotes.this);
            String textfromfile="";

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("getCurrentDirectory(): " + fc.getCurrentDirectory());
                System.out.println("getSelectedFile() : " + fc.getSelectedFile());      

                try{
                    FileInputStream fin=new FileInputStream(fc.getSelectedFile());
                    BufferedInputStream bin=new BufferedInputStream(fin);
                    int i;
                    while((i=bin.read())!=-1){
                        //System.out.print((char)i);
                        String temp = String.valueOf((char) i);
                        textfromfile = textfromfile + temp;
                    }
                    bin.close();
                    fin.close();
                    }catch(Exception e){System.out.println(e);}

                    System.out.println(textfromfile);
                    TextArea.setText(textfromfile);
                    currentfile=fc.getSelectedFile().toString();
                
            }
        }
    }
});


    saveAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            try{
                FileOutputStream fout=new FileOutputStream(currentfile);
            
                BufferedOutputStream bout=new BufferedOutputStream(fout);
                String s=TextArea.getText();
                byte b[]=s.getBytes();
                bout.write(b);
                bout.flush();
                bout.close();
                fout.close();
            }
                catch(Exception e){System.out.println("success");}
        }
    
    });


    saveasAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0){

        fc.setDialogTitle("Specify a file to save");   
 
        int userSelection = fc.showSaveDialog(MyNotes.this);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fc.getSelectedFile();
        System.out.println("Save as file: " + fileToSave.getAbsolutePath());

        try{
                FileOutputStream fout=new FileOutputStream(fileToSave);
            
                BufferedOutputStream bout=new BufferedOutputStream(fout);
                String s=TextArea.getText();
                byte b[]=s.getBytes();
                bout.write(b);
                bout.flush();
                bout.close();
                fout.close();
            }

        catch(Exception e){System.out.println("success");}
    }
}
});


    cutAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            TextArea.cut();
        }
    
    });

    copyAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            TextArea.copy();
        }
    
    });
    
    pasteAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
                
            TextArea.paste();
        }
    
    });

    undoAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            try {
               undo.undo();
            } catch (CannotRedoException cre) {
               cre.printStackTrace();
            }
        }
    });

    findAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            frame = new JFrame("InputDialog");

            String word = JOptionPane.showInputDialog(frame, "Find:");
            String wordfromTextArea=TextArea.getText();
            String search=word;
            if (wordfromTextArea.indexOf(search) != -1)
                {
                    //System.out.println("Found!");
                     TextArea.setCaretPosition(wordfromTextArea.indexOf(search));
                }
            else
                {
                    //System.out.println("Not Found!");
                    JFrame Wordnotfound = new JFrame("Search Results");

                    JLabel labelwordnotfound = new JLabel("WORD NOT FOUND!");

                    Wordnotfound.add(labelwordnotfound);

                    Wordnotfound.setSize(250,100);

                    Wordnotfound.setLocationRelativeTo(null);

                    Wordnotfound.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    Wordnotfound.setVisible(true);
                }

        }  
    });

   
    selectallAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            TextArea.selectAll();
        }               

    });

    fontAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0)
        {
            jfonchoo.showDialog(TextArea);
            TextArea.setFont(jfonchoo.getSelectedFont());
        }
    });


    aboutAction.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent arg0) 
        {
            About abo = new About();

            abo.setVisible(true);

            abo.main(null);
        }
    
    });
}

    public static void main(String[] args) {

        MyNotes mn = new MyNotes();

        ImageIcon img = new ImageIcon("C://KarishmaJ//UnicompProject//res//IconImage.png");

        mn.setIconImage(img.getImage());

        mn.setVisible(true);

        mn.getContentPane().setBackground(Color.BLACK);

        mn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}