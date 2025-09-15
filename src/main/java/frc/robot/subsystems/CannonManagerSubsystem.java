package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CannonManagerSubsystem extends SubsystemBase{
    private class Cannon {

        public static enum CannonState { //Using an enum because it improves readability. 
            READY, 
            SHOOTING, 
            EMPTY
        }

        private CannonState m_State = CannonState.READY;

        private DigitalOutput m_Relay;

        Cannon(int port) {
            m_Relay = new DigitalOutput(port);
        }

        void launch() {
            if (m_State == CannonState.READY) {
                m_Relay.set(true);
                m_State = CannonState.SHOOTING;
            }
        }

        void launchFinish() {
            if (m_State == CannonState.SHOOTING) {
                m_Relay.set(false);
                m_State = CannonState.EMPTY;
            }
        }

        CannonState getState() { return m_State; }
        void reload() { m_State = CannonState.READY; }
    }

    private Cannon[] m_Cannons;
    public int currentShooterIndex = 0;

    public CannonManagerSubsystem() {
        m_Cannons = new Cannon[Constants.CannonConstants.NumberOfShooters];

        for (int i = 0; i < Constants.CannonConstants.NumberOfShooters; i++) {
            m_Cannons[i] = new Cannon(i);
        }
    }

    @Override
    public void periodic() {
    }    

    public void shootSpecificBarrel(int barrel) { m_Cannons[barrel].launch(); }

    //This function finds the next loaded barrel and fires it, this includes wrapping to the 1st barrel if needed. 
    public void cycleAndShoot() {
        if (m_Cannons[currentShooterIndex].getState() == Cannon.CannonState.READY) {

            currentShooterIndex++;
            currentShooterIndex = currentShooterIndex % Constants.CannonConstants.NumberOfShooters;
            m_Cannons[currentShooterIndex].launch();
            
        } else { DriverStation.reportWarning("All Barrels Empty!", null); }
    }

    public void launchFinish() {
        m_Cannons[currentShooterIndex].launchFinish();
    }

    public void reloadCannon(int cannonIndex) { m_Cannons[cannonIndex].reload(); }
}
