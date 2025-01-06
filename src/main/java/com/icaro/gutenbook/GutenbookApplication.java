package com.icaro.gutenbook;

import com.icaro.gutenbook.main.Principal;
import com.icaro.gutenbook.view.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GutenbookApplication implements CommandLineRunner {
    
    

	public static void main(String[] args) {
		SpringApplication.run(GutenbookApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
      
       
               
       
       Principal principal = new Principal();
		principal.muestraMenu();
    }

}
