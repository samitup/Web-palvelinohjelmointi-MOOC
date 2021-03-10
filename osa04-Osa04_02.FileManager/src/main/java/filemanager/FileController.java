package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    FileRepository fileRepo;

    @GetMapping("/files")
    public String showFiles(Model model) {
        model.addAttribute("files", fileRepo.findAll());
        return "files";
    }
    @PostMapping("/files")
    public String postFile(@RequestParam("file") MultipartFile file) throws IOException{
        FileObject fo = new FileObject();
        fo.setContentType(file.getContentType());
        fo.setName(file.getOriginalFilename());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());
        fileRepo.save(fo);
        return "redirect:/files";
    }
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> showFile(@PathVariable Long id){
        FileObject fo = fileRepo.getOne(id);
        
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());
        return new ResponseEntity<byte[]>(fo.getContent(),headers, HttpStatus.CREATED);
    }
    @DeleteMapping("/files/{id}")
    public String deleteFile(@PathVariable Long id){
        FileObject fo = fileRepo.getOne(id);
        fileRepo.delete(fo);
        return "redirect:/files";
    }
}
