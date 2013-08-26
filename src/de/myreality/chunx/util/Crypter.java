/*
 * chunx is a Java 2D chunk engine to generate "infinite" worlds.
 * Copyright (C) 2013  Miguel Gonzalez
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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package de.myreality.chunx.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Provides crypting for objects
 * 
 * @author sherif <http://stackoverflow.com/users/446552/sherif>
 * @since 1.0
 * @version 1.0
 */
public class Crypter {

	private Cipher deCipher;
	private Cipher enCipher;
	private SecretKeySpec key;
	private IvParameterSpec ivSpec;


	private MessageDigest digest;
	private static final byte[] IV_BYTES = new byte[] { 0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00 };
	
	public Crypter(String key) {
		this(key.getBytes(), IV_BYTES);
	}

	public Crypter(byte[] keyBytes, byte[] ivBytes) {
		// wrap key data in Key/IV specs to pass to cipher

		ivSpec = new IvParameterSpec(ivBytes);
		// create the cipher with the algorithm you choose
		// see javadoc for Cipher class for more info, e.g.
		try {
			DESKeySpec dkey = new DESKeySpec(keyBytes);
			key = new SecretKeySpec(dkey.getKey(), "DES");
			deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String md5(String original) {
		
		if (digest == null) {
			 try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = original.getBytes("UTF-8");
			byte[] data  = digest.digest(bytesOfMessage);
			//convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < data.length; i++) {
	          sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return original;
		
	}

	public byte[] encrypt(Object obj) throws InvalidKeyException,
			InvalidAlgorithmParameterException, IOException,
			IllegalBlockSizeException, ShortBufferException,
			BadPaddingException {
		byte[] input = convertToByteArray(obj);
		enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		return enCipher.doFinal(input);

		// cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		// byte[] encypted = new byte[cipher.getOutputSize(input.length)];
		// int enc_len = cipher.update(input, 0, input.length, encypted, 0);
		// enc_len += cipher.doFinal(encypted, enc_len);
		// return encypted;

	}

	public Object decrypt(byte[] encrypted) throws InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, IOException, ClassNotFoundException {
		deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

		return convertFromByteArray(deCipher.doFinal(encrypted));

	}

	private Object convertFromByteArray(byte[] byteObject) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream bais;

		ObjectInputStream in;
		bais = new ByteArrayInputStream(byteObject);
		in = new ObjectInputStream(bais);
		Object o = in.readObject();
		in.close();
		return o;

	}

	private byte[] convertToByteArray(Object complexObject) throws IOException {
		ByteArrayOutputStream baos;

		ObjectOutputStream out;

		baos = new ByteArrayOutputStream();

		out = new ObjectOutputStream(baos);

		out.writeObject(complexObject);

		out.close();

		return baos.toByteArray();

	}

}