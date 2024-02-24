package project.duhan.gamjamarket.product.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.product.dommain.ProductRepository;
import project.duhan.gamjamarket.product.service.dto.ProductRegisterCommand;

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
        Member member = createMember();
        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        productService.register(new ProductRegisterCommand(1L, "모니터", Money.wons(10000), 2L));

        verify(productRepository, times(1)).save(any());
    }

    private Member createMember() {
        Member member = Member.builder().id(1L).build();
        member.verifyRegion(new Address("서울특별시", "동작구", "대방동"));
        return member;
    }

}
