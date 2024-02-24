package project.duhan.gamjamarket.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.product.dommain.Product;
import project.duhan.gamjamarket.product.dommain.ProductRepository;
import project.duhan.gamjamarket.product.service.dto.ProductRegisterCommand;

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
        Product product = command.toEntity(member.getRegion());
        productRepository.save(product);
    }

}
