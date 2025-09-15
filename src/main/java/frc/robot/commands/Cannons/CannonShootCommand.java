package frc.robot.commands.Cannons;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.subsystems.CannonManagerSubsystem;

public class CannonShootCommand extends Command{
    private final CannonManagerSubsystem m_CannonManagerSubsystem;

    public CannonShootCommand(CannonManagerSubsystem shooterSystem) {
        m_CannonManagerSubsystem = shooterSystem;
        addRequirements(m_CannonManagerSubsystem);
    }
    
    @Override
    public void execute() {
        Commands.run(m_CannonManagerSubsystem::cycleAndShoot, m_CannonManagerSubsystem)
                .andThen(Commands.waitSeconds(Constants.CannonConstants.WaitTime))
                .andThen(Commands.run(m_CannonManagerSubsystem::launchFinish, m_CannonManagerSubsystem))
                .andThen(Commands.waitSeconds(0.5));
    }
}
