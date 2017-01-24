package meghana.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import meghana.dao.DpDaoImpl;
import meghana.model.DisplayPicture;
import meghana.model.RegisterUser;

@Controller
public class DpController {
	
	@Autowired
	private DpDaoImpl dpdaoimpl;
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public String initialFileUpload( HttpSession session,@RequestParam CommonsMultipartFile fileUpload) throws Exception {
         RegisterUser pals=(RegisterUser)session.getAttribute("pal");
         if(pals==null)
        	 throw new RuntimeException("Not logged in");
         
         if (fileUpload != null ) {
             CommonsMultipartFile aFile = fileUpload;
             
             DisplayPicture getUploadFile=dpdaoimpl.getFile(pals.getUsername());

             
             if(getUploadFile!=null)
             {
            	 getUploadFile.setDpname(aFile.getOriginalFilename());
                 getUploadFile.setDp(aFile.getBytes()); 
                 getUploadFile.setUsername(pals.getUsername());
                 dpdaoimpl.update(getUploadFile);
                 byte[] imagefiles=getUploadFile.getDp();
                 try{
		         		File file=new File("C:/Users/user/workspace/PalsConnectFE/WebContent/images/images/"+pals.getUsername());
		         		FileOutputStream fos = new FileOutputStream(file);
		         		fos.write(imagefiles);
		         		fos.close();
		         		}catch(Exception e){
		         		e.printStackTrace();
		         		}
             }
             
             
             else
             {
             DisplayPicture uploadFile = new DisplayPicture();        
                uploadFile.setDpname(aFile.getOriginalFilename());
                uploadFile.setDp(aFile.getBytes()); 
                uploadFile.setUsername(pals.getUsername());
                dpdaoimpl.save(uploadFile);
	             byte[] imagefiles=uploadFile.getDp();  
	             try{
		         		File file=new File("C:/Users/user/workspace/PalsConnectFE/WebContent/images/images/"+pals.getUsername());
		         		FileOutputStream fos = new FileOutputStream(file);
		         		fos.write(imagefiles);
		         		fos.close();
		         		}catch(Exception e){
		         		e.printStackTrace();
		         		}

             }    
		               
		         	
                    session.removeAttribute("pal");
            		session.invalidate();
         }
 
         return "redirect:http://localhost:8080/PalsConnectFE/#/welcome";
    }	
	

	
}
	


