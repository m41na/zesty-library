package com.practicaldime.chat;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.practicaldime.library.service.LibraryEndpoint;
import com.practicaldime.zesty.app.AppServer;

public class App {
	
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		int port = 1337;
		String host = "localhost";
		
		Map<String, String> props = Maps.newHashMap();
		props.put("appctx", "/");
		props.put("assets", "www/chat");
		props.put("cors", "true");

		AppServer router = new AppServer(props).router();
		//router.websocket("/chat/*", ()-> new Chat("chat"))
		router.servlet("/graphql", null, LibraryEndpoint.create())
		.listen(port, host, (msg) -> {
			LOG.info(msg);
		});		
	}
}
