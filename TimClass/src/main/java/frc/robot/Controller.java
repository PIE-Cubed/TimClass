// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;



/** Add your docs here. */
public class Controller {

    private static final String MANIP_NAME = "abcd";
    private static final String DRIVER_NAME = "abcd";
    private static final int UNKNOWN_PORT = -1;


    /* Instance variables */
    private XboxController   manipulatorController;
    private XboxController   driverController;
    private int              manipulatorPort;
    private int              driverPort;


    /* Constructor */
    public Controller()  {

        manipulatorPort = getManipulatorPort();
        driverPort      = getDriverPort();

        if (manipulatorPort != UNKNOWN_PORT)  {
            manipulatorController = new XboxController(manipulatorPort);
        }

        if (driverPort != UNKNOWN_PORT)  {
            driverController = new XboxController(driverPort);
        }

        /* Debug to get actual controller names for constants above.  
              This can be commented once we have the names. */
        testPrintControllerNames();
    }

    public int getManipulatorPort()  {
        int portNum;
        GenericHID  genericController;

        for (portNum = 0; portNum < 6; portNum++)  {
            genericController = new GenericHID(portNum);
            if (genericController.getName().equals(MANIP_NAME) == true)  {
                return portNum;
            }
        }

        System.out.println("ERROR: Could not determine Manipulator Controller USB port!!!");
        return UNKNOWN_PORT;
    }

    public int getDriverPort()  {
        int portNum;
        GenericHID  genericController;

        for (portNum = 0; portNum < 6; portNum++)  {
            genericController = new GenericHID(portNum);
            if (genericController.getName().equals(DRIVER_NAME) == true)  {
                return portNum;
            }
        }

        System.out.println("ERROR: Could not determine Driver Controller USB port!!!");
        return UNKNOWN_PORT;
    }

    public void updateControllers()  {
        int currentManipulatorPort;
        int currentDriverPort;

        currentManipulatorPort = getManipulatorPort();
        currentDriverPort      = getDriverPort();

        if (manipulatorPort != currentManipulatorPort)  {
             manipulatorController = new XboxController(currentManipulatorPort);
             manipulatorPort = currentManipulatorPort;
             System.out.println("Manipulator Controller now on port:" + manipulatorPort);
        }

        if (driverPort != currentDriverPort)  {
            driverController = new XboxController(currentDriverPort);
            driverPort = currentDriverPort;
            System.out.println("Driver Controller now on port:" + driverPort);
        }
    }

        public void testPrintControllerNames()  {
        int portNum;
        GenericHID  genericController;

        for (portNum = 0; portNum < 6; portNum++)  {
            genericController = new GenericHID(portNum);
            System.out.println("Port:" + portNum + "Controller Name:" + genericController.getName());
        }
    }

}
