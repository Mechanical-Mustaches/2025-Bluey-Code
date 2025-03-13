package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class HumanPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final FlyWheelSubsystem flywheel;
    private final ConveyorSubsystem conveyor;
    private boolean hasNote;
    private boolean safteyCheck;

    public HumanPosition(PivotSubsystem pivot, ElevatorSubsystem elevator,
     FlyWheelSubsystem flywheel, ConveyorSubsystem conveyor){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flywheel;
        this.conveyor = conveyor;
    }

    @Override 
    public void initialize(){
        pivot.pivotHumanPosition();
        elevator.humanPosition();
        hasNote = false;
        safteyCheck = false;

    }

    @Override
    public void execute(){
        if(!flywheel.isNoteSeen() && hasNote == false){
            flywheel.sourceNomNom();
            conveyor.conveyFromSource();  
            System.out.println("no note");
        } 
        else if(flywheel.isNoteSeen() && hasNote == false){
            flywheel.rampDown();
            conveyor.stopConvey();
            hasNote = true;
            System.out.println("note!");
        }

        if(hasNote){
            if(flywheel.isNoteSeen()) {
                conveyor.conveyMoveBack();
                System.out.println("Movin ada da way");
            } else {
                conveyor.stopConvey();
                System.out.println("done baby!");
                safteyCheck= true;
            }
            
        }

    }

    @Override
    public boolean isFinished(){
        return safteyCheck;
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
        flywheel.rampDown();
    }

}
