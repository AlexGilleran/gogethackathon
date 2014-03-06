package com.alexgilleran.goget.thrift;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

@Component("ThriftServlet")
public class ThriftServlet extends TServlet implements HttpRequestHandler {
    private static final long serialVersionUID = 9082487503825628701L;

    private static final String POST = "POST";

    @Autowired
    public ThriftServlet(TProcessor processor, TProtocolFactory protocolFactory) {
	super(processor, protocolFactory);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	    IOException {
	response.addHeader("Access-Control-Allow-Origin", "*");
	Enumeration<String> allowHeaders = request.getHeaders("Access-Control-Request-Headers");
	while (allowHeaders.hasMoreElements()) {
	    response.addHeader("Access-Control-Allow-Headers", allowHeaders.nextElement());
	}
	response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	if (request.getMethod().equals(POST)) {
	    doPost(request, response);
	}
    }
}
