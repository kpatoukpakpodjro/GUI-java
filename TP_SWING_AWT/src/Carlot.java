import javax.swing.*;
//import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
class CarLot
{
	// class variables
	private Vector registeredDrivers;
	private Vector parkingStalls;
	private String dataFile;
	private int maxLotSize;
	
	// constructor
	public CarLot(int maxSize, String fileName)
	{
		registeredDrivers = new Vector();
		parkingStalls = new Vector();
		maxLotSize = maxSize;
		dataFile = fileName;
		loadData();
	}

	public String getDataFileName()
	{
		return dataFile;
	}

	public int getMaxSize()
	{
		return maxLotSize;
	}

	public int carCount()
	{
		return parkingStalls.size();
	}

	// Input: license plate number
	// Output: Stall number in parking lot
	// Error State: returned String is ""
	public String findStallLocation(String licenseNum)
	{
		String currentStall = "";
		String returnVal = "";

		for(int i = 0; i < parkingStalls.size(); i++)
		{
			currentStall = (String)parkingStalls.elementAt(i);

			if(licenseNum.equals(currentStall))
			{
				returnVal = Integer.toString(i);
				break;
			}			
		}

		return returnVal;
	}

	// Loads data from file (maintain persistence upon close)
	public int loadData()
	{
		// each row in the data file represents a registered car
		// format:  
		// licensePlateNum|currentlyParked

		FileReader file;
		BufferedReader buffer;
		StringTokenizer tokens;
		String currentLine = "";
		String licensePlate = "";
		String currentlyParked = "";
		
		try
		{
			file = new FileReader(dataFile);
			buffer = new BufferedReader(file);

			// read and parse each line in the file
			while((currentLine = buffer.readLine()) != null)
			{
				int returnVal = 0;
				tokens = new StringTokenizer(currentLine, "|");
				licensePlate = tokens.nextToken();
				currentlyParked = tokens.nextToken();

				// load all registered drivers
				registeredDrivers.addElement(licensePlate);

				// load car into stall if status is "Y"
				if(currentlyParked.equals("Y"))
				{
				   if(parkingStalls.size() < maxLotSize)
				   {
					 parkingStalls.addElement(licensePlate);
				   }
				   else
				   {
						returnVal = -1;
				   }
				}
			}	
		}
		catch(FileNotFoundException f)
		{
			return -1;
		}
		catch(IOException io)
		{
			return -1;
		}		
		
		return 0;
	}

	// Saves status data upon request to data file
	public int saveData()
	{
		FileWriter writer = null;
		PrintWriter printer = null;
		String currentRecord = "";
		String licensePlate = "";
		String parkedPlates = "";
		String currentlyParked = "";

		try
		{
			writer = new FileWriter(dataFile);
			printer = new PrintWriter(writer);

			// build data record by parsing Vectors
			for(int i = 0; i < registeredDrivers.size(); i++)
			{
			  licensePlate = (String)registeredDrivers.elementAt(i);
			  currentlyParked = "N";

			  for(int j = 0; j < parkingStalls.size(); j++)
			  {
			    parkedPlates = (String)parkingStalls.elementAt(j);

			    if(parkedPlates.equals(licensePlate))
			    {
			      currentlyParked = "Y";
			      break;
			    }
			  }

			  currentRecord = licensePlate + "|" + currentlyParked;

			  // output record to file
			  printer.println(currentRecord);
			}

			// close output streams
			writer.close();
			printer.close();
		}
		catch(IOException io)
		{
			
			return -1;
		}	

		return 0;
	}

	public boolean carEnter(String licenseNum)
	{
		String parkedCar = "";
		boolean alreadyHere = false;
		
		// Check stalls to see if car is already parked
		for(int i = 0; i < parkingStalls.size(); i++)
		{
			parkedCar = (String)parkingStalls.elementAt(i);

			if(parkedCar.equals(licenseNum))
			{
				alreadyHere = true;
			}			
		}
		
		// car is not already parked
		if(!alreadyHere)
		{
			// space is still available
			if(!lotFull())
			{
				parkingStalls.addElement(licenseNum);
				return true;
			}
			// space not available
			else
			{
				return false;
			}
		}
		// car already parked in lot
		else
		{
			return false;
		}
	}

	public boolean carExit(String licenseNum)
	{
		boolean returnVal = false; //default return value
		String parkedCar = "";

		// searching for car in stalls
		for(int i = 0; i < parkingStalls.size(); i++)
		{
			parkedCar = (String)parkingStalls.elementAt(i);

			// car found
			if(parkedCar.equals(licenseNum))
			{
				parkingStalls.removeElementAt(i);
				returnVal = true;
				break;
			}			
		}

		return returnVal;
	}

