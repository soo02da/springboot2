package ne.kr.dev.springboot.service;

import lombok.RequiredArgsConstructor;
import ne.kr.dev.springboot.domain.posts.Posts;
import ne.kr.dev.springboot.domain.posts.PostsRepository;
import ne.kr.dev.springboot.web.dto.PostsResponseDto;
import ne.kr.dev.springboot.web.dto.PostsSaveRequestDto;
import ne.kr.dev.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(dto.getTitle(), dto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
