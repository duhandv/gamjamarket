package project.duhan.gamjamarket.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.product.domain.Product;
import project.duhan.gamjamarket.product.domain.ProductRepository;
import project.duhan.gamjamarket.product.service.dto.ProductQueryResponse;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
class ProductQueryServiceIGTest {

    @Autowired
    private ProductQueryService productQueryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setup() {
        productRepository.saveAll(List.of(
                Product.builder()
                    .region("동작구")
                    .memberId(1L)
                    .name("모니터")
                    .categoryId(1L)
                    .amount(Money.wons(5000))
                    .build(),
                Product.builder()
                    .region("동작구")
                    .name("키보드")
                    .categoryId(1L)
                    .amount(Money.wons(1500))
                    .memberId(2L)
                    .build(),
                Product.builder()
                    .region("동작구")
                    .name("키보드")
                    .categoryId(1L)
                    .amount(Money.wons(1500))
                    .memberId(3L)
                    .build(),
                Product.builder()
                    .region("영등포구")
                    .name("키보드")
                    .categoryId(1L)
                    .amount(Money.wons(1500))
                    .memberId(4L)
                    .build(),
                Product.builder()
                    .region("용산구")
                    .name("키보드")
                    .categoryId(1L)
                    .amount(Money.wons(1500))
                    .memberId(5L)
                    .build(),
                Product.builder()
                    .region("중구")
                    .name("키보드")
                    .categoryId(1L)
                    .amount(Money.wons(1500))
                    .memberId(6L)
                    .build()));
    }

    @Test
    void userOnlyCanSee_ownRegion() {
        // given
        Member member = createRegionVerifiedMember();
        memberRepository.save(member);

        // when
        List<ProductQueryResponse> actual = productQueryService.findAll(member.getId());

        // then
        then(actual.size()).isEqualTo(3);
    }

    private Member createRegionVerifiedMember() {
        Member member = Member.builder().build();
        member.verifyRegion(new Address("서울특별시", "동작구", "대방동"));
        return member;
    }

}