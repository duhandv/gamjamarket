package project.duhan.gamjamarket.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.duhan.gamjamarket.common.DataResult;
import project.duhan.gamjamarket.common.auth.Login;
import project.duhan.gamjamarket.member.service.MemberQueryService;
import project.duhan.gamjamarket.member.service.dto.MemberQueryResponse;

@Tag(name = "Member")
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberQueryService memberQueryService;

    public MemberController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping
    public ResponseEntity<DataResult<MemberQueryResponse>> get(@Login Long memberId) {
        return ResponseEntity.ok(DataResult.of(memberQueryService.get(memberId)));
    }

}