	public boolean lotFull()
	{
		// compare stalls occupied to max lot size
		if(parkingStalls.size() == maxLotSize)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

/*
Provides the graphical layout for the Status tab
*/
class Status
{
	public static JPanel statusTab = new JPanel();
	public static JPanel statusScreen1;

	static JTextField licensePlateField = new JTextField(20);

	// retrieves Status panel and sets visible
	static JPanel startup()
	{	
		statusScreen1 = Status.getStatusScreen1();
		statusTab.add(statusScreen1);

		statusScreen1.setVisible(true);

		return statusTab;

	}

	//defines and retrieves Status panel
	static JPanel getStatusScreen1()
        {   
	  statusScreen1 = new JPanel(new FlowLayout());
          JPanel generalPanel = new JPanel();
	  generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));
	  generalPanel.add(Box.createVerticalStrut(170));

	  JPanel holderPanel = new JPanel(new BorderLayout());
	  holderPanel.setLayout(new BoxLayout(holderPanel,BoxLayout.Y_AXIS));
	  JPanel criteriaPanel = new JPanel();
	  criteriaPanel.setLayout(new BoxLayout(criteriaPanel,BoxLayout.X_AXIS));
	  JLabel licensePlateLabel = new JLabel("License Plate Number:");

		Font textFont = new Font("SanSerif", Font.PLAIN, 24);
		Font textFieldFont = new Font("Serif", Font.PLAIN, 20);

		licensePlateLabel.setFont(textFont);
		licensePlateField.setFont(textFieldFont);

		criteriaPanel.add(Box.createHorizontalStrut(40));
		criteriaPanel.add(licensePlateLabel);
		criteriaPanel.add(licensePlateField);
		criteriaPanel.add(Box.createHorizontalStrut(40));

		final JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		JButton lotCapacityButton = new JButton("Nombres palces libres");
		JButton saveStateButton = new JButton("enregistrer  ");
		JButton findStallButton = new JButton("Localiser un  Vehicle");

		JButton clearButton = new JButton(" Effacer  ");

		lotCapacityButton.setFont(textFont);
		saveStateButton.setFont(textFont);
		findStallButton.setFont(textFont);
		clearButton.setFont(textFont);

		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(lotCapacityButton);	
		buttonPanel.add(saveStateButton);
		buttonPanel.add(findStallButton);
		buttonPanel.add(clearButton);

		holderPanel.add(criteriaPanel);
		holderPanel.add(Box.createVerticalStrut(30));
		holderPanel.add(buttonPanel);
		generalPanel.add(holderPanel);
		statusScreen1.add(generalPanel);
		statusScreen1.add(Box.createHorizontalStrut(150));


		// button listener for lot capacity
        lotCapacityButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		        {   
			 // Retrieve required information
		       String licensePlate = licensePlateField.getText().trim();

			 int totalCapacity = Fenetre.ListVoiture.getMaxSize();
			 int currentlyOccupied = Fenetre.ListVoiture.carCount();
			 int freeSpace = totalCapacity - currentlyOccupied;

			 // Print dialog box
			 JOptionPane.showMessageDialog((Component) buttonPanel, 
				"Total Capacity: " + totalCapacity +
				"\nCurrently Occupied: " + currentlyOccupied +
				"\nFree Space: " + freeSpace,
				"Current Car Lot Statistics", JOptionPane.INFORMATION_MESSAGE);
				
				// reset active tab and field data
				Fenetre.index.setSelectedIndex(0);
				licensePlateField.setText("");						
            }
		});

		// button listener for save state
		saveStateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{   
				// perform save operation
				int result = Fenetre.ListVoiture.saveData();

				// check if successful and report results
				if(result == 0)
				{
			       JOptionPane.showMessageDialog((Component) buttonPanel, "Data for all registered users has been updated in file: " +
                   Fenetre.ListVoiture.getDataFileName(),"Data Stored Successfully", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
			JOptionPane.showMessageDialog((Component) buttonPanel, 
			        "Data could not be stored!",
			     "Data Extract Failure", JOptionPane.ERROR_MESSAGE);
				}

				// reset active tab and field data
				Fenetre.index.setSelectedIndex(0);
				licensePlateField.setText("");						
			}
		});

		// button listener for car location search
		findStallButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		    {   
				// retrieve input and perform search
		       String licensePlate = licensePlateField.getText().trim();
	           String stallNumber =Fenetre.ListVoiture.findStallLocation(licensePlate);

			// check operation result and report using dialog boxes
			if(!stallNumber.equals(""))
			{
			 JOptionPane.showMessageDialog((Component) buttonPanel, 
				"Location of car #" + licensePlate + ":" +
				"\nStall " + stallNumber,
				"Car Location Found", 
                                JOptionPane.INFORMATION_MESSAGE);
			 }
			 else
			 {
			JOptionPane.showMessageDialog((Component) buttonPanel, 
				"Localisation de la voiture  #" + licensePlate + ":" 
                                + "non trouvable." + 
          "\nLe vehicule n'est peut-être pas au parking .","Car Location Found", JOptionPane.ERROR_MESSAGE);
				}				
				
			// reset active tab and field data
			Fenetre.index.setSelectedIndex(0);
			licensePlateField.setText("");						
            }
		});		

		// button listener for clear
		clearButton.addActionListener(new ActionListener()
		{   public void actionPerformed(ActionEvent e)
		    {
				// reset license plate field
				licensePlateField.setText("");
			}
		});

		return statusScreen1;
    }
}

