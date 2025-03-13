// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.util.function.DoubleSupplier;


import swervelib.SwerveController;

/**
 * An example command that uses an example subsystem.
 */
public class noteTrack extends Command
{

  private final SwerveSubsystem  swerve;
  private final PIDController pidController = new PIDController(0.025, 0, 0);
  private final DoubleSupplier   vX;
  private final DoubleSupplier   vY;
  private final DoubleSupplier   omega;
  private final SwerveController controller;

  /**
   * Creates a new ExampleCommand.
   *
   * @param swerve The subsystem used by this command.
   */
  public noteTrack(SwerveSubsystem swerve, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier omega)
  {
    this.swerve = swerve;
    this.vX = vX;
    this.vY = vY;    
    this.omega = omega;
    this.controller = swerve.getSwerveController();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    SmartDashboard.putBoolean("seeNote", false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double xVelocity   = Math.pow(vX.getAsDouble(), 3);
    double yVelocity   = Math.pow(vY.getAsDouble(), 3);
    double angVelocity = Math.pow(omega.getAsDouble(), 3);
    
    var alliance = DriverStation.getAlliance();
    
    if(alliance.isPresent() ? alliance.get() == DriverStation.Alliance.Red : false){
      yVelocity = -1*yVelocity;
      xVelocity = -1*xVelocity;
    }

    
   
    if(LimelightHelpers.getTV("limelight-note")){
      SmartDashboard.putBoolean("seeNote", true);
      double rotation = pidController.calculate(LimelightHelpers.getTX("limelight-note"), 0.0);

      swerve.drive(new Translation2d(xVelocity * swerve.maximumSpeed, yVelocity * swerve.maximumSpeed),
                 rotation*3,
                 true);
    } 
    else{ 
      SmartDashboard.putBoolean("seeNote", false);
      swerve.drive(new Translation2d(xVelocity * swerve.maximumSpeed, yVelocity * swerve.maximumSpeed),
                 angVelocity * controller.config.maxAngularVelocity,
                 true);
    } 

  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
