/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author woohoo
 */
@Controller
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/files")
    public String view(Model model) {
        model.addAttribute("files", fileRepository.findAll());
        return "files";
    }

    @PostMapping("/files")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        File f = new File();
        f.setName(file.getOriginalFilename());
        f.setContentType(file.getContentType());
        f.setContentLength(file.getSize());
        f.setContent(file.getBytes());
        fileRepository.save(f);
        
        return "redirect:/files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        System.out.println(id);
        File file = fileRepository.getOne(id);
        
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentLength(file.getContentLength());
        headers.add("Content-Disposition", "attachment; filename =" + file.getName());
        
        return new ResponseEntity<>(file.getContent(), headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable Long id) {
        fileRepository.delete(fileRepository.getOne(id));
        
        return "redirect:/files";
    }
    
}
