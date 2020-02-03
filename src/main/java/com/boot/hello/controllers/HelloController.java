package com.boot.hello.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	
	@RequestMapping("/cm")
	public String readConfig(){
		   StringBuffer sb = new StringBuffer();
        try (InputStream input = new FileInputStream("/opt/app-root/myapp.config")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
         
            sb.append(prop.getProperty("welcome.message")).append("--------").append(prop.getProperty("current.environment")).append("------");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
