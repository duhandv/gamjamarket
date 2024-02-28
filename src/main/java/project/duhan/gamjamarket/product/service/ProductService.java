package project.duhan.gamjamarket.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.member.domain.RegionVerifyState;
import project.duhan.gamjamarket.product.dommain.Product;
import project.duhan.gamjamarket.product.dommain.ProductRepository;
import project.duhan.gamjamarket.product.service.dto.ProductRegisterCommand;
import project.duhan.gamjamarket.product.service.dto.ProductUpdateCommand;

@Service
@Transactional
public class ProductService {

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    public ProductService(MemberRepository memberRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public void register(ProductRegisterCommand command) {
        Member member = memberRepository.findById(command.memberId()).orElseThrow();
        if (member.getRegionVerifyState() != RegionVerifyState.VERIFIED) {
            throw new IllegalStateException("동네 인증을 해야합니다.");
        }
        Product product = command.toEntity(member.getRegion());
        productRepository.save(product);
    }

    public void update(ProductUpdateCommand command) {
        Member member = memberRepository.findById(command.memberId()).orElseThrow();
        if (member.getRegionVerifyState() != RegionVerifyState.VERIFIED) {
            throw new IllegalStateException("동네 인증을 해야합니다.");
        }
        Product product = productRepository.findById(command.productId()).orElseThrow();
        product.update(command.name(), command.amount(), member.getRegion(), command.categoryId());
    }

}
