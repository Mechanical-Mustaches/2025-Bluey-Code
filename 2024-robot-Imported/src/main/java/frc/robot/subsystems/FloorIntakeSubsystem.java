package frc.robot.subsystems;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FloorIntakeSubsystem extends SubsystemBase {
  /** Creates a new FloorIntakeSubsystem. */
  private SparkMax m_floorIntake = new SparkMax(9, MotorType.kBrushless);
  private SparkMax m_floorIntake_helper = new SparkMax(25, MotorType.kBrushless);

  public FloorIntakeSubsystem() {
    var intakeConfiguration = new SparkMaxConfig().idleMode(IdleMode.kCoast);
    var helperConfiguration = new SparkMaxConfig().apply(intakeConfiguration).follow(m_floorIntake);

    m_floorIntake.configure(intakeConfiguration, null, null);
    m_floorIntake_helper.configure(helperConfiguration, null, null);
  }

  private void setSpeed(double percent) {
    m_floorIntake.set(percent);
    SmartDashboard.putNumber("floor intake percent", percent);
  }

  public void intakeForward() {
    setSpeed(1);

  }

  public void intakeStop() {
    setSpeed(0);
  }

  public void intakeReverse() {
    setSpeed(-0.8);
  }
}
