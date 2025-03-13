package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.pivotConstants;

public class PivotSubsystem extends SubsystemBase {

    private final SparkMax m_pivot = new SparkMax(14, MotorType.kBrushless);
    private final SparkClosedLoopController m_PidController = m_pivot.getClosedLoopController();

    private double kP = 2.5; // 4
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 0.6; // 0.4
    private double kMinOutput = -0.25; // 0.15

    public PivotSubsystem() {

        var closedLoopConfig = new ClosedLoopConfig()
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .pid(kP, kI, kD)
                .velocityFF(kFF)
                .iZone(kIz)
                .outputRange(kMinOutput, kMaxOutput);

        SmartDashboard.setDefaultNumber("pos", 0);

        var pivotConfiguration = new SparkMaxConfig()
                .apply(closedLoopConfig)
                .idleMode(IdleMode.kBrake);

        m_pivot.configure(pivotConfiguration, null, null);
    }

    /*
     * Pivot States:
     * Base Position
     * Amp Position
     * Human Position
     * Climb Position
     * Trap Position
     * Defense Position (Last resort use w/ limelight maybe?)
     * Podium Position
     */

    private void setArmPosition(float deg) {
        m_PidController.setReference((deg) / 360, SparkMax.ControlType.kPosition);
        SmartDashboard.putNumber("pivot setpoint", (deg));
    }

    public void pivotBasePosition() {
        setArmPosition(pivotConstants.basePosition);
    }

    public void pivotAmpPosition() {
        setArmPosition(254f);
    }

    public void pivotHumanPosition() {
        setArmPosition(164f);
    }

    public void pivotSkipPosition() {
        setArmPosition(184f);
    }

    public void pivotClimbPosition() {
        setArmPosition(209f);
    }

    public void pivotTrapPosition() {
        setArmPosition(322);
    }

    public void pivotPodiumPosition() {
        setArmPosition(192f);
    }

    public void pivotSubWooferPosition() {
        setArmPosition(164f);
    }

    public void pivotDefencePosition() {
        setArmPosition(156f);
    }

    public void pivotTestPosition() {
        double output = SmartDashboard.getNumber("pos", pivotConstants.basePosition);

        output = Math.max(output, pivotConstants.basePosition);
        double deg = Math.min(output, 210);
        setArmPosition((float) deg);
    }

    public void dynamicShot(double Distance) {

        // equation from google sheets for our line of best fit
        double output = Distance * 10 + 150;

        // give output an upper and lower bound to not break the pivot
        output = Math.max(output, pivotConstants.basePosition);
        double deg = Math.min(output, 210);

        setArmPosition((float) deg);
    }
}
