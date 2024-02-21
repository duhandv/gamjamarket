package project.duhan.gamjamarket.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.duhan.gamjamarket.common.DataResult;
import project.duhan.gamjamarket.common.auth.Login;
import project.duhan.gamjamarket.member.service.MemberQueryService;
import project.duhan.gamjamarket.member.service.dto.MemberQueryResponse;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberQueryService memberQueryService;

    public MemberController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @GetMapping
    public ResponseEntity<DataResult<MemberQueryResponse>> get(@Login Long memberId) {
        return ResponseEntity.ok(DataResult.of(memberQueryService.get(memberId)));
    }

}
