// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class turretConstants {
    public static final int traverseMotorPort = 0;
    public static final int tiltMotorPort = 1;

    //public static final int leftLimitSwitchPort = 0; 
    //public static final int rightLimitSwitchPort = 1;

    public static final double maximumAgeOfNetworkTableEntry = 0.01;
  }

  public static class cannonConstants {
    public static enum statusStates{ //Using an enum because it improves readability. 
      READYTOSHOOT, 
      SHOOTING, 
      EMPTY
    }
    
    public static final double waitTime = 0.25; //The time to keep the solenoid open in seconds
    public static final int numberOfShooters = 6;
    public static final int[] cannonPorts = {1, 2, 3, 4, 5, 6};
    public static final boolean[] loadedBarrels = {true, true, true, 
                                                 true, true, true};
  }

  
  public static class drivetrainConstants {
    public static final int[] motorPorts = {6, 7, 8, 9};
    public static final Boolean[] motorInverts = {true, false}; 
  }

}
