/*
 * Primary and Secondary School License - for use in teaching and meeting
 * course requirements at primary and secondary schools only.  Not for
 * government, commercial, university, or other organizational use.
 *
 * File: LedRGB_data.c
 *
 * Code generated for Simulink model 'LedRGB'.
 *
 * Model version                  : 1.9
 * Simulink Coder version         : 8.12 (R2017a) 16-Feb-2017
 * C/C++ source code generated on : Sat Apr  7 17:41:21 2018
 *
 * Target selection: ert.tlc
 * Embedded hardware selection: Atmel->AVR
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */

#include "LedRGB.h"
#include "LedRGB_private.h"

/* Block parameters (auto storage) */
P_LedRGB_T LedRGB_P = {
  0.0,                                 /* Mask Parameter: blue_gain
                                        * Referenced by: '<S4>/Slider Gain'
                                        */
  0.0,                                 /* Mask Parameter: red_gain
                                        * Referenced by: '<S6>/Slider Gain'
                                        */
  0.53266,                             /* Mask Parameter: green_gain
                                        * Referenced by: '<S5>/Slider Gain'
                                        */
  11U,                                 /* Mask Parameter: PWM_pinNumber
                                        * Referenced by: '<S1>/PWM'
                                        */
  9U,                                  /* Mask Parameter: PWM_pinNumber_m
                                        * Referenced by: '<S2>/PWM'
                                        */
  10U,                                 /* Mask Parameter: PWM_pinNumber_mb
                                        * Referenced by: '<S3>/PWM'
                                        */
  1.0,                                 /* Expression: 1
                                        * Referenced by: '<Root>/cst_blue'
                                        */
  255.0,                               /* Expression: 255
                                        * Referenced by: '<Root>/Gain2'
                                        */
  1.0,                                 /* Expression: 1
                                        * Referenced by: '<Root>/cst_red'
                                        */
  255.0,                               /* Expression: 255
                                        * Referenced by: '<Root>/Gain'
                                        */
  1.0,                                 /* Expression: 1
                                        * Referenced by: '<Root>/cst_green '
                                        */
  255.0                                /* Expression: 255
                                        * Referenced by: '<Root>/Gain1'
                                        */
};

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
