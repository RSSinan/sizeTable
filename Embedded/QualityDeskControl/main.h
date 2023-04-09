//
// Created by turga on 17.03.2023.
//
#include <time.h>
#include <stdio.h>
#include <string.h>
#include <pico/stdio.h>
#include <pico/time.h>
#include <pico/types.h>
#include <hardware/clocks.h>
#include <hardware/gpio.h>
#include <hardware/uart.h>
#include <hardware/adc.h>

#ifndef QUALITYDESKCONTROL_MAIN_H
#define QUALITYDESKCONTROL_MAIN_H

#define LASER_1_PIN 8
#define LASER_2_PIN 9
#define LASER_3_PIN 10
#define LASER_4_PIN 11

#define RDM_RX_PIN  1
#define RDM_TX_PIN  0
#define RDM_UART    uart0

#define SENS_1_AIN_PIN  26
#define SENS_2_AIN_PIN  27

#define RDM_WAIT_TIME   1.0 // 1 Sec
#define RDM_READ_SIZE   14
#define CARD_SIZE       8
#define RDM_PCK_HEAD    0x02
#define RDM_PCK_END     0x03
#define RDM_DEF_PATTERN 0x53

#define DIST_READINGS   100


// Comminication Characters

#define LASER_1_ON       '1'
#define LASER_2_ON       '2'
#define LASER_3_ON       '3'
#define LASER_4_ON       '4'
#define LASER_1_OFF      '5'
#define LASER_2_OFF      '6'
#define LASER_3_OFF      '7'
#define LASER_4_OFF      '8'
#define GET_DISTANCES    '9'
#define GET_RFID_CARD    '0'
#define CLEAR_RFID_CARD  'c'

void setupGpio(void);

void setupAdc(void);

void setupRdmUart(void);

uint16_t getDist1AnalogValue(void);

uint16_t getDist2AnalogValue(void);

float getDistanceSens1VoltageValue(void);

float getDistanceSens2VoltageValue(void);

float calculateAnalogToVoltage(uint16_t analog_value);

float calculateVoltageToMills(float voltage);

bool waitRfidCard(double timeout);

uint8_t calculateChecksumCardId(void);

clock_t getTick(void);

void setLaser(uint gpio, uint val);

#endif //QUALITYDESKCONTROL_MAIN_H
