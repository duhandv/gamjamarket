package project.duhan.gamjamarket.product.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.product.domain.Product;
import project.duhan.gamjamarket.product.domain.ProductRepository;
import project.duhan.gamjamarket.product.service.dto.ProductRegisterCommand;
import project.duhan.gamjamarket.product.service.dto.ProductUpdateCommand;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    void registerProduct() {
        Member member = createRegionVerifiedMember();
        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        productService.register(new ProductRegisterCommand(1L, "모니터", Money.wons(10000), 2L));

        verify(productRepository, times(1)).save(any());
    }

    @Test
    void throwException_whenNotRegionVerifiedUser_registerProduct() {
        Member member = Member.builder().id(1L).build();
        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        assertThrows(IllegalStateException.class,
                () -> productService.register(new ProductRegisterCommand(1L, "모니터", Money.wons(10000), 2L)));
    }

    @Test
    void updateProduct() {
        Member member = createRegionVerifiedMember();
        Product product = Product.builder()
            .id(2L)
            .amount(Money.wons(1000))
            .name("삼성모니터")
            .categoryId(1L)
            .memberId(member.getId())
            .build();
        given(memberRepository.findById(any())).willReturn(Optional.of(member));
        given(productRepository.findById(any())).willReturn(Optional.of(product));

        productService.update(new ProductUpdateCommand(product.getId(), member.getId(), "엘지모니터", Money.wons(1500), 3L));

        then(product.getName()).isEqualTo("엘지모니터");
        then(product.getAmount()).isEqualTo(Money.wons(1500));
    }

    @Test
    void throwException_whenNotRegionVerifiedUser_updateProduct() {
        Member member = Member.builder().id(1L).build();
        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        assertThrows(IllegalStateException.class, () -> productService
            .update(new ProductUpdateCommand(any(), member.getId(), "엘지모니터", Money.wons(1500), 3L)));
    }

    @Test
    void increaseLikeCount_whenLikeProduct() {
        Product product = Product.builder().id(1L).memberId(2L).build();
        given(productRepository.findById(any())).willReturn(Optional.of(product));

        productService.like(1L, 2L);

        then(product.getLikeCount()).isEqualTo(1);
    }

    @Test
    void decreaseLikeCount_whenUnlike() {
        Product product = Product.builder().id(1L).memberId(2L).build();
        given(productRepository.findById(any())).willReturn(Optional.of(product));

        productService.like(1L, 1L);
        productService.like(1L, 1L);
        productService.cancelLike(1L, 1L);

        then(product.getLikeCount()).isEqualTo(1);
    }

    private Member createRegionVerifiedMember() {
        Member member = Member.builder().id(1L).build();
        member.verifyRegion(new Address("서울특별시", "동작구", "대방동"));
        return member;
    }

}
