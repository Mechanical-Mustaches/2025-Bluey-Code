package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ConveyorMoveBack extends Command {
    ConveyorSubsystem conveyor;
    FlyWheelSubsystem flywheel;

    public ConveyorMoveBack(ConveyorSubsystem conveyor, FlyWheelSubsystem flywheel){
        this.conveyor = conveyor;
        this.flywheel = flywheel;
    }

    @Override
    public void initialize(){
        conveyor.conveyMoveBack();
        LimelightHelpers.setLEDMode_ForceOn("limelight-april");
    }

    @Override
    public boolean isFinished(){
        return !flywheel.isNoteSeen();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
    }
}
