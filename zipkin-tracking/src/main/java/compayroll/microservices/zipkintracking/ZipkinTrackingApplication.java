package compayroll.microservices.zipkintracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinTrackingApplication.class, args);
	}

}
