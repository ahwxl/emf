package com.bplow.look.bass.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.caucho.hessian.io.HessianOutput;

/**
 * 远程方法调用的一些功能
 * 
 * @author new
 * 
 */

public class HessianService {

	// 将文件流组装到hessianOutPut里,远程上传文件使用
	private static void uploadNIO(File file, HessianOutput out)
			throws IOException {
		out.writeByteBufferStart();
		System.out.println("file = " + file.exists());
		try {
			FileChannel channel = new FileInputStream(file).getChannel();
			System.out.println("file Size: " + channel.size());
			final int size = 10485760;// 10485760=1024*1024*10
			ByteBuffer buf = ByteBuffer.allocateDirect(size);
			int numRead = 0;
			do {
				numRead = channel.read(buf);
				buf.flip();// limit=current position;position=0;
				int limit = buf.limit();
				byte[] tmpByteArray = new byte[limit];
				while (buf.hasRemaining()) {
					buf.get(tmpByteArray);
					out.writeByteBufferPart(tmpByteArray, 0, limit);
				}
				buf.clear();// position=0;limit=capacity;reset mark;
			} while (numRead > 0);
			out.writeByteBufferEnd(new byte[0], 0, 0);
		} catch (Error e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
