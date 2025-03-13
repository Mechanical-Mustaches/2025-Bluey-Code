// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.util.function.DoubleSupplier;

/**
 * An example command that uses an example subsystem.
 */
public class liveShot extends Command
{

  private final SwerveSubsystem  swerve;
  private final PivotSubsystem  pivot;
  private final FlyWheelSubsystem  flywheel;
  private final PIDController pidController = new PIDController(0.025, 0, 0);
  private final DoubleSupplier   vX;
  private final DoubleSupplier   vY;
  private double speakerXloc = 0;
  private double speakerYloc = 5.5;
  private boolean isRedAlliance = (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == DriverStation.Alliance.Red : false);


  /**
   * Creates a new ExampleCommand.
   *
   * @param swerve The subsystem used by this command.
   */
  public liveShot(SwerveSubsystem swerve, PivotSubsystem pivot, FlyWheelSubsystem flywheel, DoubleSupplier vX, DoubleSupplier vY)
  {
    this.swerve = swerve;
    this.pivot = pivot;
    this.flywheel = flywheel;
    this.vX = vX;
    this.vY = vY;    
    pidController.enableContinuousInput(-180, 180);

    addRequirements(swerve, pivot, flywheel);
  }

  @Override
  public void initialize()
  { 
    if(isRedAlliance){
      speakerXloc = 16.54;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    //get joystick values for drivetrain
    double xVelocity   = Math.pow(vX.getAsDouble(), 3);
    double yVelocity   = Math.pow(vY.getAsDouble(), 3);

    if(isRedAlliance){
      yVelocity = -1*yVelocity;
      xVelocity = -1*xVelocity;
    }

    double deltaX = swerve.getPose().getX() - speakerXloc;
    double deltaY = swerve.getPose().getY() - speakerYloc;
    double hypo = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
    double degOffset =  Math.atan(deltaY/deltaX)*180/Math.PI;

    if(isRedAlliance){
      degOffset = degOffset+180;
    }

    double rotation = pidController.calculate(swerve.getHeading().getDegrees(), degOffset);

    swerve.drive(new Translation2d(xVelocity * swerve.maximumSpeed, yVelocity * swerve.maximumSpeed),
                rotation*5,
                true);
    SmartDashboard.putNumber("Speaker Distance: ", hypo);
    flywheel.farShot();
    pivot.dynamicShot(hypo);
  }
   
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    flywheel.rampDown();
    pivot.pivotBasePosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
