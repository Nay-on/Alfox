/* Copyright 2009 Gary Briggs

This file is part of obdgpslogger.

obdgpslogger is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.

obdgpslogger is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with obdgpslogger.  If not, see <http://www.gnu.org/licenses/>.
*/

/** \file
  \brief Functions to convert from OBD output to actual values
  Functions declared in this file are named by their OBD Service command
   which is two hex digits. It's easier than coming up with short unique
   names myself. Please refer to obdservicecommands.h to see what's what.
*/

#ifndef __OBDCONVERTFUNCTIONS_H
#define __OBDCONVERTFUNCTIONS_H

#ifdef __cplusplus
extern "C" {
#endif //  __cplusplus

// http://en.wikipedia.org/wiki/Table_of_OBD-II_Codes

/// All obd conversion functions adhere to this
typedef float (*OBDConvFunc)(unsigned int A, unsigned int B,
	unsigned int C, unsigned int D);

/// All obd reverse conversion functions adhere to this
/// return value is number of values [A,B,C,D] filled.
typedef int (*OBDConvRevFunc)(float val, unsigned int *A, unsigned int *B,
	unsigned int *C, unsigned int *D);

float obdConvert_04    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_05    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_06_09 (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0A    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0B    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0C    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0D    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0E    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_0F    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_10    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_11    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_14_1B (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_1F    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_21    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_22    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_23    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_24_2B (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_2C    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_2D    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_2E    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_2F    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_30    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_31    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_32    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_33    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_34_3B (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_3C_3F (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_42    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_43    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_44    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_45    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_46    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_47_4B (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_4C    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_4D    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_4E    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);
float obdConvert_52    (unsigned int A, unsigned int B, unsigned int C, unsigned int D);

int obdRevConvert_04    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_05    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_06_09 (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0A    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0B    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0C    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0D    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0E    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_0F    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_10    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_11    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_14_1B (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_1F    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_21    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_22    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_23    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_24_2B (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_2C    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_2D    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_2E    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_2F    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_30    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_31    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_32    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_33    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_34_3B (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_3C_3F (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_42    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_43    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_44    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_45    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_46    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_47_4B (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_4C    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_4D    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_4E    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);
int obdRevConvert_52    (float val, unsigned int *A, unsigned int *B, unsigned int *C, unsigned int *D);

#ifdef __cplusplus
}
#endif //  __cplusplus


#endif // __OBDCONVERTFUNCTIONS_H

