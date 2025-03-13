package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class AmpPosition extends Command{
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final FlyWheelSubsystem flywheel;
   

    public AmpPosition(PivotSubsystem pivot, ElevatorSubsystem elevator, FlyWheelSubsystem flywheel){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flywheel;

    }

    @Override
    public void initialize(){
       pivot.pivotAmpPosition();
       flywheel.ampShot();
       elevator.ampPosition();
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean i){
        pivot.pivotBasePosition();
        flywheel.rampDown();
    }
}
