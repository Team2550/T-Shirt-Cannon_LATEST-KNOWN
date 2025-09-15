package frc.robot.commands.Cannons;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CannonManagerSubsystem;

public class REM_cannonShootNetworkTablesCommand extends Command{
    private final CannonManagerSubsystem m_cannonManagerSubsystem;
    private NetworkTableInstance m_networkTable;
    private NetworkTable m_cannonNetworkTable;
    private NetworkTableEntry m_buttonStatesNetworkTableEntry;
    private boolean[] previousButtonStates;


    public REM_cannonShootNetworkTablesCommand(CannonManagerSubsystem cannonManagerSubsystem) {
        m_networkTable = NetworkTableInstance.getDefault();
        m_cannonNetworkTable = m_networkTable.getTable("cannons");
        m_buttonStatesNetworkTableEntry = m_cannonNetworkTable.getEntry("buttonStates");

        previousButtonStates = m_buttonStatesNetworkTableEntry.getBooleanArray(new boolean[Constants.CannonConstants.NumberOfShooters]);

        m_cannonManagerSubsystem = cannonManagerSubsystem;
        addRequirements(m_cannonManagerSubsystem);
    }
    
    @Override
    public void execute() {
        boolean[] currentButtonStates = m_buttonStatesNetworkTableEntry.getBooleanArray(new boolean[Constants.CannonConstants.NumberOfShooters]);

        for (int i = 0; i < Constants.CannonConstants.NumberOfShooters; i++) {
            if (currentButtonStates[i] != previousButtonStates[i] && currentButtonStates[i] == true){
                m_cannonManagerSubsystem.shootSpecificBarrel(i);
            }
        }

        previousButtonStates = currentButtonStates;
    }
}
