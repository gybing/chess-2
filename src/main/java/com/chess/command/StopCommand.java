package com.chess.command;

import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.chess.StartChessEngine;
import com.chess.config.ChessEngineConfig;
import com.chess.process.EngineProcess;

public class StopCommand implements Runnable {
	private static final long LIMITTIME = Integer.valueOf(ChessEngineConfig.limitTime);// 引擎最多可以考虑的时间，超过就要马上返回下法。
	private Logger log = Logger.getLogger(StopCommand.class);

	@Override
	public void run() {
		long currentTimeMillis = System.currentTimeMillis();
		if (StartChessEngine.requestTime != 0 && !StartChessEngine.hasReturn
				&& currentTimeMillis - StartChessEngine.requestTime > LIMITTIME) {
			try {
				OutputStream outputStream = EngineProcess.getOutputStream();
				outputStream.write("stop\r\n".getBytes());
				outputStream.flush();
				log.debug("stop");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
