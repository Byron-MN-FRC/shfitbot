// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4859.SlaveTest.subsystems;


import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc4859.SlaveTest.commands.DriveWithJoyStick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSolenoid shifter;
    private WPI_TalonSRX talonRightMaster;
    private WPI_TalonSRX talonRightFollower;
    private SpeedControllerGroup talonGroupRight;
    private WPI_TalonSRX talonLeftMaster;
    private WPI_TalonSRX talonLeftFollower;
    private SpeedControllerGroup talonGroupLeft;
    private DifferentialDrive tankDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    double limitedJoystick = 0;
    private double y = 0;
	private double twist = 0;

    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shifter = new DoubleSolenoid(10, 0, 1);
        addChild("Shifter",shifter);
        
        
        talonRightMaster = new WPI_TalonSRX(8);
        
        
        
        talonRightFollower = new WPI_TalonSRX(7);
        
        
        
        talonGroupRight = new SpeedControllerGroup(talonRightMaster, talonRightFollower  );
        addChild("talonGroupRight",talonGroupRight);
        
        
        talonLeftMaster = new WPI_TalonSRX(0);
        
        
        
        talonLeftFollower = new WPI_TalonSRX(1);
        
        
        
        talonGroupLeft = new SpeedControllerGroup(talonLeftMaster, talonLeftFollower  );
        addChild("talonGroupLeft",talonGroupLeft);
        
        
        tankDrive = new DifferentialDrive(talonGroupRight, talonRightMaster);
        addChild("tankDrive",tankDrive);
        tankDrive.setSafetyEnabled(true);
        tankDrive.setExpiration(0.1);
        tankDrive.setMaxOutput(1.0);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        motorConfig();
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoyStick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private void motorConfig(){
        talonRightFollower.follow(talonRightMaster);
        talonRightMaster.setInverted(false);
        talonRightFollower.setInverted(InvertType.FollowMaster);
    }

    public void DriveForward(){
        talonRightMaster.set(.5);
    }

    public void stop(){
        talonRightMaster.set(0);
    }
    public void ShiftUp(){
        shifter.set(Value.kForward);
    }
    public void ShiftDown(){
        shifter.set(Value.kReverse);
    }
    public void driveWithJoystick(Joystick joystickP0) {
		y = -joystickP0.getY();
        twist = joystickP0.getTwist();
        double change = y - limitedJoystick; 
        if (change > 0.01) {change = 0.01;}
        else if (change < -0.01) {change = -0.01;}
        limitedJoystick += change;
        tankDrive.arcadeDrive(limitedJoystick, twist);
    }

}



