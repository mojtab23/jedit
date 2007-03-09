/*
 * WincryptCipherPlugin - A jEdit plugin as wincrypt cipher implementation for the CipherPlugin
 * :tabSize=4:indentSize=4:noTabs=true:
 *
 * Copyright (C) 2007 Bj�rn "Vampire" Kautler
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class wincrypt_WincryptCipher */

#ifndef _Included_wincrypt_WincryptCipher
#define _Included_wincrypt_WincryptCipher
#define _WIN32_WINNT 0x0400
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     wincrypt_WincryptCipher
 * Method:    encryptNative
 * Signature: ([B[C[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_wincrypt_WincryptCipher_encryptNative
  (JNIEnv *, jobject, jbyteArray, jcharArray, jbyteArray);

/*
 * Class:     wincrypt_WincryptCipher
 * Method:    decryptNative
 * Signature: ([B[C[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_wincrypt_WincryptCipher_decryptNative
  (JNIEnv *, jobject, jbyteArray, jcharArray, jbyteArray);

/*
 * Class:     wincrypt_WincryptCipher
 * Method:    isNativeAvailable
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_wincrypt_WincryptCipher_isNativeAvailable
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
