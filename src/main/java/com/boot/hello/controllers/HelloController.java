package com.boot.hello.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String greetings() {
		
		
		return "Hello there! How are you?.....   " + System.getenv("HOSTNAME");
		
	}

	@RequestMapping("/sec")
	public String readSecret() {
		   StringBuffer sb = new StringBuffer();
        try (InputStream input = new FileInputStream("/opt/app-root/myapp.sec")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
         
            sb.append(prop.getProperty("db.url")).append("--------").append(prop.getProperty("db.user")).append("------").append(prop.getProperty("db.password"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
	
	@RequestMapping("/config")
	public String readConfiguration() {
	StringBuffer sb = new StringBuffer();
	try{
	 Properties prop = new Properties();
	 String str = System.getenv("HELLO_MESSAGE");
	 prop.load(new StringReader(str));
  	 sb.append(prop.getProperty("MESSAGE"));
     } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
	
	@RequestMapping("/cm")
	public String readConfig(){
		   StringBuffer sb = new StringBuffer();
        
         
            sb.append(System.getenv("WELCOME_MESSAGE")).append("--------").append(System.getenv("NAME1")).append("------").append(System.getenv("KEY1"));
            
        return sb.toString();
    }
    @RequestMapping("/healthz")
    public ResponseEntity<?> checkHealth(){
		String message=null;
		HttpStatus status;
		if (healthy) {
			message = "ping /healthz --> pong [healthy]";
			status = HttpStatus.OK;
		}else {
			message = "ping /healthz => pong [unhealthy]";
			status = HttpStatus.SERVICE_UNAVAILABLE;
		}
		System.out.println(message);
		return new ResponseEntity<String>(message,status);
    }
	@RequestMapping("/ready")
	public ResponseEntity<?> checkReadiness(){
		String message=null;
		HttpStatus status;
		long now =(System.currentTimeMillis() / 1000) % 60;
		if (now > 30) {
			 message = "ping /ready => pong [ready]";
			status = HttpStatus.OK;
		}else {
			message = "ping /ready => pong [notready]\n Error! Service not ready for requests...\\n";
			status = HttpStatus.SERVICE_UNAVAILABLE;
		}
		System.out.println(message);
		return new ResponseEntity<String>(message,status);
    }
	@RequestMapping("/flip")
	public ResponseEntity<?> flip(@RequestParam("op") String flag){
		String message=null;
		 if (flag == "kill") {
			  message = "Received kill request. Changing app state to unhealthy...\n Switched app state to unhealthy...\\n";
			  healthy = false;
			}
			else if (flag == "awaken") {
			  message ="Received awaken request. Changing app state to healthy...\n Switched app state to healthy...\n";
			  healthy = true;
			}
		System.out.println(message);
		return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    boolean healthy=true;
	long started;
	public HelloController() {
		// TODO Auto-generated constructor stub
		started = System.currentTimeMillis();
	}
}
