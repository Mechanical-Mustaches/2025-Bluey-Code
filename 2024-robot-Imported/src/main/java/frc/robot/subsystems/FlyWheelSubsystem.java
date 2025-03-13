package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLimitSwitch;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class FlyWheelSubsystem extends SubsystemBase {
  /** Creates a new FlyWheelSubsystem. */
  private SparkMax m_leftWheel = new SparkMax(11, MotorType.kBrushless);
  private SparkMax m_rightWheel = new SparkMax(12, MotorType.kBrushless);
  private final SparkLimitSwitch lineBreak = m_leftWheel.getForwardLimitSwitch();

  public FlyWheelSubsystem() {
    var rightConfiguration = new SparkMaxConfig().follow(m_leftWheel, true);

    m_rightWheel.configure(rightConfiguration, null, null);
  }

  /*
   * Subwoofer Speed: 40% straight on, side
   * Podium Speed: 70%
   * 193in Speed:
   */

  public void rampUp() {
    m_leftWheel.set(0.5);
  }

  public void rampDown() {
    // m_leftWheel.set(0.3);
    m_leftWheel.set(0);
  }

  public boolean isNoteSeen() {
    return lineBreak.isPressed();
  }

  public void ampShot() {
    m_leftWheel.set(0.4); // 0.2;
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void closeShot() {
    m_leftWheel.set(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void farShot() {
    m_leftWheel.set(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void sourceNomNom() {
    // m_leftWheel.set(-0.7);
    m_leftWheel.set(-0.3);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

}
