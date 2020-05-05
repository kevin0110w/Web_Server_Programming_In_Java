/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gifbin;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author woohoo
 */
@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/gifs")
    public String home() {
        return "redirect:/gifs/" + 1L;
    }

    @GetMapping("/gifs/{id}")
    public String getGifId(Model model, @PathVariable Long id) {
        Gif g = gifRepository.getOne(id);
        Integer count = gifRepository.findAll().size();
        List<Gif> allImages = gifRepository.findAll();
        int counter = 0;
        for (int i = 0; i < allImages.size(); i++) {
            if (allImages.get(i).equals(g)) {
                counter = i;
                break;
            }
        }

        Gif previous = null;
        Gif next = null;
        if (counter > 0) {
            previous = allImages.get(counter - 1);
        }

        if (counter < allImages.size() - 1) {
            next = allImages.get(counter + 1);
        }
        model.addAttribute("current", g.getId());
        model.addAttribute("count", count);

        if (previous != null) {
            model.addAttribute("previous", previous.getId());
        }

        if (next != null) {
            model.addAttribute("next", next.getId());
        }

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return gifRepository.findById(id).get().getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("image/gif")) {
            Gif fo = new Gif();
            fo.setContent(file.getBytes());
            gifRepository.save(fo);
        }
        return "redirect:/gifs";
    }
}
