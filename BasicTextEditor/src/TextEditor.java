import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //set the properties.
    JFrame frame;
    JMenuItem openFile,saveFile,newFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JMenu fileMenu;
    JMenu editMenu;
    JMenuBar menuBar;
    JTextArea textArea;
    TextEditor(int x, int y){
        //Initialize the frame.
        frame =new JFrame();
        textArea=new JTextArea();
        //Initialize the file menu items.
        openFile =new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        newFile = new JMenuItem("New");
        //Initialize the file menu items.
        cut =new JMenuItem("Cut");
        copy =new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Add the file menu items to the file menu.
        //Initialize file menu.
        fileMenu =new JMenu("File");
        //Initialize edit menu.
        editMenu = new JMenu("Edit");
        //Add action listener to the file menu items.
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(newFile);

        //Add ActionListener to the edit menu items.
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Add the edit menu items to the edit menu;
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(selectAll);
        editMenu.add(close);

        //Initialize the menu bar.
        menuBar =new JMenuBar();
        //Add the file menu to the menu bar;
        menuBar.add(fileMenu);
        //Add the edit menu to the menu bar;
        menuBar.add(editMenu);
        //Adding the menuBar to the frame.
        frame.setJMenuBar(menuBar);
        //creating container pane.
        JPanel panel=new JPanel();
        //set the border of the panel.
        panel.setBorder(new EmptyBorder(5,5,5,5));
        //set the layout of the panel to prevent the defoult flow layout.
        panel.setLayout(new BorderLayout(0,0));
        //Adding verticle and horizontal scroll option to the textArea.
        JScrollPane jScrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Adding the panel to the frame.
        panel.add(jScrollPane,BorderLayout.CENTER);
        frame.add(panel);
        frame.setBounds(x,y,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("OpenSource TextEditor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
   @Override
   public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //perform the paste option.
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform the copy option.
            textArea.copy();
        }
       if(actionEvent.getSource()==paste){
           //perform the paste option.
           textArea.paste();
       }
       if(actionEvent.getSource()==selectAll){
           //perform the select all option.
           textArea.selectAll();
       }
       if(actionEvent.getSource()==close){
           //perform the close option.
           System.exit(0);
       }
       if(actionEvent.getSource()==openFile){
           //Open a file chooser.
           JFileChooser jFileChooser=new JFileChooser("c:");
           //Select the intended file.
           //User select the file and enter open ar cancel button.
           int chooseOption=jFileChooser.showOpenDialog(null);
           if(chooseOption==jFileChooser.APPROVE_OPTION){
               //getting the selected by user.
               File file=jFileChooser.getSelectedFile();
               //getting the selected file absolute path.
               String path=file.getAbsolutePath();

               try{
                   //Initialize the filereader.
                   FileReader fileReader=new FileReader(path);
                   //Initialize the Buffer Reader
                   BufferedReader bufferedReader=new BufferedReader(fileReader);
                   //Read the content of the file.
                   String intermediate="", output="";
                   while((intermediate=bufferedReader.readLine())!=null){
                       output+=intermediate+"\n";
                   }
                   //Setting the content of the text area.
                   textArea.setText(output);
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
           }
       }
       if(actionEvent.getSource()==saveFile){
           //Initialize the file chooser.
           JFileChooser jFileChooser=new JFileChooser("c:");
           //get choose option from file chooser.
           int chooseOption=jFileChooser.showSaveDialog(null);
           //Check if save button is clicked or not.
           if(chooseOption==JFileChooser.APPROVE_OPTION){
               //create a new file with choosen directory and new file name.
               File file=new File(jFileChooser.getSelectedFile().getAbsolutePath()+".txt");
               try{
                   //Initiaize the file writer
                   FileWriter fileWriter=new FileWriter(file);
                   //Initialize the bufferedwriter.
                   BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                   //writer the content of the textArea to file.
                   textArea.write(bufferedWriter);
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
           }
       }
       if(actionEvent.getSource()==newFile){
           TextEditor textEditor=new TextEditor(150,150);
       }
   }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor(100,100);
    }
}