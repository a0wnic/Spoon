/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * Add your docs here.
 */
public class TurretSubsystem extends SubsystemBase {
  private static final int deviceID1 = 5;
  private static final int deviceID2 = 6;
  private static final int deviceID3 = 7;
  private static final int deviceID4 = 8;
  WPI_TalonSRX m_turret;
  WPI_TalonSRX m_feeder;
  WPI_TalonFX m_shooterLeft;
  WPI_TalonFX m_shooterRight;
  
  public TurretSubsystem(){
    m_turret = new WPI_TalonSRX(deviceID2);
    m_feeder = new WPI_TalonSRX(deviceID1);//What are we going to do about the pigeon?
    m_shooterLeft = new WPI_TalonFX(deviceID3);
    m_shooterRight = new WPI_TalonFX(deviceID4);
    boolean _brake = true;
    m_shooterLeft.setNeutralMode(NeutralMode.Brake);
    m_shooterRight.setNeutralMode(NeutralMode.Brake);
  }

  public void setTurretSpeed(double speed){ //sets and regulates turret speed
    if (speed > 1) {
      speed = 1;
    }
    else if (speed < -1) {
      speed = -1;
    }
    m_turret.set(speed*0.25); 
  }

  public double encoderVal(){ //gets the value of the turret magnetic encoder
    double position = m_turret.getSelectedSensorPosition(0);
    System.out.println("encoder position " + position);
    return position;
  }

  
}