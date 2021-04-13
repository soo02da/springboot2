package ne.kr.dev.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // final 로 지정된 필드가 포함된 생성자를 생성합니다.
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
