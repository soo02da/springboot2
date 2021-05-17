package ne.kr.dev.springboot.web;

import lombok.RequiredArgsConstructor;
import ne.kr.dev.springboot.config.auth.LoginUser;
import ne.kr.dev.springboot.config.auth.dto.SessionUser;
import ne.kr.dev.springboot.service.PostsService;
import ne.kr.dev.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /**
     *
     * @param model
     * @param user WebMvcConfigurer 를 구현한 WebConfig 의 addArgumentResolvers
     *             에서 어노테이션을 등록함으로서 컨트롤러에서 어노테이션으로 SessionUser 를 가져올 수 있도록 구현하였습니다
     * @return
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
