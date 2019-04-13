package com.practicaldime.chat;

import com.practicaldime.zesty.websock.AppWsMessage;

public class Msg extends AppWsMessage {

	public final int status;
	
	public Msg(String from, String to, String time, String error, int status) {
		super(from, to, time, error);
		this.status = status;
	}
}
