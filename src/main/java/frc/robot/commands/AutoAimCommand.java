/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.*;
import frc.robot.RobotContainer;

public class AutoAimCommand extends CommandBase {
  Limelight turret_Limelight;
  TurretSubsystem turret_subsystem;
  double adjust;
  double mfd;
  boolean buttonPressed;
  //boolean button;

  public AutoAimCommand(double modifier) {
    // public AutoAimCommand(Limelight subsystem, TurretSubsystem subsystem2, double modifier)
    System.out.println("autoaim constr");
    // addRequirements(subsystem);
    // addRequirements(subsystem2);
    // limelight_subsystem = subsystem;
    // turret_subsystem = subsystem2;
    turret_Limelight = new Limelight("limelight-turret");
    turret_subsystem = RobotContainer.m_turret_subsystem;

    mfd = modifier;
    //this.button = button;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    System.out.println("autoaim init");
    double adjust = turret_Limelight.steeringAdjust();//if there is a target, get the distance from it
    RobotContainer.m_turret_subsystem.setTurretSpeed(-adjust, 0.25);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    System.out.println("autoaim exec ");
    adjust = turret_Limelight.steeringAdjust();
    System.out.println("stadj returned" + adjust);
    RobotContainer.m_turret_subsystem.setTurretSpeed(-adjust, 0.25);
  }


  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    System.out.println("autoaim end");
    RobotContainer.m_turret_subsystem.setTurretSpeed(0.0, 0.0);
  }

  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    System.out.println("autoaim is finished");
    buttonPressed = (RobotContainer.m_oi.circle()==false);
    return (buttonPressed);
  }

}
