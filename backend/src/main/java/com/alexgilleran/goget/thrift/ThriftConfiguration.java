package com.alexgilleran.goget.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexgilleran.goget.thrift.PodService.Iface;

@Configuration
public class ThriftConfiguration {
    @Autowired
    private PodServiceHandler handler;

    @Bean
    public TProcessor createProcessor() {
	return new PodService.Processor<Iface>(handler);
    }

    @Bean
    public TProtocolFactory createProtocolFactory() {
	return new TJSONProtocol.Factory();
    }
}
