package com.boot.hello.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.StringReader;

import org.springframework.web.bind.annotation.RequestMapping;
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
}
