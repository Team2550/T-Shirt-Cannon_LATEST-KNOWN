// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.TurretControlMotorsCommand;
import frc.robot.commands.Cannons.CannonShootCommand;
import frc.robot.subsystems.CannonManagerSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    
    public final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem(Constants.drivetrainConstants.motorPorts, Constants.drivetrainConstants.motorInverts);
    
    private final CannonManagerSubsystem m_CannonManagerSubsystem = new CannonManagerSubsystem();
    private final Command m_cannonShootCommand = new CannonShootCommand(m_CannonManagerSubsystem);
    
    private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
    private final TurretControlMotorsCommand m_turretControlMotors = new TurretControlMotorsCommand(m_turretSubsystem, m_driverController);
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        configureBindings();
        m_drivetrain.setDefaultCommand(new DrivetrainCommand(m_drivetrain, m_driverController)); //TODO: Figure out which method is better, this method or the below method
        m_turretSubsystem.setDefaultCommand(m_turretControlMotors); //When the turret is not being used, use the regular control method. 
        
    }
    
    
    private void configureBindings() {  
        m_driverController.rightTrigger().onTrue(m_cannonShootCommand);
    }
    
    public Command getAutonomousCommand() {return null;}
}
