package tablewithpopup;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

public class tablewithpopup extends JFrame{
    private static final long serialVersionUID = 1L;

    // CONSTRUCTOR FOR TABLEWITHPOPUP CLASS
    public tablewithpopup()
    {
	super ("Table with Popup");
	
	//CREATING A MODEL AND JTABLE INSTANCE WITH MODEL AS ARGUMENT
	tablemodel m=new tablemodel();
	final JTable table=new JTable(m);
	JLabel label=new JLabel(" ");
	 label.setHorizontalAlignment(JLabel.CENTER);
	 label.setVerticalAlignment(JLabel.BOTTOM);
	 label.setSize(400,100);  
	
	// ADDING DATA TO MODEL REMEMBER IT IS AN OBJECT
	m.adddata(new data("Bhavin","Sanghani",52));
	m.adddata(new data("Nandita","Sanghani",50));
	m.adddata(new data("Nikeeta","Sanghani",20));
	m.adddata(new data("Dhairya","Sanghani",16));
	
	
	 // CREATE A POPUP NOW
	    final JPopupMenu pop=new JPopupMenu();
	    JMenuItem item1=new JMenuItem("item 1");
	    JMenuItem item2=new JMenuItem("item 2");
	    JMenuItem item3=new JMenuItem("item 3");
	    JMenuItem item4=new JMenuItem("item 4");
	    
	    pop.add(item1);
	    pop.add(item2);
	    pop.add(new JPopupMenu.Separator());
	    pop.add(item3);
	    pop.add(item4);
	    
	    item1.addActionListener(new ActionListener()
	    {
		         public void actionPerformed(ActionEvent e) {              
		             label.setText("Item 1 Selected");  
		         }  
		        });  
	    
	    item2.addActionListener(new ActionListener()
	    {
		   
		         public void actionPerformed(ActionEvent e) {              
		             label.setText("Item 2 Selected");  
		         }  
		        });  
	    
	    item3.addActionListener(new ActionListener()
	    {
		   
		         public void actionPerformed(ActionEvent e) {              
		             label.setText("Item 3 Selected");  
		         }  
		        });  
	    
	    item4.addActionListener(new ActionListener()
	    {
		   
		         public void actionPerformed(ActionEvent e) {              
		             label.setText("Item 4 Selected");  
		         }  
		        });  
	    
	    getContentPane().add(label);
	    
	    // IF YOU DONT WANT TO ADD INDIVIDUAL LISTNERS TO THE POPUP MENU ITEMS YOU CAN SIMPLY ADD 
	    // ITEMS AS BELOW
	    
	    
	    /*
	    pop.add(new JMenuItem("Item 1"));
	    pop.add(new JMenuItem("Item 2"));
	    pop.add(new JMenuItem("Item 3"));
	    pop.add(new JPopupMenu.Separator());
	    pop.add(new JMenuItem("Item 4"));
	    */
	  // CREATING TABLE HEADERS
	    
	    final JTableHeader theader= table.getTableHeader();
	    theader.addMouseListener(new MouseAdapter()
	    {
		//YOU CAN USE MOUSECLICKED ALSO BUT IT MEANS PRESSING AND RELEASING THE MOUSE
		//MOUSE PRESSED MEANS SINGLE ACTION WILL FIRE THE EVENT
		public void mousePressed(MouseEvent e)
		{
		    if (SwingUtilities.isRightMouseButton(e))
		    {
			//SINCE WE SHOW AT THE HEADER YOU HAVE TO RIGHT CLICK ON TABLE HEADER ONLY
			// OTHERWISE THE POPUP MENU WILL NOT SHOW
			pop.show(theader, e.getX(), e.getY());
		    }
			
		}
		
	    });
	    
	//ADD TABLE TO SCROLL PANE AND THEN ADD SCROLL PANE TO THE PARENT CONTAINER WINDOW    
	JScrollPane pane=new JScrollPane(table);
	getContentPane().add(pane,BorderLayout.CENTER);

	// THIS IS TO SAFELY CLOSE THE APPLICATION
	addWindowListener(new WindowAdapter()
	{
	    public void windowClosing(WindowEvent we)
	    {
		System.exit(0);
	    }
	});
	
    } // end of contsturctor
    
    // MAIN METHOD
    public static void main(String[] args) {
	
	//CREATE INSTANCE OF OUTERMOST CLASS
	tablewithpopup nn=new tablewithpopup();
	nn.pack();
	nn.setVisible(true);

    }
    
class data{
    
    private String name;
    private String lastname;
    private int age;

    //CONTSTRUCTOR FOR DATA CLASS
    public data(String name, String lastname, int age)
    {
	this.name=name;
	this.lastname=lastname;
	this.age=age;
     }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
} // end of data class

class tablemodel extends AbstractTableModel
{
    //SETTING COLUMN HEADERS
    private static final long serialVersionUID = 1L;
    //final because we never have to change this
    final String[] columnheaders= {"Name","Last Name", "Age"};
    
    //DATA TYPES FOR COLUMNS
    final Class[] columndatatypes= {String.class,String.class,Integer.class};
    
    //CREATE VECTOR TO HOLD DATA FOR THE TABLE
    Vector<Object> datavector=new Vector<Object>();
    
    public void adddata( data d)
    {
	//add data object which referes to class data above into the vector named datavector
	datavector.add(d);
	fireTableRowsInserted(datavector.size()-1, datavector.size()-1);
    }
    
    @Override
    public int getRowCount() {
	// TODO Auto-generated method stub
	return datavector.size();
    }

    @Override
    public int getColumnCount() {
	// TODO Auto-generated method stub
	return columnheaders.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
                    
                    data dd=(data) datavector.get(rowIndex);
                    if (columnIndex==0)
                    {
                        return dd.getName();
                    }
                    	
                    if (columnIndex==1)
                    {
                        return dd.getLastname();
                    }
                    
                    if (columnIndex==2)
                    {
                        return dd.getAge();
                    }
                    	return null;
    	}
    
} // end of class tablemodel
} // end of tablewithpopup class