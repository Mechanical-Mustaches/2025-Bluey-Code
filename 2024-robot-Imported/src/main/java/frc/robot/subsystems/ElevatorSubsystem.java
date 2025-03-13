package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
   // Created an Elevator Subsystem
   private final SparkMax m_elevator = new SparkMax(13, MotorType.kBrushless);
   private final SparkMax m_elevator_helper = new SparkMax(15, MotorType.kBrushless);
   private final RelativeEncoder elevatorEncoder = m_elevator.getEncoder();
   private final SparkClosedLoopController m_PidController = m_elevator.getClosedLoopController();

   private double kP = 0.1;
   private double kI = 0;
   private double kD = 0;
   private double kIzone = 0;
   private double kFF = 0;
   private double kMaxOutput = 0.9;
   private double kMinOutput = -0.7;

   public ElevatorSubsystem() {
      elevatorEncoder.setPosition(0);

      var closedLoopConfiguration = new ClosedLoopConfig().pid(kP, kI, kD).iZone(kIzone).velocityFF(kFF)
            .outputRange(kMinOutput, kMaxOutput).feedbackSensor(FeedbackSensor.kPrimaryEncoder);

      var elevatorConfiguration = new SparkMaxConfig().apply(closedLoopConfiguration).inverted(true)
            .idleMode(IdleMode.kBrake);

      var elevatorHelperConfiguration = new SparkMaxConfig().idleMode(IdleMode.kBrake).follow(m_elevator);

      m_elevator.configure(elevatorConfiguration, null, null);
      m_elevator_helper.configure(elevatorHelperConfiguration, null, null);
   }

   /*
    * Five States: (Least amout of movement)
    * Base (start) Position
    * Amp Position = 15
    * Human Position = 3
    * Trap Position
    * Defense Position (Last resort use w/ limelight maybe?)
    * Podium Position
    * 
    * Max Pos --> -44
    */

   private void setElevatorHight(double heightInchs) {
      m_PidController.setReference(heightInchs, SparkMax.ControlType.kPosition);
   }

   public void basePosition() {
      setElevatorHight(0.0);
   }

   public void ampPosition() {
      setElevatorHight(16.0);
   }

   public void humanPosition() {
      setElevatorHight(2.0);
   }

   public void trapPosition() {
      setElevatorHight(55);
   }

   public void defensePosition() {
      setElevatorHight(1);
   }

   public void podiumPosition() {
      setElevatorHight(0);
   }

   public void subWooferPosition() {
      setElevatorHight(0.0);
   }

}