/*
Provides the graphical layout for the Add or Remove Car tab
*/
class AddOrRemoveCar
{
	public static JPanel addOrRemoveCarTab = new JPanel();
	public static JPanel addOrRemoveCarScreen1;

	static JTextField licensePlateField = new JTextField(20);

	// Retrieves and returns add/remove car panel
	static JPanel startup()
	{     addOrRemoveCarScreen1 =AddOrRemoveCar.getAddOrRemoveCarScreen1();
		addOrRemoveCarTab.add(addOrRemoveCarScreen1);

		addOrRemoveCarScreen1.setVisible(true);

		return addOrRemoveCarTab;
	}

	// Defines and returns graphical components for screen
	static JPanel getAddOrRemoveCarScreen1()
    {   
		addOrRemoveCarScreen1 = new JPanel(new FlowLayout());
        JPanel generalPanel = new JPanel();
	    generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));
		generalPanel.add(Box.createVerticalStrut(170));

		JPanel holderPanel = new JPanel(new BorderLayout());
	    holderPanel.setLayout(new BoxLayout(holderPanel, BoxLayout.X_AXIS));
		JPanel criteriaPanel = new JPanel(new FlowLayout());
        JLabel licensePlateLabel = new JLabel("License PlateNumber:",SwingConstants.RIGHT);

		Font textFont = new Font("SanSerif", Font.PLAIN, 24);
		Font textFieldFont = new Font("Serif", Font.PLAIN, 20);

		licensePlateLabel.setFont(textFont);
		licensePlateField.setFont(textFieldFont);

		criteriaPanel.add(licensePlateLabel);
		criteriaPanel.add(licensePlateField);

		final JPanel buttonPanel = new JPanel(new FlowLayout());
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton addButton = new JButton("Add Car to Lot");
		JButton removeButton = new JButton("Remove Car from Lot");
		JButton clearButton = new JButton("Clear Data");

		addButton.setFont(textFont);
		removeButton.setFont(textFont);
		clearButton.setFont(textFont);

		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(clearButton);

		holderPanel.add(criteriaPanel);		
		generalPanel.add(holderPanel);
		holderPanel.add(Box.createVerticalStrut(75));
		generalPanel.add(buttonPanel);
		addOrRemoveCarScreen1.add(generalPanel);
		addOrRemoveCarScreen1.add(Box.createHorizontalStrut(100));


		// button listener for adding car to lot
        addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		    {   
		       String licensePlate = licensePlateField.getText().trim();

				// check validity of input
				if((licensePlate.length() == 0))
				{
		            JOptionPane.showMessageDialog((Component) buttonPanel, "Please fill in the field and try again","Blank Field", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// perform enter operation
		            boolean result = Fenetre.ListVoiture.carEnter(licensePlate);

					// check outcome and report results
					if(!result)
					{
	                	JOptionPane.showMessageDialog((Component) buttonPanel, "This license plate is either not registered or is already in thelot. " +"Please try again.","Invalid Operation", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
	                    int another = JOptionPane.showConfirmDialog((Component)buttonPanel,			"The car has been added. Add another car to the lot?",
                        "Add Car",JOptionPane.YES_NO_OPTION);

		                // reset input field
		                licensePlateField.setText("");
						
		// change tabs based on user input
		if(another == JOptionPane.NO_OPTION)
		{
			Fenetre.index.setSelectedIndex(0);
		}						
	 }					
	}
       }
       });

		// button listener for removing car from lot
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		    {   
				// retrieve input data
		        String licensePlate = licensePlateField.getText().trim();
				
				// check data validity
				if((licensePlate.length() == 0))
				{
					// invalid
	                JOptionPane.showMessageDialog((Component) buttonPanel,"Please fill in the field and try again","Blank Field", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// valid

					// perform exit operation
		            boolean result = Fenetre.ListVoiture.carExit(licensePlate);

					// check outcome and report results
					if(!result)
					{
	                    JOptionPane.showMessageDialog((Component) buttonPanel, "This license plate is invalid or is already in the lot. Please tryagain.",
                        "Invalid Operation", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
	                    int another = JOptionPane.showConfirmDialog((Component)buttonPanel,"The car has been removed. Remove another car?", "AddCar",JOptionPane.YES_NO_OPTION);
                        licensePlateField.setText("");
						
		if(another == JOptionPane.NO_OPTION)
		{
			Fenetre.index.setSelectedIndex(0);
		}						
	}					
       }
      }
      });

		// button listener for clear button
		clearButton.addActionListener(new ActionListener()
		{   public void actionPerformed(ActionEvent e)
		    {
				// reset text field
				licensePlateField.setText("");
			}
		});

		return addOrRemoveCarScreen1;
    }
}
